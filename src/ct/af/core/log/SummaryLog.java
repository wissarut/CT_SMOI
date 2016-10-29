package ct.af.core.log;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormatter;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AppInfo;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.utils.AppLog;

public class SummaryLog {
	
    public String getSummaryLog(EC02Instance ec02Ins,String timeStampIn,String session, String initInvoke,String msisdn,String cmdName, String identity, String resultCode,String resultDesc, String developerMessage,String destinationList) {       
    	
	    	DateTimeFormatter formatDateWithMiTz = Config.formatDateWithMiTz;
	    	
	    	DateTime dateTimeNow = new DateTime();

			DateTime reqTimeStamp = formatDateWithMiTz.parseDateTime(timeStampIn);
			DateTime resTimeStamp = dateTimeNow;
			 
			Long usageTime = new Duration(reqTimeStamp,resTimeStamp ).getMillis();
		
    		StringBuilder str = new StringBuilder();
	        
	        str.append("SESSION="+session).append("|");
	        str.append("INVOKE="+initInvoke).append("|");
	        str.append("MSISDN="+msisdn).append("|");
	        str.append("COMMAND="+cmdName).append("|");
	        str.append("RESULTCODE="+resultCode).append("|");
	        str.append("DESC="+resultDesc).append("|");
	        str.append("RESPONSE_TIME="+usageTime);
	        
	        return str.toString();
	    }
    
    public String writeSummary(AbstractAF abstractAF,EC02Instance ec02Ins,AFSubInstance afSubIns){

    	SummaryLog summaryLog = new SummaryLog();
        String sumLog = summaryLog.getSummaryLog(
        		ec02Ins, 
        		afSubIns.getSubInitTimeStampIn(),
        		abstractAF.getEquinoxProperties().getSession(),
        		afSubIns.getSubInitInvoke(), 
        		afSubIns.getSubMSISDN(), 
        		afSubIns.getSubInitEvent(), 
        		abstractAF.getEquinoxProperties().getSession(), 
        		afSubIns.getSubFinalCode().getFinalResultCode(),
            	afSubIns.getSubFinalCode().getFinalDesc(),
        		null,
        		afSubIns.getSubDestinationString());

        if(Config.APP_LOG_DEBUG_FLAG) {
        	AppLog.d(new AppInfo().composeSummaryDebug(ec02Ins, sumLog));
        }
        
        return sumLog;

    }
	 
}
