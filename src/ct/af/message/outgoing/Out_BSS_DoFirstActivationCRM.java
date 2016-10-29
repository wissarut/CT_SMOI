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

public class Out_BSS_DoFirstActivationCRM {

public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.BSS_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
			map.put(EEqxMsg.SOAPACTION.getEqxMsg(), "do_FirstActivationCRM");
			map.put(EEqxMsg.URL.getEqxMsg(), "/BSSBroker/Gateways/FirstActivationbyCRM");
		eqxRawData.setRawDataAttributes(map);
		
		StringBuilder strbuilder = new StringBuilder();
		
		String IVRlanguage = "" ;
		String SmsLang = "" ;
		String UssdLang = "" ;
		if(afSubIns.getClientData().getUssdLang().equals("1")){
			IVRlanguage = "THA";
			UssdLang = "THA";
			SmsLang = "THA";
		}
		if(afSubIns.getClientData().getUssdLang().equals("2")){
			IVRlanguage = "ENG";
			UssdLang = "ENG";
			SmsLang = "ENG";
		}
		
		String soMode = (StringUtils.isBlank(afSubIns.getClientData().getChannel()))? "IVR" : afSubIns.getClientData().getChannel();
		String siteId = (StringUtils.isBlank(afSubIns.getClientData().getScpid()))? ec02Instance.getAFInstance().getMainLocation() : afSubIns.getClientData().getScpid();
		String ms = afSubIns.getClientData().getMs().substring(2, 11);
		
		DateTimeFormatter formatMSGID = DateTimeFormat.forPattern(";yyyyMMdd;HHmmss;SSS");

		strbuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">")
               .append("<soapenv:Header/>")
               .append("<soapenv:Body>")
                  .append("<ns2:do_FirstActivationCRM xmlns=\"http://www.asiainfo.com/obd/CommonComponents.obd\" xmlns:ns2=\"http://www.asiainfo.com/obd/InfoSyncDefines.obd\">")
                     .append("<ns2:sOper>")
                     
                        .append("<_so_nbr>"+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+formatMSGID.print(new DateTime())+"</_so_nbr>")
                        .append("<_busi_code>"+"1009"+"</_busi_code>")
                        .append("<_so_mode>"+soMode+"</_so_mode>")
                        .append("<_source_system>"+"INGW"+"</_source_system>")
                        .append("<_charge_flag>"+"1"+"</_charge_flag>")
						.append("<_so_date>"+new DateTime()+"</_so_date>")
						.append("<_phone_id>"+"0"+ms+"</_phone_id>")
                        .append("<_remark>"+afSubIns.getClientData().getSgw()+"</_remark>")
                        
                     .append("</ns2:sOper>")
                     
                        .append("<ns2:_phone_id>"+"0"+ms+"</ns2:_phone_id>")
                        .append("<ns2:_SMS_language>"+SmsLang+"</ns2:_SMS_language>")
                        .append("<ns2:_USSD_language>"+UssdLang+"</ns2:_USSD_language>")
                        .append("<ns2:_IVR_language>"+IVRlanguage+"</ns2:_IVR_language>")
                        .append("<ns2:_location_code>"+afSubIns.getClientData().getLocation()+"</ns2:_location_code>")
                        .append("<ns2:_notification_flag>"+"1"+"</ns2:_notification_flag>")
                        .append("<ns2:_site_id>"+siteId+"</ns2:_site_id>")
                        
//                        .append("<ns2:SProductOrderOperList>")
//                           .append("<com:SProductOrderOperList_Item>")
//                              .append("<com:_oper_type>"+afSubIns.getClientData().getDat()+"</com:_oper_type>")
//                              .append("<com:SProductOrderList>")
//                                 .append("<com:SProductOrderList_Item>")
//                                    .append("<com:_product_class>"+"56"+"</com:_product_class>")
//                                 .append("</com:SProductOrderList_Item>")
//                              .append("</com:SProductOrderList>")
//                           .append("</com:SProductOrderOperList_Item>")
//                        .append("</ns2:SProductOrderOperList>")
                        
                  .append("</ns2:do_FirstActivationCRM>")
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
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BSS_DOFIRSTACTIVATIONCRM_REQUEST);
		
		return eqxRawData;
	}

}
