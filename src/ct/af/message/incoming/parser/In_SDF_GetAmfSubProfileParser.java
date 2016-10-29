package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.ECType;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_SDF_GetAmfSubProfile;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_SDF_GetAmfSubProfileParser {
	
	private static In_SDF_GetAmfSubProfileParser parser = null;
	
	protected  In_SDF_GetAmfSubProfileParser() {
	  
	}
	public static In_SDF_GetAmfSubProfileParser getInstance() {
		if(parser == null) {
			parser = new In_SDF_GetAmfSubProfileParser();
		}
		return parser;
	}
	
	public Param_SDF_GetAmfSubProfile doParser(EquinoxRawData eqxRawData) {
		
		Param_SDF_GetAmfSubProfile getAmfSubProfileParameter = new Param_SDF_GetAmfSubProfile();
		
		String eqxRawDataMsg = "";
		
		if(eqxRawData.getCType().equals(ECType.TEXTPLAIN.getCType())) {
			
			eqxRawDataMsg = eqxRawData.getRawDataAttribute("val");
			
		} else {
			
			try {
				
				Serializer serializer = new Persister();
			     
			     ERDData erdData = serializer.read(ERDData.class, "<xml>"+eqxRawData.getRawDataMessage()+"</xml>");
			     eqxRawDataMsg = erdData.getValue();
			     
			} catch (Exception e) {
				AppLog.e("[Exception] Error GetAmfSubProfile parser"+e);
				return getAmfSubProfileParameter;
			}
			
		}
		AppLog.d(">>>>>>>>> "+eqxRawDataMsg);
		try {
			
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject)parser.parse(eqxRawDataMsg);
			
			getAmfSubProfileParameter = new Param_SDF_GetAmfSubProfile(object);
		
		} catch (Exception e) {
			AppLog.e("[Exception] Error getAmfCounterParameter parser"+e);
			return getAmfSubProfileParameter;
		}
		
		
		
		if(!StringUtils.isBlank(getAmfSubProfileParameter.getResultCode()) && StringUtils.isNotBlank(getAmfSubProfileParameter.getDeveloperMessage())) {
			
			if(getAmfSubProfileParameter.getResultCode().equals("20000")) {
				
				if(getAmfSubProfileParameter.getAmfSubProfile().size() > 0) {
					
					getAmfSubProfileParameter.setRespResultCode(EResultCode.RE20000);
					getAmfSubProfileParameter.setNotMissing(true);
					getAmfSubProfileParameter.setValid(true);
					return getAmfSubProfileParameter;
					
				} else {
					
					getAmfSubProfileParameter.setRespResultCode(EResultCode.RE40301);
					return getAmfSubProfileParameter;
					
				}
				
			} else {
				
				getAmfSubProfileParameter.setRespResultCode(EResultCode.RE50000);
				//rewardMonitoringParameter.setRespResultCode(rewardMonitoringParameter.getResultCode());
				getAmfSubProfileParameter.setNotMissing(true);
				return getAmfSubProfileParameter;
				
			}
			
		} else {
			
			getAmfSubProfileParameter.setRespResultCode(EResultCode.RE40301);
			return getAmfSubProfileParameter;
			
		}
		
	}

}
