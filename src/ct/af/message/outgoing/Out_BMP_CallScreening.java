package ct.af.message.outgoing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
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

public class Out_BMP_CallScreening {

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
			map.put(EEqxMsg.SOAPACTION.getEqxMsg(), "ManSubCallScreenNo");
		eqxRawData.setRawDataAttributes(map);
		
		StringBuilder strbuilder = new StringBuilder();
		
		
		String operationType = "1";
		String callScreenType = "";
		
		if(afSubIns.getSubInitCmd().equals(ECommand.ADDSCRSCREENNO.getCommand())) {
			operationType = "1";
			
			if(StringUtils.isNotBlank(afSubIns.getClientData().getScreentype())) {
				callScreenType = afSubIns.getClientData().getScreentype();
			}
			
		} else if(afSubIns.getSubInitCmd().equals(ECommand.DELESCRSCREENNO.getCommand())) {
			operationType = "2";
			if(StringUtils.isNotBlank(afSubIns.getClientData().getScreentype())) {
				callScreenType = afSubIns.getClientData().getScreentype();
			}
		} else if(afSubIns.getSubInitCmd().equals(ECommand.MODISCRSCREENTYPE.getCommand())) {
			operationType = "4";
			callScreenType = afSubIns.getClientData().getDat();
		}  else if(afSubIns.getSubInitCmd().equals(ECommand.MODISCRWHITELIST.getCommand())) {
			operationType = "3";
		}
		
		
		strbuilder.append("<ERDHeader>"
				+ "<Header name=\"Content-Type\" value=\"text/xml\" />"
				+ "<Header name=\"charset\" value=\"utf-8\" />"
				+ "<Header name=\"SOAPAction\" value=\"ManSubCallScreenNo\" />"
						+ "</ERDHeader>"
					+ "<ERDData value=\"&lt;?xml version=&quot;1.0&quot; encoding=&quot;utf-8&quot; ?&gt;"
					+ "&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:bus=&quot;http://www.huawei.com/bme/cbsinterface/scp/businessmgrmsg&quot; xmlns:com=&quot;http://www.huawei.com/bme/cbsinterface/common&quot; xmlns:bus1=&quot;http://www.huawei.com/bme/cbsinterface/scp/businessmgr&quot;&gt;"
			   + "&lt;soapenv:Header/&gt;"
			   + "&lt;soapenv:Body&gt;"
			      + "&lt;bus:ManSubCallScreenNoRequestMsg&gt;"
			         + "&lt;RequestHeader&gt;"
			            + "&lt;com:CommandId&gt;ManSubCallScreenNo&lt;/com:CommandId&gt;"
			            + "&lt;com:Version&gt;1&lt;/com:Version&gt;"
			            + "&lt;com:TransactionId&gt;SGSCP_"+afSubIns.getClientData().getSgw()+"_"+afSubIns.getClientData().getSsid()+"&lt;/com:TransactionId&gt;"
			            + "&lt;com:SequenceId&gt;1&lt;/com:SequenceId&gt;"
			            + "&lt;com:RequestType&gt;Event&lt;/com:RequestType&gt;"
			            + "&lt;com:SerialNo&gt;"+afSubIns.getClientData().getSsid()+"&lt;/com:SerialNo&gt;"
			         + "&lt;/RequestHeader&gt;"
			         + "&lt;ManSubCallScreenNoRequest&gt;"
			            + "&lt;bus1:SubscriberNo&gt;"+afSubIns.getSubMSISDN().substring(2)+"&lt;/bus1:SubscriberNo&gt;");
		
						if(StringUtils.isNotBlank(callScreenType)) {
							strbuilder.append("&lt;bus1:CallScreenType&gt;"+callScreenType+"&lt;/bus1:CallScreenType&gt;");
						}
		
						ArrayList<String> datArr = new ArrayList<String>();
						
						if(afSubIns.getClientData().getDat().contains("&")) {
							datArr =  new ArrayList<String>(Arrays.asList(afSubIns.getClientData().getDat().split("&")));
						} else {
							datArr.add(afSubIns.getClientData().getDat());
						}
						
						if((afSubIns.getSubInitCmd().equals(ECommand.ADDSCRSCREENNO.getCommand()))
								|| afSubIns.getSubInitCmd().equals(ECommand.DELESCRSCREENNO.getCommand())) {
						
							for(String msisdn:datArr) {
								
								strbuilder.append("&lt;bus1:CallScreenNoInfo&gt;");
					       
								if(!msisdn.startsWith("66")) {
									msisdn = "66"+msisdn.substring(1);
								}
								
								strbuilder.append("&lt;bus1:callScreenNo&gt;"+msisdn+"&lt;/bus1:callScreenNo&gt;");
							
								
								if(afSubIns.getSubInitCmd().equals(ECommand.MODISCRSCREENTYPE.getCommand())) {
									
									strbuilder.append("&lt;bus1:RoutingMethod&gt;4&lt;/bus1:RoutingMethod&gt;");
								
								}
								
								if(afSubIns.getSubInitCmd().equals(ECommand.ADDSCRSCREENNO.getCommand())) {
						
									String listType = afSubIns.getClientData().getScreentype();
						
									if(listType.equals("2")) {
										listType = "1";
									} else if(listType.equals("1")) {
										listType = "0";
									} else {
										listType = "1";
									}
					
									strbuilder.append("&lt;bus1:ListType&gt;"+listType+"&lt;/bus1:ListType&gt;");
								
								}
				
								strbuilder.append("&lt;/bus1:CallScreenNoInfo&gt;");
					
							}
						
						}
						
						if((afSubIns.getSubInitCmd().equals(ECommand.MODISCRWHITELIST.getCommand()))) {
							
							String dat = afSubIns.getClientData().getDat();
							
							if(dat.equals("0")) {
								dat = "1";
							} else if(dat.equals("1")) {
								dat = "3";
							}
							
							strbuilder.append("&lt;bus1:CallScreenNoInfo&gt;");
							
							if(dat.equals("3")) {
								strbuilder.append("&lt;bus1:routeNumber&gt;6681900129&lt;/bus1:routeNumber&gt;");
							}
							
							strbuilder.append("&lt;bus1:RoutingMethod&gt;"+dat+"&lt;/bus1:RoutingMethod&gt;");
						
							strbuilder.append("&lt;/bus1:CallScreenNoInfo&gt;");
						}
						
		strbuilder.append("&lt;bus1:OperationType&gt;"+operationType+"&lt;/bus1:OperationType&gt;"
			         + "&lt;/ManSubCallScreenNoRequest&gt;"
			      + "&lt;/bus:ManSubCallScreenNoRequestMsg&gt;"
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
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BMP_CALLSCREENING_REQUEST);
		
		return eqxRawData;
	}

}
