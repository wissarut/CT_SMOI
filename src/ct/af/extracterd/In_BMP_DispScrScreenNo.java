package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_BMP_DispScrScreenNo;
import ct.af.message.incoming.parser.In_BMP_DispScrScreenNoParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_BMP_DispScrScreenNo {
	
	public AFSubInstance extractRawData(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns,EquinoxRawData eqxRawData) {
		
		String rawDataRet = eqxRawData.getRet();
		String rawDataECode = eqxRawData.getRawDataAttribute("ecode");
		
		EResultCode resultCode = EResultCode.RE50000;
		EStats statIn = null;
		
		boolean isValid = false;
		boolean isRetNormal = true;

		if(eqxRawData.getRawDataMessage() != null
				&& !rawDataECode.equals("503")
				&& rawDataRet.equals(ERet.RET0.getRet())) {
			
			In_BMP_DispScrScreenNoParser dispScrScreenNoParser = In_BMP_DispScrScreenNoParser.getInstance();
			Param_BMP_DispScrScreenNo dispScrScreenNoParameter = dispScrScreenNoParser.doParser(eqxRawData);
			
			isValid = dispScrScreenNoParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParam(dispScrScreenNoParameter);
				statIn = EStats.INGATEWAY_RECEIVE_BMP_DISPSCRSCREENNO_RESPONSE;
				resultCode = dispScrScreenNoParameter.getRespResultCode();
				
			} else {
				
				statIn = EStats.INGATEWAY_RECEIVE_BMP_BAD_DISPSCRSCREENNO_RESPONSE;
				resultCode = dispScrScreenNoParameter.getRespResultCode();
				
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_BMP_DISPSCRSCREENNO_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_BMP_DISPSCRSCREENNO_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_BMP_DISPSCRSCREENNO_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_BMP_DISPSCRSCREENNO_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_BMP_DISPSCRSCREENNO_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.BMP_DispScrScreenNo.toString());
		afSubIns.setSubControlState(ESubState.BMP_DispScrScreenNo.toString());

		return afSubIns;
			
	}
	
}