package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
	
public class Out_USMP_FirstActivate {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {

		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.USMP_INTERFACE);
		
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
			map.put(EEqxMsg.URL.getEqxMsg(), Config.URL_USMP_FIRSTACTIVATE);
			map.put(EEqxMsg.SOAPACTION.getEqxMsg(), "http://vsmp.ais.co.th/webservices/FirstActivate");
			map.put(EEqxMsg.FILTER.getEqxMsg(), "FirstActivate");
		eqxRawData.setRawDataAttributes(map);
		
		DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZ");
		DateTimeFormatter formatDateForReport = Config.formatDate;
		DateTime firstActTime = Config.formatDateWithMiTz.parseDateTime(afSubIns.getFirstsActDate());
		
		int refillStMonth = Config.DEFAULT_REFILLSTOPTIME_MONTH;
		
		if(afSubIns.getRefillStopTimeMonth() != null) {
			refillStMonth = Integer.parseInt(afSubIns.getRefillStopTimeMonth());
		}
		
		DateTime refillStopTime = firstActTime.plusMonths(refillStMonth);
				
		if(!StringUtils.isBlank(afSubIns.getRefillStopTime())) {
			refillStopTime = formatDate.parseDateTime(afSubIns.getRefillStopTime());
		}
		
		
		
		String mainProductExpire = formatDate.print(refillStopTime);
		DateTime mainProductExpireDt = formatDate.parseDateTime(mainProductExpire);
		afSubIns.getReportData().setMainProductExpireDt(formatDateForReport.print(mainProductExpireDt));
		
		String channel = afSubIns.getClientData().getChannel();
		
		if(channel == null) {
			channel = "";
		}
		
		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("<UriOverride value=\"/webservices/FirstActivate\" />"
				+ "<HeaderOverride name=\"SOAPAction\" value=\"http://vsmp.ais.co.th/webservices/FirstActivate\" />"
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soapenv:Header/>"
				+ "<soapenv:Body>"
				+ "<FirstActivate xmlns=\"http://vsmp.ais.co.th/webservices/\">"
					+ "<Username>SGSCP</Username>"
					+ "<OrderRef>"+afSubIns.getClientData().getSsid()+"</OrderRef>"
					+ "<OrderDesc>firstact_"+afSubIns.getClientData().getSgw()+"_"+channel+"</OrderDesc>"
					+ "<Msisdn>"+afSubIns.getSubMSISDN()+"</Msisdn>"
					+ "<OldCounterName>"+afSubIns.getOldPromoName()+"</OldCounterName>"
					+ "<NewCounterName>"+afSubIns.getNewPromoName()+"</NewCounterName>"
					+ "<RefillStopTime>"+formatDate.print(refillStopTime)+"</RefillStopTime>"
					+ "<StartTime>"+formatDate.print(firstActTime)+"</StartTime>"
					+ "<ExpiryTime>"+formatDate.print(firstActTime)+"</ExpiryTime>"
					+ "<RecurringState>recurring</RecurringState>"
				+ "</FirstActivate>"
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
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_USMP_FIRSTACTIVATE_REQUEST);
		
		return eqxRawData;
	}

}
