package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BSS_DoSetNegativeBalance;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_BSS_DoSetNegativeBalanceParser {
	
	private static In_BSS_DoSetNegativeBalanceParser parser = null;
	
	protected  In_BSS_DoSetNegativeBalanceParser() {
	  
	}
	public static In_BSS_DoSetNegativeBalanceParser getInstance() {
		if(parser == null) {
			parser = new In_BSS_DoSetNegativeBalanceParser();
		}
		return parser;
	}
	
	public Param_BSS_DoSetNegativeBalance doParser(EquinoxRawData eqxRawData) {
		Param_BSS_DoSetNegativeBalance setNegativeBalanceParameter = new Param_BSS_DoSetNegativeBalance();
		
		try {
			Serializer serializer = new Persister();
			setNegativeBalanceParameter = serializer.read(Param_BSS_DoSetNegativeBalance.class, eqxRawData.getRawDataMessage() );
			
			setNegativeBalanceParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error setNegativeBalance parser");
			return setNegativeBalanceParameter;
		}
		
		if(!StringUtils.isBlank(setNegativeBalanceParameter.getResultCode())) {
			
			if(setNegativeBalanceParameter.getResultCode().equals("1000000")) {
				
				setNegativeBalanceParameter.setRespResultCode(EResultCode.RE1000000);
				setNegativeBalanceParameter.setNotMissing(true);
				setNegativeBalanceParameter.setValid(true);
				return setNegativeBalanceParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return setNegativeBalanceParameter;
				
			}
			
		} else {
			
			setNegativeBalanceParameter.setRespResultCode(EResultCode.RE40301);
			return setNegativeBalanceParameter;
			
		}

	}
	
}
