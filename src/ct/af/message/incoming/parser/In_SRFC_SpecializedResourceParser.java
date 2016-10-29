package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_SRFC_SpecializedResource;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_SRFC_SpecializedResourceParser {
	
	private static In_SRFC_SpecializedResourceParser parser = null;
	
	protected  In_SRFC_SpecializedResourceParser() {
	  
	}
	public static In_SRFC_SpecializedResourceParser getInstance() {
		if(parser == null) {
			parser = new In_SRFC_SpecializedResourceParser();
		}
		return parser;
	}
	
	public Param_SRFC_SpecializedResource doParser(EquinoxRawData eqxRawData) {
		Param_SRFC_SpecializedResource specializedResourceParameter = new Param_SRFC_SpecializedResource();
		
		try {
			Serializer serializer = new Persister();
			specializedResourceParameter = serializer.read(Param_SRFC_SpecializedResource.class, "<xml>" + eqxRawData.getRawDataMessage() + "</xml>");
			
			specializedResourceParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error specializedResource parser");
			return specializedResourceParameter;
		}
		
		if(!StringUtils.isBlank(specializedResourceParameter.getSessionID())
				&&!StringUtils.isBlank(specializedResourceParameter.getResultCode())
				&&!StringUtils.isBlank(specializedResourceParameter.getAuthApplicationID())
				&&!StringUtils.isBlank(specializedResourceParameter.getAuthRequestType())
				&&!StringUtils.isBlank(specializedResourceParameter.getResultCode())
				&&!StringUtils.isBlank(specializedResourceParameter.getOriginHost())
				&&!StringUtils.isBlank(specializedResourceParameter.getOriginRealm())) {
			
			if(specializedResourceParameter.getResultCode().equals("2001")) {
				
				specializedResourceParameter.setRespResultCode(EResultCode.RE2001);
				specializedResourceParameter.setNotMissing(true);
				specializedResourceParameter.setValid(true);
				return specializedResourceParameter;
				
			} else {
				
				specializedResourceParameter.setRespResultCode(EResultCode.RE50000);
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return specializedResourceParameter;
				
			}
			
		} else {
			
			specializedResourceParameter.setRespResultCode(EResultCode.RE40301);
			return specializedResourceParameter;
			
		}

	}
	
}
