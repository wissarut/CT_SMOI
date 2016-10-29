package ct.af.message.incoming.parser;

import java.util.List;
import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.AVP;
import ct.af.message.incoming.parameter.AccountInfo_AMF_ObtainNoneBalance;
import ct.af.message.incoming.parameter.Counters_AMF_ObtainNoneBalance;
import ct.af.message.incoming.parameter.Param_AMF_ObtainCounter;
import ct.af.message.incoming.parameter.VAL;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_AMF_ObtainCounterParser {
	
	private static In_AMF_ObtainCounterParser parser = null;
	
	protected  In_AMF_ObtainCounterParser() {
	  
	}
	public static In_AMF_ObtainCounterParser getInstance() {
		if(parser == null) {
			parser = new In_AMF_ObtainCounterParser();
		}
		return parser;
	}
	
	public Param_AMF_ObtainCounter doParser(EquinoxRawData eqxRawData) {
		
		Param_AMF_ObtainCounter obtainNonBalanceCounterParser = new Param_AMF_ObtainCounter();
		
		try {
			Serializer serializer = new Persister();
			obtainNonBalanceCounterParser = serializer.read(Param_AMF_ObtainCounter.class, "<xml>" + eqxRawData.getRawDataMessage() + "</xml>");
			
			obtainNonBalanceCounterParser.setRecive(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error obtainNoneBalanceCounter parser");
			return obtainNonBalanceCounterParser;
		}
		
		List<AVP> listAVP = obtainNonBalanceCounterParser.getAvp();
	
		for (AVP avp: listAVP) {
			
			if (avp.getType().equals("methodVersion")) {

				obtainNonBalanceCounterParser.setMethodVersion(avp.getVal().get(0).getValue());
		
			}
			
			if (avp.getType().equals("accountInfo")) {
		
				for (VAL val: avp.getVal()) {
					
					AccountInfo_AMF_ObtainNoneBalance accountInfo = new AccountInfo_AMF_ObtainNoneBalance();
					String[] message = val.getValue().split(",", -1);
					
					if(message.length == 13) {
						
						accountInfo.setSubscriptionState(message[0]);
						accountInfo.setRegistrationDate(message[1]);
						accountInfo.setValidityDate(message[2]);
						accountInfo.setClassOfService(message[3]);
						accountInfo.setPaymentType(message[4]);
						accountInfo.setSpName(message[5]);
						accountInfo.setBrandId(message[6]);
						
						obtainNonBalanceCounterParser.getAccountInfoAMFObtainNoneBalance().add(accountInfo);
						
					}
					
				}
		
			}
			
			if (avp.getType().equals("counters")) {
				
				for (VAL val: avp.getVal()) {
					
					Counters_AMF_ObtainNoneBalance counters = new Counters_AMF_ObtainNoneBalance();
					String[] message = val.getValue().split("," ,-1);
					
					if(message.length == 42) {
						
						counters.setID(message[0]);
						counters.setValue(message[1]);
						counters.setReserve(message[2]);
						counters.setConsumptionDirection(message[3]);
						counters.setLowerLimit(message[4]);
						counters.setUpperLimit(message[5]);
						counters.setCounterThreshold(message[6]);
						counters.setLowerThreshold2(message[7]);
						counters.setUpperThreshold1(message[8]);
						counters.setUpperThreshold2(message[9]);
						counters.setExpiryTime(message[10]);
						counters.setExpiryThreshold1(message[11]);
						counters.setExpiryThreshold2(message[12]);
						counters.setRegistrationTime(message[13]);
						counters.setFirstModifiedTime(message[14]);
						counters.setLastModifiedTime(message[15]);
						counters.setPackPriority(message[16]);
						counters.setOrderTime(message[17]);
						counters.setSponsorID(message[18]);
						counters.setSubscriptionClass1(message[19]);
						counters.setSubscriptionClass2(message[20]);
						counters.setCounterPriority(message[21]);
						counters.setWhitelistSubTriggerType(message[22]);
						counters.setBlacklistSubTriggerType(message[23]);
						counters.setTbcf(message[24]);
						counters.setType(message[25]);
						counters.setWhitelistTime(message[26]);
						counters.setBlockID(message[27]);
						counters.setNotificationPolicyControl(message[28]);
						counters.setFriendID(message[29]);
						counters.setInterLockCode(message[30]);
						counters.setRechargeOnDepletion(message[31]);
						counters.setRewardInfo(message[32]);
						counters.setPackageName(message[33]);
						
						obtainNonBalanceCounterParser.getCountersAMFObtainNoneBalance().add(counters);
						
					}
					
				}
			}
		}
		
		if(!StringUtils.isBlank(obtainNonBalanceCounterParser.getMethodVersion())
				&& obtainNonBalanceCounterParser.getAccountInfoAMFObtainNoneBalance().size() > 0
				&& obtainNonBalanceCounterParser.getCountersAMFObtainNoneBalance().size() > 0) {
			
			obtainNonBalanceCounterParser.setRespResultCode(EResultCode.RE20000);
			obtainNonBalanceCounterParser.setNotMissing(true);
			obtainNonBalanceCounterParser.setValid(true);
			return obtainNonBalanceCounterParser;
			
		} else {
			
			obtainNonBalanceCounterParser.setRespResultCode(EResultCode.RE40301);
			return obtainNonBalanceCounterParser;
		}
		
	}
	
}
