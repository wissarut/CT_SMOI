package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_USMP_InquiryCounter;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_USMP_InquiryCounterParser {
	
	private static In_USMP_InquiryCounterParser parser = null;
	
	protected  In_USMP_InquiryCounterParser() {
	  
	}
	public static In_USMP_InquiryCounterParser getInstance() {
		if(parser == null) {
			parser = new In_USMP_InquiryCounterParser();
		}
		return parser;
	}
	
	public Param_USMP_InquiryCounter doParser(EquinoxRawData eqxRawData) {
		Param_USMP_InquiryCounter inquiryCounterParameter = new Param_USMP_InquiryCounter();
		
		try {
			Serializer serializer = new Persister();
			inquiryCounterParameter = serializer.read(Param_USMP_InquiryCounter.class, eqxRawData.getRawDataMessage());
			
			inquiryCounterParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error firstActivate parser"+e);
			return inquiryCounterParameter;
		}
		
		AppLog.d("getIsSuccess : "+inquiryCounterParameter.getIsSuccess());
		AppLog.d("getCode : "+inquiryCounterParameter.getCode());
		
		String mainProF = "";
		String productNumber = "";
		String counterName = "";
		
		if(inquiryCounterParameter.getCounterInfo() != null) {
			for(int i=0;i<inquiryCounterParameter.getCounterInfo().size();i++) {
				
				if(!StringUtils.isBlank(inquiryCounterParameter.getCounterInfo().get(i).getMainPromotionF())) {
					if(inquiryCounterParameter.getCounterInfo().get(i).getMainPromotionF().equals("1")) {
						mainProF = inquiryCounterParameter.getCounterInfo().get(i).getMainPromotionF();
					}
				}
	
				if(!StringUtils.isBlank(inquiryCounterParameter.getCounterInfo().get(i).getProductNumber())) {
					productNumber = inquiryCounterParameter.getCounterInfo().get(i).getProductNumber();
				}
	
				if(!StringUtils.isBlank(inquiryCounterParameter.getCounterInfo().get(i).getCounterName())) {
					counterName = inquiryCounterParameter.getCounterInfo().get(i).getCounterName();
					
				}
				
			}
		}
		
		if(!StringUtils.isBlank(inquiryCounterParameter.getIsSuccess()) && !StringUtils.isBlank(inquiryCounterParameter.getCode())) {
			
			if(inquiryCounterParameter.getIsSuccess().equalsIgnoreCase("true") && inquiryCounterParameter.getCode().equals("VSMP-00000000")
					&& mainProF.length() == 1 && StringUtils.isNotBlank(productNumber) && StringUtils.isNotBlank(counterName)) {
				
				inquiryCounterParameter.setRespResultCode(EResultCode.REVSMP00000000);
				inquiryCounterParameter.setNotMissing(true);
				inquiryCounterParameter.setValid(true);
				return inquiryCounterParameter;
				
			} else {
				
				if(inquiryCounterParameter.getCounterInfo() == null){
					inquiryCounterParameter.setRespResultCode(EResultCode.REret1);
				}
				if(mainProF.length() != 1) {
					AppLog.e("MainPromotionF : " + mainProF.length());
				}
				if(StringUtils.isBlank(productNumber)) {
					AppLog.e("inquiryCounterParameter.getCounterInfo().get(i).getProductNumber() is null");
				}
				if(StringUtils.isBlank(counterName)) {
					AppLog.e("inquiryCounterParameter.getCounterInfo().get(i).getCounterName() is null");
				}
				//modifySubscriberParameter.setRespResultCode(modifySubscriberParameter.getResultCode());
				inquiryCounterParameter.setRespResultCode(EResultCode.RE40301);
				return inquiryCounterParameter;
				
			}
			
		} else {
			
			if(mainProF.length() != 1) {
				AppLog.e("MainPromotionF : " + mainProF.length());
			}
			if(StringUtils.isBlank(productNumber)) {
				AppLog.e("inquiryCounterParameter.getCounterInfo().get(i).getProductNumber() is null");
			}
			if(StringUtils.isBlank(counterName)) {
				AppLog.e("inquiryCounterParameter.getCounterInfo().get(i).getCounterName() is null");
			}

			
			inquiryCounterParameter.setRespResultCode(EResultCode.RE40301);
			return inquiryCounterParameter;
		}

	}

}
