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

public class Out_BSS_DoQueryFN {

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
		String ms = afSubIns.getClientData().getMs().substring(2, 11);
		
		DateTimeFormatter dateTime = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		DateTimeFormatter formatMSGID = DateTimeFormat.forPattern(";yyyyMMdd;HHmmss;SSS");

		strbuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://www.asians1o.com/obd/InfoSyncDefines.obd\" xmlns:com=\"http://www.asians1o.com/obd/CommonComponents.obd\">")
               .append("<soapenv:Header/>")
               .append("<soapenv:Body>")
                  .append("<ns1:do_QueryFN>")
                     .append("<ns1:sOper>")
                     	.append("<ns0:_so_nbr>"+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+formatMSGID.print(new DateTime())+"</ns0:_so_nbr>")
                        .append("<ns0:_busi_code>"+"6014"+"</ns0:_busi_code>")
                        .append("<ns0:_so_mode>"+soMode+"</ns0:_so_mode>")
                        .append("<ns0:_source_system>"+afSubIns.getClientData().getSgw()+"</ns0:_source_system>")
                        .append("<ns0:_charge_flag>"+"1"+"</ns0:_charge_flag>")
                        .append("<ns0:_phone_id>"+"0"+ms+"</ns0:_phone_id>")
                        .append("<ns0:_so_date>"+dateTime.print(new DateTime().plusSeconds(Config.BSS_TIMEOUT))+"</ns0:_so_date>")
                     .append("</ns1:sOper>")
                     .append("<ns1:_phone_id>"+"0"+ms+"</ns1:_phone_id>")
                  .append("</ns1:do_QueryFN>")
               .append("</soapenv:Body>")
               .append("<soapenv:Header/>")
            .append("</soapenv:Envelope>");
		
		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.BSS_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BSS_DOQUERYFN_REQUEST);
		
		return eqxRawData;
	}

}
