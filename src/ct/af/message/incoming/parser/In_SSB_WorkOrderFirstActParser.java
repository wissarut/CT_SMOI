package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.ECType;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_SSB_WorkOrderFirstAct;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_SSB_WorkOrderFirstActParser {
	
	private static In_SSB_WorkOrderFirstActParser parser = null;
	
	protected  In_SSB_WorkOrderFirstActParser() {
	  
	}
	public static In_SSB_WorkOrderFirstActParser getInstance() {
		if(parser == null) {
			parser = new In_SSB_WorkOrderFirstActParser();
		}
		return parser;
	}
	
	public Param_SSB_WorkOrderFirstAct doParser(EquinoxRawData eqxRawData) {
		Param_SSB_WorkOrderFirstAct workOrderFirstActParameter = new Param_SSB_WorkOrderFirstAct();
		
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
				return workOrderFirstActParameter;
			}
			
		}
		
		try {
			
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject)parser.parse(eqxRawDataMsg);
			
			workOrderFirstActParameter = new Param_SSB_WorkOrderFirstAct(object);
			
		} catch (Exception e) {
			AppLog.e("[Exception] Error workOrderFirstAct parser");
			return workOrderFirstActParameter;
		}
		
		if(!StringUtils.isBlank(workOrderFirstActParameter.getResultCode())) {
			
			if(workOrderFirstActParameter.getResultCode().equals("20000")) {
				
				workOrderFirstActParameter.setRespResultCode(EResultCode.RE20000);
				workOrderFirstActParameter.setNotMissing(true);
				workOrderFirstActParameter.setValid(true);
				return workOrderFirstActParameter;
				
			} else {
				
				//workOrderFirstActParameter.setRespResultCode(workOrderFirstActParameter.getResultCode());
				return workOrderFirstActParameter;
				
			}
			
		} else {
			
			workOrderFirstActParameter.setRespResultCode(EResultCode.RE40301);
			return workOrderFirstActParameter;
			
		}
		
	}
	
}
