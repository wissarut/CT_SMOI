package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_USMP_InquirySubscriberInfo;
import ct.af.message.incoming.parser.In_USMP_InquirySubscriberInfoParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_USMP_InquirySubscriberInfo {
	
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
				&& rawDataECode.equals("200")
				&& rawDataRet.equals(ERet.RET0.getRet())) {
			
			In_USMP_InquirySubscriberInfoParser inquirySubscriberInfoParser = In_USMP_InquirySubscriberInfoParser.getInstance();
			Param_USMP_InquirySubscriberInfo inquirySubscriberInfoParameter = inquirySubscriberInfoParser.doParser(eqxRawData);
			
			isValid = inquirySubscriberInfoParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParamInquirySubscriberInfo(inquirySubscriberInfoParameter);
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYSUBSCRIBERINFO_RESPONSE;
				resultCode = inquirySubscriberInfoParameter.getRespResultCode();
				
			} else {
				
				if(inquirySubscriberInfoParameter.getRespResultCode().equals(EResultCode.REret1)) {
					
					statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYSUBSCRIBERINFO_ERROR;
					resultCode = inquirySubscriberInfoParameter.getRespResultCode();
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_USMP_BAD_INQUIRYSUBSCRIBERINFO_RESPONSE;
					resultCode = inquirySubscriberInfoParameter.getRespResultCode();
				}
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYSUBSCRIBERINFO_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYSUBSCRIBERINFO_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYSUBSCRIBERINFO_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYSUBSCRIBERINFO_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYSUBSCRIBERINFO_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.USMP_InquirySubscriberInfo.toString());
		afSubIns.setSubControlState(ESubState.USMP_InquirySubscriberInfo.toString());
		
		return afSubIns;
			
	}
	
}
