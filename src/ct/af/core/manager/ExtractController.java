package ct.af.core.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import ct.af.core.log.DestinaionLog;
import ct.af.core.log.InputLog;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EResultCode;
import ct.af.enums.ERet;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.extracterd.In_Idle_Unknown;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AFUtils;
import ct.af.utils.AppInfo;
import ct.af.utils.Config;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class ExtractController {
	
	public AFSubInstance enterToParser(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns,EquinoxRawData eqxRawData,String currentState) {
		
		AppLog.d("extract class : In_"+currentState);
		
		if(afSubIns != null) {
			afSubIns.setSubNextState(ESubState.Unknown.name());
		}
		
		try {
			
			String extracterdClassStr = "ct.af.extracterd.In_"+currentState;
			
			Class<?> extracterdClass = Class.forName(extracterdClassStr);
			Object extracterdIn = extracterdClass.newInstance(); 
			String methodName = "extractRawData";
	        Method getNameMethod = extracterdIn.getClass().getMethod(methodName , AbstractAF.class,EC02Instance.class,AFSubInstance.class,EquinoxRawData.class);
	        
	        afSubIns = (AFSubInstance) getNameMethod.invoke(extracterdIn , abstractAF,ec02Instance,afSubIns,eqxRawData); 
	        
	        return afSubIns;
	        
		} catch (IllegalAccessException e) {
			AppLog.e("Cannot get extract Class : In_"+currentState);
			AppLog.e(e);
			return new In_Idle_Unknown().extractRawData(abstractAF, ec02Instance, afSubIns, eqxRawData);
		} catch (ClassNotFoundException e) {
			AppLog.e("Cannot get extract Class : In_"+currentState);
			AppLog.e(e);
			return new In_Idle_Unknown().extractRawData(abstractAF, ec02Instance, afSubIns, eqxRawData);
		} catch (InstantiationException e) {
			AppLog.e("Cannot get extract Class : In_"+currentState);
			AppLog.e(e);
			return new In_Idle_Unknown().extractRawData(abstractAF, ec02Instance, afSubIns, eqxRawData);
		} catch (NoSuchMethodException e) {
			AppLog.e("Cannot get extract Class : In_"+currentState);
			AppLog.e(e);
			return new In_Idle_Unknown().extractRawData(abstractAF, ec02Instance, afSubIns, eqxRawData);
		} catch (SecurityException e) {
			AppLog.e("Cannot get extract Class : In_"+currentState);
			AppLog.e(e);
			return new In_Idle_Unknown().extractRawData(abstractAF, ec02Instance, afSubIns, eqxRawData);
		} catch (InvocationTargetException e) {
			AppLog.e("Cannot get extract Class : In_"+currentState);
			AppLog.e(e);
			return new In_Idle_Unknown().extractRawData(abstractAF, ec02Instance, afSubIns, eqxRawData);
		}

	}
	
	public AFSubInstance checkStateByInvoke(AbstractAF abstractAF,EC02Instance ec02Instance,EquinoxRawData eqxRawData) {
		
		String rawDataInvoke = eqxRawData.getInvoke();
		String rawDataType = eqxRawData.getType();
		String rawDataRet = eqxRawData.getRet();
		
		AFSubInstance afSubIns = null;
		String currentState = ESubState.Unknown.toString();
		
		boolean isEDBPurge = false;
		boolean isDelayResp = false;
		
		if(rawDataType.equals(EEventType.REQUEST.getEventType()) && rawDataRet.equals(ERet.RET0.getRet())) {

			afSubIns = enterToParser(abstractAF, ec02Instance, afSubIns, eqxRawData, ESubState.Idle_Request.toString());
			
			ec02Instance.getAFInstance().putMainSubInstance(afSubIns.getSubInstanceNo(), afSubIns);
			
		} else {
			
			String[] retError = new String[]{"1","2","3"};
			
			if((Arrays.asList(retError).contains(rawDataRet)) && (rawDataType.equals(EEventType.RESPONSE.getEventType()))) {
				
				AppLog.e("EDBPurgeMessage (Ret=3)");
				isEDBPurge = true;
				
			} else {
				
				String respSubInsNo = AFUtils.getSubInsNoFromInvoke(rawDataInvoke);
				HashMap<String, AFSubInstance> subIns = ec02Instance.getAFInstance().getMainSubInstance();
				
				boolean subInsFound = subIns.containsKey(respSubInsNo);
				
				if(subInsFound) {
					
					ArrayList<String> subInsInvoke = subIns.get(respSubInsNo).getSubInvoke();
   			 		int subInsInvokeSize = subInsInvoke.size();
   			 		
   			 		if(subInsInvokeSize > 0) {
   		
   			 			if(!subInsInvoke.contains(rawDataInvoke)) {
	   			 			
   			 				AppLog.e("isDelay Response - respSubInsNo not in list subInsInvoke");
   			 				isDelayResp = true;
   			 				
	   			 		}
	   			 		
   			 		} else {
   			
   			 			AppLog.e("isDelay Response - InvokeSize <= 0");
   			 			isDelayResp = true;
   			 			
   			 		}
   			 		
				} else {
					
					AppLog.e("isDelay Response - subIns not Found");
					isDelayResp = true;
					
				}
				
			}
			
			if(isEDBPurge || isDelayResp) {
				
				afSubIns = enterToParser(abstractAF, ec02Instance, afSubIns, eqxRawData, "Idle_"+ESubState.Unknown.toString());
				
			} else {
				
				afSubIns = ec02Instance.getAFInstance().getMainSubInstance().get(AFUtils.getSubInsNoFromInvoke(rawDataInvoke));
		 		
				currentState = AFUtils.getNextStateFromInvoke(rawDataInvoke);
				
		 		ArrayList<String> subInvoke = afSubIns.getSubInvoke();
			 	
			 	AppLog.d("Before Delete invoke : "+subInvoke.toString());

			 	subInvoke.remove(subInvoke.indexOf(rawDataInvoke));
			 	
			 	AppLog.d("After Delete invoke : "+subInvoke.toString());
			 	
			 	afSubIns = enterToParser(abstractAF, ec02Instance, afSubIns, eqxRawData, currentState);
		
			 	if(afSubIns.getSubHostInsNo() != null) {
			 		
			 		AFSubInstance hostSubIns = ec02Instance.getAFInstance().getMainSubInstance().get(afSubIns.getSubHostInsNo());
			 		ArrayList<String> hostSubInvoke = hostSubIns.getSubInvoke();
			 		hostSubInvoke.remove(hostSubInvoke.indexOf(rawDataInvoke));
			 		hostSubIns.setSubCurrentState(afSubIns.getSubCurrentState());
			 		
			 	}
			 	
			}

		}
		
		if(Config.APP_LOG_DEBUG_FLAG) {
			AppLog.d(new AppInfo().composeExtractInfo(eqxRawData, afSubIns,isEDBPurge,isDelayResp));
		}
		
		return afSubIns;
		
	}
	
	public void keepInputLogAndKeepStat(AbstractAF abstractAF,EC02Instance ec02Instance,
			EquinoxRawData eqxRawData,AFSubInstance afSubIns) {
		
		String rawDataOrig = eqxRawData.getOrig();
		String rawDataRet = eqxRawData.getRet();
		String rawDataECode = eqxRawData.getRawDataAttribute("ecode");

		String respServiceOrig;
		
		try {
			
			respServiceOrig = rawDataOrig.split("\\.")[2];
			
		} catch(Exception ex) {
			
			respServiceOrig = abstractAF.getEquinoxProperties().getApplicationName();
			
		}
		
		if(rawDataRet.equals("2") || rawDataRet.equals("3") || rawDataECode.equals("503")) {
			
			Map<String, String> map = new HashMap<String, String>();
	
			if(rawDataRet.equals("2") || rawDataRet.equals("3")) {
				
				EResultCode resultCode = EResultCode.REret2;
				
				if(rawDataRet.equals("3")) {
					resultCode = EResultCode.REret3;
				}
				
				map.put(EEqxMsg.VAL.getEqxMsg(), "{\"ret"+rawDataRet+"\":\""+resultCode.getResultCode()+"\"}");
						
			} else if(rawDataECode.equals("503")) {
		
				map.put(EEqxMsg.VAL.getEqxMsg(), "{\"503\":\""+EResultCode.RE503.geteDesc()+"\"}");
				
			}
			
			eqxRawData.setRawDataAttributes(map);
		}

		EResultCode resultCode = afSubIns.getSubResultCode();

		EStats statIn = afSubIns.getStatsIn().get(afSubIns.getStatsIn().size() - 1);
		
		DestinaionLog destinationLog = afSubIns.getDestinationLogTemp();
		
		if(destinationLog == null) {
			destinationLog = new DestinaionLog();
			destinationLog.setNode(respServiceOrig);
			destinationLog.setEvent(statIn.getStatName().toString());
			destinationLog.putResultMap(resultCode);
		} else {
			destinationLog.countRequest();
			destinationLog.putResultMap(resultCode);
		}
		
		afSubIns.setDestinationLogTemp(destinationLog);
		
		if(afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
			
			if(StringUtils.isBlank(afSubIns.getSubHostInsNo())) {	
				
				afSubIns.setSubDestinationList(destinationLog.getDestination());
				
			} else {

				ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).setSubDestinationList(destinationLog.getDestination());
			
			}
			
			afSubIns.setDestinationLogTemp(null);
			if(!afSubIns.getSubRecieveRet().equals("4")) {
				afSubIns.setSubCountServerRequest(0);
			}
		}
		
	 	//Compose Input Log
		DateTime timeStampIn = Config.formatDateWithMiTz.parseDateTime(ec02Instance.getAFInstance().getMainTimeStampIncoming());
		
		Long resTime = (long) 0;
		 
		if(afSubIns.getSubTimeStampOutgoing() != null) {
			
			DateTime timeStampOutgoing = Config.formatDateWithMiTz.parseDateTime(afSubIns.getSubTimeStampOutgoing());
			
			resTime = new Duration(timeStampOutgoing,timeStampIn).getMillis();
			
		}
				
		InputLog inputLogObj = new InputLog(eqxRawData,statIn,afSubIns.getSubInitCmd(),resTime.toString());

		if(Config.APP_LOG_DEBUG_FLAG) {
			AppLog.d(new AppInfo().composeInputDebug(ec02Instance, eqxRawData, afSubIns));
		}
		
		if(StringUtils.isBlank(afSubIns.getSubHostInsNo())) {	
			
			afSubIns.addSubInputLogs(inputLogObj);
			
		} else {

			ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).addSubInputLogs(inputLogObj);
			ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).addStatsIn(statIn);
			afSubIns.getStatsIn().clear();
		}
		
	}

}
