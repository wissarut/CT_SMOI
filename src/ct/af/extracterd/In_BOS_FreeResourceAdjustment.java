package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_BOS_FreeResourceAdjustment;
import ct.af.message.incoming.parser.In_BOS_FreeResourceAdjustmentParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_BOS_FreeResourceAdjustment {
	
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
				&& rawDataRet.equals(ERet.RET0.getRet())) {
			
			In_BOS_FreeResourceAdjustmentParser freeResourceAdjustmentParser = In_BOS_FreeResourceAdjustmentParser.getInstance();
			Param_BOS_FreeResourceAdjustment freeResourceAdjustmentParameter = freeResourceAdjustmentParser.doParser(eqxRawData);
			
			isValid = freeResourceAdjustmentParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParam(freeResourceAdjustmentParameter);
				statIn = EStats.INGATEWAY_RECEIVE_BOS_BAD_FREERESOURCEADJUSTMENT_RESPONSE;
				resultCode = freeResourceAdjustmentParameter.getRespResultCode();
				
			} else {
				
				statIn = EStats.INGATEWAY_RECEIVE_BOS_BAD_FREERESOURCEADJUSTMENT_RESPONSE;
				resultCode = freeResourceAdjustmentParameter.getRespResultCode();
				
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_BOS_FREERESOURCEADJUSTMENT_REQUEST_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_BOS_FREERESOURCEADJUSTMENT_REQUEST_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_BOS_FREERESOURCEADJUSTMENT_REQUEST_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_BOS_FREERESOURCEADJUSTMENT_REQUEST_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_BOS_FREERESOURCEADJUSTMENT_REQUEST_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.BOS_FreeResourceAdjustment.toString());
		afSubIns.setSubControlState(ESubState.BOS_FreeResourceAdjustment.toString());
		
		return afSubIns;
			
	}
	
}
