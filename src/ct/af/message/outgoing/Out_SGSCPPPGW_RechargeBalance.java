package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;
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

public class Out_SGSCPPPGW_RechargeBalance {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
	
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTPLAIN.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.SGSCPPPGW_INTERFACE);
		
		StringBuilder strbuilder = new StringBuilder();
		
		String scp = "";
		if(ec02Instance.getAFInstance().getMainLocation() != null) {
			scp = "&amp;scp="+ec02Instance.getAFInstance().getMainLocation();
		}
		
		strbuilder.append(StringEscapeUtils.escapeHtml4(afSubIns.getSubDataForward())+scp);
	
		Map<String, String> map = new HashMap<String, String>();
		map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
		map.put(EEqxMsg.VAL.getEqxMsg(), strbuilder.toString());
		eqxRawData.setRawDataAttributes(map);
		
		//eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd()
				,afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.SGSCPPPGW_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_PPGW_RECHARGEBALANCE_REQUEST);
		
		return eqxRawData;
	}

}
