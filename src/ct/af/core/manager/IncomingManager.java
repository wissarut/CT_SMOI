package ct.af.core.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ct.af.core.log.DetailLog;
import ct.af.enums.EEventType;
import ct.af.enums.EResultCode;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AppInfo;
import ct.af.utils.Config;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.af.data.GlobalData;
import ec02.data.E01Data;
import ec02.utils.AppLog;

public class IncomingManager {
	
	DateTimeFormatter formatDateWithMiTz = Config.formatDateWithMiTz;
	
	@SuppressWarnings("unchecked")
	public String start(AbstractAF abstractAF, EC02Instance ec02Instance, ArrayList<EquinoxRawData> equinoxRawDatas) {
		
		DateTime timeStampIn = new DateTime();
		ec02Instance.getAFInstance().setMainTimeStampIncoming(formatDateWithMiTz.print(timeStampIn));
	
		if(Config.APP_LOG_DEBUG_FLAG) {
			AppLog.d(new AppInfo().composeIncommingInfo(Config.formatDateWithHyphen.print(timeStampIn),abstractAF.getEquinoxProperties().isTimeout(),abstractAF.getUtils().getGlobalData().isRecieve(),equinoxRawDatas.size()));
		}
		
		boolean isRetTimeout = abstractAF.getEquinoxProperties().isTimeout();

		if(isRetTimeout) {
			
			AppLog.d("Call TimeoutManager.recvTimeoutManager()"); 

			TimeoutManager timeoutManager = new TimeoutManager();
			timeoutManager.recvTimeoutManager(abstractAF, ec02Instance, timeStampIn);
			
		}
		
		GlobalData e01 = abstractAF.getUtils().getGlobalData();

		if(e01.isRecieve()) {
			
			for (E01Data data : e01.getDataResultSet())
		      {

				AppLog.d("E01 Data : "+data.getId());
				
				EquinoxRawData eqxRawData = new EquinoxRawData();
				eqxRawData.setInvoke(data.getId());
				eqxRawData.setType(EEventType.RESPONSE.getEventType());
				eqxRawData.setName("DB");
				eqxRawData.setCType("DB");
				eqxRawData.setRet("0");
				
				JSONObject obj = new JSONObject();
					obj.put("command", data.getCmdName());
					obj.put("resultcode",data.getResultCode());
					obj.put("id",data.getId());
					obj.put("objecttype",data.getKeyObject().getObjectType());

					JSONArray listKey = new JSONArray();
					
					for(String key:data.getKeyObject().getKeySet()) {
						listKey.add(key);
					}	
	
					obj.put("objKey", listKey);
					obj.put("data", data.getData());
				
				AppLog.d("E01 Data : "+obj.toJSONString());
				
				eqxRawData.setRawMessage(obj.toJSONString());
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("ecode", data.getResultCode());
				eqxRawData.setRawDataAttributes(map);
	
				equinoxRawDatas.add(eqxRawData);

		      }
			
		}
		
		for(EquinoxRawData eqxRawData : equinoxRawDatas) {
			
			ExtractController extractController = new ExtractController();
			AFSubInstance afSubIns = extractController.checkStateByInvoke(abstractAF, ec02Instance, eqxRawData);
			
			extractController.keepInputLogAndKeepStat(abstractAF, ec02Instance, eqxRawData, afSubIns);
   
		}
		
		HashMap<String, AFSubInstance> subInstanceHashMap = ec02Instance.getAFInstance().getMainSubInstance();
		SortedSet<String> subInstancekeys = new TreeSet<String>(subInstanceHashMap.keySet());
		
		List<String> toRemove = new ArrayList<String>();
		
		for (String key : subInstancekeys) {
		
			AppLog.d("##### Sub Instance Key : "+key+" #####");
			
			AFSubInstance afSubInstance = ec02Instance.getAFInstance().getMainSubInstance(key);
			
			doActionSubIns(abstractAF, ec02Instance, afSubInstance);
			
			if(afSubInstance.getSubNextState().equals(ESubState.END.toString()) && afSubInstance.getSubHostInsNo() != null) {
				ec02Instance.getAFInstance().getMainSubInstance().get(afSubInstance.getSubHostInsNo()).decrementSubCountChild();
				toRemove.add(afSubInstance.getSubInstanceNo());
			}
	
			AppLog.d("##### End Sub Instance Key : "+key+" #####");
			
	    }
		
		for(String key:toRemove) {
			
			AFSubInstance hostSubIns = ec02Instance.getAFInstance().getMainSubInstance(ec02Instance.getAFInstance().getMainSubInstance(key).getSubHostInsNo());
			composeLog(abstractAF, ec02Instance, hostSubIns);

			ec02Instance.getAFInstance().getMainSubInstance().remove(key);
			
		}
		
		LogStatController composeLog = new LogStatController();
		
		composeLog.writeLogStat(abstractAF, ec02Instance);
		
		if(Config.APP_LOG_DEBUG_FLAG) {
			AppLog.d(new AppInfo().showInfoDebug(abstractAF,ec02Instance));
		}
		
		return new TimeoutManager().setEqxTimeout(abstractAF, ec02Instance, timeStampIn);
		
	}
	
