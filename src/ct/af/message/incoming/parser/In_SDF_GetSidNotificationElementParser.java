package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.ECType;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_SDF_GetSidNotificationElement;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_SDF_GetSidNotificationElementParser {
	
	private static In_SDF_GetSidNotificationElementParser parser = null;
	
	protected  In_SDF_GetSidNotificationElementParser() {
	  
	}
	public static In_SDF_GetSidNotificationElementParser getInstance() {
		if(parser == null) {
			parser = new In_SDF_GetSidNotificationElementParser();
		}
		return parser;
	}
	
	public Param_SDF_GetSidNotificationElement doParser(EquinoxRawData eqxRawData) {
		
		Param_SDF_GetSidNotificationElement getSidNotificationElementParameter = new Param_SDF_GetSidNotificationElement();
		
		String eqxRawDataMsg = "";
		
		if(eqxRawData.getCType().equals(ECType.TEXTPLAIN.getCType())) {
			
			eqxRawDataMsg = eqxRawData.getRawDataAttribute("val");
			
		} else {
			
			try {
				
				Serializer serializer = new Persister();
			     
			     ERDData erdData = serializer.read(ERDData.class, "<xml>"+eqxRawData.getRawDataMessage()+"</xml>");
			     eqxRawDataMsg = erdData.getValue();
			     
			} catch (Exception e) {
				AppLog.e("[Exception] Error GetSidNotificationElement parser");
				return getSidNotificationElementParameter;
			}
			
		}
		AppLog.d(">>>>>>>>> "+eqxRawDataMsg);
		try {
			
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject)parser.parse(eqxRawDataMsg);
			
			getSidNotificationElementParameter = new Param_SDF_GetSidNotificationElement(object);
			
		} catch (Exception e) {
			AppLog.e("[Exception] Error getAmfCounterParameter parser"+e);
			return getSidNotificationElementParameter;
		}
		
		if(!StringUtils.isBlank(getSidNotificationElementParameter.getResultCode()) && 
				!StringUtils.isBlank(getSidNotificationElementParameter.getDeveloperMessage())) {
			
			if(getSidNotificationElementParameter.getResultCode().equals("20000")) {
				
				if(getSidNotificationElementParameter.getSidNotificationElement().size() > 0) {
					
					getSidNotificationElementParameter.setRespResultCode(EResultCode.RE20000);
					getSidNotificationElementParameter.setNotMissing(true);
					getSidNotificationElementParameter.setValid(true);
					return getSidNotificationElementParameter;
					
				} else {
					
					getSidNotificationElementParameter.setRespResultCode(EResultCode.RE40301);
					return getSidNotificationElementParameter;
					
				}
				
			} else {
				
				getSidNotificationElementParameter.setRespResultCode(EResultCode.RE50000);
				//rewardMonitoringParameter.setRespResultCode(rewardMonitoringParameter.getResultCode());
				getSidNotificationElementParameter.setNotMissing(true);
				return getSidNotificationElementParameter;
				
			}
			
		} else {
			
			getSidNotificationElementParameter.setRespResultCode(EResultCode.RE40301);
			return getSidNotificationElementParameter;
			
		}
		
	}

}
