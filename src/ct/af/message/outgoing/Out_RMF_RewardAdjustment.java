package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EMethod;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class Out_RMF_RewardAdjustment {//INCOMPLETE

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTPLAIN.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.RMF_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
			map.put(EEqxMsg.URL.getEqxMsg(), Config.URL_RMF_REWARDADJUST);
				
		StringBuilder strbuilder = new StringBuilder();
		
		String rewardType = "";
		
		if(afSubIns.getClientData().getResetSecondpocket().equals("1")) {
			rewardType = "2";
			afSubIns.getClientData().setResetSecondpocket("0");
		} else if(afSubIns.getClientData().getResetPrmmoney().equals("1")) {
			rewardType = "3";
			afSubIns.getClientData().setResetPrmmoney("0");
		}
			
		strbuilder.append("{\"sessionId\":\""+afSubIns.getClientData().getSsid()+"\""
				+ ",\"clientName\":\"SGSCP\""
				+ ",\"userId\":{"
					+ "\"userIdType\":\"0\""
					+ ",\"userIdData\":\""+afSubIns.getSubMSISDN()+"\""
				+ "}"
				+ ",\"eventTimeStamp\":\""+Config.formatDateWithBetDT.print(new DateTime())+"\""
				+ ",\"rewardType\":\""+rewardType+"\""
				+ ",\"balanceLimitMode\":\"0\""
				+ ",\"balanceAdjustMode\":\"1\""
				+ ",\"balance\":\""+Config.RMF_ADJUSTBALANCE+"\""
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
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_RMF_REWARDADJUSTMENT_REQUEST);
		
		return eqxRawData;
	}

}
