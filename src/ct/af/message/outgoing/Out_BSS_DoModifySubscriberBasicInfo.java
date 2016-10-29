package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import ct.af.enums.ECType;
import ct.af.enums.ECommand;
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

public class Out_BSS_DoModifySubscriberBasicInfo {//INCOMPLETE
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
	
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.BSS_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
		eqxRawData.setRawDataAttributes(map);
		
		DateTimeFormatter dateTime = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		DateTimeFormatter formatMSGID = DateTimeFormat.forPattern(";yyyyMMdd;HHmmss;SSS");
		
		StringBuilder strbuilder = new StringBuilder();
		
		String soMode = (StringUtils.isBlank(afSubIns.getClientData().getChannel()))? "IVR" : afSubIns.getClientData().getChannel();
		String ms = afSubIns.getClientData().getMs().substring(2, 11);
		
		String IVRlanguage = "";
		String SmsLang = "";
		String UssdLang = "";
		
		if(afSubIns.getClientData().getUssdLang().equals("1")){
			IVRlanguage = "THA";
			UssdLang = "THA";
			SmsLang = "THA";
		} else if(afSubIns.getClientData().getUssdLang().equals("2")){
			IVRlanguage = "ENG";
			UssdLang = "ENG";
			SmsLang = "ENG";
		}
		
		strbuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:inf=\"http://www.asiainfo.com/obd/InfoSyncDefines.obd\" xmlns:com=\"http://www.asiainfo.com/obd/CommonComponents.obd\">")
               .append("<soapenv:Header/>")
               .append("<soapenv:Body>")
                  .append("<inf:do_ModifySubscriberBasicInfo>")
                     .append("<inf:sOper>")
                        .append("<com:_so_nbr>"+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+formatMSGID.print(new DateTime())+"</com:_so_nbr>")
                        .append("<com:_busi_code>"+"1033"+"</com:_busi_code>")
                        .append("<com:_so_mode>"+soMode+"</com:_so_mode>")
                        .append("<com:_source_system>"+afSubIns.getClientData().getSgw()+"</com:_source_system>")
                        .append("<com:_so_date>"+dateTime.print(new DateTime().plusSeconds(Config.BSS_TIMEOUT))+"</com:_so_date>")
                        .append("<com:_charge_flag >"+"1"+"</com:_charge_flag >")
                        .append("<com:_phone_id >"+"0"+ms+"</com:_phone_id >")
                     .append("</inf:sOper>");
                     strbuilder.append("<inf:subscriberBasicInfo>");
                     
                     if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSLANGUAGE.getCommand())) {
                    	 strbuilder.append("<com:_IVR_language>"+IVRlanguage+"</com:_IVR_language>");
                     } else if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSSMSLANGUAGE.getCommand())) {
                    	 strbuilder.append("<com:_USSD_language>"+UssdLang+"</com:_USSD_language>")
                    	 .append("<com:_SMS_language>"+SmsLang+"</com:_SMS_language>");
                     }
                     
                     strbuilder.append("<com:_phone_id>"+"0"+ms+"</com:_phone_id>") // Pending
                     .append("</inf:subscriberBasicInfo>")
                    .append("</inf:do_ModifySubscriberBasicInfo>")
               .append("</soapenv:Body>")
            .append("</soapenv:Envelope>");
		
	
		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.BSS_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BSS_DOMODIFYSUBSCRIBERBASICINFO_REQUEST);
		
		return eqxRawData;
	}

}
