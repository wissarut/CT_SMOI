package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ct.af.enums.ECType;
import ct.af.enums.EEventType;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Counters_AMF_CheckBalance;
import ct.af.message.incoming.parameter.Param_DS2A_InquirySubscriber;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;
             
public class Out_SRFC_SpecializedResource {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.SPECIALIZED_RESOURCE.getCType());
		eqxRawData.setName(EProtocal.DIAMETER.toString());
		eqxRawData.setTo(Config.SRFC_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
		eqxRawData.setRawDataAttributes(map);

		String custId = afSubIns.getInquirySubParam().getResultInfo().get(0).getCustomerId();
		
		String[] E01Value =  afSubIns.getE01Data().split("\\|" , -1);
		
		DateTimeFormatter formatDate = DateTimeFormat.forPattern("ddMMYYYY HH:mm:ss");
		
		DateTime currentTime = new DateTime();
		
		StringBuilder strbuilder = new StringBuilder();
		
		 strbuilder.append("<Session-Id value=\""+afSubIns.getClientData().getSsid()+"\"/>"
	            + "<Auth-Application-Id value=\"8676\"/>"
	            + "<Origin-Host value=\""+Config.SRFC_ORIGIN_HOST+"\"/>"
	            + "<Origin-Realm value=\""+Config.SRFC_ORIGIN_REALM+"\"/>"
	            + "<Destination-Realm value=\""+Config.SRFC_DESTINATION_REALM+"\"/>"
	            + "<Auth-Request-Type value=\"2\"/>"
	            + "<TORO-UI-Session-Treatment>");
	            
		 String inband = "0";
		 String waitRRR = "0";
		 String outband = "1";

		 if(E01Value.length == 5) {
	         if(!StringUtils.isBlank(E01Value[2])) {
	        	 inband = E01Value[2];
	         }
	         
	         if(!StringUtils.isBlank(E01Value[4])) {
	        	 waitRRR = E01Value[4];
	         }
	         
	         if(!StringUtils.isBlank(E01Value[3])) {
	        	 outband = E01Value[3];
	         }
		 }
         
         String balance = "0";
         
         List<Counters_AMF_CheckBalance> countersChkBalance = afSubIns.getSubResponseParamCheckBalance().getCountersAMFCheckBalance();
			
         for (int i = 0; i < countersChkBalance.size(); i++) {
				String countIDAMF = countersChkBalance.get(i).getCounterID();
			
				if (countIDAMF.equals("200010")) {
					
					AppLog.d("is 200010");
					balance = countersChkBalance.get(i).getCurrentBalance();
				}
         }
         
         try {
         
	         DateTimeFormatter formatDate2 = DateTimeFormat.forPattern("yyyyMMddHHmmssZ");
	         Param_DS2A_InquirySubscriber inquirySubscriberParameter = afSubIns.getInquirySubParam();
	         
	         strbuilder.append("<Inband-Indicator value=\""+inband+"\"/>"
			 +"<Wait-Result-Indicator value=\""+waitRRR+"\"/>"
			 +"<Session-Timeout value=\"5\"/>"
			   + "<Outband-Channel value=\""+outband+"\"/>"
			   + "</TORO-UI-Session-Treatment>"
		            + "<TORO-Customer-Id value=\""+custId+"\"/>"
		            + "<TORO-Subscription-Id>"
			            + "<TORO-Subscription-Id-Type value=\"0\"/>"
			            + "<TORO-Subscription-Id-Data value=\""+afSubIns.getSubMSISDN()+"\"/>"
		            + "</TORO-Subscription-Id>"
		            + "<TORO-Language-Preference value=\""+afSubIns.getClientData().getSmsLang()+"\"/>"
		            + "<TORO-UI-Menu>"
			            + "<Menu-Id value=\""+E01Value[1]+"\"/>"
			            + "<Menu-Unique-Identifier value=\""+custId+"|"+afSubIns.getNewPromoName()+"|"+formatDate.print(currentTime)+"\"/>"
		            + "</TORO-UI-Menu>"
		            + "<TORO-UI-Inline-Parameter>"
		        		+ "<Parameter-Name value=\"balance\"/>"
		        		+ "<Parameter-Value value=\""+balance+"\"/>"
		        	+ "</TORO-UI-Inline-Parameter>"
		            + "<TORO-UI-Inline-Parameter>"
	            		+ "<Parameter-Name value=\"activeStopTime\"/>"
	            		+ "<Parameter-Value value=\""+Config.formatDateWithBetDT.print(formatDate2.parseDateTime(afSubIns.getInquirySubParam().getResultInfo().get(0).getActiveStopTime()))+"\"/>"
	            	+ "</TORO-UI-Inline-Parameter>"
			        + "<TORO-UI-Inline-Parameter>"
		            	+ "<Parameter-Name value=\"sgw\"/>"
		            	+ "<Parameter-Value value=\""+afSubIns.getClientData().getSgw()+"\"/>"
					+ "</TORO-UI-Inline-Parameter>"
					+ "<TORO-UI-Inline-Parameter>"
		            	+ "<Parameter-Name value=\"language\"/>"
		            	+ "<Parameter-Value value=\""+afSubIns.getClientData().getSmsLang()+"\"/>"
					+ "</TORO-UI-Inline-Parameter>"
					+ "<TORO-UI-Inline-Parameter>"
			        	+ "<Parameter-Name value=\"ssid\"/>"
			        	+ "<Parameter-Value value=\""+afSubIns.getClientData().getSsid()+"\"/>"
					+ "</TORO-UI-Inline-Parameter>"
					+ "<TORO-UI-Inline-Parameter>"
				    	+ "<Parameter-Name value=\"scpid\"/>"
				    	+ "<Parameter-Value value=\""+afSubIns.getClientData().getScpid()+"\"/>"
					+ "</TORO-UI-Inline-Parameter>"
			        + "<TORO-UI-Inline-Parameter>"
				    	+ "<Parameter-Name value=\"spName\"/>"
				    	+ "<Parameter-Value value=\""+inquirySubscriberParameter.getResultInfo().get(0).getDs2spName()+"\"/>"
					+ "</TORO-UI-Inline-Parameter>"
					+ "<TORO-UI-Inline-Parameter>"
			    		+ "<Parameter-Name value=\"brandId\"/>"
			    		+ "<Parameter-Value value=\""+inquirySubscriberParameter.getResultInfo().get(0).getDs2brandId()+"\"/>"
			    	+ "</TORO-UI-Inline-Parameter>"
			    	+ "<TORO-UI-Inline-Parameter>"
			    		+ "<Parameter-Name value=\"cos\"/>"
			    		+ "<Parameter-Value value=\""+afSubIns.getNewCos()+"\"/>"
			    	+ "</TORO-UI-Inline-Parameter>"
			        + "<TORO-UI-Inline-Parameter>"
			 			+ "<Parameter-Name value=\"customerCategory\"/>"
			 			+ "<Parameter-Value value=\""+inquirySubscriberParameter.getResultInfo().get(0).getDs2customerCategory()+"\"/>"
			 		+ "</TORO-UI-Inline-Parameter>"
			 		+ "<TORO-UI-Inline-Parameter>"
			 			+ "<Parameter-Name value=\"customerSubCategory\"/>"
			 			+ "<Parameter-Value value=\""+inquirySubscriberParameter.getResultInfo().get(0).getDs2customerSubCategory()+"\"/>"
			 		+ "</TORO-UI-Inline-Parameter>"
			 		+ "<TORO-UI-Inline-Parameter>"
			 			+ "<Parameter-Name value=\"TBCF\"/>"
			 			+ "<Parameter-Value value=\""+inquirySubscriberParameter.getResultInfo().get(0).getDs2gprsTBCF()+"\"/>"
			 		+ "</TORO-UI-Inline-Parameter>");
			 
	
			eqxRawData.setRawMessage(strbuilder.toString());
        
        } catch (Exception e) {
        	AppLog.e("Datatime is wrong format");
        }
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.SRFC_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SRFC_SPECIALIZEDRESOURCE_REQUEST);
		
		return eqxRawData;
	}

}
