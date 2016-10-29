package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;
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
import ct.af.message.incoming.parameter.Param_DS2A_InquirySubscriber;
import ct.af.message.incoming.parameter.Param_USMP_FirstActivate;
import ct.af.message.incoming.parameter.Param_USMP_InquiryCounter;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;
			
public class Out_SSB_WorkOrderFirstAct {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
	
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.SSB_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
			map.put(EEqxMsg.URL.getEqxMsg(), Config.URL_SSB_WORKORDER);
		eqxRawData.setRawDataAttributes(map);
		
		String custId = afSubIns.getInquirySubParam().getResultInfo().get(0).getCaId();
		String acctId = afSubIns.getInquirySubParam().getResultInfo().get(0).getBaId();
		
		
		DateTimeFormatter formatDate = DateTimeFormat.forPattern("ddMMYYYY HH:mm:ss");
		DateTimeFormatter formatDateForReport = Config.formatDate;
		DateTime currentTime = new DateTime();
		
		afSubIns.getReportData().setTransactionDateTime(formatDateForReport.print(currentTime));

		HashMap<String, String> langMap = new HashMap<String, String>();
		
		String[] strLang = Config.WORKORDER_LANG_MAPPING.split(",");
		
		for(String langData:strLang) {
			AppLog.d("langData : "+langData);
			langMap.put(langData.split("\\|")[0], langData.split("\\|")[1]);
			AppLog.d("key :"+langData.split("\\|")[0]);
		}
		String sms = langMap.get(afSubIns.getClientData().getSmsLang());
		String email = langMap.get(afSubIns.getClientData().getEmailLang());
		String ussd = langMap.get(afSubIns.getClientData().getUssdLang());
		String ivr = langMap.get(afSubIns.getClientData().getIvrLang());
		if(sms == null){
			sms = "";
		}
		if(email == null){
			email = "";
		}
		if(ussd == null){
			ussd = "";
		}
		if(ivr == null){
			ivr = "";
		}
		StringBuilder strbuilder = new StringBuilder();
		Param_DS2A_InquirySubscriber inquirySubParameter = afSubIns.getInquirySubParam();
		Param_USMP_FirstActivate firstActivateParameter = afSubIns.getSubResponseParamFirstAct();
		Param_USMP_InquiryCounter inquiryCounterParameter = afSubIns.getSubResponseParamInquiryCounter();
	
		DateTime activationTimeDt = Config.formatDateWithMiTz.parseDateTime(afSubIns.getFirstsActDate());
		String activeStopTime = inquirySubParameter.getResultInfo().get(0).getActiveStopTime();
		DateTime activeStopTimeDT = null;
		DateTime productExpireDt = null;
		
		AppLog.d("current time : "+new DateTime());
		AppLog.d("activeStopTime : "+activeStopTime);
		
		try {
		DateTimeFormatter formatDateOfDS2A = DateTimeFormat.forPattern("yyyyMMddHHmmssZ");
		activeStopTimeDT = formatDateOfDS2A.parseDateTime(activeStopTime);
		
		} catch (Exception e) {
			AppLog.e("DateTime is Wrong format");
		}
		
