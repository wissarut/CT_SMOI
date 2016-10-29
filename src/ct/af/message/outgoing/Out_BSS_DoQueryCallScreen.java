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

public class Out_BSS_DoQueryCallScreen {

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
	
		StringBuilder strbuilder = new StringBuilder();
		
		String soMode = (StringUtils.isBlank(afSubIns.getClientData().getChannel()))? "IVR" : afSubIns.getClientData().getChannel();
		String siteId = (StringUtils.isBlank(afSubIns.getClientData().getScpid()))? ec02Instance.getAFInstance().getMainLocation() : afSubIns.getClientData().getScpid();
		String ms = afSubIns.getClientData().getMs().substring(2, 11);
		
		DateTimeFormatter dateTime = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		DateTimeFormatter formatMSGID = DateTimeFormat.forPattern(";yyyyMMdd;HHmmss;SSS");

		 strbuilder.append("<UriOverride value=\"/BSSBroker/Gateways/QueryCallScreen\" />")
		 .append("<HeaderOverride name="+"\"SOAPAction\""+" value="+"\"do_QueryCallScreen\""+" />")
		 .append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">")
		 .append("<soapenv:Header/>")
		 .append("<soapenv:Body>")
		 .append("<ns2:do_QueryCallScreen xmlns=\"http://www.asiainfo.com/obd/CommonComponents.obd\" xmlns:ns2=\"http://www.asiainfo.com/obd/InfoSyncDefines.obd\">")
		     .append("<ns2:sOper>")
		         .append("<_so_nbr>"+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+formatMSGID.print(new DateTime())+"</_so_nbr>")
		         .append("<_busi_code>"+"6017"+"</_busi_code>")
		         .append("<_so_mode>"+soMode+"</_so_mode>")
		         .append("<_source_system>"+"INGW"+"</_source_system>")
		         .append("<_charge_flag>"+"1"+"</_charge_flag>")
		         .append("<_so_date>"+dateTime.print(new DateTime().plusSeconds(Config.BSS_TIMEOUT))+"</_so_date>")
		         .append("<_phone_id>"+"0"+ms+"</_phone_id>")
		         .append("<_remark>"+afSubIns.getClientData().getSgw()+"</_remark>")
		     .append("</ns2:sOper>")
		     .append("<_site_id>"+siteId+"</_site_id>")
		 .append("</ns2:do_QueryCallScreen>")
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
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BSS_DOQUERYCALLSCREEN_REQUEST);
		
		return eqxRawData;
	}

}
