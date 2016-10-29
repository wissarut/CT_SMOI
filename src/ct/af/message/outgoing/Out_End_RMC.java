package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EFinalCode;
import ct.af.enums.EMethod;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_End_RMC {
	
public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.RESPONSE.getEventType());
		eqxRawData.setCType(ECType.TEXTPLAIN.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(afSubIns.getSubInitOrig());
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
//			map.put(EEqxMsg.URL.getEqxMsg(), Config.URL_RMF_REWARDMONITORING);
				
		StringBuilder strbuilder = new StringBuilder();
		
		EFinalCode finaleCode = afSubIns.getSubFinalCode();
		strbuilder.append("{\"resultCode\":\""+finaleCode.getFinalResultCode()+"\","
				+ "\"developerMessage\":\""+finaleCode.getFinalDesc()+"\"}");

		map.put("val", strbuilder.toString());
		eqxRawData.setRawDataAttributes(map);
		
		//-- Invoke --//
		String invoke = afSubIns.getSubInitInvoke();//AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(0)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_RECEIVE_RMF_RMCA_RESPONSE);
		
		return eqxRawData;
	}

}
