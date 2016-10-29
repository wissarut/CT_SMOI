package ct.af.message.outgoing;

import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.ECommand;
import ct.af.enums.EEventType;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_BOS_SubscriberAccountEnquiry {//

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.CREDIT_CONTROL.getCType());
		eqxRawData.setName(EProtocal.DIAMETER.toString());
		eqxRawData.setTo(Config.BOS_INTERFACE);
		
		String qType = (afSubIns.getSubInitCmd().equals(ECommand.DISPPPSINFO.getCommand()))? "0" : "1";
		
		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("<Session-Id value = \""+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+";"+Config.formatDateWithSemiCo.print(new DateTime())+"\" />"
				+ "<Origin-Host value = \""+Config.INGW_SMOI_ORIGIN_HOST+"\" />"
				+ "<Origin-Realm value = \""+Config.INGW_SMOI_ORIGIN_REALM+"\" />"
				+ "<Destination-Host value = \""+afSubIns.getE01DestHost()+"\" />"
				+ "<Destination-Realm value = \""+afSubIns.getE01DestRealm()+"\" />"
				+ "<Auth-Application-Id value = \"4\" />"
				+ "<Service-Context-Id value = \"AccountQuery@ais.co.th\" />"
				+ "<CC-Request-Type value = \"4\" />"
				+ "<CC-Request-Number value = \"0\" />"
				+ "<Event-Timestamp value=\""+AFUtils.timestampSinceFirstJan1900()+"\" />"
				+ "<Subscription-Id>"
					+ "<Subscription-Id-Type value = \"0\" />"
					+ "<Subscription-Id-Data value = \""+afSubIns.getSubMSISDN()+"\" />"
				+ "</Subscription-Id>"
				+ "<Service-Information>"
					+ "<IN-Information>"
						+ "<Query-Type value = \""+qType+"\" />"
						+ "<Access-Method value = \""+Config.CCR_ACCESS_METHOD+"\" />"
					+ "</IN-Information>"
				+ "</Service-Information>" );

		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.BOS_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BOS_ACCOUNTQUERY_REQUEST);
		
		return eqxRawData;
	}
}
