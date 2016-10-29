package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_USMP_ModifyCounter;
import ct.af.message.incoming.parser.In_USMP_ModifyCounterParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_USMP_ModifyCounter {
	
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
			
			In_USMP_ModifyCounterParser modifyCounterParser = In_USMP_ModifyCounterParser.getInstance();
			Param_USMP_ModifyCounter modifyCounterParameter = modifyCounterParser.doParser(eqxRawData);
			
			isValid = modifyCounterParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParam(modifyCounterParameter);
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYCOUNTER_RESPONSE;
				resultCode = modifyCounterParameter.getRespResultCode();
				
			} else {
				
				if(modifyCounterParameter.getRespResultCode().equals(EResultCode.REret1)) {
					
					statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYCOUNTER_ERROR;
					resultCode = modifyCounterParameter.getRespResultCode();
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_USMP_BAD_MODIFYCOUNTER_RESPONSE;
					resultCode = modifyCounterParameter.getRespResultCode();
				}
			}
			
		
		} else {
		
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYCOUNTER_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYCOUNTER_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYCOUNTER_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYCOUNTER_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_MODIFYCOUNTER_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.USMP_ModifyCounter.toString());
		afSubIns.setSubControlState(ESubState.USMP_ModifyCounter.toString());
		
		return afSubIns;
			
	}

}
