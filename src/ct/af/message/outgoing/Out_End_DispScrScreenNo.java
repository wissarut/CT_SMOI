package ct.af.message.outgoing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ct.af.utils.StringUtils;
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
import ct.af.message.incoming.parameter.CallScreenNoInfo;
import ct.af.message.incoming.parameter.CallScreenNoInfoList_Item;
import ct.af.message.incoming.parameter.Param_BMP_DispScrScreenNo;
import ct.af.message.incoming.parameter.Param_BSS_DoQueryCallScreen;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_End_DispScrScreenNo {
	
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
			
			if(ec02Instance.getAFInstance().getMainProfile().equals(EFunctionGroup.INS.toString())) 
			{
				
				if(finaleCode.getFinalResultCode().equals("000")) {
		 			
					Param_BMP_DispScrScreenNo respParam = (Param_BMP_DispScrScreenNo) afSubIns.getSubResponseParam();
					 			
					String screentype = respParam.getCallScreenType();
					
					List<CallScreenNoInfo> callScrNoInfoList = respParam.getCallScreenNoInfo();
					List<CallScreenNoInfo> callScrNoInfoListBlack = new ArrayList<CallScreenNoInfo>();
					List<CallScreenNoInfo> callScrNoInfoListWhite = new ArrayList<CallScreenNoInfo>();
					 				
					 				if(callScrNoInfoList != null) {
						for(CallScreenNoInfo item: callScrNoInfoList){
					        if(item.getListType().equalsIgnoreCase("0")){
					        	callScrNoInfoListBlack.add(item);
					        }else{
					        	callScrNoInfoListWhite.add(item);
					        }
					 				    }
					 				}
					 				
					 				strbuilder.append("<screentype>" + screentype + "</screentype>"
					 						+ "<icstype></icstype>"
					 						+ "<totalnum>0</totalnum>"
					 						+ "<msisdnArray></msisdnArray>"
					 						+ "<totalnumBlack>"+callScrNoInfoListBlack.size()+"</totalnumBlack>"
					 						+ "<totalnumWhite>"+callScrNoInfoListWhite.size()+"</totalnumWhite>"
					 						+ "<msisdnArrayBlack>");
					for (CallScreenNoInfo item: callScrNoInfoListBlack)
					 				{
						strbuilder.append("<msisdn>"+item.getCallScreenNo()+"</msisdn>");
					 				}
					 				strbuilder.append("</msisdnArrayBlack>"
					 									+ "<msisdnArrayWhite>");
					for (CallScreenNoInfo item: callScrNoInfoListWhite)
					 				{
						strbuilder.append("<msisdn>"+item.getCallScreenNo()+"</msisdn>");
					 				}
					strbuilder.append("</msisdnArrayWhite>"
							+ "<CLIR>"+respParam.getCLIRFlag()+"</CLIR>");
					 		} 
				
			} else if(ec02Instance.getAFInstance().getMainProfile().equals(EFunctionGroup.BOS.toString())) {
			
				Param_BSS_DoQueryCallScreen respParam = (Param_BSS_DoQueryCallScreen) afSubIns.getSubResponseParam();
			
				String screentype = "1";
				
				if(!StringUtils.isBlank(respParam.getCallScreenNoInfoListRoutingMethod()))
					if(respParam.getCallScreenNoInfoListRoutingMethod().equals("2"))
						screentype = "2";
				
				List<CallScreenNoInfoList_Item> callScrNoInfoList = respParam.getCallScreenNoInfoListItem();
				List<String> callScrNoInfoListBlack = new ArrayList<String>();
				List<String> callScrNoInfoListWhite = new ArrayList<String>();
				
				if(callScrNoInfoList != null) {
					for(CallScreenNoInfoList_Item item: callScrNoInfoList){
						if(!StringUtils.isBlank(item.getCallscreenNo()))
						{
					        if(item.getListType().equalsIgnoreCase("0"))
					        {
					        	callScrNoInfoListBlack.add("66"+item.getCallscreenNo().substring(1, 10));
					        } else {
					        	callScrNoInfoListWhite.add("66"+item.getCallscreenNo().substring(1, 10));
					        }
						}
				    }
				}
				
				strbuilder.append("<screentype>" + screentype + "</screentype>"
						+ "<icstype></icstype>"
						+ "<totalnum>0</totalnum>"
						+ "<msisdnArray></msisdnArray>"
						+ "<totalnumBlack>"+callScrNoInfoListBlack.size()+"</totalnumBlack>"
						+ "<totalnumWhite>"+callScrNoInfoListWhite.size()+"</totalnumWhite>"
						+ "<msisdnArrayBlack>");
				for (String item: callScrNoInfoListBlack)
				{
					strbuilder.append("<msisdn>"+item+"</msisdn>");
				}
				strbuilder.append("</msisdnArrayBlack>"
									+ "<msisdnArrayWhite>");
				for (String item: callScrNoInfoListWhite)
				{
					strbuilder.append("<msisdn>"+item+"</msisdn>");
				}
				strbuilder.append("</msisdnArrayWhite>");
				
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
