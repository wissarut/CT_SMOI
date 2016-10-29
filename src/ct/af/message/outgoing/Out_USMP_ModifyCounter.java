package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ct.af.enums.ECType;
import ct.af.enums.EEventType;
import ct.af.enums.EMethod;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_USMP_FirstActivate;
import ct.af.message.incoming.parameter.Param_USMP_InquiryCounter;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_USMP_ModifyCounter {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.USMP_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put("method", EMethod.POST.toString());
			map.put("url", Config.URL_USMP_MODIFYCOUNTER);
			map.put("SOAPAction", "http://vsmp.ais.co.th/webservices/ModifyCounter");
			map.put("filter", "ModifyCounter");
		eqxRawData.setRawDataAttributes(map);
		
		String channel = afSubIns.getClientData().getChannel();
		
		if(channel == null) {
			channel = "";
		}
		
		String productNumber = "";	
		
		if(afSubIns.getOldCos().equals(afSubIns.getNewCos())) {
			Param_USMP_InquiryCounter inquiryCounterParam = (Param_USMP_InquiryCounter) afSubIns.getSubResponseParamInquiryCounter();	
			
			for(int i=0;i<inquiryCounterParam.getCounterInfo().size();i++) {
				
				if(!StringUtils.isBlank(inquiryCounterParam.getCounterInfo().get(i).getMainPromotionF())) {
					if(inquiryCounterParam.getCounterInfo().get(i).getMainPromotionF().equals("1")) {
						productNumber = inquiryCounterParam.getCounterInfo().get(i).getProductNumber();
					}
				}
				
			}
		} else {
			Param_USMP_FirstActivate firstActParam = afSubIns.getSubResponseParamFirstAct();
			for(int i=0;i<firstActParam.getCounterInfo().size();i++) {
				
				if(!StringUtils.isBlank(firstActParam.getCounterInfo().get(i).getMainPromotionF())) {
					if(firstActParam.getCounterInfo().get(i).getMainPromotionF().equals("1")) {
						productNumber = firstActParam.getCounterInfo().get(i).getProductNumber();
					}
				}
				
			}
			
		}
		DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZ");
		DateTimeFormatter formatDateForReport = Config.formatDate;
		DateTime firstActTime = Config.formatDateWithMiTz.parseDateTime(afSubIns.getFirstsActDate());
		
		int refillStMonth = Config.DEFAULT_REFILLSTOPTIME_MONTH;
		
		if(afSubIns.getRefillStopTimeMonth() != null) {
			refillStMonth = Integer.parseInt(afSubIns.getRefillStopTimeMonth());
		}
		
		DateTime refillStopTime = firstActTime.plusMonths(refillStMonth);

		if(StringUtils.isNotBlank(afSubIns.getRefillStopTime())) {
			refillStopTime = formatDate.parseDateTime(afSubIns.getRefillStopTime());
			
		}
		
		afSubIns.setRefillStopTimeModifyCounterReq(formatDate.print(refillStopTime));

		String mainProductExpire = formatDate.print(refillStopTime);
		DateTime mainProductExpireDt = formatDate.parseDateTime(mainProductExpire);
		afSubIns.getReportData().setMainProductExpireDt(formatDateForReport.print(mainProductExpireDt));

		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("<UriOverride value=\"/webservices/ModifyCounter\" />"
				+ "<HeaderOverride name=\"SOAPAction\" value=\"http://vsmp.ais.co.th/webservices/ModifyCounter\" />"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soap:Body>"
				+ "<ModifyCounter xmlns=\"http://vsmp.ais.co.th/webservices/\">"
				    + "<Username>SGSCP</Username>"
				    + "<OrderRef>"+afSubIns.getClientData().getSsid()+"</OrderRef>"
				    + "<OrderDesc>firstact_"+afSubIns.getClientData().getSgw()+"_"+channel+"</OrderDesc>"
				    + "<Msisdn>"+afSubIns.getSubMSISDN()+"</Msisdn>"
					+ "<ProductNumber>"+productNumber+"</ProductNumber>"
				    + "<ExpiryRecurringFlag></ExpiryRecurringFlag>"
				    + "<StartTime>"+formatDate.print(firstActTime)+"</StartTime>"
					+ "<ExpiryTime>"+formatDate.print(firstActTime)+"</ExpiryTime>"
				    + "<CounterState></CounterState>"
				    + "<RefillStopTime>"+formatDate.print(refillStopTime)+"</RefillStopTime>"
				+ "</ModifyCounter>"
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
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_USMP_MODIFYCOUNTER_REQUEST);
		
		return eqxRawData;
	}

}
