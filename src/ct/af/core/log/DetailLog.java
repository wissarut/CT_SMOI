package ct.af.core.log;

import ec02.af.abstracts.AbstractAF;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.Config;
import ct.af.utils.StringUtils;

public class DetailLog {

    DateTimeFormatter formatDateWithMiTz = Config.formatDateWithMiTz;
    DateTimeFormatter formatDateWithMi = Config.formatDateWithMi;

    public String getInput(List<InputLog> inputLogs) {

        StringBuilder str = new StringBuilder();
        
        if((inputLogs != null) && (inputLogs.size() > 0)){
            for (InputLog inputLog : inputLogs) {

                if(Config.DETAIL_LOG_RAWDATA_ENABLED) {
	                str.append(inputLog.getRawDataMsg().replace("\"", "\\\"")+",");//.replaceAll("\\s+","")+",");
                }
     
            }

            if(str.length() > 0) {
            	str.delete(str.length() - 1, str.length()); // remove ","
            }
            
        }

        return str.toString();
    }

    public String getOutput(List<OutputLog> outputLogs) {
    	
    	StringBuilder str = new StringBuilder();
    	
        if(null != outputLogs && outputLogs.size() > 0) {
        	
            for (OutputLog outputLog : outputLogs) {

            	if(Config.DETAIL_LOG_RAWDATA_ENABLED) {
	                str.append(outputLog.getRawDataMsg().replace("\"", "\\\"")+",");//.replaceAll("\\s+","")+",");
                }
 
            }

            if(str.length() > 0) {
            	str.delete(str.length() - 1, str.length()); // remove ","
            }
            
        }
	        
        return str.toString();

    }

    public String getDetailLog(String sessionID, String initInvoke, String state, String msisdn, String command,String inputLog,String nextstate,String outputLog, String resultcode, String desc) {
    	
    	StringBuilder str = new StringBuilder();
    	
        str.append("SESSIONID="+sessionID).append("|");
        str.append("INVOKE="+initInvoke).append("|");
        str.append("STATE="+state).append("|");
        str.append("MSISDN="+msisdn).append("|");
        str.append("COMMAND="+command).append("|");
        str.append("INPUT="+inputLog).append("|");
        str.append("NEXTSTATE="+nextstate).append("|");
        str.append("OUTPUT="+outputLog);
        
        if(nextstate.contains("End_")) {
        	str.append("|RESULTCODE="+resultcode).append("|");
            str.append("DESC="+desc);
        }

        return str.toString();
    }
    
    public String writeDetailLog(DetailLog detailLog,AbstractAF abstractAF,EC02Instance ec02Ins,AFSubInstance afSunIns,DateTime timeStampIn,DateTime timeStampOut){
    	
    	String finalCode = null;
    	String finalDesc = null;
    	String nextState = afSunIns.getSubNextState();
    	
    	if(afSunIns.getSubFinalCode() != null) {
    		finalCode = afSunIns.getSubFinalCode().getFinalResultCode();
    		finalDesc = afSunIns.getSubFinalCode().getFinalDesc();
    	}
    	
    	if(afSunIns.getSubNextState().equals(ESubState.WAIT.toString())
    			&& StringUtils.isNotBlank(afSunIns.getSubStateHostWait())) {
    		nextState = afSunIns.getSubStateHostWait();
    	}
    	
        String dtLog = detailLog.getDetailLog(
    	
    	abstractAF.getEquinoxProperties().getSession(),
    	afSunIns.getSubInitInvoke(), 
    	afSunIns.getSubCurrentState(), 
    	afSunIns.getSubMSISDN(), 
    	afSunIns.getSubInitCmd(),
    	getInput(afSunIns.getSubInputLogs()),
    	nextState,
    	getOutput(afSunIns.getSubOutputLogs()),
    	finalCode,
    	finalDesc);
        
        return dtLog;
        
    }
}
