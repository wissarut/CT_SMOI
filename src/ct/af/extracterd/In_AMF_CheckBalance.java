package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_AMF_CheckBalance;
import ct.af.message.incoming.parser.In_AMF_CheckBalanceParser;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_AMF_CheckBalance {
	
	public AFSubInstance extractRawData(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns,EquinoxRawData eqxRawData) {
		
		String rawDataRet = eqxRawData.getRet();
		String rawDataECode = eqxRawData.getRawDataAttribute("ecode");
		
		EResultCode resultCode = EResultCode.RE50000;
		EStats statIn = null;
		
		boolean isValid = false;
		boolean isRetNormal = true;

		if(eqxRawData.getRawDataMessage() != null
				&& !rawDataECode.equals("503")
				&& rawDataRet.equals(ERet.RET0.getRet())
				&& rawDataECode.equals("0")) {
			
			In_AMF_CheckBalanceParser checkBalanceParser = In_AMF_CheckBalanceParser.getInstance();
			Param_AMF_CheckBalance checkBalanceParameter = checkBalanceParser.doParser(eqxRawData);
			
			isValid = checkBalanceParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParam(checkBalanceParameter);
				afSubIns.setSubResponseParamCheckBalance(checkBalanceParameter);
				
				if(StringUtils.isNotBlank(afSubIns.getSubHostInsNo())) {
					ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).setSubResponseParamCheckBalance(checkBalanceParameter);
				}
				
				statIn = EStats.INGATEWAY_RECEIVE_AMF_CHECKBALANCE_RESPONSE;
				resultCode = checkBalanceParameter.getRespResultCode();

			} else {
				
				statIn = EStats.INGATEWAY_RECEIVE_AMF_BAD_CHECKBALANCE_RESPONSE;
				resultCode = checkBalanceParameter.getRespResultCode();
				
			}
			
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				afSubIns.setSubRecieveRet("4");
				statIn = EStats.INGATEWAY_RECEIVE_AMF_CHECKBALANCE_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_AMF_CHECKBALANCE_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_AMF_CHECKBALANCE_ERROR;
				resultCode = EResultCode.RE503;
			} else if(!rawDataECode.equals("0")) {
				statIn = EStats.INGATEWAY_RECEIVE_AMF_CHECKBALANCE_ERROR;
				resultCode = EResultCode.REEcodeNot0;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_AMF_CHECKBALANCE_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.AMF_CheckBalance.toString());
		afSubIns.setSubControlState(ESubState.AMF_CheckBalance.toString());

		return afSubIns;
			
	}
	
}
