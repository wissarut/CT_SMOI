package ct.af.message.outgoing;

import ct.af.enums.*;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_USMP_FirstActivate;
import ct.af.message.incoming.parameter.Param_USMP_InquiryCounter;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

public class Out_RMF_RewardMonitoring {//INCOMPLETE

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTPLAIN.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.RMF_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
			map.put(EEqxMsg.URL.getEqxMsg(), Config.URL_RMF_REWARDMONITORING);
		
		
		AFSubInstance subInsForState = null;
		
		if(StringUtils.isNotBlank(afSubIns.getSubHostInsNo())) {
			subInsForState = ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo());
		} else {
			subInsForState = afSubIns;
		}
				
		
		String rewardId = "";
		AppLog.d("RW ID : "+subInsForState.getRewardId());
	
		if(subInsForState.getRewardId().size() > 1) {
			rewardId = subInsForState.getRewardId().get(subInsForState.getRewardId().size() - 1);
			subInsForState.getRewardId().remove(subInsForState.getRewardId().size() - 1);
			AppLog.d("RW ID : "+subInsForState.getRewardId());
		} else {
			rewardId = subInsForState.getRewardId().get(0);
		}

		String counterName = null;
		String productNumber = null;
		String templateVersion = null;

		if(subInsForState.getOldCos().equals(subInsForState.getNewCos())) {
			Param_USMP_InquiryCounter inquiryCounterParam = (Param_USMP_InquiryCounter) subInsForState.getSubResponseParamInquiryCounter();

			for(int i=0;i<inquiryCounterParam.getCounterInfo().size();i++) {

				if(!StringUtils.isBlank(inquiryCounterParam.getCounterInfo().get(i).getMainPromotionF())) {
					if(inquiryCounterParam.getCounterInfo().get(i).getMainPromotionF().equals("1")) {
						counterName = inquiryCounterParam.getCounterInfo().get(i).getCounterName();
						productNumber = inquiryCounterParam.getCounterInfo().get(i).getProductNumber();
						templateVersion = inquiryCounterParam.getCounterInfo().get(i).getTemplateVersion();
					}
				}

			}
		} else {
			Param_USMP_FirstActivate firstActParam = subInsForState.getSubResponseParamFirstAct();
			for(int i=0;i<firstActParam.getCounterInfo().size();i++) {

				if(!StringUtils.isBlank(firstActParam.getCounterInfo().get(i).getMainPromotionF())) {
					if(firstActParam.getCounterInfo().get(i).getMainPromotionF().equals("1")) {
						counterName = firstActParam.getCounterInfo().get(i).getCounterName();
						productNumber = firstActParam.getCounterInfo().get(i).getProductNumber();
						templateVersion = firstActParam.getCounterInfo().get(i).getTemplateVersion();
					}
				}

			}

		}
				
		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("{\"sessionId\":\""+subInsForState.getClientData().getSsid()+"\""
				+ ",\"clientName\":\"SGSCP\""
				+ ",\"userId\":{"
					+ "\"userIdType\":\"0\""
					+ ",\"userIdData\":\""+subInsForState.getSubMSISDN()+"\""
				+ "}"
				+ ",\"event\":\"1\""
				+ ",\"eventTimeStamp\":\""+Config.formatDateWithBetDT.print(new DateTime())+"\""
				+ ",\"rewardId\":\""+rewardId+"\""
				+ ",\"osRarFlag\":\"true\""
				+ ",\"waitConfirm\":{"
					+ "\"waitConfirmationFlag\":\"true\""
					+ ",\"callBackIp\":\""+Config.RMF_SMOI_IP+"\""
					+ ",\"callBackPort\":\""+Config.RMF_SMOI_PORT+"\""
				+ "}"
				+ ",\"package\":{"
					+ "\"packageName\":\""+ counterName + "\""
					+ ",\"packageNumber\":\""+ productNumber + "\""
					+ ",\"packageVersion\":\""+templateVersion +"\""
				+ "}"
				+ "}");

		map.put("val", strbuilder.toString());
		eqxRawData.setRawDataAttributes(map);
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.RMF_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_RMF_REWARDMONITORING_REQUEST);
		
		return eqxRawData;
	}

}
