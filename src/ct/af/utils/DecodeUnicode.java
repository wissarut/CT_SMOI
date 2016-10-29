package ct.af.utils;

public class DecodeUnicode {

	public String convertUnicodeToThai(String source) {

	    String result="";

	    int position=source.indexOf("%");
	    while(position!=-1) {
	        if(position!=0){
	        	result+=source.substring(0,position);
	        }
	        String token=source.substring(position+1,position+3).concat( source.substring(position+4,position+6) );
	        source=source.substring(position+6);
	        result+=(char)Integer.parseInt(token,16);
	        position=source.indexOf("%");
	    }
	    result+=source;

	    return result;
	}
	
}
