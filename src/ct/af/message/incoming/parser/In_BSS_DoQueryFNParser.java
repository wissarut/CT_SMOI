package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BSS_DoQueryFN;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_BSS_DoQueryFNParser {
	
	private static In_BSS_DoQueryFNParser parser = null;
	
	protected  In_BSS_DoQueryFNParser() {
	  
	}
	public static In_BSS_DoQueryFNParser getInstance() {
		if(parser == null) {
			parser = new In_BSS_DoQueryFNParser();
		}
		return parser;
	}
	
	public Param_BSS_DoQueryFN doParser(EquinoxRawData eqxRawData) {
		Param_BSS_DoQueryFN queryFNParameter = new Param_BSS_DoQueryFN();
		
		try {
			Serializer serializer = new Persister();
			queryFNParameter = serializer.read(Param_BSS_DoQueryFN.class, eqxRawData.getRawDataMessage());
			
			queryFNParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error queryFN parser");
			System.out.println(e.toString());
			return queryFNParameter;
		}
		
		if(!StringUtils.isBlank(queryFNParameter.getResultCode())) {
			
			if(queryFNParameter.getResultCode().equals("1000000")) {
				
				queryFNParameter.setRespResultCode(EResultCode.RE1000000);
				queryFNParameter.setNotMissing(true);
				queryFNParameter.setValid(true);
				return queryFNParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return queryFNParameter;
				
			}
			
		} else {
			
			queryFNParameter.setRespResultCode(EResultCode.RE40301);
			return queryFNParameter;
			
		}

	}
	
}
