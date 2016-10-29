package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BOS_SubscriberAccountEnquiry;
import ct.af.message.incoming.parser.bos.DiameterCCA;
import ec02.af.data.EquinoxRawData;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;

public class In_BOS_SubscriberAccountEnquiryParser {
	
	private static In_BOS_SubscriberAccountEnquiryParser parser = null;
	
	protected  In_BOS_SubscriberAccountEnquiryParser() {
	  
	}
	public static In_BOS_SubscriberAccountEnquiryParser getInstance() {
		if(parser == null) {
			parser = new In_BOS_SubscriberAccountEnquiryParser();
		}
		return parser;
	}
	
	public Param_BOS_SubscriberAccountEnquiry doParser(EquinoxRawData eqxRawData) {
		
		Param_BOS_SubscriberAccountEnquiry subscriberAccountEnquiryParameter = new Param_BOS_SubscriberAccountEnquiry();

		DiameterCCA dcca = new DiameterCCA();
        DiameterCreditControlAnswer cca = dcca.createDiameterCreditControlAnswer(eqxRawData.getRawDataMessage());
        
        subscriberAccountEnquiryParameter.setDiamAns(cca);
      
		if(!StringUtils.isBlank(subscriberAccountEnquiryParameter.getDiamAns().getResultCode())) {
			
			if(subscriberAccountEnquiryParameter.getDiamAns().getResultCode().equals("2001")) {
				
				subscriberAccountEnquiryParameter.setRespResultCode(EResultCode.RE2001);
				subscriberAccountEnquiryParameter.setNotMissing(true);
				subscriberAccountEnquiryParameter.setValid(true);
				return subscriberAccountEnquiryParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return subscriberAccountEnquiryParameter;
				
			}
			
		} else {
			
			subscriberAccountEnquiryParameter.setRespResultCode(EResultCode.RE40301);
			return subscriberAccountEnquiryParameter;
			
		}
        
	}
	
}