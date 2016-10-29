package ct.af.message.incoming.parser;

import java.util.List;
import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.AVP;
import ct.af.message.incoming.parameter.AccountInfo_AMF_ModifyCounters;
import ct.af.message.incoming.parameter.Counters_AMF_ModifyCounters;
import ct.af.message.incoming.parameter.Param_AMF_ModifyCounters;
import ct.af.message.incoming.parameter.VAL;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_AMF_ModifyCountersParser {
	
	private static In_AMF_ModifyCountersParser parser = null;
	
	protected  In_AMF_ModifyCountersParser() {
	  
	}
	public static In_AMF_ModifyCountersParser getInstance() {
		if(parser == null) {
			parser = new In_AMF_ModifyCountersParser();
		}
		return parser;
	}
	
	public Param_AMF_ModifyCounters doParser(EquinoxRawData eqxRawData) {
		
		Param_AMF_ModifyCounters modifyCountersParameter = new Param_AMF_ModifyCounters();
		
		try {
			Serializer serializer = new Persister();
			modifyCountersParameter = serializer.read(Param_AMF_ModifyCounters.class, "<xml>" + eqxRawData.getRawDataMessage() + "</xml>");
			
			modifyCountersParameter.setRecive(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error modifyCounters parser");
			return modifyCountersParameter;
		}
		
		List<AVP> listAVP = modifyCountersParameter.getAvp();
		
		for (AVP avp: listAVP) {
			
			if (avp.getType().equals("methodVersion")) {
		
				modifyCountersParameter.setMethodVersion(avp.getVal().get(0).getValue());
				
			}
			
			if (avp.getType().equals("counters")) {
		
				for (VAL val: avp.getVal()) {
					
					Counters_AMF_ModifyCounters counters = new Counters_AMF_ModifyCounters();
					String[] message = val.getValue().split(",", -1);
				
					if(message.length == 8) {
						
						counters.setID(message[0]);
						counters.setReserve(message[1]);
						counters.setOperationstate(message[2]);
						counters.setNewvalue(message[3]);
						counters.setNewexpirytime(message[4]);
						counters.setAbmftid(message[5]);
						counters.setValueapplied(message[6]);
						counters.setValidityapplied(message[7]);
						
						modifyCountersParameter.getCountersAMFModifyCounters().add(counters);
						
					}
					
				}

			}
		
			if (avp.getType().equals("accountInfo")) {
				
				for (VAL val: avp.getVal()) {
					
					AccountInfo_AMF_ModifyCounters accountInfo = new AccountInfo_AMF_ModifyCounters();
					String[] message = val.getValue().split("," ,-1);
					
					if(message.length == 5) {
						
						accountInfo.setState(message[0]);
						accountInfo.setActiveStopTime(message[1]);
						accountInfo.setSuspendStopTime(message[2]);
						accountInfo.setDisableStopTime(message[3]);
						accountInfo.setTerminateStopTime(message[4]);
						
						modifyCountersParameter.getAccountInfoAMFModifyCounters().add(accountInfo);
					}
					
				}
			
			}
		}
		
		if(!StringUtils.isBlank(modifyCountersParameter.getMethodVersion())) {
			
			modifyCountersParameter.setRespResultCode(EResultCode.RE20000);
			modifyCountersParameter.setNotMissing(true);
			modifyCountersParameter.setValid(true);
			return modifyCountersParameter;
			
		} else {
			
			modifyCountersParameter.setRespResultCode(EResultCode.RE40301);
			return modifyCountersParameter;
		}
		
	}
	
}
