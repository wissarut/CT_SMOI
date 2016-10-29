package ct.af.message.incoming.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import ct.af.utils.Config;

public class Validator {
	
	
	public static boolean isString(String str,int lng) {
		
		if(lng == 0){
			
			if( str.matches(".*") ){
				return true;
			} else {
				return false;
			}

		} else {
			
			if( str.matches("^\\w{"+lng+"}") ){
				return true;
			} else {
				return false;
			}

		}

    }
	
	public static boolean isDigit(String str,int lng) {
		
		if(lng == 0){
			
			if( str.matches("\\d*")){
				return true;
			} else {
				return false;
			}

		} else {
			
			if( str.matches("^\\d{"+lng+"}") ){
				return true;
			} else {
				return false;
			}

		}
    }
	
	public static boolean isMsisdnFormat(String msisdn,String beginWith) {

		if(msisdn.matches("^("+beginWith+")\\d{9}")){
			return true;
		} else {
			return false;
		}

    }
	
	
	public static boolean isDateFormat(String strDate) {
		
		 
		 SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		 
		 try {
			 sf.parse(strDate);

		 } catch (ParseException e) {
			 return false;
		 } catch (Exception e) {
			 return false;
		}
		 
        return true;
    }
	
	public static boolean isDateFormatWithTimezone(String strDate) {
		
		 try {
			 
			 if(strDate.length() > 19 || strDate.length() < 19) {
				 return false;
			 } else {
				 Config.formatDateWithMiTz.parseDateTime(strDate);
			 }

		 } catch (Exception e) {
			 return false;
		 }
		 
       return true;
   }
	
	public static boolean isNumeric(String strNum)  {  
		try {
			Double.parseDouble(strNum);  
			
		} catch(NumberFormatException e) {  
			return false;  
		}  
		
		return true;  
	}
	
	public static boolean isAlphaNumeric(String s){
		
	    String pattern= "^[a-zA-Z0-9]*$";
	    
        if(s.matches(pattern)){
            return true;
        }
        
        return false;   
        
	}
	
}
