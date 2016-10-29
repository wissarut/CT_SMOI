package ct.af.utils;

import java.util.HashMap;
import ct.af.enums.EFinalCode;

public class InitalData {
	
	public static boolean hasReloadInitalData = true;
	
	public static HashMap<String, EFinalCode> mapFinalCode = new HashMap<String, EFinalCode>();
	
	public static void loadInitalData() {
		
		loadInitFinalCode();
		
		hasReloadInitalData = false;
		
	}
	
	public static void loadInitFinalCode() {
		
		 for (EFinalCode conditon : EFinalCode.values()) {
			 
			 String keys = conditon.getClientCommand()+conditon.getCurrentState()+conditon.getResultCode();
			 
			 mapFinalCode.put(keys, conditon);
			 
		 }
	
	}

}