	public void doActionSubIns(AbstractAF abstractAF, EC02Instance ec02Instance,AFSubInstance afSubInstance) {
		
		if(afSubInstance.getSubInvoke().size() == 0
				&& afSubInstance.getSubNextOfNextState().equals(ESubState.Unknown.toString())) {
			
			if(afSubInstance.getSubNextState().equals(ESubState.Unknown.toString())) {
				
				new DoBusinessController().doBusinessLogic(abstractAF,ec02Instance,afSubInstance);
				
			}
			
			new ConstructController().constructGateway(abstractAF, ec02Instance, afSubInstance);
			
			composeLog(abstractAF, ec02Instance, afSubInstance);
			
			if(StringUtils.isNotBlank(afSubInstance.getSubHostInsNo())) {

				AFSubInstance mainSubIns = ec02Instance.getAFInstance().getMainSubInstance(afSubInstance.getSubHostInsNo());
				
				mainSubIns.subChildHasError(afSubInstance.isSubHasErrorForWriteDetailLog(), afSubInstance.getSubCurrentState());

				if(mainSubIns.getSubInvoke().size() == 0) {
					
					AppLog.d("Host ready to process !");
					AppLog.d("Sub Child has error : "+mainSubIns.isSubChildHasError());
					mainSubIns.setSubControlState(mainSubIns.getSubNextOfNextState());
					mainSubIns.setSubNextOfNextState(ESubState.Unknown.toString());
					mainSubIns.setSubNextState(ESubState.Unknown.toString());
					mainSubIns.setSubIsValid(!mainSubIns.isSubChildHasError());
					
					if(mainSubIns.getSubIsValid()) {
						mainSubIns.setSubResultCode(EResultCode.RE20000);
					} else {
						mainSubIns.setSubResultCode(EResultCode.RE50000);
					}
					
					doActionSubIns(abstractAF, ec02Instance, ec02Instance.getAFInstance().getMainSubInstance(afSubInstance.getSubHostInsNo()));
				
				}
			}
		
		}
		
	}
	
	public void composeLog(AbstractAF abstractAF, EC02Instance ec02Instance,AFSubInstance afSubInstance) {
		
		DateTime timeStampOut = new DateTime();

		int inputLogSize = afSubInstance.getSubInputLogs().size();
		int outputLogSize = afSubInstance.getSubOutputLogs().size();
		
		if(inputLogSize > 0 || outputLogSize > 0) {
			
			DetailLog detailLog = new DetailLog();
			
			String detailPrint = detailLog.writeDetailLog(detailLog,abstractAF,ec02Instance,afSubInstance,formatDateWithMiTz.parseDateTime(ec02Instance.getAFInstance().getMainTimeStampIncoming()),timeStampOut);
			
			AppLog.d("DETAIL LOG Print :"+detailPrint);
			if(Config.APP_LOG_DEBUG_FLAG) {
				AppLog.d(new AppInfo().composeDetailDebug(ec02Instance,detailPrint));
			}
			afSubInstance.setDetailLog(detailPrint);
			
    	}

		afSubInstance.setSubTimeStampOutgoing(formatDateWithMiTz.print(timeStampOut));

		if(Config.APP_LOG_DEBUG_FLAG) {
			AppLog.d(new AppInfo().composeInfoDebug(ec02Instance,afSubInstance));
		}

		afSubInstance.getSubInputLogs().clear();
		afSubInstance.getSubOutputLogs().clear();
		afSubInstance.setSubResponseParam(null);
		
	}
	
}
