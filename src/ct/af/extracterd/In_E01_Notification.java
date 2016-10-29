package ct.af.extracterd;

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

public class In_E01_Notification {
	
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
				if(StringUtils.isNotBlank(data[0]) && StringUtils.isNotBlank(data[1]) && StringUtils.isNotBlank(data[2])
						&& StringUtils.isNotBlank(data[3]) && StringUtils.isNotBlank(data[4])) {
					
	
					if(data.length == 5 && data[1].matches("[0-9]+") && data[1].length() == 5) {
						
						isValid = true;
					} else {
						
						if(data.length != 5) {
							AppLog.e("Incomplete Data");
						}
						if(!data[1].matches("[0-9]+") || data[1].length() != 5) {
							AppLog.e("menuId is wrong format");
						}
					}
				}
			}
			
			if(isValid) {

				afSubIns.setE01Data(jsonObject.get("data").toString());
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NOTIFICATION_RESPONSE_SUCCESS;
				resultCode = EResultCode.RE20000;
				
			} else {
							
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NOTIFICATION_RESPONSE_ERROR;
				resultCode = EResultCode.RE50000;
				
			}
	
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NOTIFICATION_REQUEST_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NOTIFICATION_RESPONSE_ERROR;
//				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NOTIFICATION_REQUEST_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET3.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NOTIFICATION_RESPONSE_ERROR;
//				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NOTIFICATION_REQUEST_ABORT;
				resultCode = EResultCode.REret3;
				isRetNormal = false;
			}  else if(rawDataECode.equals("32")) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NOTIFICATION_RESPONSE_INCOMPLETE_DATA;
				resultCode = EResultCode.RE32;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NOTIFICATION_RESPONSE_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
			isRetNormal = false;
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.E01_Notification.toString());
		afSubIns.setSubControlState(ESubState.E01_Notification.toString());
		
		return afSubIns;
			
	}
	
}
