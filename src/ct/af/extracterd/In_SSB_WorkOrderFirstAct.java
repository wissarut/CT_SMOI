package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_SSB_WorkOrderFirstAct;
import ct.af.message.incoming.parser.In_SSB_WorkOrderFirstActParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_SSB_WorkOrderFirstAct {
	
	public AFSubInstance extractRawData(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns,EquinoxRawData eqxRawData) {
		
		String rawDataRet = eqxRawData.getRet();
		String rawDataECode = eqxRawData.getRawDataAttribute("ecode");
		
		EResultCode resultCode = EResultCode.RE50000;
		EStats statIn = null;
		
		boolean isValid = false;
		boolean isRetNormal = true;
				
		if(eqxRawData.getRawDataMessage() != null
				&& !rawDataECode.equals("503")
				&& rawDataECode.equals("200")
				&& rawDataRet.equals(ERet.RET0.getRet())) {
			
			In_SSB_WorkOrderFirstActParser workOrderFirstActParser = In_SSB_WorkOrderFirstActParser.getInstance();
			Param_SSB_WorkOrderFirstAct workOrderFirstActParameter = workOrderFirstActParser.doParser(eqxRawData);
			
			isValid = workOrderFirstActParameter.isValid();
			
			if(isValid) {

				afSubIns.setSubResponseParam(workOrderFirstActParameter);
				statIn = EStats.INGATEWAY_RECEIVE_SSB_WORKORDERFIRSTACT_RESPONSE;
				resultCode = workOrderFirstActParameter.getRespResultCode();
				
			} else {
				
				statIn = EStats.INGATEWAY_RECEIVE_SSB_BAD_WORKORDERFIRSTACT_RESPONSE;
				resultCode = workOrderFirstActParameter.getRespResultCode();
				
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_SSB_WORKORDERFIRSTACT_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_SSB_WORKORDERFIRSTACT_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_SSB_WORKORDERFIRSTACT_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_SSB_WORKORDERFIRSTACT_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_SSB_WORKORDERFIRSTACT_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.SSB_WorkOrderFirstAct.toString());
		afSubIns.setSubControlState(ESubState.SSB_WorkOrderFirstAct.toString());
		
		return afSubIns;
			
	}
	
}
