package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.ECType;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_RMF_RewardMonitoring;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_RMF_RewardMonitoringParser {
	
	private static In_RMF_RewardMonitoringParser parser = null;
	
	protected  In_RMF_RewardMonitoringParser() {
	  
	}
	public static In_RMF_RewardMonitoringParser getInstance() {
		if(parser == null) {
			parser = new In_RMF_RewardMonitoringParser();
		}
		return parser;
	}
	
	public Param_RMF_RewardMonitoring doParser(EquinoxRawData eqxRawData) {
		
		Param_RMF_RewardMonitoring rewardMonitoringParameter = null;
		
		String eqxRawDataMsg = "";
		
		if(eqxRawData.getCType().equals(ECType.TEXTPLAIN.getCType())) {
			
			eqxRawDataMsg = eqxRawData.getRawDataAttribute("val");
			
		} else {
			
			try {
				
				Serializer serializer = new Persister();
			     
			     ERDData erdData = serializer.read(ERDData.class, "<xml>"+eqxRawData.getRawDataMessage()+"</xml>");
			     eqxRawDataMsg = erdData.getValue();
			} catch (Exception e) {
				AppLog.e("[Exception] Error workOrderFirstAct parser");
				return rewardMonitoringParameter;
			}
			
		}
		
		try {
	
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject) parser.parse(eqxRawDataMsg);
			
			rewardMonitoringParameter = new Param_RMF_RewardMonitoring(object);
			
			rewardMonitoringParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error rewardMonitoring parser");
			return rewardMonitoringParameter;
		}
		
		if(!StringUtils.isBlank(rewardMonitoringParameter.getResultCode()) 
				&&!StringUtils.isBlank(rewardMonitoringParameter.getDeveloperMessage())) {
			
			if(rewardMonitoringParameter.getResultCode().equals("20000")) {
				
				rewardMonitoringParameter.setRespResultCode(EResultCode.RE20000);
				rewardMonitoringParameter.setNotMissing(true);
				rewardMonitoringParameter.setValid(true);
				return rewardMonitoringParameter;
				
			} else {
				
				//rewardMonitoringParameter.setRespResultCode(rewardMonitoringParameter.getResultCode());
				rewardMonitoringParameter.setNotMissing(true);
				return rewardMonitoringParameter;
				
			}
			
		} else {
			
			rewardMonitoringParameter.setRespResultCode(EResultCode.RE40301);
			return rewardMonitoringParameter;
			
		}
		
	}
	
}
