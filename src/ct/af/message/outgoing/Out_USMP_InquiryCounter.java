package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import ct.af.enums.ECType;
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

public class Out_USMP_InquiryCounter {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.USMP_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put("method", EMethod.POST.toString());
			map.put("url", Config.URL_USMP_INQUIRYCOUNTER);
			map.put("SOAPAction", "http://vsmp.ais.co.th/webservices/InquiryCounter");
			map.put("filter", "InquiryCounter");
		eqxRawData.setRawDataAttributes(map);
		
		String channel = afSubIns.getClientData().getChannel();
		
		if(channel == null) {
			channel = "";
		}
		
		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("<UriOverride value=\"/webservices/InquiryCounter\" />"
				+ "<HeaderOverride name=\"SOAPAction\" value=\"http://vsmp.ais.co.th/webservices/InquiryCounter\" />"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soap:Body>"
				+ "<InquiryCounter xmlns=\"http://vsmp.ais.co.th/webservices/\">"
				    + "<Username>SGSCP</Username>"
				    + "<OrderRef>"+afSubIns.getClientData().getSsid()+"</OrderRef>"
				    + "<OrderDesc>firstact_"+afSubIns.getClientData().getSgw()+"_"+channel+"</OrderDesc>"
				    + "<Msisdn>"+afSubIns.getSubMSISDN()+"</Msisdn>"
					+ "<ProductNumber></ProductNumber>"
				+ "</InquiryCounter>"
				+ "</soap:Body>"
				+ "</soap:Envelope>");
				
		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.USMP_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_USMP_INQUIRYCOUNTER_REQUEST);
		
		return eqxRawData;
	}

}
