package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_USMP_InquirySubscriberInfo;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_USMP_InquirySubscriberInfoParser {
	
	private static In_USMP_InquirySubscriberInfoParser parser = null;
	
	protected  In_USMP_InquirySubscriberInfoParser() {
	  
	}
	public static In_USMP_InquirySubscriberInfoParser getInstance() {
		if(parser == null) {
			parser = new In_USMP_InquirySubscriberInfoParser();
		}
		return parser;
	}
	
	public Param_USMP_InquirySubscriberInfo doParser(EquinoxRawData eqxRawData) {
		Param_USMP_InquirySubscriberInfo inquirySubscriberInfoParameter = new Param_USMP_InquirySubscriberInfo();
		
		try {
			Serializer serializer = new Persister();
			inquirySubscriberInfoParameter = serializer.read(Param_USMP_InquirySubscriberInfo.class, eqxRawData.getRawDataMessage());
			
			inquirySubscriberInfoParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.d("[Exception] Error inquirySubscriberInfo parser");
			return inquirySubscriberInfoParameter;
		}
		
		AppLog.d("inquirySubscriberInfo"+inquirySubscriberInfoParameter.getIsSuccess());
		
		if(!StringUtils.isBlank(inquirySubscriberInfoParameter.getIsSuccess()) && !StringUtils.isBlank(inquirySubscriberInfoParameter.getCode())) {
			
			if(inquirySubscriberInfoParameter.getIsSuccess().equalsIgnoreCase("true") && inquirySubscriberInfoParameter.getCode().equals("VSMP-00000000")) {
				
				inquirySubscriberInfoParameter.setRespResultCode(EResultCode.REVSMP00000000);
				inquirySubscriberInfoParameter.setNotMissing(true);
				inquirySubscriberInfoParameter.setValid(true);
				return inquirySubscriberInfoParameter;
				
			} else {
				
				inquirySubscriberInfoParameter.setRespResultCode(EResultCode.REret1);
				
				//modifySubscriberParameter.setRespResultCode(modifySubscriberParameter.getResultCode());
				return inquirySubscriberInfoParameter;
				
			}
			
		} else {
			
			inquirySubscriberInfoParameter.setRespResultCode(EResultCode.RE40301);
			return inquirySubscriberInfoParameter;
		}

	}
	
}
