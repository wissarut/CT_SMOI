package ct.af.message.incoming.parser;

import java.util.List;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ct.af.enums.EResultCode;
import ct.af.message.incoming.parameter.AVP;
import ct.af.message.incoming.parameter.AccountingInfo;
import ct.af.message.incoming.parameter.Param_DS2A_InquirySubscriber;
import ct.af.message.incoming.parameter.ResultInfo;
import ct.af.message.incoming.parameter.VAL;
import ct.af.utils.StringUtils;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_DS2A_InquirySubscriberParser {
	
	private static In_DS2A_InquirySubscriberParser parser = null;
	
	protected  In_DS2A_InquirySubscriberParser() {
	  
	}
	public static In_DS2A_InquirySubscriberParser getInstance() {
		if(parser == null) {
			parser = new In_DS2A_InquirySubscriberParser();
		}
		return parser;
	}
	
	public Param_DS2A_InquirySubscriber doParser(EquinoxRawData eqxRawData) {
		
		Param_DS2A_InquirySubscriber inquirySubscriberParameter = new Param_DS2A_InquirySubscriber();
		
		try {
			Serializer serializer = new Persister();
			inquirySubscriberParameter = serializer.read(Param_DS2A_InquirySubscriber.class, "<xml>" + eqxRawData.getRawDataMessage() + "</xml>");
			
			inquirySubscriberParameter.setRecive(true);
		} catch (Exception e) {
			AppLog.e("[Exception] Error inquirySubscriber parser");
			return inquirySubscriberParameter;
		}
		
		List<AVP> listAVP = inquirySubscriberParameter.getAvp();
		boolean keyUnknown = false;
		EResultCode resultCode = EResultCode.RE40301;
		
		for (AVP avp: listAVP) {
			
			if (avp.getType().equals("methodVersion")) {
				inquirySubscriberParameter.setMethodVersion(avp.getVal().get(0).getValue());
			}
				
			
			if (avp.getType().equals("resultInfo") && avp.getVal().get(0).getValue() != null) {
				
				for (VAL val: avp.getVal()) {
					
					ResultInfo resultInfo = new ResultInfo();
					String[] message = val.getValue().split(",",-1);
					
					if(message.length == 1) {
						keyUnknown = true;
					}
					
					if(message.length == 32) {
						
						resultInfo.setMsisdn(message[0]);
						resultInfo.setCustomerId(message[1]);
						resultInfo.setDs2state(message[2]);
						resultInfo.setDs2language(message[3]);
						resultInfo.setDs2pin(message[4]);
						resultInfo.setDs2hackTime(message[5]);
						resultInfo.setDs2hints(message[6]);
						resultInfo.setDs2gprsTBCF(message[7]);
						resultInfo.setDs2servPackageId(message[8]);
						resultInfo.setDs2pin2(message[9]);
						resultInfo.setDs2brandId(message[10]);
						resultInfo.setDs2spName(message[11]);
						resultInfo.setDs2customerCategory(message[12]);
						resultInfo.setDs2customerSubCategory(message[13]);
						resultInfo.setDs2customerSegment(message[14]);
						resultInfo.setEmailLanguage(message[15]);
						resultInfo.setIvrLanguage(message[16]);
						resultInfo.setUssdLanguage(message[17]);
						resultInfo.setSmsLanguage(message[18]);
						resultInfo.setRegistrationDate(message[19]);
						resultInfo.setTimeDrivenState(message[20]);
						resultInfo.setFraudFlag(message[21]);
						resultInfo.setPinFraudFlag(message[22]);
						resultInfo.setUserReqSuspend(message[23]);
						resultInfo.setDPmode(message[24]);
						resultInfo.setActiveStopTime(message[25]);
						resultInfo.setSuspendStopTime(message[26]);
						resultInfo.setDisableStopTime(message[27]);
						resultInfo.setTerminateStopTime(message[28]);
						resultInfo.setFreezeTime(message[29]);
						resultInfo.setBaId(message[30]);
						resultInfo.setCaId(message[31]);
						
						inquirySubscriberParameter.getResultInfo().add(resultInfo);
						
					}
					
				}
	
			}
			
			if (avp.getType().equals("accountingInfo")) {
				
				for (VAL val: avp.getVal()) {
					AccountingInfo accountingInfo = new AccountingInfo();
					String[] message = val.getValue().split(",",-1);
						
					accountingInfo.setDs2classOfservice(message[0]);
					accountingInfo.setDs2serviceLocation(message[1]);
//					accountingInfo.setGt(message[2]);
//					accountingInfo.setDpc(message[3]);
//					accountingInfo.setDs2shareAccSwitch(message[4]);
					accountingInfo.setDs2cbpLocation(message[5]);
//					accountingInfo.setDs2ipAdress(message[6]);
//					accountingInfo.setDs2ipPort(message[7]);
//					accountingInfo.setDs2dataServiceLifeStyle(message[8]);
//					accountingInfo.setDs2notificationPolicyControl(message[9]);
//					accountingInfo.setDs2recurringPolicyControl(message[10]);
//					accountingInfo.setDs2deselectPolicyControl(message[11]);
//					accountingInfo.setDs2offlineNotificationPolicyControl(message[12]);
//					accountingInfo.setHomelocation(message[13]);
//					accountingInfo.setAmf1stTopupRewardLifeTime(message[14]);
//					accountingInfo.setAmf1stTopupRewardPromo(message[15]);
//					accountingInfo.setDs3NotifPolicyControlAS(message[16]);
					
					inquirySubscriberParameter.getAccountingInfo().add(accountingInfo);
						
				
					
				}
				
			}
			/*
			if (avp.getType().equals("toggledAccountingInfo")) {
				List<ToggledAccountingInfo> toggledAccountingInfoList = new ArrayList<ToggledAccountingInfo>();
				
				for (VAL val: avp.getVal()) {
					ToggledAccountingInfo toggledAccountingInfo = new ToggledAccountingInfo();
					String[] message = val.getValue().split(",",-1);
					
					if(message.length == 10) {
						
						toggledAccountingInfo.setDs2toggleF(message[0]);
						toggledAccountingInfo.setDs2stateT(message[1]);
						toggledAccountingInfo.setDs2classOfServiceT(message[2]);
						toggledAccountingInfo.setDs2serviceLocationT(message[3]);
						toggledAccountingInfo.setGt(message[4]);
						toggledAccountingInfo.setDpc(message[5]);
						toggledAccountingInfo.setDs2shareAccSwitchT(message[6]);
						toggledAccountingInfo.setDs2cbpLocationT(message[7]);
						toggledAccountingInfo.setDs2ipAdress(message[8]);
						toggledAccountingInfo.setDs2ipPort(message[9]);
						
						toggledAccountingInfoList.add(toggledAccountingInfo);
						
					}
					
				}
				
				inquirySubscriberParameter.setToggledAccountingInfo(toggledAccountingInfoList);
			}
			
			if (avp.getType().equals("prefixAccountingInfo")) {
				List<PrefixAccountingInfo> prefixAccountingInfoList = new ArrayList<PrefixAccountingInfo>();
				
				for (VAL val: avp.getVal()) {
					PrefixAccountingInfo prefixAccountingInfo = new PrefixAccountingInfo();
					String[] message = val.getValue().split(",",-1);
					
					if(message.length == 9) {
						
						prefixAccountingInfo.setDs2state(message[0]);
						prefixAccountingInfo.setDs2classOfService(message[1]);
						prefixAccountingInfo.setDs2serviceLocation(message[2]);
						prefixAccountingInfo.setGt(message[3]);
						prefixAccountingInfo.setDpc(message[4]);
						prefixAccountingInfo.setDs2shareAccSwitch(message[5]);
						prefixAccountingInfo.setDs2cbpLocation(message[6]);
						prefixAccountingInfo.setDs2ipAdress(message[7]);
						prefixAccountingInfo.setDs2ipPort(message[8]);
						
						prefixAccountingInfoList.add(prefixAccountingInfo);
						
					}
					
				}
				
				inquirySubscriberParameter.setPrefixAccountingInfo(prefixAccountingInfoList);
			}
			*/
		}
		
		if(inquirySubscriberParameter.getResultInfo().size() == 1 
				&& StringUtils.isNotBlank(inquirySubscriberParameter.getAccountingInfo().get(0).getDs2classOfservice())
				&& StringUtils.isNotBlank(inquirySubscriberParameter.getResultInfo().get(0).getCustomerId())
				&& StringUtils.isNotBlank(inquirySubscriberParameter.getResultInfo().get(0).getActiveStopTime())
				&& StringUtils.isNotBlank(inquirySubscriberParameter.getResultInfo().get(0).getDs2spName())
				&& StringUtils.isNotBlank(inquirySubscriberParameter.getResultInfo().get(0).getDs2brandId())
				&& StringUtils.isNotBlank(inquirySubscriberParameter.getResultInfo().get(0).getDs2customerCategory())
				&& StringUtils.isNotBlank(inquirySubscriberParameter.getResultInfo().get(0).getDs2customerSubCategory())
				&& StringUtils.isNotBlank(inquirySubscriberParameter.getResultInfo().get(0).getDs2gprsTBCF())
				&& StringUtils.isNotBlank(inquirySubscriberParameter.getResultInfo().get(0).getCaId())
				&& StringUtils.isNotBlank(inquirySubscriberParameter.getResultInfo().get(0).getBaId())) {
			
			inquirySubscriberParameter.setRespResultCode(EResultCode.RE20000);
			inquirySubscriberParameter.setNotMissing(true);
			inquirySubscriberParameter.setValid(true);
			return inquirySubscriberParameter;
			
		} else {
			
			if(keyUnknown == false) {
			
				if(StringUtils.isBlank(inquirySubscriberParameter.getAccountingInfo().get(0).getDs2classOfservice())) {
					AppLog.e("inquirySubscriberParameter.getAccountingInfo().getDs2classOfservice() is null");
				}
				if(inquirySubscriberParameter.getResultInfo().size() == 1) {
					if(StringUtils.isBlank(inquirySubscriberParameter.getResultInfo().get(0).getCustomerId())) {
						AppLog.e("inquirySubscriberParameter.getResultInfo().getCustomerId() is null");
					}
					if(StringUtils.isBlank(inquirySubscriberParameter.getResultInfo().get(0).getActiveStopTime())) {
						AppLog.e("inquirySubscriberParameter.getResultInfo().get(0).getActiveStopTime() is null");
					}
					if(StringUtils.isBlank(inquirySubscriberParameter.getResultInfo().get(0).getDs2spName())) {
						AppLog.e("inquirySubscriberParameter.getResultInfo().get(0).getDs2spName() is null");
					}
					if(StringUtils.isBlank(inquirySubscriberParameter.getResultInfo().get(0).getDs2brandId())) {
						AppLog.e("inquirySubscriberParameter.getResultInfo().get(0).getDs2brandId() is null");
					}
					if(StringUtils.isBlank(inquirySubscriberParameter.getResultInfo().get(0).getDs2customerCategory())) {
						AppLog.e("inquirySubscriberParameter.getResultInfo().get(0).getDs2customerCategory() is null");
					}
					if(StringUtils.isBlank(inquirySubscriberParameter.getResultInfo().get(0).getDs2customerSubCategory())) {
						AppLog.e("inquirySubscriberParameter.getResultInfo().get(0).getDs2customerSubCategory() is null");
					}
					if(StringUtils.isBlank(inquirySubscriberParameter.getResultInfo().get(0).getDs2gprsTBCF())) {
						AppLog.e("inquirySubscriberParameter.getResultInfo().get(0).getDs2gprsTBCF() is null");
					}
					if(StringUtils.isBlank(inquirySubscriberParameter.getResultInfo().get(0).getCaId())) {
						AppLog.e("inquirySubscriberParameter.getResultInfo().get(0).getCaId() is null");
					}
					if(StringUtils.isBlank(inquirySubscriberParameter.getResultInfo().get(0).getBaId())) {
						AppLog.e("inquirySubscriberParameter.getResultInfo().get(0).getBaId() is null");
					}
				} else {
					AppLog.e("ResultInfo is null");
				}
			} else {
				resultCode = EResultCode.RE221;
			}
				
				
			
			inquirySubscriberParameter.setRespResultCode(resultCode);
			return inquirySubscriberParameter;
		}
		
	}
	
}
