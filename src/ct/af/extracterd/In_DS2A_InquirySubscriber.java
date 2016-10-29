package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_DS2A_InquirySubscriber;
import ct.af.message.incoming.parser.In_DS2A_InquirySubscriberParser;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
			
public class In_DS2A_InquirySubscriber {
	
	public AFSubInstance extractRawData(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns,EquinoxRawData eqxRawData) {
		
		String rawDataRet = eqxRawData.getRet();
		String rawDataECode = eqxRawData.getRawDataAttribute("ecode");
		
		EResultCode resultCode = EResultCode.RE50000;
		EStats statIn = null;
		
		boolean isValid = false;
		boolean isRetNormal = true;
		
		if(eqxRawData.getRawDataMessage() != null
				&& !eqxRawData.getRawDataMessage().equals("")
				&& !rawDataECode.equals("503")
				&& rawDataRet.equals(ERet.RET0.getRet())
				&& rawDataECode.equals("0")) {
			
			In_DS2A_InquirySubscriberParser inquirySubscriberParser = In_DS2A_InquirySubscriberParser.getInstance();
			Param_DS2A_InquirySubscriber inquirySubscriberParameter = inquirySubscriberParser.doParser(eqxRawData);
			
			isValid = inquirySubscriberParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setInquirySubParam(inquirySubscriberParameter);
				
				if(StringUtils.isNotBlank(afSubIns.getSubHostInsNo())) {
					ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).setInquirySubParam(inquirySubscriberParameter);
				}
				
				statIn = EStats.INGATEWAY_RECEIVE_DS2A_INQUIRYSUBSCRIBER_RESPONSE;
				resultCode = inquirySubscriberParameter.getRespResultCode();
				
			} else {
				
				if(inquirySubscriberParameter.getRespResultCode().equals(EResultCode.RE221)) {
					
					statIn = EStats.INGATEWAY_RECEIVE_DS2A_INQUIRYSUBSCRIBER_UNKNOWN_MSISDN;
					resultCode = inquirySubscriberParameter.getRespResultCode();
				
				} else {
				
					statIn = EStats.INGATEWAY_RECEIVE_DS2A_BAD_INQUIRYSUBSCRIBER_RESPONSE;
					resultCode = inquirySubscriberParameter.getRespResultCode();
				}
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				afSubIns.setSubRecieveRet("4");
				statIn = EStats.INGATEWAY_RECEIVE_DS2A_INQUIRYSUBSCRIBER_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_DS2A_INQUIRYSUBSCRIBER_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_DS2A_INQUIRYSUBSCRIBER_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(!rawDataECode.equals("0")) {
				statIn = EStats.INGATEWAY_RECEIVE_DS2A_INQUIRYSUBSCRIBER_ERROR;
				resultCode = EResultCode.REEcodeNot0;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_DS2A_INQUIRYSUBSCRIBER_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.DS2A_InquirySubscriber.toString());
		afSubIns.setSubControlState(ESubState.DS2A_InquirySubscriber.toString());
		
		return afSubIns;
			
	}
	
}
