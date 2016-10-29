package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BOS_MainbalanceAccountAdjustment;
import ct.af.message.incoming.parser.bos.DiameterCCA;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;

public class In_BOS_MainBalanceAccountAdjustmentParser {
	
	private static In_BOS_MainBalanceAccountAdjustmentParser parser = null;
	
	protected  In_BOS_MainBalanceAccountAdjustmentParser() {
	  
	}
	public static In_BOS_MainBalanceAccountAdjustmentParser getInstance() {
		if(parser == null) {
			parser = new In_BOS_MainBalanceAccountAdjustmentParser();
		}
		return parser;
	}
	
	public Param_BOS_MainbalanceAccountAdjustment doParser(EquinoxRawData eqxRawData) {

		Param_BOS_MainbalanceAccountAdjustment mainBalanceAccountAdjustmentParameter = new Param_BOS_MainbalanceAccountAdjustment();
		
		DiameterCCA dcca = new DiameterCCA();
        DiameterCreditControlAnswer cca = dcca.createDiameterCreditControlAnswer(eqxRawData.getRawDataMessage());
        
        mainBalanceAccountAdjustmentParameter.setDiamAns(cca);
      
		if(!StringUtils.isBlank(mainBalanceAccountAdjustmentParameter.getDiamAns().getResultCode())) {
			
			if(mainBalanceAccountAdjustmentParameter.getDiamAns().getResultCode().equals("2001")) {
				
				mainBalanceAccountAdjustmentParameter.setRespResultCode(EResultCode.RE2001);
				mainBalanceAccountAdjustmentParameter.setNotMissing(true);
				mainBalanceAccountAdjustmentParameter.setValid(true);
				return mainBalanceAccountAdjustmentParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return mainBalanceAccountAdjustmentParameter;
				
			}
			
		} else {
			
			mainBalanceAccountAdjustmentParameter.setRespResultCode(EResultCode.RE40301);
			return mainBalanceAccountAdjustmentParameter;
			
		}
		
	}
	
}
