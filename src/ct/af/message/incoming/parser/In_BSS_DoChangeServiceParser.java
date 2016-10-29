package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BSS_DoChangeService;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_BSS_DoChangeServiceParser {
	
	private static In_BSS_DoChangeServiceParser parser = null;
	
	protected  In_BSS_DoChangeServiceParser() {
	  
	}
	public static In_BSS_DoChangeServiceParser getInstance() {
		if(parser == null) {
			parser = new In_BSS_DoChangeServiceParser();
		}
		return parser;
	}
	
	public Param_BSS_DoChangeService doParser(EquinoxRawData eqxRawData) {
		Param_BSS_DoChangeService changeServiceParameter = new Param_BSS_DoChangeService();
		
		try {
			Serializer serializer = new Persister();
			changeServiceParameter = serializer.read(Param_BSS_DoChangeService.class,eqxRawData.getRawDataMessage());
			
			changeServiceParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error changeService parser");
		}
		
		
		if(!StringUtils.isBlank(changeServiceParameter.getResultCode())) {
			
			if(changeServiceParameter.getResultCode().equals("1000000")) {
				
				changeServiceParameter.setRespResultCode(EResultCode.RE1000000);
				changeServiceParameter.setNotMissing(true);
				changeServiceParameter.setValid(true);
				return changeServiceParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return changeServiceParameter;
				
			}
			
		} else {
			
			changeServiceParameter.setRespResultCode(EResultCode.RE40301);
			return changeServiceParameter;
			
		}

	}
	
}
