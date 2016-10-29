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

public class Out_AMF_CheckBalance {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.EXTENDED.getCType());
		eqxRawData.setName(EProtocal.LDAP.toString());
		eqxRawData.setTo(Config.AMF_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.OID.getEqxMsg(), "0.0.17.1218.8.7.9");
		eqxRawData.setRawDataAttributes(map);
		
		String balanceType = "1";
		
		if(afSubIns.getSubInitCmd().equals(ECommand.ACTIVATEPPSSINGSUB.getCommand())) {
			balanceType = "1";
		} else if(afSubIns.getSubInitCmd().equals(ECommand.DISPPPSINFO.getCommand())) {
			balanceType = "4";
		}
		
		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("<AVP type=\"methodVersion\">"
				+ "<vals value=\"1\"/>"
				+ "</AVP>"
				+ "<AVP type=\"subscriptionId\">"
				+ "<vals value=\"0|"+afSubIns.getSubMSISDN()+"\"/>"
				+ "</AVP>"
				+ "<AVP type=\"balanceType\">"
				+ "<vals value=\""+balanceType+"\"/>"
				+ "</AVP>");
		
		if(afSubIns.getSubInitCmd().equals(ECommand.DISPPPSINFO.getCommand())) {
			if(ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getClientData().getPrmmoneyusageinfo().equals("1")) {
				strbuilder.append("<AVP type=\"requestPromoDetails\">"
						+ "<vals value=\""+ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getClientData().getPrmmoneyusageinfo()+"\"/>"
						+ "</AVP>");
			}
		}

		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.AMF_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_AMF_CHECKBALANCE_REQUEST);
		
		return eqxRawData;
	}

}
