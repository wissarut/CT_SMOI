package ct.af.core.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EState;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class TimeoutManager {

	public void recvTimeoutManager(AbstractAF abstractAF, EC02Instance ec02Instance,DateTime timeStampIn) {

		HashMap<String, AFSubInstance> subInstanceHashMap = ec02Instance.getAFInstance().getMainSubInstance();
		SortedSet<String> subInstancekeys = new TreeSet<String>(subInstanceHashMap.keySet());
		
		for (String key : subInstancekeys) {
			
			AFSubInstance afSubInstance = subInstanceHashMap.get(key);
			
			if(!afSubInstance.getSubNextState().contains(ESubState.WAIT.toString()) 
					&& !afSubInstance.getSubNextState().contains(ESubState.END.toString())) {
				
				DateTime timeoutSub = Config.formatDateWithMiTz.parseDateTime(afSubInstance.getSubTimeout());
			
				if(!timeoutSub.isAfter(timeStampIn)) {
					
					AppLog.d("CASE : Timeout");
					
					afSubInstance.setSubCurrentState(afSubInstance.getSubNextState());
					afSubInstance.setSubNextState(ESubState.Unknown.toString());
					afSubInstance.setSubIsValid(false);
					
					EquinoxRawData eqxRawData = new EquinoxRawData();
					
					String invoke = "";
					if(afSubInstance.getSubInvoke().size() > 0) {
						invoke = afSubInstance.getSubInvoke().get(0);
					} 
			
					eqxRawData.setInvoke(invoke);
					eqxRawData.setType(EEventType.RESPONSE.getEventType());
					eqxRawData.setOrig(afSubInstance.getSubInitOrig());
					eqxRawData.setRet("4");
					
					Map<String, String> map = new HashMap<String, String>();
					map.put(EEqxMsg.METHOD.getEqxMsg(), "");
					map.put(EEqxMsg.URL.getEqxMsg(), afSubInstance.getSubInitURL());
					map.put(EEqxMsg.VAL.getEqxMsg(), "");
					
					eqxRawData.setRawDataAttributes(map);
					
					ExtractController extractController = new ExtractController();
					afSubInstance = extractController.checkStateByInvoke(abstractAF, ec02Instance, eqxRawData);
					extractController.keepInputLogAndKeepStat(abstractAF, ec02Instance, eqxRawData, afSubInstance);
						
				}
			}
		}

	}

	public String setEqxTimeout(AbstractAF abstractAF, EC02Instance ec02Instance,DateTime timeStampIn) {
		
		DateTime preTimeout = timeStampIn.plusYears(1);

		HashMap<String, AFSubInstance> subInstanceHashMap = ec02Instance.getAFInstance().getMainSubInstance();
		SortedSet<String> subInstancekeys = new TreeSet<String>(subInstanceHashMap.keySet());
		
		String nextState = EState.IDLE.toString();
		
		for (String keyIns : subInstancekeys) { 
				
			AFSubInstance afSubInsance = subInstanceHashMap.get(keyIns);

			DateTime subTimeout;
	
			if(!(afSubInsance.getSubNextState().equals(ESubState.WAIT.toString())
					|| afSubInsance.getSubNextState().equals(ESubState.END.toString())
					|| afSubInsance.getSubNextState().contains(ESubState.END.toString()+"_"))) {

				subTimeout = Config.formatDateWithMiTz.parseDateTime(afSubInsance.getSubTimeout());

				if(subTimeout != null) {
					
					if(subTimeout.isBefore(preTimeout)) {
						
						preTimeout = subTimeout;
						
					}
					
				}
				
				nextState = afSubInsance.getSubNextState();
				
				
			}
			
		}
		
		AppLog.d("preTimeout after loop sub ins = "+preTimeout);

		AppLog.d("timeStampIn : "+timeStampIn);
		
		Seconds diffTimeout = Seconds.secondsBetween(timeStampIn, preTimeout);
		
		AppLog.d("preTimeout - timeStampIn = diffTimeout : "+diffTimeout.getSeconds());

		if(diffTimeout.getSeconds() < 1) {
			
			AppLog.d("diffTimeout < 1");
			ec02Instance.setTimeout("1");
			
		} else {

			AppLog.d("diffTimeout > 1");
			
			if(diffTimeout.getSeconds() > 30000000) {
				
				ec02Instance.setTimeout("30000000");
				
			} else {
				
				ec02Instance.setTimeout(String.valueOf(diffTimeout.getSeconds()));
				
			}
		}
		
		AppLog.d("set timeout = "+ec02Instance.getTimeout());
		
		String state = nextState;
		
		AppLog.d("COUNT WAIT : "+ec02Instance.getAFInstance().getMainCountWait());
		AppLog.d("COUNT PRO  : "+ec02Instance.getAFInstance().getMainCountProcess());
		
		if(ec02Instance.getAFInstance().getMainCountWait() == 0
				&& ec02Instance.getAFInstance().getMainCountProcess() == 0) {
			
			state = EState.IDLE.toString();
			ec02Instance.setTimeout("0");
			
		}

		return state;
		
	}
}
