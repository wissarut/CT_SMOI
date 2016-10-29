
package ct.af.message.outgoing;

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

public class Out_End_DispPPSFntelNo {
	
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
		
		if(finaleCode.getFinalResultCode().equals("000"))
		{
			if(!ec02Instance.getAFInstance().getMainProfile().equals(EFunctionGroup.RTBS.toString())) {
//			Param_BSS_DoQueryFN respParam = (Param_BSS_DoQueryFN) afSubIns.getSubResponseParam();
//			
//			List<ProductFNList> productFnList = respParam.getProductFNList();
//			int count = productFnList.size();
//			String fn1Str = "";
//			
//			strbuilder.append("<count>"+count+"</count>"	// Pending
//						+ fn1Str	// Pending
//						+ "<effectiveDate1>"+""+"</effectiveDate1>"	// Pending [ Expire not found in response message. ]
//						+ "<expireDate1>%s</expireDate1>"	
//						+ "<GroupType1>%d</GroupType1>"	
//						+ "<fn2>%s</fn2>"	
//						+ "<effectiveDate2>%s</effectiveDate2>"	
//						+ "<expireDate2>%s</expireDate2>"	
//						+ "<GroupType2>%d</GroupType2>");
			}
		}
		
		strbuilder.append("</vcrr>");

		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = afSubIns.getSubInitInvoke();//new AFUtils().invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubIntCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(10)));
		
		//-- Stats --//
		afSubIns.addStatsOut(finaleCode.getStat());
		
		return eqxRawData;
	}

}
