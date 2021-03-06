package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class Out_SDF_GetSidNotificationElement {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTPLAIN.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.SDF_INTERFACE);
		
		AFSubInstance subInsForState = null;
		
		if(StringUtils.isNotBlank(afSubIns.getSubHostInsNo())) {
			subInsForState = ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo());
		} else {
			subInsForState = afSubIns;
		}
		
		String promoNameNoti = "";
		AppLog.d("promoNameNoti : "+subInsForState.getRewardId());
	
		if(subInsForState.getPromoNameNoti().size() > 1) {
			promoNameNoti = subInsForState.getPromoNameNoti().get(subInsForState.getPromoNameNoti().size() - 1);
			subInsForState.getPromoNameNoti().remove(subInsForState.getPromoNameNoti().size() - 1);
			AppLog.d("promoNameNoti : "+subInsForState.getPromoNameNoti());
		} else {
			promoNameNoti = subInsForState.getPromoNameNoti().get(0);
		}
		
		Map<String, String> map = new HashMap<String, String>();
			map.put("method", "GET");
			map.put(EEqxMsg.URL.getEqxMsg(), "/v1/configuration/o/nss/o/sid/o/notificationElements/sidNotificationElem/"+promoNameNoti+".json?scope=one&fields=sidTextMessage,sidTextUnicode");
			map.put(EEqxMsg.VAL.getEqxMsg(), "");
		eqxRawData.setRawDataAttributes(map);
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.SDF_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SDF_GET_SIDNOTIFICATIONELEMENT_REQUEST);
		
		return eqxRawData;
	}

}
