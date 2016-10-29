package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BSS_DoCallScreenNo;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_BSS_DoCallScreenNoParser {
	
	private static In_BSS_DoCallScreenNoParser parser = null;
	
	protected  In_BSS_DoCallScreenNoParser() {
	  
	}
	public static In_BSS_DoCallScreenNoParser getInstance() {
		if(parser == null) {
			parser = new In_BSS_DoCallScreenNoParser();
		}
		return parser;
	}
	
	public Param_BSS_DoCallScreenNo doParser(EquinoxRawData eqxRawData) {
		Param_BSS_DoCallScreenNo callScreenNoParameter = new Param_BSS_DoCallScreenNo();
		
		try {
			Serializer serializer = new Persister();
			callScreenNoParameter = serializer.read(Param_BSS_DoCallScreenNo.class, eqxRawData.getRawDataMessage() );
			
			callScreenNoParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error callScreenNo parser");
			return callScreenNoParameter;
		}
		
		if(!StringUtils.isBlank(callScreenNoParameter.getResultCode())) {
			
			if(callScreenNoParameter.getResultCode().equals("1000000")) {
				
				callScreenNoParameter.setRespResultCode(EResultCode.RE1000000);
				callScreenNoParameter.setNotMissing(true);
				callScreenNoParameter.setValid(true);
				return callScreenNoParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return callScreenNoParameter;
				
			}
			
		} else {
			
			callScreenNoParameter.setRespResultCode(EResultCode.RE40301);
			return callScreenNoParameter;
			
		}

	}
	
}
