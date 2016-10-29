package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_USMP_ModifyCounter;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_USMP_ModifyCounterParser {
	
	private static In_USMP_ModifyCounterParser parser = null;
	
	protected  In_USMP_ModifyCounterParser() {
	  
	}
	public static In_USMP_ModifyCounterParser getInstance() {
		if(parser == null) {
			parser = new In_USMP_ModifyCounterParser();
		}
		return parser;
	}
	
	public Param_USMP_ModifyCounter doParser(EquinoxRawData eqxRawData) {
		Param_USMP_ModifyCounter modifyCounterParameter = new Param_USMP_ModifyCounter();
		
		try {
			Serializer serializer = new Persister();
			modifyCounterParameter = serializer.read(Param_USMP_ModifyCounter.class, eqxRawData.getRawDataMessage());
			
			modifyCounterParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error modifySubscriber parser"+e);
			return modifyCounterParameter;
		}

		if(!StringUtils.isBlank(modifyCounterParameter.getIsSuccess()) && !StringUtils.isBlank(modifyCounterParameter.getCode())) {
			
			AppLog.d("IsSuccess : " + modifyCounterParameter.getIsSuccess());
			AppLog.d("Code : " + modifyCounterParameter.getCode());
			
			if(modifyCounterParameter.getIsSuccess().equalsIgnoreCase("true") && modifyCounterParameter.getCode().equals("VSMP-00000000")) {
				
				modifyCounterParameter.setRespResultCode(EResultCode.REVSMP00000000);
				modifyCounterParameter.setNotMissing(true);
				modifyCounterParameter.setValid(true);
				return modifyCounterParameter;
				
			} else {
				
				modifyCounterParameter.setRespResultCode(EResultCode.REret1);
				//modifySubscriberParameter.setRespResultCode(modifySubscriberParameter.getResultCode());
				return modifyCounterParameter;
				
			}
			
		} else {
			
			if(StringUtils.isBlank(modifyCounterParameter.getIsSuccess())) {
				AppLog.e("IsSuccess is null");
			}
			if(StringUtils.isBlank(modifyCounterParameter.getCode())) {
				AppLog.e("Code is null");
			}
			
			modifyCounterParameter.setRespResultCode(EResultCode.RE40301);
			return modifyCounterParameter;
		}
		
	}

}
