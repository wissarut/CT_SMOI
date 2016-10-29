package ct.af.message.outgoing;

import ct.af.enums.*;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_DS2A_InquirySubscriber;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

public class Out_RMF_RewardOffering {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTPLAIN.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.RMF_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
			map.put(EEqxMsg.URL.getEqxMsg(), Config.URL_RMF_REWARDOFFERING);				
				
		StringBuilder strbuilder = new StringBuilder();
		Param_DS2A_InquirySubscriber inquiryparam = afSubIns.getInquirySubParam();
		
		int validityHours = 0;
		int expireHours = 0;
		int expireConfHours = 0;
		
		if(StringUtils.isNotBlank(afSubIns.getClientData().getValidity())) {
			int validity = Integer.parseInt(afSubIns.getClientData().getValidity());
			validityHours = validity * 24;
		}
		if(StringUtils.isNotBlank(afSubIns.getClientData().getExpire())) {
			
			int expire = Integer.parseInt(afSubIns.getClientData().getExpire());
			expireHours = expire * 24;
					
		} else{
			
			int expireConf = Integer.parseInt(Config.DEFAULT_VALIDITY);
			expireConfHours = expireConf * 24;
		}
		
		String rewardType = "";
		String balance = "";
		String validityy = "";
		if(afSubIns.getRmfOffering().equalsIgnoreCase("MainBalance")) {
			rewardType = "0";
			balance = afSubIns.getClientData().getBalance();
			validityy = Integer.toString(validityHours);
		}
		if(afSubIns.getRmfOffering().equalsIgnoreCase("MonetaryUsage")) {
			rewardType = "2";
			balance = afSubIns.getClientData().getPrmmoney();

			if(StringUtils.isNotBlank(afSubIns.getClientData().getExpire())) {
				validityy = Integer.toString(expireHours);
			} else {
				validityy = Integer.toString(expireConfHours);
			}
		}
		if(afSubIns.getRmfOffering().equalsIgnoreCase("FreeResource1") || afSubIns.getRmfOffering().equalsIgnoreCase("FreeResource2")) {
			rewardType = "3";
			
			if(StringUtils.isNotBlank(afSubIns.getClientData().getExpire())) {
				validityy = Integer.toString(expireHours);
			} else {
				validityy = Integer.toString(expireConfHours);
			}
		}
		
		strbuilder.append("{\"sessionId\":\""+afSubIns.getClientData().getSsid()+"\""
				+ ",\"clientName\":\""+afSubIns.getClientData().getSgw()+"\""
				+ ",\"userId\":{"
					+ "\"userIdType\":\"0\""
					+ ",\"userIdData\":\""+afSubIns.getSubMSISDN()+"\""
				+ "}"
				+ ",\"profile\":{"
				+ "\"subscriptionState\":\""+inquiryparam.getResultInfo().get(0).getDs2state()+"\""
				+ ",\"timeDrivenState\":\""+inquiryparam.getResultInfo().get(0).getTimeDrivenState()+"\""
				+ ",\"servicePackageId\":\""+inquiryparam.getResultInfo().get(0).getDs2servPackageId()+"\""
				+ ",\"cos\":\""+inquiryparam.getAccountingInfo().get(0).getDs2classOfservice()+"\""
				+ ",\"category\":\""+inquiryparam.getResultInfo().get(0).getDs2customerCategory()+"\""
				+ ",\"subCategory\":\""+inquiryparam.getResultInfo().get(0).getDs2customerSubCategory()+"\""
				+ ",\"segment\":\""+inquiryparam.getResultInfo().get(0).getDs2customerSegment()+"\""
				+ ",\"customerId\":\""+inquiryparam.getResultInfo().get(0).getCustomerId()+"\""
				+ ",\"language\":{"
				+ "\"language\":\""+inquiryparam.getResultInfo().get(0).getDs2language()+"\""
				+ ",\"smsLanguage\":\""+inquiryparam.getResultInfo().get(0).getSmsLanguage()+"\""
				+ ",\"ussdLanguage\":\""+inquiryparam.getResultInfo().get(0).getUssdLanguage()+"\""
				+ "}"
				+ ",\"baId\":\""+inquiryparam.getResultInfo().get(0).getBaId()+"\""
				+ ",\"caId\":\""+inquiryparam.getResultInfo().get(0).getCaId()+"\""
				+ "}"
				+ ",\"eventTimeStamp\":\""+Config.formatDateWithBetDT.print(new DateTime())+"\""
				+ ",\"rewardType\":\""+rewardType+"\""
				+ ",\"balance\":\""+balance+"\""
				+ ",\"validity\":\""+validityy+"\"");
				
				if(afSubIns.getRmfOffering().equalsIgnoreCase("MonetaryUsage")) {
					strbuilder.append(",\"resourceId\":\""+Config.INS_RESOURCE_ID_PRMMONEY+"\"");
				}
				
				if(afSubIns.getRmfOffering().equalsIgnoreCase("FreeResource1")) {
					strbuilder.append(",\"counterValue\":\""+afSubIns.getClientData().getPrmsm()+"\""
							+ ",\"package\":{"
							+ "\"packageName\":\""+Config.DEFAULT_PACKNAMEPRMSM+"\""
							+ "}");
				}
				
				if(afSubIns.getRmfOffering().equalsIgnoreCase("FreeResource2")) {
					strbuilder.append(",\"counterValue\":\""+afSubIns.getClientData().getPrmminute()+"\""
							+ ",\"package\":{"
							+ "\"packageName\":\""+Config.DEFAULT_PACKNAMEPRMMINUTE+"\""
							+ "}");
				}
				
				strbuilder.append("}");

		map.put("val", strbuilder.toString());
		eqxRawData.setRawDataAttributes(map);
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.RMF_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_RMF_REWARDOFFERING_REQUEST);
		
		return eqxRawData;
	}

}
