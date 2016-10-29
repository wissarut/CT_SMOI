package ct.af.substate;

import ct.af.enums.EFunctionGroup;
import ct.af.enums.EResultCode;
import ct.af.enums.ESubState;
import ct.af.instance.AFInstance;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_DS2A_InquirySubscriber;
import ct.af.message.incoming.parameter.Param_Idle_Request;
import ct.af.utils.Config;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.utils.AppLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Do_DS2A_InquirySub {

	public void doBusinessLogic(AbstractAF abstractAF, EC02Instance ec02Instance, AFSubInstance afSubIns) {

		Param_DS2A_InquirySubscriber inqParameter = afSubIns.getInquirySubParam();

		AFInstance afInstance = ec02Instance.getAFInstance();
		String Cmd = afSubIns.getSubInitCmd();
		
		EFunctionGroup locationGroup = EFunctionGroup.Unknown;
		String location = "";
		
		String oldCos = inqParameter.getAccountingInfo().get(0).getDs2classOfservice();
		
		if(StringUtils.isBlank(afSubIns.getClientData().getIvrLang())) {
			afSubIns.getClientData().setIvrLang(inqParameter.getResultInfo().get(0).getIvrLanguage());
		}
		
		if(StringUtils.isBlank(afSubIns.getClientData().getSmsLang())) {
			afSubIns.getClientData().setSmsLang(inqParameter.getResultInfo().get(0).getSmsLanguage());
		}
		
		if(StringUtils.isBlank(afSubIns.getClientData().getUssdLang())) {
			afSubIns.getClientData().setUssdLang(inqParameter.getResultInfo().get(0).getUssdLanguage());
		}
		
		if(StringUtils.isBlank(afSubIns.getClientData().getEmailLang())) {
			afSubIns.getClientData().setEmailLang(inqParameter.getResultInfo().get(0).getEmailLanguage());
		}
		
		if(!StringUtils.isBlank(inqParameter.getAccountingInfo().get(0).getDs2cbpLocation())) {
			location = inqParameter.getAccountingInfo().get(0).getDs2cbpLocation();
		} else if(!StringUtils.isBlank(inqParameter.getAccountingInfo().get(0).getDs2serviceLocation())) {
			location = inqParameter.getAccountingInfo().get(0).getDs2serviceLocation();
		}

		AppLog.d("Location from DS2A : "+location);
		
		String[] chkprefixINS = Config.INS_LOCATION.split(",",-1);

		for(String str : chkprefixINS) {
			if (location.startsWith(str)) {
				locationGroup = EFunctionGroup.INS;
			}
		}

		String[] chkprefixBOS = Config.BOS_LOCATION.split(",");

		for (String str : chkprefixBOS) {
			if (location.startsWith(str)) {
				locationGroup = EFunctionGroup.BOS;
			}

		}

		AppLog.d("MainLocation  "+location);
		AppLog.d("MainProfile  "+locationGroup);
		AppLog.d("FunctionGroup  "+locationGroup);
		
		afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
		afInstance.setMainLocation(location);
		afInstance.setMainProfile(locationGroup.toString());
		afSubIns.setSubFunctionGroup(locationGroup.toString());
			
		if(locationGroup.equals(EFunctionGroup.INS)) {
			
			if (Cmd.equals("dispPPSInfo")) {
				
				afSubIns.setSubNextState(ESubState.AMF_CheckBalance+","+ESubState.AMF_ObtainCounter);
				afSubIns.setSubNextOfNextState(ESubState.AMF_ModifyCounters_AND_AMF_ObtainNoneBalanceCounter.toString());
				afSubIns.setSubStateHostWait(ESubState.AMF_ModifyCounters_AND_AMF_ObtainNoneBalanceCounter.toString());
			
			} else if (Cmd.equals("dispPPSPkgrew")) {
				
				 afSubIns.setSubControlState(ESubState.Unknown.toString());
				 afSubIns.setSubNextState(ESubState.Unknown.toString());
				 
			} else if (Cmd.equals("modiPPSValidity")) {
				
				AppLog.d("Config.MODIVALIDITY_TMF_FLAG : "+Config.MODIVALIDITY_TMF_FLAG);
				
				if(Config.MODIVALIDITY_TMF_FLAG == 0) {
					
					afSubIns.setSubControlState(ESubState.AMF_ModifyCounters.toString());
					afSubIns.setSubNextState(ESubState.AMF_ModifyCounters.toString());
					
				} else if(Config.MODIVALIDITY_TMF_FLAG == 1) {
					
					afSubIns.setSubControlState(ESubState.TMF_AccountAdjustment.toString());
					afSubIns.setSubNextState(ESubState.TMF_AccountAdjustment.toString());
					
				}
				
			} else if (Cmd.equals("modiPPSMultiAttr")) {
				
				if(!StringUtils.isBlank(afSubIns.getClientData().getBalance())
						|| !StringUtils.isBlank(afSubIns.getClientData().getValidity())) {
					
					if(!StringUtils.isBlank(afSubIns.getClientData().getPrmmoney())
							|| !StringUtils.isBlank(afSubIns.getClientData().getPrmsm())
							|| !StringUtils.isBlank(afSubIns.getClientData().getPrmminute())
							|| !StringUtils.isBlank(afSubIns.getClientData().getPoint())
							|| !StringUtils.isBlank(afSubIns.getClientData().getFreecalltimes())
							|| !StringUtils.isBlank(afSubIns.getClientData().getFreerbtsong())
							|| !StringUtils.isBlank(afSubIns.getClientData().getFreerbtmf())
							|| !StringUtils.isBlank(afSubIns.getClientData().getScore())
							|| !StringUtils.isBlank(afSubIns.getClientData().getPrmscore())
							|| !StringUtils.isBlank(afSubIns.getClientData().getSmusage())
							|| !StringUtils.isBlank(afSubIns.getClientData().getScore())) {
						
						AppLog.e("[Invalid] [balance,validity is comming] parameter one of the except list is not null");
						afSubIns.setSubInternalCode(EResultCode.REClient_send_Bad_request);
						afSubIns.setSubNextState(ESubState.End_ModiPPSMultiAttr.toString());
					
					} else {
						
						afSubIns.setSubControlState(ESubState.TMF_AccountAdjustment.toString());
						afSubIns.setSubNextState(ESubState.TMF_AccountAdjustment.toString());
						
					}
					
				}
				
			} else if (Cmd.equals("addScrScreenNo")) {
				
				Param_Idle_Request inqIdleParameter = (Param_Idle_Request) afSubIns.getClientData();

				AppLog.d("Config :"+Config.ADDCALLSCREENNO);
				AppLog.d("getScreentype : "+inqIdleParameter.getScreentype());
				AppLog.d("getDat : "+inqIdleParameter.getDat());

				List<String> items = new ArrayList<String>();

				try {
					items = Arrays.asList(Config.ADDCALLSCREENNO.split("\\s*,\\s*"));
				} catch(Exception ex) {
					items.add(Config.ADDCALLSCREENNO);
				}

				if(!(afSubIns.getClientData().getScreentype().equals("1") && items.contains(inqIdleParameter.getDat()))){
					
					afSubIns.setSubControlState(ESubState.BMP_CallScreening.toString());
					afSubIns.setSubNextState(ESubState.BMP_CallScreening.toString());
					
				} else {

					afSubIns.setSubControlState(ESubState.End_AddScrScreenNo.toString());
					afSubIns.setSubNextState(ESubState.End_AddScrScreenNo.toString());
					afSubIns.setSubInternalCode(EResultCode.REClient_send_Bad_request);
					
				}
				
			} else if (Cmd.equals("deleScrScreenNo")||Cmd.equals("modiScrScreenType")||Cmd.equals("modiScrWhiteList")) {
				
				afSubIns.setSubControlState(ESubState.BMP_CallScreening.toString());
				afSubIns.setSubNextState(ESubState.BMP_CallScreening.toString());
				
			} else if (Cmd.equals("dispScrScreenNo")) {
				
				afSubIns.setSubControlState(ESubState.BMP_DispScrScreenNo.toString());
				afSubIns.setSubNextState(ESubState.BMP_DispScrScreenNo.toString());
				
			} else if (Cmd.equals("activatePPSSingSub")) {
				
				afSubIns.setSubControlState(ESubState.E01_NewClassOfService.toString());
				afSubIns.setSubNextState(ESubState.E01_NewClassOfService.toString());
				afSubIns.setSubFunctionGroup(EFunctionGroup.FirstActivate.toString());
				afSubIns.setOldCos(oldCos);
				afSubIns.setNewCos(oldCos);
				
				afSubIns.getReportData().setCustomerID(afSubIns.getInquirySubParam().getResultInfo().get(0).getCaId());
				afSubIns.getReportData().setAccountID(afSubIns.getInquirySubParam().getResultInfo().get(0).getBaId());
				
				if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2brandId().equals("5")) {	
					afSubIns.getReportData().setOldUserState(afSubIns.getInquirySubParam().getResultInfo().get(0).getTimeDrivenState());
					
				} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2brandId().equals("4")) {	
					afSubIns.getReportData().setOldUserState(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2state());
				}
				
				String oldSuspendFlag = "Suspend Request";

				if(afSubIns.getInquirySubParam().getResultInfo().get(0).getFraudFlag().equalsIgnoreCase("false")) {
					oldSuspendFlag = "Normal";
				}

				afSubIns.getReportData().setOldSuspendFlag(oldSuspendFlag);

				String oldFraudFlag = "Fraud";

				if(afSubIns.getInquirySubParam().getResultInfo().get(0).getPinFraudFlag().equalsIgnoreCase("false")) {
					oldFraudFlag = "No Fraud";
				}
				
				afSubIns.getReportData().setOldFraudFlag(oldFraudFlag);
				
			} else if (Cmd.equals("modiPPSLanguage") || Cmd.equals("modiPPSSMSLanguage")) {
				
				afSubIns.setSubControlState(ESubState.USMP_ModifySubscriber.toString());
				afSubIns.setSubNextState(ESubState.USMP_ModifySubscriber.toString());
				afSubIns.setOldCos(oldCos);
				afSubIns.setNewCos(oldCos);
				AppLog.d("OLD COS : "+afSubIns.getOldCos());
				
			} else if (Cmd.equals("modiPPSCreditLimit")) {
				
				if(afSubIns.getClientData().getFlagCreditlimit().equals("1")) {
					
					if(Integer.parseInt(afSubIns.getClientData().getIncrement()) >= 0) {
						
						afSubIns.setSubControlState(ESubState.SDF_GetAmfCounter.toString());
						afSubIns.setSubNextState(ESubState.SDF_GetAmfCounter.toString());
						afSubIns.setOldCos(oldCos);
					
					} else {
						
						afSubIns.setSubInternalCode(EResultCode.REClient_send_Bad_request);
						afSubIns.setSubControlState(ESubState.End_ModiPPSCreditLimit.toString());
						afSubIns.setSubNextState(ESubState.End_ModiPPSCreditLimit.toString());
					}
					
				} else if(afSubIns.getClientData().getFlagCreditlimit().equals("0")) {
					
					if(Integer.parseInt(afSubIns.getClientData().getIncrement()) == 0) {
						
						afSubIns.setSubInternalCode(EResultCode.REClient_send_Bad_request);
						afSubIns.setSubControlState(ESubState.End_ModiPPSCreditLimit.toString());
						afSubIns.setSubNextState(ESubState.End_ModiPPSCreditLimit.toString());
					
					} else {
						
						afSubIns.setSubControlState(ESubState.SDF_GetAmfCounter.toString());
						afSubIns.setSubNextState(ESubState.SDF_GetAmfCounter.toString());
						afSubIns.setOldCos(oldCos);
						AppLog.d(afSubIns.getOldCos());
						AppLog.d("OLD COS : "+afSubIns.getOldCos());
						
					}
					
				} else {
					
					afSubIns.setSubInternalCode(EResultCode.REClient_send_Bad_request);
					afSubIns.setSubControlState(ESubState.End_ModiPPSCreditLimit.toString());
					afSubIns.setSubNextState(ESubState.End_ModiPPSCreditLimit.toString());
					
				}
				
			} else if (Cmd.equals("setPPSReward")) {
				
				if((StringUtils.isNotBlank(afSubIns.getClientData().getBalance()) || StringUtils.isNotBlank(afSubIns.getClientData().getValidity())) 
						&& StringUtils.isBlank(afSubIns.getClientData().getPrmmoney())
						&& StringUtils.isBlank(afSubIns.getClientData().getPrmsm())
						&& StringUtils.isBlank(afSubIns.getClientData().getPrmminute())
						&& StringUtils.isBlank(afSubIns.getClientData().getPoint())
						&& StringUtils.isBlank(afSubIns.getClientData().getFreecalltimes())
						&& StringUtils.isBlank(afSubIns.getClientData().getFreerbtsong())
						&& StringUtils.isBlank(afSubIns.getClientData().getFreerbtmf())
						&& StringUtils.isBlank(afSubIns.getClientData().getScore())
						&& StringUtils.isBlank(afSubIns.getClientData().getPrmscore())) {
					
					afSubIns.setRmfOffering("MainBalance");
					afSubIns.setSubControlState(ESubState.RMF_RewardOffering.toString());
					afSubIns.setSubNextState(ESubState.RMF_RewardOffering.toString());
				
				} else if( StringUtils.isBlank(afSubIns.getClientData().getBalance()) && StringUtils.isBlank(afSubIns.getClientData().getValidity())
						&& ((StringUtils.isNotBlank(afSubIns.getClientData().getPrmmoney()) && StringUtils.isNotBlank(afSubIns.getClientData().getExpire()))
						|| (StringUtils.isNotBlank(afSubIns.getClientData().getPrmmoney()))) ) {
					
					afSubIns.setRmfOffering("MonetaryUsage");
					afSubIns.setSubControlState(ESubState.RMF_RewardOffering.toString());
					afSubIns.setSubNextState(ESubState.RMF_RewardOffering.toString());

				} else if( StringUtils.isBlank(afSubIns.getClientData().getBalance()) && StringUtils.isBlank(afSubIns.getClientData().getValidity())
						&& ((StringUtils.isNotBlank(afSubIns.getClientData().getPrmsm()) && StringUtils.isNotBlank(afSubIns.getClientData().getExpire()))
						|| (StringUtils.isNotBlank(afSubIns.getClientData().getPrmsm()))) ) {
					
					afSubIns.setRmfOffering("FreeResource1");
					afSubIns.setSubControlState(ESubState.RMF_RewardOffering.toString());
					afSubIns.setSubNextState(ESubState.RMF_RewardOffering.toString());

				} else if( StringUtils.isBlank(afSubIns.getClientData().getBalance()) && StringUtils.isBlank(afSubIns.getClientData().getValidity())
						&& ((StringUtils.isNotBlank(afSubIns.getClientData().getPrmminute()) && StringUtils.isNotBlank(afSubIns.getClientData().getExpire()))
						|| (StringUtils.isNotBlank(afSubIns.getClientData().getPrmminute()))) ) {
					
					afSubIns.setRmfOffering("FreeResource2");
					afSubIns.setSubControlState(ESubState.RMF_RewardOffering.toString());
					afSubIns.setSubNextState(ESubState.RMF_RewardOffering.toString());
				
				} else {
					
					afSubIns.setSubInternalCode(EResultCode.REClient_send_Bad_request);
					afSubIns.setSubControlState(ESubState.End_SetPPSReward.toString());
					afSubIns.setSubNextState(ESubState.End_SetPPSReward.toString());
				}
				
			} else if (Cmd.equals("modiPPSMultiWallet")) {
				
				if(afSubIns.getClientData().getResetBalance().equals("0") 
						&& afSubIns.getClientData().getResetSecondpocket().equals("0")
						&& afSubIns.getClientData().getResetPrmmoney().equals("0")) {
					
					afSubIns.setSubInternalCode(EResultCode.RE20000);
					afSubIns.setSubControlState(ESubState.End_ModiPPSMultiWallet.toString());
					afSubIns.setSubNextState(ESubState.End_ModiPPSMultiWallet.toString());
				
				} else if(afSubIns.getClientData().getResetBalance().equals("1")) {
					
					afSubIns.setSubControlState(ESubState.TMF_AccountAdjustment.toString());
					afSubIns.setSubNextState(ESubState.TMF_AccountAdjustment.toString());
				
				} else if(afSubIns.getClientData().getResetBalance().equals("0")
						&& (afSubIns.getClientData().getResetSecondpocket().equals("1"))
						|| afSubIns.getClientData().getResetPrmmoney().equals("1")) {
					
					afSubIns.setSubControlState(ESubState.RMF_RewardAdjustment.toString());
					afSubIns.setSubNextState(ESubState.RMF_RewardAdjustment.toString());
					
				}
				
			} else {
				
				afInstance.setMainProfile(EFunctionGroup.RTBS.toString());
				afSubIns.setSubFunctionGroup(EFunctionGroup.RTBS.toString());
				afSubIns.setSubControlState(ESubState.SGSCPSMOI_ForwardCommand.toString());
				afSubIns.setSubNextState(ESubState.SGSCPSMOI_ForwardCommand.toString());
				afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
				
			}
			
		} else if(locationGroup.equals(EFunctionGroup.BOS)) {
			
			if (Cmd.equals("dispPPSInfo")) {
				
				afSubIns.setSubControlState(ESubState.E01_Destination.toString());
				afSubIns.setSubNextState(ESubState.E01_Destination.toString());
				
			} else if (Cmd.equals("dispPPSPkgrew")) {
				
				afSubIns.setSubControlState(ESubState.E01_Destination.toString());
				afSubIns.setSubNextState(ESubState.E01_Destination.toString());
				
			} else if (Cmd.equals("modiPPSValidity")) {
				
				afSubIns.setSubControlState(ESubState.E01_Destination.toString());
				afSubIns.setSubNextState(ESubState.E01_Destination.toString());
				
			} else if (Cmd.equals("modiPPSMultiAttr")) {

				Param_Idle_Request inqIdleParameter = (Param_Idle_Request) afSubIns.getClientData();

				boolean balanceChk = StringUtils.isBlank(afSubIns.getClientData().getBalance()); // Balance is null or empty.
				boolean validityChk = StringUtils.isBlank(afSubIns.getClientData().getValidity()); // Validity is null or empty.
				boolean paramChk =  StringUtils.isBlank(inqIdleParameter.getPrmmoney()) // All param is null or empty.
						&& StringUtils.isBlank(inqIdleParameter.getPrmminute())
						&& StringUtils.isBlank(inqIdleParameter.getPoint())
						&& StringUtils.isBlank(inqIdleParameter.getFreecalltimes())
						&& StringUtils.isBlank(inqIdleParameter.getFreerbtsong())
						&& StringUtils.isBlank(inqIdleParameter.getFreerbtmf())
						&& StringUtils.isBlank(inqIdleParameter.getScore())
						&& StringUtils.isBlank(inqIdleParameter.getPrmscore())
						&& StringUtils.isBlank(inqIdleParameter.getSmusage());

				if ( (!balanceChk && (!validityChk || !paramChk))
						|| (balanceChk && validityChk && paramChk) ) {
						
						AppLog.e("[Invalid] [balance,validity is comming] parameter one of the except list is not null");
						afSubIns.setSubInternalCode(EResultCode.REClient_send_Bad_request);
						afSubIns.setSubControlState(ESubState.End_ModiPPSMultiAttr.toString());
						afSubIns.setSubNextState(ESubState.End_ModiPPSMultiAttr.toString());
					
					} else {
						
						afSubIns.setSubControlState(ESubState.E01_Destination.toString());
						afSubIns.setSubNextState(ESubState.E01_Destination.toString());
						
					}
					
				
			} else if (Cmd.equals("addScrScreenNo")||Cmd.equals("deleScrScreenNo")||Cmd.equals("modiScrWhiteList")) {
				
				afSubIns.setSubControlState(ESubState.BSS_DoCallScreenNo.toString());
				afSubIns.setSubNextState(ESubState.BSS_DoCallScreenNo.toString());
				
			} else if (Cmd.equals("dispScrScreenNo")) {
				
				afSubIns.setSubControlState(ESubState.BSS_DoQueryCallScreen.toString());
				afSubIns.setSubNextState(ESubState.BSS_DoQueryCallScreen.toString());
			
			} else if (Cmd.equals("purchasePPSPackage")) {
				
				afSubIns.setSubControlState(ESubState.BSS_DoChangeService.toString());
				afSubIns.setSubNextState(ESubState.BSS_DoChangeService.toString());
			
			} else if (Cmd.equals("setPPSReward")) {

				Param_Idle_Request inqIdleParameter = (Param_Idle_Request) afSubIns.getClientData();

				boolean balanceChk = StringUtils.isBlank(afSubIns.getClientData().getBalance()); // Balance is null or empty.
				boolean validityChk = StringUtils.isBlank(afSubIns.getClientData().getValidity()); // Validity is null or empty.
				boolean paramChk =  StringUtils.isBlank(inqIdleParameter.getPrmmoney()) // All param is null or empty.
						&& StringUtils.isBlank(inqIdleParameter.getPrmminute())
						&& StringUtils.isBlank(inqIdleParameter.getPoint())
						&& StringUtils.isBlank(inqIdleParameter.getFreecalltimes())
						&& StringUtils.isBlank(inqIdleParameter.getFreerbtsong())
						&& StringUtils.isBlank(inqIdleParameter.getFreerbtmf())
						&& StringUtils.isBlank(inqIdleParameter.getScore())
						&& StringUtils.isBlank(inqIdleParameter.getPrmscore())
						&& StringUtils.isBlank(inqIdleParameter.getSmusage());

					if ( (!balanceChk && (!validityChk || !paramChk))
						|| (balanceChk && validityChk && paramChk) ) {
						
						AppLog.e("[Invalid] [balance,validity is comming] parameter one of the except list is not null");
						afSubIns.setSubInternalCode(EResultCode.REClient_send_Bad_request);
						afSubIns.setSubControlState(ESubState.End_SetPPSReward.toString());
						afSubIns.setSubNextState(ESubState.End_SetPPSReward.toString());
					
					} else {
						
						if (Config.SETPPSREWARD_BOS_DCC.equalsIgnoreCase("true")) {
							
							afSubIns.setSubControlState(ESubState.E01_Destination.toString());
							afSubIns.setSubNextState(ESubState.E01_Destination.toString());
							
						} else {
							
							afSubIns.setSubControlState(ESubState.BSS_DoAdjustBalance.toString());
							afSubIns.setSubNextState(ESubState.BSS_DoAdjustBalance.toString());
							
						}
						
					}
				
			} else if (Cmd.equals("modiPPSCreditLimit")) {
				
				afSubIns.setSubControlState(ESubState.BSS_DoSetNegativeBalance.toString());
				afSubIns.setSubNextState(ESubState.BSS_DoSetNegativeBalance.toString());
			
			} else if (Cmd.equals("activatePPSSingSub")) {
				
				afSubIns.setSubControlState(ESubState.BSS_DoFirstActivationCRM.toString());
				afSubIns.setSubNextState(ESubState.BSS_DoFirstActivationCRM.toString());
			
			} else {
				
				afInstance.setMainProfile(EFunctionGroup.RTBS.toString());
				afSubIns.setSubFunctionGroup(EFunctionGroup.RTBS.toString());
				afSubIns.setSubControlState(ESubState.SGSCPSMOI_ForwardCommand.toString());
				afSubIns.setSubNextState(ESubState.SGSCPSMOI_ForwardCommand.toString());
				afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
				
			}
		
		} else if(!StringUtils.isBlank(location)){
			
			afInstance.setMainProfile(EFunctionGroup.RTBS.toString());
			afSubIns.setSubFunctionGroup(EFunctionGroup.RTBS.toString());
			afSubIns.setSubControlState(ESubState.SGSCPSMOI_ForwardCommand.toString());
			afSubIns.setSubNextState(ESubState.SGSCPSMOI_ForwardCommand.toString());
			afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
			
		} else {
			
			AppLog.e("No Location");
			
			afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
			afSubIns.setSubInternalCode(EResultCode.RE40401);
			afSubIns.setSubHasErrorForWriteDetailLog(true);
			ec02Instance.getAFInstance().decrementMainCountProcess();
			
		}
		
	}

}
