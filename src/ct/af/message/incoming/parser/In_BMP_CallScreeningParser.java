package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BMP_CallScreening;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_BMP_CallScreeningParser {
	
	private static In_BMP_CallScreeningParser parser = null;
	
	protected  In_BMP_CallScreeningParser() {
	  
	}
	public static In_BMP_CallScreeningParser getInstance() {
		if(parser == null) {
			parser = new In_BMP_CallScreeningParser();
		}
		return parser;
	}
	
	public Param_BMP_CallScreening doParser(EquinoxRawData eqxRawData) {
		
		Param_BMP_CallScreening callScreeningParameter = new Param_BMP_CallScreening();
		
		try {
			Serializer serializer = new Persister();
			
			ERDData erdData = serializer.read(ERDData.class, "<xml>"+eqxRawData.getRawDataMessage()+"</xml>");
			
			AppLog.d("erdData.getValue() : "+erdData.getValue());
			
			String erdDataStr = erdData.getValue();
			if(erdDataStr.contains("<?xml")){
				erdDataStr = erdDataStr.substring(erdDataStr.indexOf("?>")+2);
				}
			
			AppLog.d(erdDataStr);
			
			callScreeningParameter = serializer.read(Param_BMP_CallScreening.class, erdDataStr);
			
			callScreeningParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error callScreening parser");
			return callScreeningParameter;
		}
		
		if(!StringUtils.isBlank(callScreeningParameter.getResultCode())) {
			
			if(callScreeningParameter.getResultCode().equals("405000000")) {
				
				callScreeningParameter.setRespResultCode(EResultCode.RE405000000);
				callScreeningParameter.setNotMissing(true);
				callScreeningParameter.setValid(true);
				return callScreeningParameter;
				
			} else {
				
				//callScreeningParameter.setRespResultCode(callScreeningParameter.getResultCode());
				return callScreeningParameter;
				
			}
			
		} else {
			
			callScreeningParameter.setRespResultCode(EResultCode.RE40301);
			return callScreeningParameter;
			
		}
		
	}
	
}
