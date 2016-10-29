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
import ct.af.message.incoming.parameter.Param_SDF_GetAmfCounter;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class Out_SDF_PutAmfCounter {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTPLAIN.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.SDF_INTERFACE);
		
		Param_SDF_GetAmfCounter sdfAmfCounter = (Param_SDF_GetAmfCounter) afSubIns.getSubResponseParam();
		
		int newCounterLowerLimit = 0;
		
		if(afSubIns.getClientData().getFlagCreditlimit().equals("1")) {
			
			newCounterLowerLimit = Integer.parseInt(afSubIns.getClientData().getIncrement());
			AppLog.d("newCounterLowerLimit"+newCounterLowerLimit);
			
		} else {
				
			String counterLowerLimit = sdfAmfCounter.getAmfCounter().get(0).getCounterLowerLimit();
			AppLog.d("oldCounterLowerLimit"+counterLowerLimit);
			
			newCounterLowerLimit = Integer.parseInt(counterLowerLimit) - Integer.parseInt(afSubIns.getClientData().getIncrement());
			
			AppLog.d("newCounterLowerLimit"+newCounterLowerLimit);

		}
		
		
		Map<String, String> map = new HashMap<String, String>();
			map.put("method", "PUT");
			map.put(EEqxMsg.URL.getEqxMsg(), "/v1/amf/MSISDN/"+afSubIns.getSubMSISDN()+"/counterId/200010.json");
			map.put(EEqxMsg.VAL.getEqxMsg(), "{\"counterLowerLimit\":\""+newCounterLowerLimit+"\",\"refillStopTime\":\""+sdfAmfCounter.getAmfCounter().get(0).getRefillStopTime()+"\"}");
		eqxRawData.setRawDataAttributes(map);
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.SDF_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SDF_PUT_AMFCOUNTER_REQUEST);
		
		return eqxRawData;
	}

}
