package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_USMP_InquiryCounter;
import ct.af.message.incoming.parser.In_USMP_InquiryCounterParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_USMP_InquiryCounter {
	
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
			
			In_USMP_InquiryCounterParser inquiryCounterParser = In_USMP_InquiryCounterParser.getInstance();
			Param_USMP_InquiryCounter inquiryCounterParameter = inquiryCounterParser.doParser(eqxRawData);
			
			isValid = inquiryCounterParameter.isValid();
		
			if(isValid) {
				
				afSubIns.setSubResponseParam(inquiryCounterParameter);
				afSubIns.setSubResponseParamInquiryCounter(inquiryCounterParameter);
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYCOUNTER_RESPONSE;
				resultCode = inquiryCounterParameter.getRespResultCode();
				
			} else {
				
				if(inquiryCounterParameter.getRespResultCode().equals(EResultCode.REret1)) {
					
					statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYCOUNTER_ERROR;
					resultCode = inquiryCounterParameter.getRespResultCode();
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_USMP_BAD_INQUIRYCOUNTER_RESPONSE;
					resultCode = inquiryCounterParameter.getRespResultCode();
				}
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				afSubIns.setSubRecieveRet("4");
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYCOUNTER_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYCOUNTER_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYCOUNTER_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYCOUNTER_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_INQUIRYCOUNTER_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.USMP_InquiryCounter.toString());
		afSubIns.setSubControlState(ESubState.USMP_InquiryCounter.toString());
		
		return afSubIns;
			
	}

}
