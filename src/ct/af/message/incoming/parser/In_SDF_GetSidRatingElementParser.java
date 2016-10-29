package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.ECType;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_SDF_GetSidRatingElement;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_SDF_GetSidRatingElementParser {
	
	private static In_SDF_GetSidRatingElementParser parser = null;
	
	protected  In_SDF_GetSidRatingElementParser() {
	  
	}
	public static In_SDF_GetSidRatingElementParser getInstance() {
		if(parser == null) {
			parser = new In_SDF_GetSidRatingElementParser();
		}
		return parser;
	}
	
	public Param_SDF_GetSidRatingElement doParser(EquinoxRawData eqxRawData) {
		
		Param_SDF_GetSidRatingElement getSidRatingElementParameter = new Param_SDF_GetSidRatingElement();
		
		String eqxRawDataMsg = "";
		
		if(eqxRawData.getCType().equals(ECType.TEXTPLAIN.getCType())) {
			
			eqxRawDataMsg = eqxRawData.getRawDataAttribute("val");
			
		} else {
			
			try {
				
				Serializer serializer = new Persister();
			     
			     ERDData erdData = serializer.read(ERDData.class, "<xml>"+eqxRawData.getRawDataMessage()+"</xml>");
			     eqxRawDataMsg = erdData.getValue();
			     
			} catch (Exception e) {
				AppLog.e("[Exception] Error GetSidRatingElement parser");
				return getSidRatingElementParameter;
			}
			
		}
		AppLog.d(">>>>>>>>> "+eqxRawDataMsg);
		try {
			
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject)parser.parse(eqxRawDataMsg);
			
			getSidRatingElementParameter = new Param_SDF_GetSidRatingElement(object);
			
		} catch (Exception e) {
			AppLog.e("[Exception] Error getAmfCounterParameter parser"+e);
			return getSidRatingElementParameter;
		}
		
		if(!StringUtils.isBlank(getSidRatingElementParameter.getResultCode())) {
			
			if(getSidRatingElementParameter.getResultCode().equals("20000")) {
				
				if(getSidRatingElementParameter.getSidRatingElement().size() > 0) {
					
					getSidRatingElementParameter.setRespResultCode(EResultCode.RE20000);
					getSidRatingElementParameter.setNotMissing(true);
					getSidRatingElementParameter.setValid(true);
					return getSidRatingElementParameter;
					
				} else {
					
					getSidRatingElementParameter.setRespResultCode(EResultCode.RE40301);
					return getSidRatingElementParameter;
					
				}
				
			} else {
				
				//rewardMonitoringParameter.setRespResultCode(rewardMonitoringParameter.getResultCode());
				getSidRatingElementParameter.setNotMissing(true);
				return getSidRatingElementParameter;
				
			}
			
		} else {
			
			getSidRatingElementParameter.setRespResultCode(EResultCode.RE40301);
			return getSidRatingElementParameter;
			
		}
		
	}

}
