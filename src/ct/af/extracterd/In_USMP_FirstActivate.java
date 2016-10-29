package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_USMP_FirstActivate;
import ct.af.message.incoming.parser.In_USMP_FirstActivateParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
		   
public class In_USMP_FirstActivate {
	
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
			
			In_USMP_FirstActivateParser firstActivateParser = In_USMP_FirstActivateParser.getInstance();
			Param_USMP_FirstActivate firstActivateParameter = firstActivateParser.doParser(eqxRawData);
			
			isValid = firstActivateParameter.isValid();
		
			if(isValid) {
				
				afSubIns.setSubResponseParam(firstActivateParameter);
				afSubIns.setSubResponseParamFirstAct(firstActivateParameter);
				statIn = EStats.INGATEWAY_RECEIVE_USMP_FIRSTACTIVATE_RESPONSE;
				resultCode = firstActivateParameter.getRespResultCode();
				
			} else {
				
				if(firstActivateParameter.getRespResultCode().equals(EResultCode.REret1)) {
					
					statIn = EStats.INGATEWAY_RECEIVE_USMP_FIRSTACTIVATE_ERROR;
					resultCode = firstActivateParameter.getRespResultCode();
				} else {
				
					statIn = EStats.INGATEWAY_RECEIVE_USMP_BAD_FIRSTACTIVATE_RESPONSE;
					resultCode = firstActivateParameter.getRespResultCode();
				}
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_FIRSTACTIVATE_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_FIRSTACTIVATE_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_FIRSTACTIVATE_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_FIRSTACTIVATE_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_USMP_FIRSTACTIVATE_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.USMP_FirstActivate.toString());
		afSubIns.setSubControlState(ESubState.USMP_FirstActivate.toString());
		
		return afSubIns;
			
	}
	
}
