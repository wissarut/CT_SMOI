package ct.af.message.incoming.parser;

import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.Param_USMP_FirstActivate;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_USMP_FirstActivateParser {
	
	private static In_USMP_FirstActivateParser parser = null;
	
	protected  In_USMP_FirstActivateParser() {
	  
	}
	public static In_USMP_FirstActivateParser getInstance() {
		if(parser == null) {
			parser = new In_USMP_FirstActivateParser();
		}
		return parser;
	}
	
	public Param_USMP_FirstActivate doParser(EquinoxRawData eqxRawData) {
		Param_USMP_FirstActivate firstActivateParameter = new Param_USMP_FirstActivate();
		
		try {
			Serializer serializer = new Persister();
			firstActivateParameter = serializer.read(Param_USMP_FirstActivate.class, eqxRawData.getRawDataMessage());
			
			firstActivateParameter.setReceived(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error firstActivate parser"+e);
			return firstActivateParameter;
		}
		
		String mainProF = "";
		String productNumber = "";
		String counterName = "";
		String stopTime = "";

		if(firstActivateParameter.getCounterInfo() != null) {
			for(int i=0;i<firstActivateParameter.getCounterInfo().size();i++) {
				
				if(!StringUtils.isBlank(firstActivateParameter.getCounterInfo().get(i).getMainPromotionF())) {
					if(firstActivateParameter.getCounterInfo().get(i).getMainPromotionF().equals("1")) {
						mainProF = firstActivateParameter.getCounterInfo().get(i).getMainPromotionF();
					}
				}
	
				if(!StringUtils.isBlank(firstActivateParameter.getCounterInfo().get(i).getProductNumber())) {
					productNumber = firstActivateParameter.getCounterInfo().get(i).getProductNumber();
				}
	
				if(!StringUtils.isBlank(firstActivateParameter.getCounterInfo().get(i).getCounterName())) {
					counterName = firstActivateParameter.getCounterInfo().get(i).getCounterName();
					
				}
				if(!StringUtils.isBlank(firstActivateParameter.getCounterInfo().get(i).getRefillStopTime())) {
					stopTime = firstActivateParameter.getCounterInfo().get(i).getRefillStopTime();
					
				}
				
			}
		}
		
		if(!StringUtils.isBlank(firstActivateParameter.getIsSuccess()) && !StringUtils.isBlank(firstActivateParameter.getCode())) {
			
			if(firstActivateParameter.getIsSuccess().equalsIgnoreCase("true") && firstActivateParameter.getCode().equals("VSMP-00000000")
					&& mainProF.length() == 1 && StringUtils.isNotBlank(productNumber) && StringUtils.isNotBlank(counterName)
					&& StringUtils.isNotBlank(stopTime)) {
				
				firstActivateParameter.setRespResultCode(EResultCode.REVSMP00000000);
				firstActivateParameter.setNotMissing(true);
				firstActivateParameter.setValid(true);
				return firstActivateParameter;
				
			} else {
				
				if(firstActivateParameter.getCounterInfo() == null){
					firstActivateParameter.setRespResultCode(EResultCode.REret1);
				}
				if(mainProF.length() != 1) {
					AppLog.e("MainPromotionF : " + mainProF.length());
				}
				if(StringUtils.isBlank(productNumber)) {
					AppLog.e("firstActivateParameter.getCounterInfo().get(i).getProductNumber() is null");
				}
				if(StringUtils.isBlank(counterName)) {
					AppLog.e("firstActivateParameter.getCounterInfo().get(i).getCounterName() is null");
				}
				if(StringUtils.isBlank(stopTime)) {
					AppLog.e("firstActivateParameter.getCounterInfo().get(i).getRefillStopTime() is null");
				}
				//modifySubscriberParameter.setRespResultCode(modifySubscriberParameter.getResultCode());
				firstActivateParameter.setRespResultCode(EResultCode.RE40301);
				return firstActivateParameter;
				
			}
			
		} else {
			
			if(mainProF.length() != 1) {
				AppLog.e("MainPromotionF : " + mainProF.length());
			}
			if(StringUtils.isBlank(productNumber)) {
				AppLog.e("firstActivateParameter.getCounterInfo().get(i).getProductNumber() is null");
			}
			if(StringUtils.isBlank(counterName)) {
				AppLog.e("firstActivateParameter.getCounterInfo().get(i).getCounterName() is null");
			}
			if(StringUtils.isBlank(stopTime)) {
				AppLog.e("firstActivateParameter.getCounterInfo().get(i).getRefillStopTime() is null");
			}
			
			firstActivateParameter.setRespResultCode(EResultCode.RE40301);
			return firstActivateParameter;
		}

	}
	
}
