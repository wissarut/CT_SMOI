package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_SGSCPSMOI_ForwardCommand {
	
	public AFSubInstance extractRawData(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns,EquinoxRawData eqxRawData) {
		
		String rawDataRet = eqxRawData.getRet();
		String rawDataECode = eqxRawData.getRawDataAttribute("ecode");
		
		EResultCode resultCode = EResultCode.RE50000;
		EStats statIn = null;
		
		boolean isValid = false;
		boolean isRetNormal = true;
		
		String subStat = "";
		
		if(!rawDataECode.equals("503")
				&& rawDataRet.equals(ERet.RET0.getRet())) {
			
				afSubIns.setSubDataForward(eqxRawData.getRawDataMessage());
				
				resultCode = EResultCode.RE000;
				isValid = true;
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				resultCode = EResultCode.REret4;
				isRetNormal = false;
				subStat = "_TIMEOUT";
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				resultCode = EResultCode.REret2;
				isRetNormal = false;
				subStat = "_REJECT";
			} else if(!rawDataECode.equals("200")) {
				resultCode = EResultCode.RE50000;
				subStat = "_ERROR";
			} else if(rawDataECode.equals("503")) {
				resultCode = EResultCode.RE50000;
				subStat = "_ERROR";
			} else {
				resultCode = EResultCode.RE50000;
				subStat = "_ERROR";
			}
			
		}
		
		//Stat
		String cmd = afSubIns.getSubInitCmd().toUpperCase();
	
		try {
			if(subStat.equals("")) {
				statIn = EStats.valueOf("INGATEWAY_RECEIVE_SMOI_"+cmd+"_RESPONSE");
			} else {
				statIn = EStats.valueOf("INGATEWAY_RECEIVE_SMOI_"+cmd+"_REQUEST"+subStat);
			}
		} catch(Exception ex) {
			if(subStat.equals("")) {
				statIn = EStats.INGATEWAY_RECEIVE_SMOI_FORWARDCOMMAND_RESPONSE;
			} else {
				if(subStat.equals("_TIMEOUT")) {
					statIn	= EStats.INGATEWAY_RECEIVE_SMOI_FORWARDCOMMAND_REQUEST_TIMEOUT;
				} else if(subStat.equals("_REJECT")) {
					statIn	= EStats.INGATEWAY_RECEIVE_SMOI_FORWARDCOMMAND_REQUEST_REJECT;
				} else if(subStat.equals("_ERROR")) {
					statIn	= EStats.INGATEWAY_RECEIVE_SMOI_FORWARDCOMMAND_REQUEST_ERROR;
				}
			}
		}
	
		//Stat
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.End_ForwardCommand.toString());
		afSubIns.setSubCurrentState(ESubState.SGSCPSMOI_ForwardCommand.toString());
		afSubIns.setSubControlState(ESubState.SGSCPSMOI_ForwardCommand.toString());
		
		return afSubIns;
			
	}
	
}