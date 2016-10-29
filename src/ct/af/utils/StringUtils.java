package ct.af.utils;

public class StringUtils {
	
	public static boolean isBlank(String str){
		
		if(str == null) {
			return true;
		} else {
			if(str.length() <= 0) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public static boolean isNotBlank(String str){
		
		if(str != null) {
			if(str.length() > 0) {
				return true;
			}
		} else {
			return false;
		}
		
		return false;
		
	}
	
	public static boolean isInteger(String str) {
		
	    try {
	        Integer.parseInt(str);
	        return true;
	        
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    
	}

}
