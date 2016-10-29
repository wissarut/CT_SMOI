package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_BOS_AccountRecharge;
import ct.af.message.incoming.parser.In_BOS_AccountRechargeParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_BOS_AccountRecharge {
	
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
			
			In_BOS_AccountRechargeParser accountRechargeCCRParser = In_BOS_AccountRechargeParser.getInstance();
			Param_BOS_AccountRecharge accountRechargeCCRParameter = accountRechargeCCRParser.doParser(eqxRawData);
			
			isValid = accountRechargeCCRParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParam(accountRechargeCCRParameter);
				statIn = EStats.INGATEWAY_RECEIVE_BOS_ACCOUNTRECHARGE_RESPONSE_SUCCESS;
				resultCode = accountRechargeCCRParameter.getRespResultCode();
				
			} else {
				
				statIn = EStats.INGATEWAY_RECEIVE_BOS_BAD_ACCOUNTRECHARGE_RESPONSE;
				resultCode = accountRechargeCCRParameter.getRespResultCode();
				
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_BOS_ACCOUNTRECHARGE_REQUEST_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_BOS_ACCOUNTRECHARGE_REQUEST_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_BOS_ACCOUNTRECHARGE_REQUEST_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_BOS_ACCOUNTRECHARGE_REQUEST_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_BOS_ACCOUNTRECHARGE_REQUEST_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.BOS_AccountRecharge.toString());
		afSubIns.setSubControlState(ESubState.BOS_AccountRecharge.toString());
		
		return afSubIns;
			
	}
	
}
