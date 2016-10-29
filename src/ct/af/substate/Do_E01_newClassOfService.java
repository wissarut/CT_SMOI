package ct.af.substate;

import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ct.af.enums.EResultCode;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.utils.AppLog;

public class Do_E01_newClassOfService {
	
	public void doBusinessLogic(AbstractAF abstractAF, EC02Instance ec02Instance, AFSubInstance afSubIns) {

		String jsonData = afSubIns.getE01Data();
		DateTime currentTime = new DateTime();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObj;
         
		try {
			
			jsonObj = (JSONObject) parser.parse(jsonData);
			
			JSONObject listCos = (JSONObject) jsonObj.get("listClassOfService");
			
			if(listCos.get("state").equals("1")) {
				
				JSONArray jsonArr = (JSONArray) listCos.get("data");
				
				for (int i = 0; i < jsonArr.size(); i++) {
					
					JSONObject dataObj = (JSONObject) jsonArr.get(i);
					
					try {
						
					DateTime startTime = Config.formatDate.parseDateTime((String) dataObj.get("starttime"));
					DateTime endtime = Config.formatDate.parseDateTime((String) dataObj.get("endtime"));
					
						if(startTime.isBefore(currentTime) && endtime.isAfter(currentTime)) {
							afSubIns.setNewCos((String) dataObj.get("classOfService"));
							afSubIns.setRefillStopTime((String) dataObj.get("refillStopTime"));
							afSubIns.setRefillStopTimeMonth((String) dataObj.get("refillStopTimeMonth"));
							
							String rewardId = (String) dataObj.get("rewardId");
							
							AppLog.d("rewardId from E01 : "+rewardId);
							
							if(rewardId.contains(",")) {
								
								String[] rewardIdArr = rewardId.split(",",-1);
								
								for(String rewardIdData:rewardIdArr) {
									if(StringUtils.isNotBlank(rewardIdData)) {
										afSubIns.addRewardId(rewardIdData);
										AppLog.d("RewardId : "+rewardIdData);
									}
								}
								
							} else {
								if(StringUtils.isNotBlank(rewardId)) {
									afSubIns.addRewardId(rewardId);
									AppLog.d("RewardId : "+rewardId);
								}
							}
							
							AppLog.d("rewardId set to array : "+afSubIns.getRewardId().toString());
							
							break;
						}
					
					} catch(Exception e) {
						
						AppLog.e("Date is wrong format.");
					}
					
				}
				
				AppLog.d("oldCos : "+afSubIns.getOldCos());
				AppLog.d("newCos : "+afSubIns.getNewCos());

				
			}
			
			afSubIns.getReportData().setMainProductID(afSubIns.getNewCos());;
			afSubIns.setSubNextState(ESubState.E01_CosToPromotionName.toString());
			afSubIns.setSubControlState(ESubState.E01_CosToPromotionName.toString());
				
		} catch (ParseException e) {
			
			AppLog.e("Cannot parse json data.");
			afSubIns.setSubNextState("End_"+afSubIns.getSubInitCmd());
			afSubIns.setSubControlState("End_"+afSubIns.getSubInitCmd());
			afSubIns.setSubInternalCode(EResultCode.RE50000);

			afSubIns.setSubHasErrorForWriteDetailLog(true);
			ec02Instance.getAFInstance().decrementMainCountProcess();
			
		}

	}
}
