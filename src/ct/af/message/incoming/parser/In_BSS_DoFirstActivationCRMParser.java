package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BSS_DoFirstActivationCRM;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_BSS_DoFirstActivationCRMParser {
	
	private static In_BSS_DoFirstActivationCRMParser parser = null;
	
	protected  In_BSS_DoFirstActivationCRMParser() {
	  
	}
	public static In_BSS_DoFirstActivationCRMParser getInstance() {
		if(parser == null) {
			parser = new In_BSS_DoFirstActivationCRMParser();
		}
		return parser;
	}
	
	public Param_BSS_DoFirstActivationCRM doParser(EquinoxRawData eqxRawData) {
		Param_BSS_DoFirstActivationCRM firstActivationCRMParameter = new Param_BSS_DoFirstActivationCRM();
		
		try {
			Serializer serializer = new Persister();
			firstActivationCRMParameter = serializer.read(Param_BSS_DoFirstActivationCRM.class, eqxRawData.getRawDataMessage());
			
			firstActivationCRMParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error firstActivationCRM parser");
			return firstActivationCRMParameter;
		}
		
		if(!StringUtils.isBlank(firstActivationCRMParameter.getResultCode())) {
			
			if(firstActivationCRMParameter.getResultCode().equals("1000000")) {
				
				firstActivationCRMParameter.setRespResultCode(EResultCode.RE1000000);
				firstActivationCRMParameter.setNotMissing(true);
				firstActivationCRMParameter.setValid(true);
				return firstActivationCRMParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return firstActivationCRMParameter;
				
			}
			
		} else {
			
			firstActivationCRMParameter.setRespResultCode(EResultCode.RE40301);
			return firstActivationCRMParameter;
			
		}

	}
	
}
