package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ct.af.enums.ECType;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_RMF_RewardAdjustment;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_RMF_RewardAdjustmentParser {
	
	private static In_RMF_RewardAdjustmentParser parser = null;
	
	protected  In_RMF_RewardAdjustmentParser() {
	  
	}
	public static In_RMF_RewardAdjustmentParser getInstance() {
		if(parser == null) {
			parser = new In_RMF_RewardAdjustmentParser();
		}
		return parser;
	}
	
	public Param_RMF_RewardAdjustment doParser(EquinoxRawData eqxRawData) {
		
		Param_RMF_RewardAdjustment rewardAdjustmentParameter = null;
		
		String eqxRawDataMsg = "";
		
		if(eqxRawData.getCType().equals(ECType.TEXTPLAIN.getCType())) {
			
			eqxRawDataMsg = eqxRawData.getRawDataAttribute("val");
			
		} else {
			
			try {
				
				Serializer serializer = new Persister();
			     
			     ERDData erdData = serializer.read(ERDData.class, "<xml>"+eqxRawData.getRawDataMessage()+"</xml>");
			     eqxRawDataMsg = erdData.getValue();
			} catch (Exception e) {
				AppLog.e("[Exception] Error RMF_RewardAdjustment parser");
				return rewardAdjustmentParameter;
			}
			
		}
		
		try {
	
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject) parser.parse(eqxRawDataMsg);
			
			rewardAdjustmentParameter = new Param_RMF_RewardAdjustment(object);
			
			rewardAdjustmentParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error RMF_RewardAdjustment parser");
			return rewardAdjustmentParameter;
		}
		
		if(!StringUtils.isBlank(rewardAdjustmentParameter.getResultCode()) 
				&&!StringUtils.isBlank(rewardAdjustmentParameter.getDeveloperMessage())) {
			
			if(rewardAdjustmentParameter.getResultCode().equals("20000")) {
				
				rewardAdjustmentParameter.setRespResultCode(EResultCode.RE20000);
				rewardAdjustmentParameter.setNotMissing(true);
				rewardAdjustmentParameter.setValid(true);
				return rewardAdjustmentParameter;
				
			} else {
				
				//rewardMonitoringParameter.setRespResultCode(rewardMonitoringParameter.getResultCode());
				rewardAdjustmentParameter.setNotMissing(true);
				return rewardAdjustmentParameter;
				
			}
			
		} else {
			
			rewardAdjustmentParameter.setRespResultCode(EResultCode.RE40301);
			return rewardAdjustmentParameter;
			
		}
		
	}
	
}
