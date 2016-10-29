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
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_AMF_ManageCounters {

	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.EXTENDED.getCType());
		eqxRawData.setName(EProtocal.LDAP.toString());
		eqxRawData.setTo(Config.AMF_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.OID.getEqxMsg(), "0.0.17.1218.8.7.3");
		eqxRawData.setRawDataAttributes(map);
		
		StringBuilder strbuilder = new StringBuilder();
		
		String productNo = "";
		String counterName = "";
		String firstActDateStr = "";
		
		strbuilder.append("<AVP type=\"methodVersion\">"
				+ "<vals value=\"4\"/>"
				+ "</AVP>"
				+ "<AVP type=\"subscriptionId\">"
				+ "<vals value=\"0|"+afSubIns.getSubMSISDN()+"\"/>"
				+ "</AVP>");
				if(ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()) == null && afSubIns.getAmfManageCounter().equals("1")) {
					strbuilder.append("<AVP type=\"chargePhase\">"
							+ "<vals value=\"2\"/>"
							+ "</AVP>");
				} else {
				
					if(ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()) != null) {
						
						if(ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getOldCos().equals(ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getNewCos())) {
							for(int i = 0; i < ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getSubResponseParamInquiryCounter().getCounterInfo().size(); i++) {
								if(!StringUtils.isBlank(ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getSubResponseParamInquiryCounter().getCounterInfo().get(i).getMainPromotionF())) {
									if(ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getSubResponseParamInquiryCounter().getCounterInfo().get(i).getMainPromotionF().equals("1")) {
										productNo = ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getSubResponseParamInquiryCounter().getCounterInfo().get(i).getProductNumber();
										counterName = ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getSubResponseParamInquiryCounter().getCounterInfo().get(i).getCounterName();
									}
								}
							}
						} else {
							for(int i = 0; i < ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getSubResponseParamFirstAct().getCounterInfo().size(); i++) {
								if(!StringUtils.isBlank(ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getSubResponseParamFirstAct().getCounterInfo().get(i).getMainPromotionF())) {
									if(ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getSubResponseParamFirstAct().getCounterInfo().get(i).getMainPromotionF().equals("1")) {
										productNo = ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getSubResponseParamFirstAct().getCounterInfo().get(i).getProductNumber();
										counterName = ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getSubResponseParamFirstAct().getCounterInfo().get(i).getCounterName();
									}
								}
							}
						}
						
						DateTime firstActDate = Config.formatDateWithMiTz.parseDateTime(ec02Instance.getAFInstance().getMainSubInstance(afSubIns.getSubHostInsNo()).getFirstsActDate());
						firstActDateStr = Config.formatDateWithBetDT.print(firstActDate);
						
					} else {
						
						if(afSubIns.getOldCos().equals(afSubIns.getNewCos())) {
							
							for(int i = 0; i < afSubIns.getSubResponseParamInquiryCounter().getCounterInfo().size(); i++) {
								if(!StringUtils.isBlank(afSubIns.getSubResponseParamInquiryCounter().getCounterInfo().get(i).getMainPromotionF())) {
									if(afSubIns.getSubResponseParamInquiryCounter().getCounterInfo().get(i).getMainPromotionF().equals("1")) {
										productNo = afSubIns.getSubResponseParamInquiryCounter().getCounterInfo().get(i).getProductNumber();
										counterName = afSubIns.getSubResponseParamInquiryCounter().getCounterInfo().get(i).getCounterName();
									}
								}
							}
						} else {
							for(int i = 0; i < afSubIns.getSubResponseParamFirstAct().getCounterInfo().size(); i++) {
								if(!StringUtils.isBlank(afSubIns.getSubResponseParamFirstAct().getCounterInfo().get(i).getMainPromotionF())) {
									if(afSubIns.getSubResponseParamFirstAct().getCounterInfo().get(i).getMainPromotionF().equals("1")) {
										productNo = afSubIns.getSubResponseParamFirstAct().getCounterInfo().get(i).getProductNumber();
										counterName = afSubIns.getSubResponseParamFirstAct().getCounterInfo().get(i).getCounterName();
									}
								}
							}
						}
						
						DateTime firstActDate = Config.formatDateWithMiTz.parseDateTime(afSubIns.getFirstsActDate());
						firstActDateStr = Config.formatDateWithBetDT.print(firstActDate);
						
						
					}
					
					strbuilder.append("<AVP type=\"eventTimestamp\">"
							+ "<vals value=\""+firstActDateStr+"\"/>"
							+ "</AVP>"
							+ "<AVP type=\"counterPackInfo\">"
							+ "<vals value=\""+productNo+",1,"+firstActDateStr+",1,"+firstActDateStr+","+counterName+",0\"/>"
							+ "</AVP>"
							+ "<AVP type=\"chargePhase\">"
							+ "<vals value=\"0\"/>"
							+ "</AVP>");
				}

		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.AMF_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_AMF_MANAGECOUNTERS_REQUEST);
		
		return eqxRawData;
	}
}
