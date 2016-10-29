package ct.af.message.outgoing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EFinalCode;
import ct.af.enums.EFunctionGroup;
import ct.af.enums.EMethod;
import ct.af.enums.EProtocal;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_End_ModiScrScreenType {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
	
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.RESPONSE.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(afSubIns.getSubInitOrig());
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
		eqxRawData.setRawDataAttributes(map);
		
		StringBuilder strbuilder = new StringBuilder();
		
		EFinalCode finaleCode = afSubIns.getSubFinalCode();
		
		strbuilder.append("<vcrr>"
				+ "<res>"+finaleCode.getFinalResultCode()+"</res>"
				+ "<desc>"+finaleCode.getFinalDesc()+"</desc>");	
		
		if(finaleCode.getFinalResultCode().equals("000")) {
			
			ArrayList<String> datArr = new ArrayList<String>();
			
			if(afSubIns.getClientData().getDat().contains("&")) {
				datArr =  new ArrayList<String>(Arrays.asList(afSubIns.getClientData().getDat().split("&")));
			} else {
				datArr.add(afSubIns.getClientData().getDat());
			}
			
			if(ec02Instance.getAFInstance().getMainProfile().equals(EFunctionGroup.INS.toString())) {
				
				strbuilder.append("<oldscreentype></oldscreentype>");
				
			} else if(ec02Instance.getAFInstance().getMainProfile().equals(EFunctionGroup.BOS.toString())) {
				
				strbuilder.append( "<succnum>"+datArr.size()+"</succnum>");
				
			}
				
		}
		
		strbuilder.append("</vcrr>");
		
		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = afSubIns.getSubInitInvoke();//new AFUtils().invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubIntCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(0)));
		
		//-- Stats --//
		afSubIns.addStatsOut(finaleCode.getStat());
		
		return eqxRawData;
	}

}
