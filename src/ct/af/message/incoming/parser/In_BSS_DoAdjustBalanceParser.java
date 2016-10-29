package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BSS_DoAdjustBalance;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_BSS_DoAdjustBalanceParser {
	
	private static In_BSS_DoAdjustBalanceParser parser = null;
	
	protected  In_BSS_DoAdjustBalanceParser() {
	  
	}
	public static In_BSS_DoAdjustBalanceParser getInstance() {
		if(parser == null) {
			parser = new In_BSS_DoAdjustBalanceParser();
		}
		return parser;
	}
	
	public Param_BSS_DoAdjustBalance doParser(EquinoxRawData eqxRawData) {
		Param_BSS_DoAdjustBalance doAdjustBalanceParameter = new Param_BSS_DoAdjustBalance();
		
		try {
			Serializer seirializer = new Persister();
			doAdjustBalanceParameter = seirializer.read(Param_BSS_DoAdjustBalance.class, eqxRawData.getRawDataMessage());
			
			doAdjustBalanceParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error doAdjustBalance parser");
			return doAdjustBalanceParameter;
		}
		
		if(!StringUtils.isBlank(doAdjustBalanceParameter.getResultCode())) {
			
			if(doAdjustBalanceParameter.getResultCode().equals("1000000")) {
				
				doAdjustBalanceParameter.setRespResultCode(EResultCode.RE1000000);
				doAdjustBalanceParameter.setNotMissing(true);
				doAdjustBalanceParameter.setValid(true);
				return doAdjustBalanceParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return doAdjustBalanceParameter;
				
			}
			
		} else {
			
			doAdjustBalanceParameter.setRespResultCode(EResultCode.RE40301);
			return doAdjustBalanceParameter;
			
		}

	}
	
}
