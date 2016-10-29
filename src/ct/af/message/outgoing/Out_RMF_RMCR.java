package ct.af.message.outgoing;

import org.joda.time.DateTime;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_RMF_RMCR {
	
public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
//		EquinoxRawData eqxRawData = new EquinoxRawData();
//		eqxRawData.setType(EEventType.REQUEST.getEventType());
//		eqxRawData.setCType(ECType.TEXTPLAIN.getCType());
//		eqxRawData.setName(EProtocal.HTTP.toString());
//		eqxRawData.setTo(Config.RMF_INTERFACE);
//		
//		Map<String, String> map = new HashMap<String, String>();
//			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
//			map.put(EEqxMsg.URL.getEqxMsg(), Config.URL_RMF_REWARDMONITORING);
//				
//		StringBuilder strbuilder = new StringBuilder();
//		
//		strbuilder.append("{\"resultCode\":\"20000\","
//				+ "\"developerMessage\":\"Success\"}");
//
//		map.put("val", strbuilder.toString());
//		eqxRawData.setRawDataAttributes(map);
		
		//-- Invoke --//
//		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
//		eqxRawData.setInvoke(invoke);
//		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Integer.parseInt(afSubIns.getDiffTimeout()))));
		
		//-- Stats --//
//		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_RMF_RMCR_REQUEST);
		
		return null;
	}

}
