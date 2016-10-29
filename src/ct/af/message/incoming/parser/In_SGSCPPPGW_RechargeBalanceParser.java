package ct.af.message.incoming.parser;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ct.af.message.incoming.parameter.Param_SGSCPPPGW_RechargeBalance;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_SGSCPPPGW_RechargeBalanceParser {
	
	private static In_SGSCPPPGW_RechargeBalanceParser parser = null;
	
	protected  In_SGSCPPPGW_RechargeBalanceParser() {
	  
	}
	public static In_SGSCPPPGW_RechargeBalanceParser getInstance() {
		if(parser == null) {
			parser = new In_SGSCPPPGW_RechargeBalanceParser();
		}
		return parser;
	}
	
	public Param_SGSCPPPGW_RechargeBalance doParser(EquinoxRawData eqxRawData) {
		Param_SGSCPPPGW_RechargeBalance ppgwRechargeBalanceParameter = new Param_SGSCPPPGW_RechargeBalance();
		
		try {
			Serializer serializer = new Persister();
			ppgwRechargeBalanceParameter = serializer.read(Param_SGSCPPPGW_RechargeBalance.class, eqxRawData.getRawDataMessage() );
			
			ppgwRechargeBalanceParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error rechargeBalance parser");
			return ppgwRechargeBalanceParameter;
		}
		
		if (ppgwRechargeBalanceParameter.getBalance() == null)
			return ppgwRechargeBalanceParameter;
		if (ppgwRechargeBalanceParameter.getExpire() == null)
			return ppgwRechargeBalanceParameter;
		if (ppgwRechargeBalanceParameter.getRes() == null)
			return ppgwRechargeBalanceParameter;
		if (ppgwRechargeBalanceParameter.getStatus() == null)
			return ppgwRechargeBalanceParameter;
		if (ppgwRechargeBalanceParameter.getVoice() == null)
			return ppgwRechargeBalanceParameter;
		
		ppgwRechargeBalanceParameter.setNotMissing(true);
		
		if (ppgwRechargeBalanceParameter.isNotMissing())
			ppgwRechargeBalanceParameter.setValid(true);
		
		return ppgwRechargeBalanceParameter;
	}
	
}
