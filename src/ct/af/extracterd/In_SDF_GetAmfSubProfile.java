package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_SDF_GetAmfSubProfile;
import ct.af.message.incoming.parser.In_SDF_GetAmfSubProfileParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_SDF_GetAmfSubProfile {
	
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
		
			In_SDF_GetAmfSubProfileParser getAmfSubProfileParser = In_SDF_GetAmfSubProfileParser.getInstance();
			Param_SDF_GetAmfSubProfile getAmfSubProfileParameter = getAmfSubProfileParser.doParser(eqxRawData);
			
			isValid = getAmfSubProfileParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParam(getAmfSubProfileParameter);
				afSubIns.setSubRespGetAmfSubProfile(getAmfSubProfileParameter);
				
				statIn = EStats.INGATEWAY_RECEIVE_SDF_GET_AMFSUBPROFILE_RESPONSE;
				resultCode = getAmfSubProfileParameter.getRespResultCode();

			} else {
				
				if(getAmfSubProfileParameter.getRespResultCode().equals(EResultCode.RE50000)) {
					
					statIn = EStats.INGATEWAY_RECEIVE_SDF_GET_AMFSUBPROFILE_ERROR;
					resultCode = getAmfSubProfileParameter.getRespResultCode();
				} else {
				
					statIn = EStats.INGATEWAY_RECEIVE_SDF_BAD_GET_AMFSUBPROFILE_RESPONSE;
					resultCode = getAmfSubProfileParameter.getRespResultCode();
				}
				
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_SDF_GET_AMFSUBPROFILE_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_SDF_GET_AMFSUBPROFILE_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_SDF_GET_AMFSUBPROFILE_ERROR;
				resultCode = EResultCode.RE503;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_SDF_GET_AMFSUBPROFILE_ERROR;
				resultCode = EResultCode.RE50000;
			}else {
				statIn = EStats.INGATEWAY_RECEIVE_SDF_GET_AMFSUBPROFILE_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.SDF_GetAmfSubProfile.toString());
		afSubIns.setSubControlState(ESubState.SDF_GetAmfSubProfile.toString());

		return afSubIns;
			
	}

}
