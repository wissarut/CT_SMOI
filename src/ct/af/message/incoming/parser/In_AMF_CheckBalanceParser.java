package ct.af.message.incoming.parser;

import java.util.List;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.AVP;
import ct.af.message.incoming.parameter.AccountInfo_AMF_CheckBalance;
import ct.af.message.incoming.parameter.Counters_AMF_CheckBalance;
import ct.af.message.incoming.parameter.Param_AMF_CheckBalance;
import ct.af.message.incoming.parameter.VAL;
import ct.af.utils.StringUtils;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_AMF_CheckBalanceParser {
	
	private static In_AMF_CheckBalanceParser parser = null;
	
	protected In_AMF_CheckBalanceParser() {
	  
	}
	public static In_AMF_CheckBalanceParser getInstance() {
		if(parser == null) {
			parser = new In_AMF_CheckBalanceParser();
		}
		return parser;
	}
	
	public Param_AMF_CheckBalance doParser(EquinoxRawData eqxRawData) {
		
		Param_AMF_CheckBalance checkBalanceParameter = new Param_AMF_CheckBalance();
		
		try {
			Serializer serializer = new Persister();
			checkBalanceParameter = serializer.read(Param_AMF_CheckBalance.class, "<xml>" + eqxRawData.getRawDataMessage() + "</xml>");
			
			checkBalanceParameter.setRecive(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error checkBalance parser");
			return checkBalanceParameter;
		}
		
	
		List<AVP> listAVP = checkBalanceParameter.getAvp();
		
		for (AVP avp: listAVP) {
			
			if (avp.getType().equals("methodVersion")) {
				
				checkBalanceParameter.setMethodVersion(avp.getVal().get(0).getValue());
				
			}
			
			if (avp.getType().equals("accountInfo")) {
	
				for (VAL val: avp.getVal()) {
					
					AccountInfo_AMF_CheckBalance accountInfo = new AccountInfo_AMF_CheckBalance();
					String[] message = val.getValue().split(",",-1);
					
					if(message.length == 10) {
						
						accountInfo.setState(message[0]);
						accountInfo.setActiveStopTime(message[1]);
						accountInfo.setSuspendStopTime(message[2]);
						accountInfo.setDisableStopTime(message[3]);
						accountInfo.setTerminateStopTime(message[4]);
						accountInfo.setMaxValidity(message[5]);
						accountInfo.setFraudFlag(message[6]);
						accountInfo.setPinFraudFlag(message[7]);
						accountInfo.setDpMode(message[8]);
						accountInfo.setUserRequestSuspend(message[9]);
						
						checkBalanceParameter.getAccountInfoAMFCheckBalance().add(accountInfo);
						
					}
					
				}
				
			}
			
			if (avp.getType().equals("counters")) {
	
				for (VAL val: avp.getVal()) {
					
					Counters_AMF_CheckBalance counters = new Counters_AMF_CheckBalance();
					
					if(StringUtils.isNotBlank(val.getValue())) {
						String[] message = val.getValue().split(",",-1);
						
						if(message.length == 9) {
							
							counters.setCounterID(message[0]);
							counters.setCurrentBalance(message[1]);
							counters.setActivationDate(message[2]);
							counters.setExpiryTime(message[3]);
							counters.setUpperLimit(message[4]);
							counters.setLowerLimit(message[5]);
							counters.setServiceId_resourceId_reourceName(message[6]);
							counters.setProductNo(message[7]);
							counters.setPromoNameNotifElem(message[8]);
	
							checkBalanceParameter.getCountersAMFCheckBalance().add(counters);
							
						} else {
							
							counters.setCounterID(message[0]);
							counters.setCurrentBalance(message[1]);
							counters.setActivationDate(message[2]);
							counters.setExpiryTime(message[3]);
							counters.setUpperLimit(message[4]);
							counters.setLowerLimit(message[5]);
							
							checkBalanceParameter.getCountersAMFCheckBalance().add(counters);
							
						}
					}
					
				}
				
			}
					
		}

		if(!StringUtils.isBlank(checkBalanceParameter.getMethodVersion())
				&& checkBalanceParameter.getAccountInfoAMFCheckBalance().size() > 0
				&& checkBalanceParameter.getCountersAMFCheckBalance().size() > 0
				&& StringUtils.isNotBlank(checkBalanceParameter.getCountersAMFCheckBalance().get(0).getCounterID())
				&& checkBalanceParameter.getCountersAMFCheckBalance().get(0).getCounterID().equals("200010")
				&& StringUtils.isNotBlank(checkBalanceParameter.getCountersAMFCheckBalance().get(0).getCurrentBalance())) {
			
			checkBalanceParameter.setRespResultCode(EResultCode.RE20000);
			checkBalanceParameter.setNotMissing(true);
			checkBalanceParameter.setValid(true);
			return checkBalanceParameter;
			
		} else {
			
			if(!StringUtils.isBlank(checkBalanceParameter.getMethodVersion())) {
				AppLog.e("checkBalanceParameter.getMethodVersion() is null");
			}
			if(checkBalanceParameter.getAccountInfoAMFCheckBalance().size() == 0 ) {
				AppLog.e("checkBalanceParameter.getAccountInfoAMFCheckBalance() is null");
			}
			if(checkBalanceParameter.getCountersAMFCheckBalance().size() == 0 ) {
				AppLog.e("checkBalanceParameter.getCountersAMFCheckBalance() is null");
			}
			if(checkBalanceParameter.getCountersAMFCheckBalance().size() != 0 ) {
				if(StringUtils.isBlank(checkBalanceParameter.getCountersAMFCheckBalance().get(0).getCounterID())) {
					AppLog.e("checkBalanceParameter.getCountersAMFCheckBalance().getCounterID is null");
				}
				if(!checkBalanceParameter.getCountersAMFCheckBalance().get(0).getCounterID().equals("200010")) {
					AppLog.e("checkBalanceParameter.getCountersAMFCheckBalance().getCounterID is not 200010");
				}
				if(StringUtils.isBlank(checkBalanceParameter.getCountersAMFCheckBalance().get(0).getCurrentBalance())) {
					AppLog.e("checkBalanceParameter.getCountersAMFCheckBalance().getCurrentBalance is null");
				}
			}
			
			checkBalanceParameter.setRespResultCode(EResultCode.RE40301);
			return checkBalanceParameter;
		}
		
	}
	
}
