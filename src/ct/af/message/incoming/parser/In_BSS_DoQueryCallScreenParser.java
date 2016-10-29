package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BSS_DoQueryCallScreen;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_BSS_DoQueryCallScreenParser {
	
	private static In_BSS_DoQueryCallScreenParser parser = null;
	
	protected  In_BSS_DoQueryCallScreenParser() {
	  
	}
	public static In_BSS_DoQueryCallScreenParser getInstance() {
		if(parser == null) {
			parser = new In_BSS_DoQueryCallScreenParser();
		}
		return parser;
	}
	
	public Param_BSS_DoQueryCallScreen doParser(EquinoxRawData eqxRawData) {
		Param_BSS_DoQueryCallScreen queryCallScreenParameter = new Param_BSS_DoQueryCallScreen();
		
		try {
			Serializer serializer = new Persister();
			queryCallScreenParameter = serializer.read(Param_BSS_DoQueryCallScreen.class, eqxRawData.getRawDataMessage());
			
			queryCallScreenParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error queryCallScreen parser");
			return queryCallScreenParameter;
		}
		
//		System.out.println(queryCallScreenParameter.getResultCode());
//		System.out.println(queryCallScreenParameter.getBosSoNbr());
		
		if(!StringUtils.isBlank(queryCallScreenParameter.getResultCode())) {
			
			if(queryCallScreenParameter.getResultCode().equals("1000000")) {
				
				queryCallScreenParameter.setRespResultCode(EResultCode.RE1000000);
				queryCallScreenParameter.setNotMissing(true);
				queryCallScreenParameter.setValid(true);
				return queryCallScreenParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return queryCallScreenParameter;
				
			}
			
		} else {
			
			queryCallScreenParameter.setRespResultCode(EResultCode.RE40301);
			return queryCallScreenParameter;
			
		}

	}
	
}
