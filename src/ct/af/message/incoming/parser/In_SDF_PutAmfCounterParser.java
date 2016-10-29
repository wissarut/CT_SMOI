package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ct.af.enums.ECType;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_SDF_PutAmfCounter;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_SDF_PutAmfCounterParser {
	
	private static In_SDF_PutAmfCounterParser parser = null;
	
	protected  In_SDF_PutAmfCounterParser() {
	  
	}
	public static In_SDF_PutAmfCounterParser getInstance() {
		if(parser == null) {
			parser = new In_SDF_PutAmfCounterParser();
		}
		return parser;
	}
	
	public Param_SDF_PutAmfCounter doParser(EquinoxRawData eqxRawData) {
		
		Param_SDF_PutAmfCounter sdfPutAmfCounterParameter = new Param_SDF_PutAmfCounter();
		
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
				return sdfPutAmfCounterParameter;
			}
			
		}
		
		try {
			
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject)parser.parse(eqxRawDataMsg);
			
			sdfPutAmfCounterParameter = new Param_SDF_PutAmfCounter(object);
			
		} catch (Exception e) {
			AppLog.e("[Exception] Error workOrderFirstAct parser");
			return sdfPutAmfCounterParameter;
		}
		
		if(!StringUtils.isBlank(sdfPutAmfCounterParameter.getResultCode()) && StringUtils.isNotBlank(sdfPutAmfCounterParameter.getResultDescription())) {
			
			if(sdfPutAmfCounterParameter.getResultCode().equals("20000")) {
				
				sdfPutAmfCounterParameter.setRespResultCode(EResultCode.RE20000);
				sdfPutAmfCounterParameter.setNotMissing(true);
				sdfPutAmfCounterParameter.setValid(true);
				return sdfPutAmfCounterParameter;
				
			} else {
				
				sdfPutAmfCounterParameter.setRespResultCode(EResultCode.RE50000);
				//workOrderFirstActParameter.setRespResultCode(workOrderFirstActParameter.getResultCode());
				return sdfPutAmfCounterParameter;
				
			}
			
		} else {
			
			sdfPutAmfCounterParameter.setRespResultCode(EResultCode.RE40301);
			return sdfPutAmfCounterParameter;
			
		}
		
	}

}
