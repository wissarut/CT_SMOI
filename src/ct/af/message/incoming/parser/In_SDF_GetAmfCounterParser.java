package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ct.af.enums.ECType;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_SDF_GetAmfCounter;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_SDF_GetAmfCounterParser {
	
	private static In_SDF_GetAmfCounterParser parser = null;
	
	protected  In_SDF_GetAmfCounterParser() {
	  
	}
	public static In_SDF_GetAmfCounterParser getInstance() {
		if(parser == null) {
			parser = new In_SDF_GetAmfCounterParser();
		}
		return parser;
	}
	
	public Param_SDF_GetAmfCounter doParser(EquinoxRawData eqxRawData) {
		
		Param_SDF_GetAmfCounter getAmfCounterParameter = new Param_SDF_GetAmfCounter();
		
		String eqxRawDataMsg = "";
		
		if(eqxRawData.getCType().equals(ECType.TEXTPLAIN.getCType())) {
			
			eqxRawDataMsg = eqxRawData.getRawDataAttribute("val");
			
		} else {
			
			try {
				
				Serializer serializer = new Persister();
			     
			     ERDData erdData = serializer.read(ERDData.class, "<xml>"+eqxRawData.getRawDataMessage()+"</xml>");
			     eqxRawDataMsg = erdData.getValue();
			} catch (Exception e) {
				AppLog.e("[Exception] Error GetAmfCounter parser");
				return getAmfCounterParameter;
			}
			
		}
		AppLog.d(">>>>>>>>> "+eqxRawDataMsg);
		try {
			
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject)parser.parse(eqxRawDataMsg);
			
			getAmfCounterParameter = new Param_SDF_GetAmfCounter(object);
			
		} catch (Exception e) {
			AppLog.e("[Exception] Error getAmfCounterParameter parser"+e);
			return getAmfCounterParameter;
		}
		
		if(!StringUtils.isBlank(getAmfCounterParameter.getResultCode())&& StringUtils.isNotBlank(getAmfCounterParameter.getDeveloperMessage())) {
			
			if(getAmfCounterParameter.getResultCode().equals("20000")) {
				
				if(getAmfCounterParameter.getAmfCounter().size() > 0) {
					
					//if(!StringUtils.isBlank(getAmfCounterParameter.getAmfCounter().get(0).getCounterLowerLimit())) {
						
						getAmfCounterParameter.setRespResultCode(EResultCode.RE20000);
						getAmfCounterParameter.setNotMissing(true);
						getAmfCounterParameter.setValid(true);
						return getAmfCounterParameter;
						
					//} else {
						
					//	getAmfCounterParameter.setRespResultCode(EResultCode.RE40301);
					//	return getAmfCounterParameter;
						
					//}
					
				} else {
					
					getAmfCounterParameter.setRespResultCode(EResultCode.RE40301);
					return getAmfCounterParameter;
					
				}
				
			} else {
				
				getAmfCounterParameter.setRespResultCode(EResultCode.RE50000);
				//rewardMonitoringParameter.setRespResultCode(rewardMonitoringParameter.getResultCode());
				getAmfCounterParameter.setNotMissing(true);
				return getAmfCounterParameter;
				
			}
			
		} else {
			
			getAmfCounterParameter.setRespResultCode(EResultCode.RE40301);
			return getAmfCounterParameter;
			
		}
		
	}
	
}