		if (!afSubIns.getOldCos().equals(afSubIns.getNewCos())) {
			
			try {
				
				DateTimeFormatter formatDateModi = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZ");
				String refillStopTime = firstActivateParameter.getCounterInfo().get(0).getRefillStopTime();
				productExpireDt = formatDateModi.parseDateTime(refillStopTime);
		
			} catch (Exception e) {
				AppLog.e("DateTime is Wrong format");
			}
			
			strbuilder.append("{\"workOrderType\": \"1\""
					+ ",\"custId\" : \""+custId+"\""
					+ ",\"acctId\" : \""+acctId+"\""
					+ ",\"mobileNo\" : \"0"+afSubIns.getSubMSISDN().substring(2, 11)+"\""
					+ ",\"activationTime\" : \""+formatDate.print(Config.formatDateWithMiTz.parseDateTime(afSubIns.getFirstsActDate()))+"\""
					+ ",\"activeChannel\" : \""+afSubIns.getClientData().getChannel()+"\""
					+ ",\"activeCAC\" : \""+afSubIns.getClientData().getLocation()+"\""
					+ ",\"expireDt\" : \""+formatDate.print(activeStopTimeDT)+"\""
					+ ",\"smsLang\" : \""+sms+"\""
					+ ",\"ussdLang\" : \""+ussd+"\""
					+ ",\"ivrLang\" : \""+ivr+"\""
					+ ",\"emailLang\" : \""+email+"\""
					+ ",\"channel\" : \""+Config.INS_SSB_CHANNEL+"\""
					+ ",\"userName\" : \"SGSCP\""
					+ ",\"productRec\": [{\"productName\": \"" + afSubIns.getNewPromoName()+"\""
					+ ",\"productSeqId\": \"" + firstActivateParameter.getCounterInfo().get(0).getProductNumber() + "\""
					+ ",\"productValidDt\": \""+formatDate.print(Config.formatDateWithMiTz.parseDateTime(afSubIns.getFirstsActDate()))+"\""
					+ ",\"productExpireDt\": \"" + formatDate.print(productExpireDt) + "\""
					+ "}]"
					+ "}");
		} else {
			
			String productName = "";
			String productSeqId = "";
			
			for(int i=0;i<inquiryCounterParameter.getCounterInfo().size();i++) {
				if(!StringUtils.isBlank(inquiryCounterParameter.getCounterInfo().get(i).getMainPromotionF())) {
					if(inquiryCounterParameter.getCounterInfo().get(i).getMainPromotionF().equals("1")) {
						
						productName = inquiryCounterParameter.getCounterInfo().get(i).getCounterName();
						productSeqId = inquiryCounterParameter.getCounterInfo().get(i).getProductNumber();
						
					}
				}
			}
			
			DateTimeFormatter formatDateToSSB = DateTimeFormat.forPattern("yyyyMMdd HH:mm:ss");
			DateTime refillStopTimeProductExpireDtDate= null;
			try{
				
				DateTimeFormatter formatDateInquiry = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZ");
				String refillStopTimeProductExpireDt = afSubIns.getRefillStopTimeModifyCounterReq();
				
				refillStopTimeProductExpireDtDate = formatDateInquiry.parseDateTime(refillStopTimeProductExpireDt);
		
			} catch (Exception e) {
				AppLog.e("DateTime is Wrong format");
			}
			
			strbuilder.append("{\"workOrderType\" : \"1\""
					+ ",\"custId\" : \""+custId+"\""
					+ ",\"acctId\" : \""+acctId+"\""
					+ ",\"mobileNo\" : \"0"+afSubIns.getSubMSISDN().substring(2, 11)+"\""
					+ ",\"activationTime\" : \""+formatDate.print(activationTimeDt)+"\""
					+ ",\"activeChannel\" : \""+afSubIns.getClientData().getChannel()+"\""
					+ ",\"activeCAC\" : \""+afSubIns.getClientData().getLocation()+"\""
					+ ",\"expireDt\" : \""+formatDate.print(activeStopTimeDT)+"\""
					+ ",\"smsLang\" : \""+sms+"\""
					+ ",\"ussdLang\" : \""+ussd+"\""
					+ ",\"ivrLang\" : \""+ivr+"\""
					+ ",\"emailLang\" : \""+email+"\""
					+ ",\"channel\" : \""+Config.INS_SSB_CHANNEL+"\""
					+ ",\"userName\" : \"SGSCP\""
					+ ",\"productRec\": [{\"productName\": \"" + productName +"\""
					+ ",\"productSeqId\": \"" + productSeqId + "\""
					+ ",\"productValidDt\": \"" + formatDate.print(activationTimeDt) + "\""
					+ ",\"productExpireDt\": \"" + formatDate.print(refillStopTimeProductExpireDtDate) + "\""
					+ "}]"
					+ "}");
		}
			
			
	//		map.put("val", strbuilder.toString());
			eqxRawData.setRawMessage("<ERDHearder />"+"<ERDData value=\""
					+ StringEscapeUtils.escapeHtml4(strbuilder.toString())+"\">"
					+ "</ERDData>");
	
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.SSB_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SSB_WORKORDERFIRSTACT_REQUEST);
		
		return eqxRawData;
		
	}

}
