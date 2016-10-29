package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_AMF_ModifyCounters;
import ct.af.message.incoming.parser.In_AMF_ModifyCountersParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_AMF_ModifyCounters {
	
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
			
			In_AMF_ModifyCountersParser modifyCountersParser = In_AMF_ModifyCountersParser.getInstance();
			Param_AMF_ModifyCounters modifyCountersParameter = modifyCountersParser.doParser(eqxRawData);
			
			isValid = modifyCountersParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParam(modifyCountersParameter);
				statIn = EStats.INGATEWAY_RECEIVE_AMF_MODIFYCOUNTERS_RESPONSE;
				resultCode = modifyCountersParameter.getRespResultCode();
				
			} else {
				
				statIn = EStats.INGATEWAY_RECEIVE_AMF_BAD_MODIFYCOUNTERS_RESPONSE;
				resultCode = modifyCountersParameter.getRespResultCode();
				
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_AMF_MODIFYCOUNTERS_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_AMF_MODIFYCOUNTERS_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("0")) {
				statIn = EStats.INGATEWAY_RECEIVE_AMF_MODIFYCOUNTERS_ERROR;
				resultCode = EResultCode.REEcodeNot0;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_AMF_MODIFYCOUNTERS_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_AMF_MODIFYCOUNTERS_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.AMF_ModifyCounters.toString());
		afSubIns.setSubControlState(ESubState.AMF_ModifyCounters.toString());
		
		return afSubIns;
			
	}
	
}
