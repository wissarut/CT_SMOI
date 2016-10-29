package ct.af.message.incoming.parser;

import java.util.List;
import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.AVP;
import ct.af.message.incoming.parameter.CounterPackInfo_AMF_ManageCounters;
import ct.af.message.incoming.parameter.Param_AMF_ManageCounters;
import ct.af.message.incoming.parameter.UpgradePackInfo_AMF_ManageCounters;
import ct.af.message.incoming.parameter.VAL;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_AMF_ManageCountersParser {
	
	private static In_AMF_ManageCountersParser parser = null;
	
	protected  In_AMF_ManageCountersParser() {
	  
	}
	public static In_AMF_ManageCountersParser getInstance() {
		if(parser == null) {
			parser = new In_AMF_ManageCountersParser();
		}
		return parser;
	}
	
	public Param_AMF_ManageCounters doParser(EquinoxRawData eqxRawData) {
		
		Param_AMF_ManageCounters param_AMF_ManageCounters = new Param_AMF_ManageCounters();
		
		try {
			Serializer serializer = new Persister();
			param_AMF_ManageCounters = serializer.read(Param_AMF_ManageCounters.class, "<xml>" + eqxRawData.getRawDataMessage() + "</xml>");
			
			param_AMF_ManageCounters.setRecive(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error modifyCounters parser");
			return param_AMF_ManageCounters;
		}
		
		List<AVP> listAVP = param_AMF_ManageCounters.getAvp();
		
		for (AVP avp: listAVP) {
			if (avp.getType().equals("methodVersion")) {
			
				param_AMF_ManageCounters.setMethodVersion(avp.getVal().get(0).getValue());
	
			}
			
			if (avp.getType().equals("counterPackInfo")) {
				
				for (VAL val: avp.getVal()) {
					
					CounterPackInfo_AMF_ManageCounters counterPackInfo = new CounterPackInfo_AMF_ManageCounters();
					String[] message = val.getValue().split(",", -1);

//					counterPackInfo.setProductNo(message[0]);
//					counterPackInfo.setSyncflag(message[1]);
//					counterPackInfo.setStartTime(message[2]);
//					counterPackInfo.setProductState(message[3]);
//					counterPackInfo.setExpiryTime(message[4]);
//					counterPackInfo.setResultState(message[5]);
					
					param_AMF_ManageCounters.getCounterPackInfoAMFManageCounters().add(counterPackInfo);

					
				}	
			}
			
			if (avp.getType().equals("upgradePackInfo")) {
				
				for (VAL val: avp.getVal()) {
					
					UpgradePackInfo_AMF_ManageCounters upgradePackInfo = new UpgradePackInfo_AMF_ManageCounters();
					String[] message = val.getValue().split(",", -1);

//					upgradePackInfo.setProductNo(message[0]);
//					upgradePackInfo.setUpgradedflag(message[1]);
					
					param_AMF_ManageCounters.getUpgradePackInfoAMFManageCounters().add(upgradePackInfo);
						
					
				}	
			}
		}
		
		if(!StringUtils.isBlank(param_AMF_ManageCounters.getMethodVersion())) {
			
			param_AMF_ManageCounters.setRespResultCode(EResultCode.RE20000);
			param_AMF_ManageCounters.setNotMissing(true);
			param_AMF_ManageCounters.setValid(true);
			return param_AMF_ManageCounters;
			
		} else {

			AppLog.e("param_AMF_ManageCounters.getMethodVersion() is null");
			
			param_AMF_ManageCounters.setRespResultCode(EResultCode.RE40301);
			return param_AMF_ManageCounters;
		}
		
	}
	
}
