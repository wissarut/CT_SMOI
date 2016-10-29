package ct.af.core.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import ct.af.core.log.SummaryLog;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.utils.AppLog;

public class LogStatController {
	
	public void writeLogStat(AbstractAF abstractAF,EC02Instance ec02Instance) {
		
		HashMap<String, AFSubInstance> subInstanceHashMap = ec02Instance.getAFInstance().getMainSubInstance();
		SortedSet<String> subInstancekeys = new TreeSet<String>(subInstanceHashMap.keySet());
		
		List<String> toRemove = new ArrayList<String>();
		
		for (String key : subInstancekeys) {
			
			AFSubInstance afSubIns = subInstanceHashMap.get(key);

			//Write Stat In
			if(afSubIns.getStatsIn().size() != 0) {
		
				for(EStats stat:afSubIns.getStatsIn()) {
					abstractAF.getUtils().incrementStats(stat.getStatName());
				}
				
			}
			
			afSubIns.getStatsIn().clear();
			
			//Write Stat Out
			if(afSubIns.getStatsOut().size() != 0) {
				
				for(EStats stat:afSubIns.getStatsOut()) {
					abstractAF.getUtils().incrementStats(stat.getStatName());
				}
				
			}
			
			afSubIns.getStatsOut().clear();
			
			//Detail Log
			List<String> detailLogList = afSubIns.getDetailLog();
			
			if(Config.DETAIL_LOG_ENABLED == 0) {
				
    			afSubIns.getDetailLog().clear();
    			
			} else if(Config.DETAIL_LOG_ENABLED == 2) {
				
				for(String detailLog : detailLogList) {
    				
    				abstractAF.getUtils().writeLog(Config.DETAILLOG_NAME, detailLog);
        	        AppLog.d("[Detail] Write Detail Log !");
        	        
    			}

				afSubIns.getDetailLog().clear();
				
			}
			
			if(afSubIns.getSubNextState().contains("End_")
					&& afSubIns.getSubCountChild() == 0) {
				
				SummaryLog summaryLog = new SummaryLog();
				abstractAF.getUtils().writeLog(Config.SUMMARYLOG_NAME, summaryLog.writeSummary(abstractAF, ec02Instance,afSubIns));
		        AppLog.d("[Summary] Write Summary Log !");
		   
		        if(afSubIns.isSubHasErrorForWriteDetailLog()) {
        			
        			for(String detailLog : detailLogList) {
        				
        				abstractAF.getUtils().writeLog(Config.DETAILLOG_NAME, detailLog);
	        	        AppLog.d("[Detail] Write Detail Log !");
	        	        
        			}
        			
        		}
		        
				toRemove.add(key);

			}
			
		}
		
		for(String key:toRemove) {
			
			ec02Instance.getAFInstance().getMainSubInstance().remove(key);
			
		}

	}

}
