package ct.af.extracterd;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_E01_CosToPromotionName {
	
	public AFSubInstance extractRawData(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns,EquinoxRawData eqxRawData) {
		
		String rawDataRet = eqxRawData.getRet();
		String rawDataECode = eqxRawData.getRawDataAttribute("ecode");
		
		EResultCode resultCode = EResultCode.RE50000;
		EStats statIn = null;
		
		boolean isValid = false;
		boolean isRetNormal = true;
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		
		try {
			
			jsonObject = (JSONObject)  parser.parse(eqxRawData.getRawDataMessage());
			
			AppLog.d("Data : "+jsonObject.get("data"));
			
			
		} catch (ParseException e) {
			AppLog.e("[ERROR] parse json error");
			rawDataECode = "32";
		}

		if(eqxRawData.getRawDataMessage() != null
				&& !eqxRawData.getRawDataMessage().equals("")
				&& !rawDataECode.equals("32")
				&& rawDataRet.equals(ERet.RET0.getRet())) {
			
			if(jsonObject.get("data").toString() != null) {
				
				String[] data = jsonObject.get("data").toString().split("\\|" ,-1);
				
				if(StringUtils.isNotBlank(data[0]) && StringUtils.isNotBlank(data[1])) {
					if(data.length == 2) {
						
						isValid = true;
						
						String key1 = (String) ((JSONArray) jsonObject.get("objKey")).get(1);
						
						String stateData = data[0];
						String promoNameData = data[1];
						
						if(key1.equals(afSubIns.getOldCos())) {
							afSubIns.setOldPromoName(promoNameData);
							afSubIns.setNewPromoName(promoNameData);
						}
						
						if(key1.equals(afSubIns.getNewCos())) {
							afSubIns.setNewPromoName(promoNameData);
						}
						
						if(stateData.equals("1")) {
							afSubIns.setStatePromoName("1");
						}
						
					} else {
						
						if(data.length != 2) {
							AppLog.e("Incomplete Data");
						}
					}
				} else {
					
					isValid = false;
				}
			}
		
			if(isValid) {

				AppLog.d("Old : "+afSubIns.getOldCos());
				AppLog.d("New : "+afSubIns.getNewCos());

				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_COSTOPROMOTIONNAME_RESPONSE_SUCCESS;
				resultCode = EResultCode.RE20000;
				
			} else {
							
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_COSTOPROMOTIONNAME_RESPONSE_ERROR;
				resultCode = EResultCode.RE50000;
				
			}

		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_COSTOPROMOTIONNAME_REQUEST_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_COSTOPROMOTIONNAME_REQUEST_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET3.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_COSTOPROMOTIONNAME_REQUEST_ABORT;
				resultCode = EResultCode.REret3;
				isRetNormal = false;
			}  else if(rawDataECode.equals("32")) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_COSTOPROMOTIONNAME_RESPONSE_INCOMPLETE_DATA;
				resultCode = EResultCode.RE32;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_COSTOPROMOTIONNAME_RESPONSE_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
		
		if(StringUtils.isNotBlank(afSubIns.getStatePromoName())) {
			if(afSubIns.getStatePromoName().equals("0")) {
				isValid = false;
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_COSTOPROMOTIONNAME_RESPONSE_ERROR;
				resultCode = EResultCode.RE50000;
			}
		}
			
		if(afSubIns.getSubInvoke().size() == 0 && afSubIns.getSubIsValid() == false && isValid == true) { 
			
			afSubIns.setSubIsValid(false);
		} else {
			
			afSubIns.setSubIsValid(isValid);
		}
		
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		
		if(afSubIns.getSubInvoke().size() == 0) {
			afSubIns.setSubCurrentState(ESubState.E01_CosToPromotionName.toString());
			afSubIns.setSubControlState(ESubState.E01_CosToPromotionName.toString());
		} else {
			afSubIns.setSubCurrentState(ESubState.WAIT.toString());
			afSubIns.setSubControlState(ESubState.WAIT.toString());
		}
		
		
		return afSubIns;
			
	}
	
}
