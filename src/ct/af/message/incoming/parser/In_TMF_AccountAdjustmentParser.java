package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_TMF_AccountAdjustment;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_TMF_AccountAdjustmentParser {
	
	private static In_TMF_AccountAdjustmentParser parser = null;
	
	protected  In_TMF_AccountAdjustmentParser() {
	  
	}
	public static In_TMF_AccountAdjustmentParser getInstance() {
		if(parser == null) {
			parser = new In_TMF_AccountAdjustmentParser();
		}
		return parser;
	}
	
	public Param_TMF_AccountAdjustment doParser(EquinoxRawData eqxRawData) {
		Param_TMF_AccountAdjustment tmfAccountAdjustmentParameter = new Param_TMF_AccountAdjustment();
		
		try {
			Serializer serializer = new Persister();
			tmfAccountAdjustmentParameter = serializer.read(Param_TMF_AccountAdjustment.class, "<xml>" + eqxRawData.getRawDataMessage() + "</xml>");
			
			tmfAccountAdjustmentParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error specializedResource parser"+e);
			return tmfAccountAdjustmentParameter;
		}
		
		if(!StringUtils.isBlank(tmfAccountAdjustmentParameter.getResultCode())) {
			
			if(tmfAccountAdjustmentParameter.getResultCode().equals("2001")) {
				
				tmfAccountAdjustmentParameter.setRespResultCode(EResultCode.RE2001);
				tmfAccountAdjustmentParameter.setNotMissing(true);
				tmfAccountAdjustmentParameter.setValid(true);
				return tmfAccountAdjustmentParameter;
				
			} else {
				
				//accountRechargeCCRParameter.setRespResultCode(accountRechargeCCRParameter.getResultCode());
				return tmfAccountAdjustmentParameter;
				
			}
			
		} else {
			
			tmfAccountAdjustmentParameter.setRespResultCode(EResultCode.RE40301);
			return tmfAccountAdjustmentParameter;
			
		}

	}
	

}
