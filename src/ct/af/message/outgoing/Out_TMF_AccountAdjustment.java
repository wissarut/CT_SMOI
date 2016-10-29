package ct.af.message.outgoing;

import ct.af.utils.StringUtils;
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

public class Out_TMF_AccountAdjustment {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
	
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.CREDIT_CONTROL.getCType());
		eqxRawData.setName(EProtocal.DIAMETER.toString());
		eqxRawData.setTo(Config.TMF_INTERFACE);
		
		StringBuilder strbuilder = new StringBuilder();
		
		String modifyAmount = "";
		String validityAmount = "";
		String modifyType = afSubIns.getClientData().getFlag();
		
		if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSVALIDITY.getCommand())) {
			modifyAmount = "0";
			validityAmount = afSubIns.getClientData().getIncrement();
		} else if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSMULTIATTR.getCommand())) {
			validityAmount = afSubIns.getClientData().getValidity();
			if(StringUtils.isBlank(afSubIns.getClientData().getBalance())) {
				modifyAmount = "0";
			} else {
				modifyAmount = afSubIns.getClientData().getBalance();
			}
		} else if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSMULTIWALLET.getCommand())) {
			modifyType = "1";
			modifyAmount = "0";
		}

		strbuilder.append("<Session-Id value=\""+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+"+"+Config.formatDateWithSemiCo.print(new DateTime())+"\"/>"			
				+ "<Origin-Host value=\""+Config.INGW_SMOI_ORIGIN_HOST+"\"/>"			
				+ "<Origin-Realm value=\""+Config.INGW_SMOI_ORIGIN_REALM+"\"/>"			
				+ "<Destination-Host value=\""+Config.TMF_DESTINATION_HOST+"\"/>"			
				+ "<Destination-Realm value=\""+Config.TMF_DESTINATION_REALM+"\"/>"			
				+ "<Auth-Application-Id value=\"4\"/>"			
				+ "<Service-Context-Id value=\"AccountAdjustment@ais.co.th\"/>"			
				+ "<CC-Request-Type value=\"4\"/>"			
				+ "<CC-Request-Number value=\"0\"/>"			
				+ "<Event-Timestamp value=\""+AFUtils.timestampSinceFirstJan1900()+"\"/>"			
				+ "<Subscription-Id>"			
					+ "<Subscription-Id-Type value=\"0\"/>"		
					+ "<Subscription-Id-Data value=\""+afSubIns.getSubMSISDN()+"\"/>"		
				+ "</Subscription-Id>"			
				+ "<Requested-Action value=\"0\"/>"			
				+ "<Service-Information>"			
					+ "<IN-Information>"		
						+ "<Etopup-Session-Id value=\""+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+"\"/>"	
						+ "<Modify-Type value=\""+modifyType+"\"/>"	
						+ "<Modify-Account-Info>");

		strbuilder.append("<Modify-Amount value=\""+modifyAmount+"\"/>");

		if(StringUtils.isNotBlank(validityAmount)) {
			strbuilder.append("<Validity-Amount value=\""+validityAmount+"\"/>");
		}
		strbuilder.append("<Deduct-Type value=\"1\"/>"
						+ "</Modify-Account-Info>");
		
		if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSMULTIATTR.getCommand())) {
			
			strbuilder.append("<Transparent-Data-1 value=\""+afSubIns.getClientData().getTransparent_data1()+"\"/>"
							+ "<Transparent-Data-2 value=\""+afSubIns.getClientData().getTransparent_data2()+"\"/>"
							+ "<Transparent-Data-3 value=\""+afSubIns.getClientData().getTransparent_data3()+"\"/>");
			
		}
		
		strbuilder.append("</IN-Information>"		
				+ "</Service-Information>"			
				+ "<Measure-Id value=\"11101\"/>");

		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.TMF_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_TMF_ACCOUNTADJUSTMENT_REQUEST);
		
		return eqxRawData;
	}

}
