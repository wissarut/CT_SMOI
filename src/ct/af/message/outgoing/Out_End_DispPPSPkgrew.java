package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EFinalCode;
import ct.af.enums.EFunctionGroup;
import ct.af.enums.EMethod;
import ct.af.enums.EProtocal;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_BOS_SubscriberAccountEnquiry;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;
import phoebe.eqx.model.dcc.cca.FreeResInfo;

public class Out_End_DispPPSPkgrew {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
	
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.RESPONSE.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(afSubIns.getSubInitOrig());
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
		eqxRawData.setRawDataAttributes(map);
		
		StringBuilder strbuilder = new StringBuilder();
		
		EFinalCode finaleCode = afSubIns.getSubFinalCode();
		
		strbuilder.append("<vcrr>"
				+ "<res>"+finaleCode.getFinalResultCode()+"</res>"
				+ "<desc>"+finaleCode.getFinalDesc()+"</desc>");	
		
		if(finaleCode.getFinalResultCode().equals("000"))
		{
			if(!ec02Instance.getAFInstance().getMainProfile().equals(EFunctionGroup.RTBS.toString())) {

			Param_BOS_SubscriberAccountEnquiry respParam = (Param_BOS_SubscriberAccountEnquiry) afSubIns.getSubResponseParam();
			DiameterCreditControlAnswer diameterResp = respParam.getDiamAns();
			
			String customerLifeCycleState = "";
			String rewprmminute = "";
			String rewprmminuteexp = "";
			String rewprmsm = "";
			String rewprmsmexp = "";
			
			try {
				
				customerLifeCycleState = AFUtils.hexToString(diameterResp.getServiceInformation().getInInformation().getCustomerLifeCycleState());
			
			} catch(Exception ex) {
				AppLog.w("Cannot get value !");
			}
			
			List<FreeResInfo> freeResInfo = diameterResp.getServiceInformation().getInInformation().getFreeResInfo();
			
			if(freeResInfo != null) {
				
				int remainingAmountRewprmminute = 0;
				int remainingAmountRewprmsm = 0;
				
				for(FreeResInfo freeResInfos:freeResInfo) {
					
					//String validityDateStr = AFUtils.hexToString(freeResInfos.getValidityDate());
					String expireDateStr = AFUtils.hexToString(freeResInfos.getExpireDate());
					
					//DateTime validityDate = Config.formatDate.parseDateTime(validityDateStr);
					DateTime expireDate = Config.formatDate.parseDateTime(expireDateStr);

					if(freeResInfos.getResourceGroupId().startsWith("112")) {
					
						rewprmminuteexp =  Config.formatDate.print(expireDate);
						
						//if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
							int amount = Integer.parseInt(freeResInfos.getRemainingAmount());
							remainingAmountRewprmminute = remainingAmountRewprmminute+amount;
						//}
						
					} else if(freeResInfos.getResourceGroupId().startsWith("172")) {
					
						rewprmsmexp =  Config.formatDate.print(expireDate);
						
						//if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
							int amount = Integer.parseInt(freeResInfos.getRemainingAmount());
							remainingAmountRewprmsm = remainingAmountRewprmsm+amount;
						//}
						
					}
					
				}
				
				if(remainingAmountRewprmminute > 0) {
					rewprmminute = String.valueOf(remainingAmountRewprmminute);
				}
				
				if(remainingAmountRewprmsm > 0) {
					rewprmsm = String.valueOf(remainingAmountRewprmsm);
				}
				
			}
			
			strbuilder.append("<state>"+customerLifeCycleState+"</state>"
								+ "<rewpoint></rewpoint>"				
								+ "<rewpointexp></rewpointexp>"				
								+ "<rewcalldis></rewcalldis>"				
								+ "<rewcalldisexp></rewcalldisexp>"			
								+ "<rewsmdis></rewsmdis>"					
								+ "<rewsmdisexp></rewsmdisexp>"				
								+ "<rewprmminute>"+rewprmminute+"</rewprmminute>" //not sure between "get from"&"summary of"
								+ "<rewprmminuteexp>"+rewprmminuteexp+"</rewprmminuteexp>"
								+ "<rewprmsm>"+rewprmsm+"</rewprmsm>"			//not sure between "get from"&"summary of"
								+ "<rewprmsmexp>"+rewprmsmexp+"</rewprmsmexp>"
								+ "<pkg1id></pkg1id>"
								+ "<pkg1name></pkg1name>"
//								+ "<pkg1leftmin>"+"</pkg1leftmin>"		//not found in T3
//								+ "<pkg1leftsm>"+"</pkg1leftsm>"
								+ "<pkg1valueexp></pkg1valueexp>"
								+ "<pkg2id>0</pkg2id>"
								+ "<pkg2name></pkg2name>"
//								+ "<pkg2leftmin>0</pkg2leftmin>"		//not found in T3
//								+ "<pkg2leftsm>0</pkg2leftsm>"
								+ "<pkg2valueexp></pkg2valueexp>"
								+ "<pkg3id>0</pkg3id>"
								+ "<pkg3name></pkg3name>"
//								+ "<pkg3leftmin>0</pkg3leftmin>"		//not found in T3
//								+ "<pkg3leftsm>0</pkg3leftsm>"
								+ "<pkg3valueexp></pkg3valueexp>"
								+ "<pkg4id>0</pkg4id>"
								+ "<pkg4name></pkg4name>"
//								+ "<pkg4leftmin>0</pkg4leftmin>"		//not found in T3
//								+ "<pkg4leftsm>0</pkg4leftsm>"
								+ "<pkg4valueexp></pkg4valueexp>"
								+ "<pkg5id>0</pkg5id>"
								+ "<pkg5name></pkg5name>"
//								+ "<pkg5leftmin>0</pkg5leftmin>"		//not found in T3
//								+ "<pkg5leftsm>0</pkg5leftsm>"
								+ "<pkg5valueexp></pkg5valueexp>");
			}
		}
		
		strbuilder.append("</vcrr>");

		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = afSubIns.getSubInitInvoke();//new AFUtils().invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubIntCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(10)));
		
		//-- Stats --//
		afSubIns.addStatsOut(finaleCode.getStat());
	
		return eqxRawData;
	}

}
