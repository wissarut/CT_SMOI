package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_USMP_ModifySubscriber;
import ct.af.message.incoming.parser.In_USMP_ModifySubscriberParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_USMP_ModifySubscriber {
	
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
			
			In_USMP_ModifySubscriberParser modifySubscriberParser = In_USMP_ModifySubscriberParser.getInstance();
			Param_USMP_ModifySubscriber modifySubscriberParameter = modifySubscriberParser.doParser(eqxRawData);
			
			isValid = modifySubscriberParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParam(modifySubscriberParameter);
				afSubIns.setSubResponseParamModifySubscriber(modifySubscriberParameter);
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYSUBSCRIBER_RESPONSE;
				resultCode = modifySubscriberParameter.getRespResultCode();
				
			} else {
				
				if(modifySubscriberParameter.getRespResultCode().equals(EResultCode.REret1)) {
					
					statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYSUBSCRIBER_ERROR;
					resultCode = modifySubscriberParameter.getRespResultCode();
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_USMP_BAD_MODIFYSUBSCRIBER_RESPONSE;
					resultCode = modifySubscriberParameter.getRespResultCode();
				}
			}
			
		
		} else {
		
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYSUBSCRIBER_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYSUBSCRIBER_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYSUBSCRIBER_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYSUBSCRIBER_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYSUBSCRIBER_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.USMP_ModifySubscriber.toString());
		afSubIns.setSubControlState(ESubState.USMP_ModifySubscriber.toString());
		
		return afSubIns;
			
	}
	
}
