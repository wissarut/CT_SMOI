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
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_AMF_ModifyCounters {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.EXTENDED.getCType());
		eqxRawData.setName(EProtocal.LDAP.toString());
		eqxRawData.setTo(Config.AMF_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.OID.getEqxMsg(), "0.0.17.1218.8.7.2");
		eqxRawData.setRawDataAttributes(map);
		
		StringBuilder strbuilder = new StringBuilder();
		
		String sessionInfoPhase = "0";
		
		String validityAdjustment = "";
		String validityReferenceTime = "";
		
		String enforceMaxValidity = "";
		
		if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSVALIDITY.getCommand())) {
			
			validityAdjustment = afSubIns.getClientData().getIncrement();
		
			if(afSubIns.getClientData().getFlag().equals("0")) {
				validityReferenceTime = "1";
			} else if(afSubIns.getClientData().getFlag().equals("1")) {
				validityReferenceTime = "0";
			}
	
			enforceMaxValidity = "1";
		
		}
	
		strbuilder.append("<AVP type=\"methodVersion\">"
				+ "<vals value=\"5\"/>"
				+ "</AVP>"
				+ "<AVP type=\"ereInfo\">"
				+ "<vals value=\"127.0.0.0\"/>"
				+ "</AVP>"
				+ "<AVP type=\"language\">"
				+ "<vals value=\""+afSubIns.getInquirySubParam().getResultInfo().get(0).getSmsLanguage()+"\"/>"
				+ "</AVP>"
				+ "<AVP type=\"subscriptionId\">"
				+ "<vals value=\"0|"+afSubIns.getSubMSISDN()+"\"/>"
				+ "</AVP>"
				+ "<AVP type=\"sessionInfo\">"
				+ "<vals value=\""+afSubIns.getClientData().getSsid()+","+sessionInfoPhase+",,,,\"/>"
				+ "</AVP>");
		
		if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSVALIDITY.getCommand())) {
			
			strbuilder.append("<AVP type=\"validityAdjustment\">"
					  + "<vals value=\""+validityAdjustment+",0,"+validityReferenceTime+"\"/>"
					  + "</AVP>");
			
			strbuilder.append("<AVP type=\"enforceMaxValidity\">"
					  + "<vals value=\""+enforceMaxValidity+"\"/>"
					  + "</AVP>");
			
		}

		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.AMF_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_AMF_MODIFYCOUNTERS_REQUEST);
		
		return eqxRawData;
	}
}
