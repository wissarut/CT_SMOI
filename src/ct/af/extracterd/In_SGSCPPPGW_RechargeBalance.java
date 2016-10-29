package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_SGSCPPPGW_RechargeBalance;
import ct.af.message.incoming.parser.In_SGSCPPPGW_RechargeBalanceParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_SGSCPPPGW_RechargeBalance {
	
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
			
			In_SGSCPPPGW_RechargeBalanceParser ppgwRechargeBalanceParser = In_SGSCPPPGW_RechargeBalanceParser.getInstance();
			Param_SGSCPPPGW_RechargeBalance ppgwRechargeBalanceParameter = ppgwRechargeBalanceParser.doParser(eqxRawData);
			
			isValid = ppgwRechargeBalanceParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParam(ppgwRechargeBalanceParameter);
				statIn = EStats.INGATEWAY_RECEIVE_SGSCP_PPGW_RECHARGEBALANCE_RESPONSE;
				resultCode = EResultCode.RE2001;
				
			} else {
				
				statIn = EStats.INGATEWAY_RECEIVE_SGSCP_PPGW_BAD_RECHARGEBALANCE_RESPONSE;
				resultCode = EResultCode.RE322;
				
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_SGSCP_PPGW_RECHARGEBALANCE_REQUEST_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_SGSCP_PPGW_RECHARGEBALANCE_REQUEST_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_SGSCP_PPGW_RECHARGEBALANCE_REQUEST_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_SGSCP_PPGW_RECHARGEBALANCE_REQUEST_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_SGSCP_PPGW_RECHARGEBALANCE_REQUEST_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
			isRetNormal = false;
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.SGSCPPPGW_RechargeBalance.toString());
		afSubIns.setSubControlState(ESubState.SGSCPPPGW_RechargeBalance.toString());
		
		return afSubIns;
			
	}
	
}
