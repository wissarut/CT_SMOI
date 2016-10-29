package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BMP_DispScrScreenNo;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_BMP_DispScrScreenNoParser {
	
	private static In_BMP_DispScrScreenNoParser parser = null;
	
	protected  In_BMP_DispScrScreenNoParser() {
	  
	}
	public static In_BMP_DispScrScreenNoParser getInstance() {
		if(parser == null) {
			parser = new In_BMP_DispScrScreenNoParser();
		}
		return parser;
	}
	
	public Param_BMP_DispScrScreenNo doParser(EquinoxRawData eqxRawData) {
		Param_BMP_DispScrScreenNo dispScrScreenNoParameter = new Param_BMP_DispScrScreenNo();
		
		try {
			Serializer serializer = new Persister();
			
			ERDData erdData = serializer.read(ERDData.class, "<xml>"+eqxRawData.getRawDataMessage()+"</xml>");
			
			AppLog.d("erdData.getValue() : "+erdData.getValue());
			
			String erdDataStr = erdData.getValue();
			if(erdDataStr.contains("<?xml")){
				erdDataStr = erdDataStr.substring(erdDataStr.indexOf("?>")+2);
				}
			
			AppLog.d(erdDataStr);
			
			dispScrScreenNoParameter = serializer.read(Param_BMP_DispScrScreenNo.class, erdDataStr);
			
			dispScrScreenNoParameter.setReceived(true);
			
		} catch (Exception e) {
			AppLog.e("[Exception] Error dispScrScreenNo parser"+e);
		}
		
		if(!StringUtils.isBlank(dispScrScreenNoParameter.getResultCode())) {
			
			if(dispScrScreenNoParameter.getResultCode().equals("405000000")) {
				
				dispScrScreenNoParameter.setRespResultCode(EResultCode.RE405000000);
				dispScrScreenNoParameter.setNotMissing(true);
				dispScrScreenNoParameter.setValid(true);
				return dispScrScreenNoParameter;
				
			} else {
				
				//callScreeningParameter.setRespResultCode(callScreeningParameter.getResultCode());
				return dispScrScreenNoParameter;
				
			}
			
		} else {
			
			dispScrScreenNoParameter.setRespResultCode(EResultCode.RE40301);
			return dispScrScreenNoParameter;
			
		}
	}
	
}
