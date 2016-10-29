package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.ECommand;
import ct.af.enums.EEventType;
import ct.af.enums.EMethod;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_USMP_FirstActivate;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_USMP_ModifySubscriber {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.USMP_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put("method", EMethod.POST.toString());
			map.put("url", Config.URL_USMP_MODIFYSUBSCRIBER);
			map.put("SOAPAction", "http://vsmp.ais.co.th/webservices/subscriber/ModifySubscriber");
			map.put("filter", "ModifySubscriber");
		eqxRawData.setRawDataAttributes(map);
		
		String channel = afSubIns.getClientData().getChannel();
		String cos = "";
		String ivrLanguage = "";
		String smsLanguage = "";
		String ussdLanguage = "";
		String emailLanguage = ""; 
		String orderDescPrefix = "";
		
//		if(StringUtils.isBlank(channel)) {
//			channel = "";
//		} else {
//			channel = "_"+channel;
//		}
		
		if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSLANGUAGE.getCommand())) {
			
			orderDescPrefix = "ivrLang";
			
			ivrLanguage = afSubIns.getClientData().getIvrLang();
		}
		
		if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSSMSLANGUAGE.getCommand())) {
			
			orderDescPrefix = "smsLang";
					
			smsLanguage = afSubIns.getClientData().getSmsLang();
			ussdLanguage = afSubIns.getClientData().getSmsLang();
		}
		
		if(afSubIns.getSubInitCmd().equals(ECommand.ACTIVATEPPSSINGSUB.getCommand())) {
			
			orderDescPrefix = "firstact";
			
			if(!afSubIns.getOldCos().equals(afSubIns.getNewCos())) {
				cos = afSubIns.getNewCos();
			}
			
			ivrLanguage = afSubIns.getClientData().getIvrLang();
			smsLanguage = afSubIns.getClientData().getSmsLang();
			ussdLanguage = afSubIns.getClientData().getUssdLang();
			emailLanguage = afSubIns.getClientData().getEmailLang();
			
		}
		
		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("<UriOverride value=\"/webservices/subscriber/ModifySubscriber\" />"
				+ "<HeaderOverride name=\"SOAPAction\" value=\"http://vsmp.ais.co.th/webservices/subscriber/ModifySubscriber\" />"
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soapenv:Header/>"
				+ "<soapenv:Body>"
				+ "<ModifySubscriber xmlns=\"http://vsmp.ais.co.th/webservices/subscriber/\">"
				    + "<Username>SGSCP</Username>"
				    + "<OrderRef>"+afSubIns.getClientData().getSsid()+"</OrderRef>"
				    + "<OrderDesc>"+orderDescPrefix+"_"+afSubIns.getClientData().getSgw()+"_"+channel+"</OrderDesc>"
				    + "<Msisdn>"+afSubIns.getSubMSISDN()+"</Msisdn>");
		
			strbuilder.append(
					"<Cos>"+cos+"</Cos>"
					+ "<CosEffectiveDate></CosEffectiveDate>"
				    + "<CbpId></CbpId>"
					+ "<ScpId></ScpId>");
					if(afSubIns.getSubInitCmd().equals(ECommand.ACTIVATEPPSSINGSUB.getCommand()) ||
							afSubIns.getSubInitCmd().equals(ECommand.MODIPPSSMSLANGUAGE.getCommand())) {
						strbuilder.append("<Lang>"+smsLanguage+"</Lang>");
					}
				strbuilder.append("<IVRLanguage>"+ivrLanguage+"</IVRLanguage>"
					+ "<SMSLanguage>"+smsLanguage+"</SMSLanguage>"
					+ "<USSDLanguage>"+ussdLanguage+"</USSDLanguage>"
					+ "<EmailLanguage>"+emailLanguage+"</EmailLanguage>"
				    + "<Model></Model>");
				    if(!afSubIns.getOldCos().equals(afSubIns.getNewCos())){
				    	
				    	Param_USMP_FirstActivate firstActParam = (Param_USMP_FirstActivate) afSubIns.getSubResponseParamFirstAct();

						for(int i=0;i<firstActParam.getCounterInfo().size();i++) {
							
							if(!StringUtils.isBlank(firstActParam.getCounterInfo().get(i).getMainPromotionF())) {
								if(firstActParam.getCounterInfo().get(i).getMainPromotionF().equals("1") && 
										firstActParam.getCounterInfo().get(i).getChildCounterInfo().get(i).getValueType().equals("gprs")) {
									
									if(firstActParam.getCounterInfo().get(i).getChildCounterInfo().get(i).getMeteringMethod().equals("duration")) {
										strbuilder.append("<Gtcf>1</Gtcf>");
									}
									if(firstActParam.getCounterInfo().get(i).getChildCounterInfo().get(i).getMeteringMethod().equals("volume")) {
										strbuilder.append("<Gtcf>0</Gtcf>");
									}
								} else {
									strbuilder.append("<Gtcf></Gtcf>");
								}
							} else {
						    	strbuilder.append("<Gtcf></Gtcf>");
						    }
							
						}
				    	
				    } else {
				    	strbuilder.append("<Gtcf></Gtcf>");
				    }
				    
				    strbuilder.append("<GtcfEffectiveDate></GtcfEffectiveDate>"
				    + "<State></State>"
				    + "<Hack></Hack>"
				    + "<PrepaidFlag></PrepaidFlag>"
				    + "<VirtualFlag></VirtualFlag>"
				    + "<AltPrefixFlag></AltPrefixFlag>"
				    + "<AltPrefixCos></AltPrefixCos>"
				    + "<ToggleFlag></ToggleFlag>"
				    + "<ToggleCos></ToggleCos>"
				    + "<ToggleState></ToggleState>"
				    + "<ServicePackageId></ServicePackageId>"
				    + "<Imsi></Imsi>"
				    + "<NewImsi></NewImsi>"
				    + "<spName></spName>"
				    + "<brandName></brandName>"
				    + "<customerCategory></customerCategory>"
				    + "<customerSubCategory></customerSubCategory>"
				    + "<customerSegment></customerSegment>"
				    + "<SubscriberRSA></SubscriberRSA>"
				    + "<HomeLocation></HomeLocation>"
				    + "<FraudFlag></FraudFlag>"
				    + "<PinFraudFlag></PinFraudFlag>"
				    + "<CustomerRequestF></CustomerRequestF>"
				    + "<DPFlag></DPFlag>"
				+ "</ModifySubscriber>"
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
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_USMP_MODIFYSUBSCRIBER_REQUEST);
		
		return eqxRawData;
	}

}
