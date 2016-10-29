package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_USMP_ModifySubscriber;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_USMP_ModifySubscriberParser {
	
	private static In_USMP_ModifySubscriberParser parser = null;
	
	protected  In_USMP_ModifySubscriberParser() {
	  
	}
	public static In_USMP_ModifySubscriberParser getInstance() {
		if(parser == null) {
			parser = new In_USMP_ModifySubscriberParser();
		}
		return parser;
	}
	
	public Param_USMP_ModifySubscriber doParser(EquinoxRawData eqxRawData) {
		Param_USMP_ModifySubscriber modifySubscriberParameter = new Param_USMP_ModifySubscriber();
		
		try {
			Serializer serializer = new Persister();
			modifySubscriberParameter = serializer.read(Param_USMP_ModifySubscriber.class, eqxRawData.getRawDataMessage());
			
			modifySubscriberParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error modifySubscriber parser"+e);
			return modifySubscriberParameter;
		}

		if(!StringUtils.isBlank(modifySubscriberParameter.getIsSuccess()) && !StringUtils.isBlank(modifySubscriberParameter.getCode())) {
			
			AppLog.d("IsSuccess : " + modifySubscriberParameter.getIsSuccess());
			AppLog.d("Code : " + modifySubscriberParameter.getCode());
			
			if(modifySubscriberParameter.getIsSuccess().equalsIgnoreCase("true") && modifySubscriberParameter.getCode().equals("VSMP-00000000")) {
				
				modifySubscriberParameter.setRespResultCode(EResultCode.REVSMP00000000);
				modifySubscriberParameter.setNotMissing(true);
				modifySubscriberParameter.setValid(true);
				return modifySubscriberParameter;
				
			} else {
				
				modifySubscriberParameter.setRespResultCode(EResultCode.REret1);
				//modifySubscriberParameter.setRespResultCode(modifySubscriberParameter.getResultCode());
				return modifySubscriberParameter;
				
			}
			
		} else {
			
			if(StringUtils.isBlank(modifySubscriberParameter.getIsSuccess())) {
				AppLog.e("IsSuccess is null");
			}
			if(StringUtils.isBlank(modifySubscriberParameter.getCode())) {
				AppLog.e("Code is null");
			}
			
			modifySubscriberParameter.setRespResultCode(EResultCode.RE40301);
			return modifySubscriberParameter;
		}
		
	}
	
}
