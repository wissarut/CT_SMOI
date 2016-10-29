package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BSS_DoModifySubscriberBasicInfo;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_BSS_DoModifySubscriberBasicInfoParser {
	
	private static In_BSS_DoModifySubscriberBasicInfoParser parser = null;
	
	protected  In_BSS_DoModifySubscriberBasicInfoParser() {
	  
	}
	public static In_BSS_DoModifySubscriberBasicInfoParser getInstance() {
		if(parser == null) {
			parser = new In_BSS_DoModifySubscriberBasicInfoParser();
		}
		return parser;
	}
	
	public Param_BSS_DoModifySubscriberBasicInfo doParser(EquinoxRawData eqxRawData) {
		Param_BSS_DoModifySubscriberBasicInfo modifySubscriberBasicInfoParameter = new Param_BSS_DoModifySubscriberBasicInfo();

		try {
			Serializer serializer = new Persister();
			modifySubscriberBasicInfoParameter = serializer.read(Param_BSS_DoModifySubscriberBasicInfo.class, eqxRawData.getRawDataMessage());
			modifySubscriberBasicInfoParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error modifySubscriberBasicInfo parser");
			return modifySubscriberBasicInfoParameter;
		}
		
		if(!StringUtils.isBlank(modifySubscriberBasicInfoParameter.getResultCode())) {
			
			if(modifySubscriberBasicInfoParameter.getResultCode().equals("1000000")) {
				
				modifySubscriberBasicInfoParameter.setRespResultCode(EResultCode.RE1000000);
				modifySubscriberBasicInfoParameter.setNotMissing(true);
				modifySubscriberBasicInfoParameter.setValid(true);
				return modifySubscriberBasicInfoParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return modifySubscriberBasicInfoParameter;
				
			}
			
		} else {
			
			modifySubscriberBasicInfoParameter.setRespResultCode(EResultCode.RE40301);
			return modifySubscriberBasicInfoParameter;
			
		}

	}
	
}
