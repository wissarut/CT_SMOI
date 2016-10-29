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

public class Out_BOS_FreeResourceAdjustment {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.CREDIT_CONTROL.getCType());
		eqxRawData.setName(EProtocal.DIAMETER.toString());
		eqxRawData.setTo(Config.BOS_INTERFACE);
		
		String transparentStr = "";
		if(afSubIns.getSubInitCmd().equals(ECommand.SETPPSREWARD.getCommand()))
		{
			transparentStr = "<Transparent-Data-3 value = \"\" />";
		} else {
			String transparent1Str = (afSubIns.getClientData().getMerchant() == null)? "<Transparent-Data-1 value = \"\" />" : "<Transparent-Data-1 value = \""+AFUtils.stringToHex(afSubIns.getClientData().getMerchant())+"\" />";
			String transparent2Str = (afSubIns.getClientData().getService() == null)? "<Transparent-Data-2 value = \"\" />" : "<Transparent-Data-2 value = \""+AFUtils.stringToHex(afSubIns.getClientData().getService())+"\" />";
			transparentStr = transparent1Str + transparent2Str + "<Transparent-Data-3 value = \"\" />";
		}

		String modifyAccountInfoStr = "";
		if(afSubIns.getClientData().getPrmmoney() != null
				&& !afSubIns.getClientData().getPrmmoney().equals(""))
		{
			modifyAccountInfoStr = modifyAccountInfoStr + "<Resource-Id value = \""+Config.RESOURCE_ID_PRMMONEY+"\" />"
								+ "<Modify-Amount value = \""+afSubIns.getClientData().getPrmmoney()+"\" />"
								+ "<Validity-Amount value = \""+""+"\" />"
								+ "<Deduct-Type value = \""+"1"+"\" />" //
								+ "<Measure-Id value = \""+"11101"+"\" />"; // Pending
		}
		if(afSubIns.getClientData().getPrmsm() != null
				&& !afSubIns.getClientData().getPrmsm().equals(""))
		{
			modifyAccountInfoStr = modifyAccountInfoStr + "<Resource-Id value = \""+Config.RESOURCE_ID_PRMSM+"\" />"
								+ "<Modify-Amount value = \""+afSubIns.getClientData().getPrmsm()+"\" />"
								+ "<Validity-Amount value = \""+""+"\" />"
								+ "<Deduct-Type value = \""+"1"+"\" />" //
								+ "<Measure-Id value = \""+"109"+"\" />"; // Pending
		}
		if(afSubIns.getClientData().getPrmminute() != null
				&& !afSubIns.getClientData().getPrmminute().equals(""))
		{
			modifyAccountInfoStr = modifyAccountInfoStr + "<Resource-Id value = \""+Config.RESOURCE_ID_PRMMINUTE+"\" />"
								+ "<Modify-Amount value = \""+afSubIns.getClientData().getPrmminute()+"\" />"
								+ "<Validity-Amount value = \""+""+"\" />"
								+ "<Deduct-Type value = \""+"1"+"\" />" //
								+ "<Measure-Id value = \""+"102"+"\" />"; // Pending
		}
		if(afSubIns.getClientData().getFreecalltimes() != null
				&& !afSubIns.getClientData().getFreecalltimes().equals(""))
		{
			modifyAccountInfoStr = modifyAccountInfoStr + "<Resource-Id value = \""+Config.RESOURCE_ID_FREECALLTIMES+"\" />"
								+ "<Modify-Amount value = \""+afSubIns.getClientData().getFreecalltimes()+"\" />"
								+ "<Validity-Amount value = \""+""+"\" />"
								+ "<Deduct-Type value = \""+"1"+"\" />" //
								+ "<Measure-Id value = \""+"109"+"\" />"; // Pending
		}
		if(afSubIns.getClientData().getFreerbtsong() != null
				&& !afSubIns.getClientData().getFreerbtsong().equals(""))
		{
			modifyAccountInfoStr = modifyAccountInfoStr + "<Resource-Id value = \""+Config.RESOURCE_ID_FREERBTSONG+"\" />"
								+ "<Modify-Amount value = \""+afSubIns.getClientData().getFreerbtsong()+"\" />"
								+ "<Validity-Amount value = \""+""+"\" />"
								+ "<Deduct-Type value = \""+"1"+"\" />" //
								+ "<Measure-Id value = \""+"109"+"\" />"; // Pending
		}
		
		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("<Session-Id value = \""+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+";"+Config.formatDateWithSemiCo.print(new DateTime())+"\" />"
				+ "<Origin-Host value = \""+Config.INGW_SMOI_ORIGIN_HOST+"\" />"
				+ "<Origin-Realm value = \""+Config.INGW_SMOI_ORIGIN_REALM+"\" />"
				+ "<Destination-Host value = \""+afSubIns.getE01DestHost()+"\" />"
				+ "<Destination-Realm value = \""+afSubIns.getE01DestRealm()+"\" />"
				+ "<Auth-Application-Id value = \"4\" />"
				+ "<Service-Context-Id value = \"AdjustAsset@ais.co.th\" />"
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
						+ "<Etopup-Session-Id value = \""+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+"\" />"
						+ "<Modify-Type value = \""+afSubIns.getClientData().getFlag()+"\" />"
						+ transparentStr
						+ "<Modify-Account-Info>"
							+ modifyAccountInfoStr
						+ "</Modify-Account-Info>"
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
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BOS_FREERESOURCEADJUSTMENT_REQUEST);
		
		return eqxRawData;
	}

}
