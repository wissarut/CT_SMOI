package ct.af.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import org.joda.time.DateTime;
import ct.af.instance.EC02Instance;
import ec02.af.abstracts.AbstractAF;
import ec02.utils.AppLog;

public class AFUtils {

	public static String subInsNoGenerator(EC02Instance ec02Ins,String command){
		 
		int newSubSessionRunNo = ec02Ins.getAFInstance().getMainSubSessionRunningNo()+1;

		ec02Ins.getAFInstance().countSubSessionRunningNo(); 
		
		return String.valueOf(newSubSessionRunNo)+command.toUpperCase();
	}
	 
	public static String invokeGenerator(AbstractAF abstractAF,String initInvoke ,String initCommand ,String nextState, String subInstanceNo,String session) {

		Random random = new Random();
		long randomNumber = (long)(9000 * random.nextDouble());
	    int randomKey = (int)(randomNumber + 10000);
	    
		String invoke = initInvoke+"."+subInstanceNo+".W_"+nextState+"."+randomKey;
	    
		return invoke;
	}
	
	public static String getNextStateFromInvoke(String invoke){
		return invoke.split("\\.")[2].substring(2);
	}
	
	public static String getSubInsNoFromInvoke(String invoke){
		try {
			return invoke.split("\\.")[1];
		} catch(Exception ex) {
			return "Unknown";
		}
	}
	 
	 public static String commandStats(String command, String stat) {
	        String message = stat;
	        if (stat.contains("%s")) {
	            message = message.replaceAll("%s", command);
	        }
	        return message;
	  }
	 
	public static String timestampSinceFirstJan1900() 
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String calendarStartString = "1900-01-01 00:00:00.00 +0000";
		Date dateCalendarStart = null;
		try {
			dateCalendarStart = formatter.parse(calendarStartString);
		} catch (ParseException e) {
			AppLog.e("[Exception] timestampSinceFirstJan1900()");
		}
		
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		
		Calendar calendarStart = Calendar.getInstance(timeZone);
		calendarStart.setTime(dateCalendarStart);
		
		Calendar calendarNow = Calendar.getInstance(timeZone);
		
		AppLog.d("Start  Time  : "+formatter.format(calendarStart.getTime()));
		AppLog.d("Current Time : "+formatter.format(calendarNow.getTime()));
		
		AppLog.d("Start   Time (Millisec) : "+calendarStart.getTimeInMillis()/1000);
		AppLog.d("Current Time (Millisec) : "+calendarNow.getTimeInMillis()/1000);

		Long dif = (calendarNow.getTimeInMillis()/1000)-(calendarStart.getTimeInMillis()/1000);
		
		AppLog.d("Result : "+Long.toString(dif));
		
		return Long.toString(dif);
	}
	
	public static String stringToHex(String text)
	{
		char[] words = text.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < words.length; i++)
	    {
	        hex.append(Integer.toHexString((int) words[i]));
	    }
		
		return "0x"+hex.toString();
	}
	
	public static String hexToString(String hex) {
		
		String hexReplace = hex.replaceAll("(.*)x", "");
		String msg = "";
		
		for (int i = 0; i < hexReplace.length(); i++) {
			int numCount = i%2;
			if(!(numCount == 0)){
				msg += hexReplace.substring(i , i+1);
			}
			
		}
		
	    return msg;
	}
	
	public static String convertStringToHex(String str){
		 
        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            String hex1 = Integer.toHexString((int)chars[i]);
            if (hex1.length() == 3) {
                hex1 = "0" + hex1;
            } else if (hex1.length() == 2) {
                hex1 = "00" + hex1;
            }
          hex.append(hex1);
        }

        return hex.toString().toUpperCase();
    }
	
	public static boolean dateTimeisInRange(DateTime startTime,DateTime endTime) {
		
		//PAST					NOW		FETURE

		//PAST.isBefore.NOW 	&& 		FETURE.isAFTER.NOW
		
		DateTime currentTime = new DateTime();
		
		return (startTime.isBefore(currentTime) && endTime.isAfter(currentTime));
		
	}
	 
}
