package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

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

public class Out_BMP_DispScrScreenNo {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.BMP_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.URL.getEqxMsg(), Config.URL_BMP_SERVICE);
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
			map.put(EEqxMsg.SOAPACTION.getEqxMsg(), "QuerySubCallScreenNo");
		eqxRawData.setRawDataAttributes(map);
		
		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("<ERDHeader>"
				+ "<Header name=\"Content-Type\" value=\"text/xml\" />"
				+ "<Header name=\"charset\" value=\"utf-8\" />"
				+ "<Header name=\"SOAPAction\" value=\"QuerySubCallScreenNo\" />"
			+ "</ERDHeader>"
		+ "<ERDData value=\"&lt;?xml version=&quot;1.0&quot; encoding=&quot;utf-8&quot; ?&gt;"
+ "&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:bus=&quot;http://www.huawei.com/bme/cbsinterface/scp/businessmgrmsg&quot; xmlns:com=&quot;http://www.huawei.com/bme/cbsinterface/common&quot; xmlns:bus1=&quot;http://www.huawei.com/bme/cbsinterface/scp/businessmgr&quot;&gt;"
	+ "&lt;soapenv:Header /&gt;"
	+ "&lt;soapenv:Body&gt;"
		+ "&lt;bus:QuerySubCallScreenNoRequestMsg&gt;"
			+ "&lt;RequestHeader&gt;"
				+ "&lt;com:CommandId&gt;QuerySubCallScreenNo&lt;/com:CommandId&gt;"
				+ "&lt;com:Version&gt;1&lt;/com:Version&gt;"
				+ "&lt;com:TransactionId&gt;SGSCP_"+afSubIns.getClientData().getSgw()+"_"+afSubIns.getClientData().getSsid()+"&lt;/com:TransactionId&gt;"
				+ "&lt;com:SequenceId&gt;1&lt;/com:SequenceId&gt;"
				+ "&lt;com:RequestType&gt;Event&lt;/com:RequestType&gt;"
				+ "&lt;com:SerialNo&gt;"+afSubIns.getClientData().getSsid()+"&lt;/com:SerialNo&gt;"
			+ "&lt;/RequestHeader&gt;"
			+ "&lt;QuerySubCallScreenNoRequest&gt;"
				+ "&lt;bus1:SubscriberNo&gt;"+afSubIns.getSubMSISDN().substring(2)+"&lt;/bus1:SubscriberNo&gt;"
			+ "&lt;/QuerySubCallScreenNoRequest&gt;"
		+ "&lt;/bus:QuerySubCallScreenNoRequestMsg&gt;"
	+ "&lt;/soapenv:Body&gt;"
+ "&lt;/soapenv:Envelope&gt;\" />");
		   
		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.BMP_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BMP_DISPSCRSCREENNO_REQUEST);
		
		return eqxRawData;
	}

}
