package ct.af.message.outgoing;

import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.EEventType;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_BOS_AccountRecharge {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
	
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.CREDIT_CONTROL.getCType());
		eqxRawData.setName(EProtocal.DIAMETER.toString());
		eqxRawData.setTo(Config.BOS_INTERFACE);
		
		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("<Session-Id value = \""+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+";"+Config.formatDateWithSemiCo.print(new DateTime())+"\" />"
				+ "<Origin-Host value = \""+Config.INGW_SMOI_ORIGIN_HOST+"\" />"
				+ "<Origin-Realm value = \""+Config.INGW_SMOI_ORIGIN_REALM+"\" />"
				+ "<Destination-Host value = \""+afSubIns.getE01DestHost()+"\" />"
				+ "<Destination-Realm value = \""+afSubIns.getE01DestRealm()+"\" />"
				+ "<Auth-Application-Id value = \"4\" />"
				+ "<Service-Context-Id value = \"TopupBalance@ais.co.th\" />"
				+ "<CC-Request-Type value = \"4\" />"
				+ "<CC-Request-Number value = \"0\" />"
				+ "<Event-Timestamp value=\""+AFUtils.timestampSinceFirstJan1900()+"\" />"
				+ "<Subscription-Id>"
					+ "<Subscription-Id-Type value = \"0\" />"
					+ "<Subscription-Id-Data value = \""+afSubIns.getSubMSISDN()+"\" />"
				+ "</Subscription-Id>"
				+ "<Requested-Action value = \"0\" />"
				+ "<Service-Information>"
					+ "<IN-Information>"
						+ "<Etopup-Session-Id value = \""+afSubIns.getClientData().getBatchno()+afSubIns.getClientData().getSsid()+"\" />"
						+ "<Recharge-Number value = \"*120\" />"
						+ "<Recharge-Money>"
							+ "<Money value = \""+afSubIns.getClientData().getFee()+"\" />"
//							+ "<Active-Day value = \"30\" />" // Pending
						+ "</Recharge-Money>"
						+ "<Card-PIN value = \""+afSubIns.getClientData().getPin()+"\" />" // Pending
						+ "<Recharge-Method value = \"8\" />"
						+ "<Recharge-Partner-Id value = \""+afSubIns.getClientData().getPin()+"\" />"
						+ "<Recharge-Partner-Id value = \""+afSubIns.getClientData().getDat()+"\" />"
//						+ "<Channel-Id value = \"16\" />" // Pending
//						+ "<Account-Type value = \"\" />" // Pending
//						+ "<Transparent-Data-1 value = \""+""+"\" />" // Pending
//						+ "<Transparent-Data-2 value = \"\" />" // Pending
//						+ "<Transparent-Data-3 value = \"\" />" // Pending
					+ "</IN-Information>"
				+ "</Service-Information>");
	
		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.BOS_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BOS_ACCOUNTRECHARGE_REQUEST);
		
		return eqxRawData;
	}

}
