package ct.af.extracterd;


import com.google.gson.JsonParseException;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class In_E01_NewClassOfService {
	
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
			
			isValid = true;
			
			afSubIns.setE01Data(jsonObject.get("data").toString());
			
			try {
				
				String data = (String) jsonObject.get("data");
				
				JSONObject dataE01Obj = (JSONObject)  parser.parse(data);
				
				JSONObject listCos = (JSONObject) dataE01Obj.get("listClassOfService");
				
				

				JSONArray jsonArr = (JSONArray) listCos.get("data");
				
				for (int i = 0; i < jsonArr.size(); i++) {
					
					JSONObject dataObj = (JSONObject) jsonArr.get(i);
					
					String startTime = "";
					String endTime = "";
					String classOfService = "";
					String rewardId = "";
					String refillStopTime = "";
					String refillStopTimeMonth = "";
					
					if(dataObj.get("starttime") != null) {
						startTime = dataObj.get("starttime").toString();
					}
					if(dataObj.get("endtime") != null) {
						endTime = dataObj.get("endtime").toString();
					}
					if(dataObj.get("classOfService") != null) {
						classOfService = dataObj.get("classOfService").toString();
					}
					if(dataObj.get("rewardId") != null) {
						rewardId = dataObj.get("rewardId").toString();
					}
					if(dataObj.get("refillStopTime") != null) {
						refillStopTime = dataObj.get("refillStopTime").toString();
					}
					if(dataObj.get("refillStopTimeMonth") != null) {
						refillStopTimeMonth = dataObj.get("refillStopTimeMonth").toString();
					}
					
					
					
					
					if(StringUtils.isBlank(startTime)
							|| StringUtils.isBlank(endTime)
							|| StringUtils.isBlank(rewardId)
							|| StringUtils.isBlank(refillStopTime)
							|| StringUtils.isBlank(classOfService)
							|| StringUtils.isBlank(refillStopTimeMonth)) {
						
						statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NEWCLASSOFSERVICE_RESPONSE_INCOMPLETE_DATA;
						resultCode = EResultCode.RE50000;
						break;
						
					} else {
						
						statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NEWCLASSOFSERVICE_RESPONSE_SUCCESS;
						resultCode = EResultCode.RE20000;
					}
				}

			} catch(JsonParseException e) {
				
				AppLog.e("Cannot Parse Json."+e);
			} catch (ParseException e) {
				AppLog.e("Cannot Parse Json."+e);
			}
		

		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NEWCLASSOFSERVICE_REQUEST_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NEWCLASSOFSERVICE_RESPONSE_ERROR;
//				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NEWCLASSOFSERVICE_REQUEST_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET3.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NEWCLASSOFSERVICE_RESPONSE_ERROR;
//				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NEWCLASSOFSERVICE_REQUEST_ABORT;
				resultCode = EResultCode.REret3;
				isRetNormal = false;
			}  else if(rawDataECode.equals("32")) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NEWCLASSOFSERVICE_RESPONSE_INCOMPLETE_DATA;
				resultCode = EResultCode.RE32;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_NEWCLASSOFSERVICE_RESPONSE_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.E01_NewClassOfService.toString());
		afSubIns.setSubControlState(ESubState.E01_NewClassOfService.toString());
		
		return afSubIns;
			
	}
	
}
