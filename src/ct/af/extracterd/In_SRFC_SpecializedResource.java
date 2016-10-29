package ct.af.extracterd;

import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_SRFC_SpecializedResource;
import ct.af.message.incoming.parser.In_SRFC_SpecializedResourceParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_SRFC_SpecializedResource {
	
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
			
			In_SRFC_SpecializedResourceParser specializedResourceParser = In_SRFC_SpecializedResourceParser.getInstance();
			Param_SRFC_SpecializedResource specializedResourceParameter = specializedResourceParser.doParser(eqxRawData);
			
			isValid = specializedResourceParameter.isValid();
			
			if(isValid) {
				
				afSubIns.setSubResponseParam(specializedResourceParameter);
				statIn = EStats.INGATEWAY_RECEIVE_SRFC_SPECIALIZEDRESOURCE_RESPONSE;
				resultCode = specializedResourceParameter.getRespResultCode();
				
			} else {
				
				if(specializedResourceParameter.getRespResultCode().equals(EResultCode.RE50000)) {
					
					statIn = EStats.INGATEWAY_RECEIVE_SRFC_SPECIALIZEDRESOURCE_ERROR;
					resultCode = specializedResourceParameter.getRespResultCode();
				
				} else {
				
					statIn = EStats.INGATEWAY_RECEIVE_SRFC_BAD_SPECIALIZEDRESOURCE_RESPONSE;
					resultCode = specializedResourceParameter.getRespResultCode();
				
				}
			}
			
		
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_SRFC_SPECIALIZEDRESOURCE_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_SRFC_SPECIALIZEDRESOURCE_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(!rawDataECode.equals("200")) {
				statIn = EStats.INGATEWAY_RECEIVE_SRFC_SPECIALIZEDRESOURCE_ERROR;
				resultCode = EResultCode.RE50000;
			} else if(rawDataECode.equals("503")) {
				statIn = EStats.INGATEWAY_RECEIVE_SRFC_SPECIALIZEDRESOURCE_ERROR;
				resultCode = EResultCode.RE50000;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_SRFC_SPECIALIZEDRESOURCE_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.SRFC_SpecializedResource.toString());
		afSubIns.setSubControlState(ESubState.SRFC_SpecializedResource.toString());
		
		return afSubIns;
			
	}
	
}
