package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
				 
public class Out_AMF_ObtainCounter {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
			//-- Construct EquinoxRawData --//
			EquinoxRawData eqxRawData = new EquinoxRawData();
			eqxRawData.setType(EEventType.REQUEST.getEventType());
			eqxRawData.setCType(ECType.EXTENDED.getCType());
			eqxRawData.setName(EProtocal.LDAP.toString());
			eqxRawData.setTo(Config.AMF_INTERFACE);
			
			Map<String, String> map = new HashMap<String, String>();
				map.put(EEqxMsg.OID.getEqxMsg(), "0.0.17.1218.8.7.1");
			eqxRawData.setRawDataAttributes(map);
			
			StringBuilder strbuilder = new StringBuilder();
			
			strbuilder.append("<AVP type=\"methodVersion\">"
					+ "<vals value=\"9\"/>"
					+ "</AVP>"
					+ "<AVP type=\"subscriptionId\">"
					+ "<vals value=\"0|"+afSubIns.getSubMSISDN()+"\" />"
					+ "</AVP>"
					+ "<AVP type=\"counterType\">"
					+ "<vals value=\"3\" />"
					+ "</AVP>"
					+ "<AVP type=\"obtainTriggerType\">"
					+ "<vals value=\"all\" />"
					+ "</AVP>");
	
			eqxRawData.setRawMessage(strbuilder.toString());
			
			//-- Invoke --//
			String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
			eqxRawData.setInvoke(invoke);
			afSubIns.addSubInvoke(invoke);
			
			//-- Timeout --//
			afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.AMF_TIMEOUT)));
			
			//-- Stats --//
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_AMF_OBTAINNONBALANCECOUNTER_REQUEST);
			
			return eqxRawData;
			
	}
}
