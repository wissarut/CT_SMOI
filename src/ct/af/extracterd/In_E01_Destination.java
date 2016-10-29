package ct.af.extracterd;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parser.json.JSONParser;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_E01_Destination {
	
	public AFSubInstance extractRawData(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns,EquinoxRawData eqxRawData) {
		
		String rawDataRet = eqxRawData.getRet();
		String rawDataECode = eqxRawData.getRawDataAttribute("ecode");
		
		EResultCode resultCode = EResultCode.RE50000;
		EStats statIn = null;
		
		boolean isValid = false;
		boolean isRetNormal = true;
		
		JSONParser parser = JSONParser.getInstance();
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
			statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_DESTINATION_RESPONSE_SUCCESS;
			resultCode = EResultCode.RE20000;
				
		} else {
			
			if(rawDataRet.equals(ERet.RET4.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_DESTINATION_REQUEST_TIMEOUT;
				resultCode = EResultCode.REret4;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET2.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_DESTINATION_RESPONSE_ERROR;
//				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_DESTINATION_REQUEST_REJECT;
				resultCode = EResultCode.REret2;
				isRetNormal = false;
			} else if(rawDataRet.equals(ERet.RET3.getRet())) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_DESTINATION_RESPONSE_ERROR;
//				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_DESTINATION_REQUEST_ABORT;
				resultCode = EResultCode.REret3;
				isRetNormal = false;
			}  else if(rawDataECode.equals("32")) {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_DESTINATION_RESPONSE_INCOMPLETE_DATA;
				resultCode = EResultCode.RE32;
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_E01_QUERY_DESTINATION_RESPONSE_ERROR;
				resultCode = EResultCode.RE50000;
			}
			
		}
			
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		afSubIns.setSubResultCode(resultCode);
		
		afSubIns.setSubNextState(ESubState.Unknown.toString());
		afSubIns.setSubCurrentState(ESubState.E01_Destination.toString());
		afSubIns.setSubControlState(ESubState.E01_Destination.toString());
		
		return afSubIns;
			
	}
	
}
