package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.ECommand;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_SDF_GetAmfCounter {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTPLAIN.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.SDF_INTERFACE);
		
		String url = "";
		
		if(afSubIns.getSubInitCmd().equals(ECommand.DISPPPSINFO.getCommand())) {
			url = "/v1/amf/msisdn/"+afSubIns.getSubMSISDN()+".json?scope=sub&filter=(objectClass=amfCounter)&fields=counterId,personalization";
		} else if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSCREDITLIMIT.getCommand())) {
			url = "/v1/amf/MSISDN/"+afSubIns.getSubMSISDN()+"/counterId/200010.json";
		}
		
		Map<String, String> map = new HashMap<String, String>();
			map.put("method", "GET");
			map.put(EEqxMsg.URL.getEqxMsg(), url);
			map.put(EEqxMsg.VAL.getEqxMsg(), "");
		eqxRawData.setRawDataAttributes(map);
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.SDF_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SDF_GET_AMFCOUNTER_REQUEST);
		
		return eqxRawData;
	}

}
