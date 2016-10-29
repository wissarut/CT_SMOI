package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BOS_AccountRecharge;
import ct.af.message.incoming.parser.bos.DiameterCCA;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;

public class In_BOS_AccountRechargeParser {
	
	private static In_BOS_AccountRechargeParser parser = null;
	
	protected  In_BOS_AccountRechargeParser() {
	  
	}
	public static In_BOS_AccountRechargeParser getInstance() {
		if(parser == null) {
			parser = new In_BOS_AccountRechargeParser();
		}
		return parser;
	}
	
	public Param_BOS_AccountRecharge doParser(EquinoxRawData eqxRawData) {
		Param_BOS_AccountRecharge accountRechargeCCRParameter = new Param_BOS_AccountRecharge();
		
		DiameterCCA dcca = new DiameterCCA();
        DiameterCreditControlAnswer cca = dcca.createDiameterCreditControlAnswer(eqxRawData.getRawDataMessage());
        
        accountRechargeCCRParameter.setDiamAns(cca);
      
		if(!StringUtils.isBlank(accountRechargeCCRParameter.getDiamAns().getResultCode())) {
			
			if(accountRechargeCCRParameter.getDiamAns().getResultCode().equals("2001")) {
				
				accountRechargeCCRParameter.setRespResultCode(EResultCode.RE2001);
				accountRechargeCCRParameter.setNotMissing(true);
				accountRechargeCCRParameter.setValid(true);
				return accountRechargeCCRParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return accountRechargeCCRParameter;
				
			}
			
		} else {
			
			accountRechargeCCRParameter.setRespResultCode(EResultCode.RE40301);
			return accountRechargeCCRParameter;
			
		}
		
	}
	
}