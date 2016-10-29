package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.ECommand;
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

public class Out_USMP_InquirySubscriberInfo {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {

		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.USMP_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put("method", EMethod.POST.toString());
			map.put("url", Config.URL_USMP_INQUIRYSUBSCRIBERINFO);
			map.put("SOAPAction", "http://vsmp.ais.co.th/webservices/subscriber/InquirySubscriberInfo");
			map.put("filter", "InquirySubscriberInfo");
		eqxRawData.setRawDataAttributes(map);
		
		AFSubInstance hostSubIns = afSubIns;
		
		if(afSubIns.getSubInitCmd().equals(ECommand.ACTIVATEPPSSINGSUB.getCommand())) {
			hostSubIns = ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo());
		}
		
		
		String channel = hostSubIns.getClientData().getChannel();
		
		if(channel == null) {
			channel = "";
		}

		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("<UriOverride value=\""+Config.URL_USMP_INQUIRYSUBSCRIBERINFO+"\" />"
				+ "<HeaderOverride name=\"SOAPAction\" value=\"http://vsmp.ais.co.th/webservices/subscriber/InquirySubscriberInfo\" />"
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sub=\"http://vsmp.ais.co.th/webservices/subscriber/\" >"
				+ "<soapenv:Header/>"
				+ "<soapenv:Body>"
				+ "<sub:InquirySubscriberInfo>"
				+ "<sub:Username>SGSCP</sub:Username>"
				+ "<sub:OrderRef>"+hostSubIns.getClientData().getSsid()+"</sub:OrderRef>"
				+ "<sub:OrderDesc>firstact_"+hostSubIns.getClientData().getSgw()+"_"+channel+"</sub:OrderDesc>"
				+ "<sub:Version>1</sub:Version>"
				+ "<sub:ParameterLists>"
				+ "<sub:ParameterList>"
				+ "<sub:ParameterName>Msisdn</sub:ParameterName>"
				+ "<sub:ParameterValue>"+hostSubIns.getSubMSISDN()+"</sub:ParameterValue>"
				+ "</sub:ParameterList>"
				+ "</sub:ParameterLists>"
				+ "</sub:InquirySubscriberInfo>"
				+ "</soapenv:Body>"
				+ "</soapenv:Envelope>");
		
		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.USMP_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_USMP_INQUIRYSUBSCRIBERINFO_REQUEST);
		
		return eqxRawData;
	}

}
