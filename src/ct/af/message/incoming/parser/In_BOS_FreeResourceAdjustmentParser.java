package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_BOS_FreeResourceAdjustment;
import ct.af.message.incoming.parser.bos.DiameterCCA;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;

public class In_BOS_FreeResourceAdjustmentParser {
	
	private static In_BOS_FreeResourceAdjustmentParser parser = null;
	
	protected  In_BOS_FreeResourceAdjustmentParser() {
	  
	}
	public static In_BOS_FreeResourceAdjustmentParser getInstance() {
		if(parser == null) {
			parser = new In_BOS_FreeResourceAdjustmentParser();
		}
		return parser;
	}
	
	public Param_BOS_FreeResourceAdjustment doParser(EquinoxRawData eqxRawData) {
	    Param_BOS_FreeResourceAdjustment freeResourceAdjustmentParameter = new Param_BOS_FreeResourceAdjustment();

	    DiameterCCA dcca = new DiameterCCA();
        DiameterCreditControlAnswer cca = dcca.createDiameterCreditControlAnswer(eqxRawData.getRawDataMessage());
        
        freeResourceAdjustmentParameter.setDiamAns(cca);
      
		if(!StringUtils.isBlank(freeResourceAdjustmentParameter.getDiamAns().getResultCode())) {
			
			if(freeResourceAdjustmentParameter.getDiamAns().getResultCode().equals("2001")) {
				
				freeResourceAdjustmentParameter.setRespResultCode(EResultCode.RE2001);
				freeResourceAdjustmentParameter.setNotMissing(true);
				freeResourceAdjustmentParameter.setValid(true);
				return freeResourceAdjustmentParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return freeResourceAdjustmentParameter;
				
			}
			
		} else {
			
			freeResourceAdjustmentParameter.setRespResultCode(EResultCode.RE40301);
			return freeResourceAdjustmentParameter;
			
		}
		
	}
	
}
