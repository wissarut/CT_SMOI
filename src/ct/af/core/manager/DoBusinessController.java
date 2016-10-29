package ct.af.core.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ct.af.enums.ECommand;
import ct.af.enums.EFunctionGroup;
import ct.af.enums.EResultCode;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Counters_AMF_CheckBalance;
import ct.af.message.incoming.parameter.Param_USMP_FirstActivate;
import ct.af.message.incoming.parameter.Param_USMP_InquiryCounter;
import ct.af.message.incoming.parameter.Param_USMP_ModifySubscriber;
import ct.af.report.ReportData;
import ct.af.substate.Do_AMF_ManageCounters;
import ct.af.substate.Do_DS2A_InquirySub;
import ct.af.substate.Do_E01_destination;
import ct.af.substate.Do_E01_newClassOfService;
import ct.af.utils.Config;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.utils.AppLog;

public class DoBusinessController {

	public void doBusinessLogic(AbstractAF abstractAF, EC02Instance ec02Instance, AFSubInstance afSubIns) {

		if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
			return;
		}
		
		if (afSubIns.getSubFunctionGroup().equals(EFunctionGroup.IncomingFromClient.toString())) {

			if (afSubIns.getSubControlState().equals(ESubState.Idle_Request.toString())) {

				if (afSubIns.getSubIsValid() && ec02Instance.getAFInstance().getMainCountProcess() == 0) {
					
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					ec02Instance.getAFInstance().incrementMainCountProcess();
					ec02Instance.getAFInstance().decrementMainCountWait();	
					
					if(afSubIns.getSubInitCmd().equals(ECommand.FEEDFORWARD.getCommand())) {
						afSubIns.setSubNextState(ESubState.SGSCPSMOI_ForwardCommand.toString());
					} else {
						afSubIns.setSubNextState(ESubState.DS2A_InquirySubscriber.toString());
					}
					
					if(afSubIns.getSubInitCmd().equals(ECommand.ACTIVATEPPSSINGSUB.getCommand())) {
						
						afSubIns.setReportData(new ReportData());
						afSubIns.getReportData().setTransactionID(afSubIns.getClientData().getSsid());
						afSubIns.getReportData().setChannel(afSubIns.getClientData().getChannel());
						afSubIns.getReportData().setMobileNumber("0"+afSubIns.getClientData().getMs().substring(2));
						afSubIns.getReportData().setActiveCAC(afSubIns.getClientData().getLocation());
						
					}
					
				} else {
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					ec02Instance.getAFInstance().decrementMainCountWait();
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if(afSubIns.getSubControlState().equals(ESubState.Idle_RMC.toString())) {
				
				afSubIns.setSubNextState(ESubState.WAIT.toString());
				afSubIns.setSubInternalCode(afSubIns.getSubResultCode());

				//ec02Instance.getAFInstance().decrementMainCountWait();
				
				for(String subInsNo:ec02Instance.getAFInstance().getMainSubInstance().keySet()) {
			
					AFSubInstance afsub = ec02Instance.getAFInstance().getMainSubInstance(subInsNo);
					
					if(afsub.getSubInitCmd().equals(ECommand.ACTIVATEPPSSINGSUB.getCommand())
							&& afsub.getSubHostInsNo() == null) {
			
//						afsub.decrementSubRequestReward();
	
						DateTimeFormatter formatDate = Config.formatDateWithMiTz;
				
						Seconds diffTimeout = Seconds.secondsBetween(formatDate.parseDateTime(ec02Instance.getAFInstance().getRmfTimeStamp()), formatDate.parseDateTime(formatDate.print(new DateTime())));
						int diff = diffTimeout.getSeconds();
						
						if(diff <= Config.RMF_CONFIRM_TIMEOUT && afsub.getSubCurrentState().equals(ESubState.SSB_WorkOrderFirstAct)) {
							afsub.setDiffTimeout(Integer.toString(Config.RMF_CONFIRM_TIMEOUT - diff));
							afsub.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Integer.parseInt(afsub.getDiffTimeout()))));
							afsub.setSubNextState(ESubState.RMF_RMCR.toString());
						}
						
//						if(afsub.getRequestReward() == 0) {
						
							ec02Instance.getAFInstance().setRMCR(true);
							
							String currentNextState = afsub.getSubNextState();
							ArrayList<String> currentInvokeList = new ArrayList<String>();
							
							for(String invoke:afsub.getSubInvoke()) {
								currentInvokeList.add(invoke);
							}
							
							afsub.getSubInvoke().clear();
							
							afsub.setSubNextState(ESubState.End_ActivatePPSSingSub.toString());
							afsub.setSubCurrentState(afsub.getSubNextState());
							
							new IncomingManager().doActionSubIns(abstractAF, ec02Instance, afsub);
							
							afsub.getSubInvoke().addAll(currentInvokeList);
							afsub.setSubNextState(currentNextState);
							
							afSubIns.setSubInitEvent(ECommand.ACTIVATEPPSSINGSUB.getCommand());
							if(currentNextState.equals(ESubState.RMF_RMCR.toString())) {
								
								afSubIns.setSubNextState("End_RMC");
								
								ec02Instance.getAFInstance().decrementMainCountWait();
								ec02Instance.getAFInstance().decrementMainCountProcess();
							}
//							if(afsub.getRequestReward() != 0) {
//								afsub.setSubNextState(currentNextState);
//							} 
							
//						}
					}
					
				}
				
				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}
				
			} else if (afSubIns.getSubControlState().equals(ESubState.DS2A_InquirySubscriber.toString())) {

				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
					
					new Do_DS2A_InquirySub().doBusinessLogic(abstractAF, ec02Instance, afSubIns);
					
				} else {
					
					if(afSubIns.getSubRecieveRet().equals("4") && afSubIns.getSubCountServerRequest() <= Config.DS2A_MAXRETRY ) {
						
						afSubIns.setSubNextState(afSubIns.getSubCurrentState());
						
					} else {
						afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						afSubIns.setSubHasErrorForWriteDetailLog(true);
						ec02Instance.getAFInstance().decrementMainCountProcess();
					}
				}	

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.SGSCPSMOI_ForwardCommand.toString())) {

				afSubIns.setSubNextState("End_FeedForward");
				afSubIns.setSubInternalCode(EResultCode.RE000);
				ec02Instance.getAFInstance().decrementMainCountProcess();

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else {

				afSubIns.setSubNextState(ESubState.End_Unknow.toString());
				afSubIns.setSubInternalCode(EResultCode.RE50000);
				afSubIns.setSubHasErrorForWriteDetailLog(true);

			}

		}

		if (afSubIns.getSubFunctionGroup().equals(EFunctionGroup.BOS.toString())) {

			if (afSubIns.getSubControlState().equals(ESubState.E01_Destination.toString())) {

				if (afSubIns.getSubIsValid()) {
					
					new Do_E01_destination().doBusinessLogic(abstractAF, ec02Instance, afSubIns);
					
				} else {
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					ec02Instance.getAFInstance().decrementMainCountProcess();
					
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else {

				afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
				afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
				ec02Instance.getAFInstance().decrementMainCountProcess();

				if(!afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE2001.getResultCode())) {
					afSubIns.setSubHasErrorForWriteDetailLog(true);
				}
				
				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			}

		}

		if (afSubIns.getSubFunctionGroup().equals(EFunctionGroup.INS.toString())) {

			if (afSubIns.getSubControlState().equals(ESubState.AMF_CheckBalance.toString())) {
			
				afSubIns.setSubNextState(ESubState.END.toString());
				afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
				
				if(!afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
					afSubIns.setSubHasErrorForWriteDetailLog(true);
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.AMF_ObtainCounter.toString())) {

				afSubIns.setSubNextState(ESubState.END.toString());
				afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
	
				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if(afSubIns.getSubControlState().equals(ESubState.AMF_ModifyCounters_AND_AMF_ObtainNoneBalanceCounter.toString())) { 
				
				if (afSubIns.getSubIsValid()) {
					
					afSubIns.setSubNextState(ESubState.SDF_GetAmfSubProfile.toString());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					
				} else {
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubControlState(afSubIns.getSubChildErrorState());
					afSubIns.setSubInternalCode(EResultCode.RE50000);
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}
				
			} else if (afSubIns.getSubControlState().equals(ESubState.SDF_GetAmfSubProfile.toString())) {

				if(afSubIns.getClientData().getPrmmoneyusageinfo().equals("1")) {
					
					if (afSubIns.getSubIsValid()
							&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
				
						List<Counters_AMF_CheckBalance> countersChkBalance = afSubIns.getSubResponseParamCheckBalance().getCountersAMFCheckBalance();

						StringBuilder nextState = new StringBuilder();
						
						for (int i = 0; i < countersChkBalance.size(); i++) {
							if(Integer.parseInt(Config.COUNTID_MONETARYUSAGEREWARD.split(",", -1)[0]) <= Integer.parseInt(countersChkBalance.get(i).getCounterID())
									&& Integer.parseInt(countersChkBalance.get(i).getCounterID()) <= Integer.parseInt(Config.COUNTID_MONETARYUSAGEREWARD.split(",", -1)[1])) {
			
								String promoNameNoti = countersChkBalance.get(i).getPromoNameNotifElem();
								if(StringUtils.isNotBlank(promoNameNoti)) {
									
									if (!afSubIns.getPromoNameNoti().contains(countersChkBalance.get(i).getPromoNameNotifElem())) {
										
										afSubIns.addPromoNameNoti(countersChkBalance.get(i).getPromoNameNotifElem());
										nextState.append(ESubState.SDF_GetSidNotificationElement.toString()+",");
										
									}
								
								}
								
							}
						}
				
						if(afSubIns.getPromoNameNoti().size() > 0) {
						
							nextState.delete(nextState.length() - 1, nextState.length());
							
							if(nextState.toString().contains(",")) {
								
								afSubIns.setSubNextOfNextState(ESubState.End_DispPPSInfo.toString());
								afSubIns.setSubStateHostWait(ESubState.SDF_GetSidNotificationElement.toString());
							}
							
							afSubIns.setSubNextState(nextState.toString());
							
						} else {
							
							afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
							afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
							ec02Instance.getAFInstance().decrementMainCountProcess();
							
							
						}
						
					} else {
						
						afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						afSubIns.setSubHasErrorForWriteDetailLog(true);
						ec02Instance.getAFInstance().decrementMainCountProcess();
						
					}
					
				} else {
					
					if (afSubIns.getSubIsValid()
							&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
						afSubIns.setSubHasErrorForWriteDetailLog(true);
					}
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					ec02Instance.getAFInstance().decrementMainCountProcess();
					
				}
				
				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.SDF_GetSidNotificationElement.toString())) {

				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
				
					if(StringUtils.isNotBlank(afSubIns.getSubHostInsNo())) {
						
						afSubIns.setSubNextState(ESubState.END.toString());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						
					} else {
						
						afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						ec02Instance.getAFInstance().decrementMainCountProcess();
						
					}
				
				} else {
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					ec02Instance.getAFInstance().decrementMainCountProcess();
					
				}
				
				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.SDF_GetAmfCounter.toString())) {

				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
					
					afSubIns.setSubNextState(ESubState.SDF_PutAmfCounter.toString());
					
				} else {
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					ec02Instance.getAFInstance().decrementMainCountProcess();
					
				}
					
				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if(afSubIns.getSubControlState().equals(ESubState.BMP_CallScreening.toString())
					|| afSubIns.getSubControlState().equals(ESubState.BMP_DispScrScreenNo.toString())) { 
				
				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE405000000.getResultCode())) {
			
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					ec02Instance.getAFInstance().decrementMainCountProcess();
			
				} else {
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					ec02Instance.getAFInstance().decrementMainCountProcess();
					
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}
				
			} else if(afSubIns.getSubControlState().equals(ESubState.TMF_AccountAdjustment.toString())) {
				
				if (afSubIns.getSubInitCmd().equals(ECommand.MODIPPSMULTIWALLET.getCommand())) {
					
					if (afSubIns.getSubIsValid()
							&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE2001.getResultCode())) {
						
						if(afSubIns.getClientData().getResetSecondpocket().equals("1")) {
							
							afSubIns.setSubNextState(ESubState.RMF_RewardAdjustment.toString());
							afSubIns.setSubInternalCode(afSubIns.getSubResultCode());	
							
						} else if(afSubIns.getClientData().getResetPrmmoney().equals("1")) {

							afSubIns.setSubNextState(ESubState.RMF_RewardAdjustment.toString());
							afSubIns.setSubInternalCode(afSubIns.getSubResultCode());	
							
						} else {
							
							afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
							afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
							afSubIns.setSubHasErrorForWriteDetailLog(true);
							ec02Instance.getAFInstance().decrementMainCountProcess();
						}
						
					} else {
						
						afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						afSubIns.setSubHasErrorForWriteDetailLog(true);
						ec02Instance.getAFInstance().decrementMainCountProcess();

					}	

					if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
						return;
					}

				}
				
			} else if(afSubIns.getSubControlState().equals(ESubState.RMF_RewardAdjustment.toString())) {
				
				if (afSubIns.getSubInitCmd().equals(ECommand.MODIPPSMULTIWALLET.getCommand())) {
					
					if (afSubIns.getSubIsValid()
							&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
						
						if(afSubIns.getClientData().getResetPrmmoney().equals("1")) {
							
							afSubIns.setSubNextState(ESubState.RMF_RewardAdjustment.toString());
							afSubIns.setSubInternalCode(afSubIns.getSubResultCode());	
							
						} else {
							
							afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
							afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
							afSubIns.setSubHasErrorForWriteDetailLog(true);
							ec02Instance.getAFInstance().decrementMainCountProcess();
						}
						
					} else {
						
						afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						afSubIns.setSubHasErrorForWriteDetailLog(true);
						ec02Instance.getAFInstance().decrementMainCountProcess();

					}	

					if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
						return;
					}

				}
				
			} else {
				
				afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
				afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
				ec02Instance.getAFInstance().decrementMainCountProcess();
				
				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}
				
			}
			
		}

		if (afSubIns.getSubFunctionGroup().equals(EFunctionGroup.FirstActivate.toString())) {

			if (afSubIns.getSubControlState().equals(ESubState.E01_NewClassOfService.toString())) {

				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
					
					new Do_E01_newClassOfService().doBusinessLogic(abstractAF, ec02Instance, afSubIns);
					
				} else {
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					ec02Instance.getAFInstance().decrementMainCountProcess();
					
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.E01_CosToPromotionName.toString())) {

				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
					
					afSubIns.setAmfManageCounter("1");
					afSubIns.setSubNextState(ESubState.AMF_ManageCounters.toString());
					
				} else {
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					ec02Instance.getAFInstance().decrementMainCountProcess();
					
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.USMP_ModifySubscriber.toString())) {

					Param_USMP_ModifySubscriber respParam = (Param_USMP_ModifySubscriber) afSubIns.getSubResponseParam();
					
					if(respParam != null) {
						if(!StringUtils.isBlank(respParam.getFirstActDate())) {
							
							DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZ");
							DateTimeFormatter formatDateForReport = Config.formatDate;
							DateTime MainProductValidDt = formatDate.parseDateTime(respParam.getFirstActDate());
							String MainProductValid = formatDate.print(MainProductValidDt);
							DateTime MainProduct = formatDate.parseDateTime(MainProductValid);
							
							afSubIns.getReportData().setNewActiveBegin(formatDateForReport.print(MainProduct));
							afSubIns.getReportData().setMainProductValidDt(formatDateForReport.print(MainProduct));
						}
					}
					afSubIns.setSubNextState(ESubState.DS2A_InquirySubscriber.toString());			

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.AMF_ManageCounters.toString())) {
				
				if (StringUtils.isNotBlank(afSubIns.getSubHostInsNo())) {
					
					afSubIns.setSubNextState(ESubState.END.toString());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
		
					if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
						return;
					}
					
				} else {

					if (afSubIns.getSubIsValid()
							
							&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
						
						new Do_AMF_ManageCounters().doBusinessLogic(abstractAF, ec02Instance, afSubIns);
						
					} else {
						
						if(afSubIns.getSubRecieveRet().equals("4") && afSubIns.getSubCountServerRequest() <= Config.AMF_MAXRETRY ) {
							
							afSubIns.setSubNextState(afSubIns.getSubCurrentState());
							
						} else {
						
							afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
							afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
							afSubIns.setSubHasErrorForWriteDetailLog(true);
							ec02Instance.getAFInstance().decrementMainCountProcess();
						}
					}
				}
				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.USMP_InquiryCounter.toString())) {

				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.REVSMP00000000.getResultCode())) {
					
					afSubIns.setSubNextState(ESubState.USMP_ModifyCounter.toString());
					
					Param_USMP_InquiryCounter inquiryCounterParam = (Param_USMP_InquiryCounter) afSubIns.getSubResponseParam();

					for(int i=0;i<inquiryCounterParam.getCounterInfo().size();i++) {
						
						if(!StringUtils.isBlank(inquiryCounterParam.getCounterInfo().get(i).getMainPromotionF())) {
							if(inquiryCounterParam.getCounterInfo().get(i).getMainPromotionF().equals("1")) {
								String mainProductSeqID = inquiryCounterParam.getCounterInfo().get(i).getProductNumber();
								afSubIns.getReportData().setMainProductSeqID(mainProductSeqID);
							}
						}
					}
					
				} else {
					
					if(afSubIns.getSubRecieveRet().equals("4") && afSubIns.getSubCountServerRequest() <= Config.USMP_MAXRETRY ) {
						
						afSubIns.setSubNextState(afSubIns.getSubCurrentState());
						
					} else {
						afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						afSubIns.setSubHasErrorForWriteDetailLog(true);
						ec02Instance.getAFInstance().decrementMainCountProcess();
					}
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.USMP_ModifyCounter.toString())) {

				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.REVSMP00000000.getResultCode())) {
					
					StringBuilder nextState = new StringBuilder();
					
//					afSubIns.setRequestReward(afSubIns.getRewardId().size());
					if(afSubIns.getRewardId().size() > 0) {
						
						nextState.append(ESubState.AMF_ManageCounters.toString()+",");
						
						for(String rewardIdData:afSubIns.getRewardId()) {
							nextState.append(ESubState.RMF_RewardMonitoring.toString()+",");
						}
						
						nextState.delete(nextState.length() - 1, nextState.length());
						
						afSubIns.setSubNextState(nextState.toString());
						
						if(nextState.toString().contains(",")) {
							afSubIns.setSubNextOfNextState(ESubState.AMF_ManageCounters_AND_RMF_RewardMonitoring.toString());
							afSubIns.setSubStateHostWait(ESubState.AMF_ManageCounters_AND_RMF_RewardMonitoring.toString());
						}
						
					} else {
						ec02Instance.getAFInstance().setRMCR(true);
	
						afSubIns.setAmfManageCounter("2");
						afSubIns.setSubNextState(ESubState.AMF_ManageCounters.toString());
					}
					
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					
				} else {
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					ec02Instance.getAFInstance().decrementMainCountProcess();
					
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.AMF_ManageCounters_AND_RMF_RewardMonitoring.toString())) {
				
				afSubIns.setSubNextState(ESubState.E01_Notification.toString());
				afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
				

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.USMP_FirstActivate.toString())) {

				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.REVSMP00000000.getResultCode())) {
					
					afSubIns.setSubNextState(ESubState.USMP_ModifyCounter.toString());
					
					Param_USMP_FirstActivate firstActParam = (Param_USMP_FirstActivate) afSubIns.getSubResponseParam();
					
					for(int i=0;i<firstActParam.getCounterInfo().size();i++) {
						
						if(!StringUtils.isBlank(firstActParam.getCounterInfo().get(i).getMainPromotionF())) {
							if(firstActParam.getCounterInfo().get(i).getMainPromotionF().equals("1")) {
								String mainProductSeqID = firstActParam.getCounterInfo().get(i).getProductNumber();
								afSubIns.getReportData().setMainProductSeqID(mainProductSeqID);
							}
						}
					}
					
				
				} else {
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					ec02Instance.getAFInstance().decrementMainCountProcess();
					
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.DS2A_InquirySubscriber.toString())) {

				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {

					afSubIns.getClientData().setSmsLang(afSubIns.getInquirySubParam().getResultInfo().get(0).getSmsLanguage());
					afSubIns.getClientData().setEmailLang(afSubIns.getInquirySubParam().getResultInfo().get(0).getEmailLanguage());
					afSubIns.getClientData().setUssdLang(afSubIns.getInquirySubParam().getResultInfo().get(0).getUssdLanguage());
					afSubIns.getClientData().setIvrLang(afSubIns.getInquirySubParam().getResultInfo().get(0).getIvrLanguage());
					
					DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyyMMddHHmmssZ");
					DateTimeFormatter formatForReport = Config.formatDate;
					DateTime newActiveStop = null;
					DateTime newSuspendStop = null;
					DateTime newDisableStop = null;
					
					try {
						
						newActiveStop = formatDate.parseDateTime(afSubIns.getInquirySubParam().getResultInfo().get(0).getActiveStopTime());
						newSuspendStop = formatDate.parseDateTime(afSubIns.getInquirySubParam().getResultInfo().get(0).getSuspendStopTime());
						newDisableStop = formatDate.parseDateTime(afSubIns.getInquirySubParam().getResultInfo().get(0).getDisableStopTime());
					
					} catch (Exception e) {
						
						AppLog.e("Datetime is wrong Format!!");
					}
					afSubIns.getReportData().setNewActiveStop(formatForReport.print(newActiveStop));
					afSubIns.getReportData().setNewSuspendStop(formatForReport.print(newSuspendStop));
					afSubIns.getReportData().setNewDisableStop(formatForReport.print(newDisableStop));
					
					String newFraudFlag = "Fraud";
					
					if(afSubIns.getInquirySubParam().getResultInfo().get(0).getPinFraudFlag().equalsIgnoreCase("false")) {
						newFraudFlag = "No Fraud";
					}
					afSubIns.getReportData().setNewFraudFlag(newFraudFlag);
					
					String newSuspendFlag = "Suspend Request";
					
					if(afSubIns.getInquirySubParam().getResultInfo().get(0).getFraudFlag().equalsIgnoreCase("false")) {
						newSuspendFlag = "Normal";
					}
					
					HashMap<String, String> langMap = new HashMap<String, String>();
					
					String[] strLang = Config.WORKORDER_LANG_MAPPING.split(",");
					
					for(String langData:strLang) {
						AppLog.d("langData : "+langData);
						langMap.put(langData.split("\\|")[0], langData.split("\\|")[1].toUpperCase());
						AppLog.d("key :"+langData.split("\\|")[0]);
					}
					
					String subscriberSegment = "";
					if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2customerSegment().equals("0")) {
						subscriberSegment = "standard";
					} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2customerSegment().equals("1")) {
						subscriberSegment = "classic";
					} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2customerSegment().equals("2")) {
						subscriberSegment = "gold";
					} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2customerSegment().equals("3")) {
						subscriberSegment = "platinum";
					} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2customerSegment().equals("4")) {
						subscriberSegment = "platinumPlus";
					} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2customerSegment().equals("5")) {
						subscriberSegment = "serenadeCeo";
					} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2customerSegment().equals("6")) {
						subscriberSegment = "serenadePrestige";
					} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2customerSegment().equals("7")) {
						subscriberSegment = "emerald";
					}
					
					String paymentType = "";
					if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2brandId().equals("4")) {
						paymentType = "Post-paid";
					} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2brandId().equals("5")) {
						paymentType = "Pre-paid";
					}
					
					afSubIns.getReportData().setPaymentType(paymentType);
					afSubIns.getReportData().setUserType(paymentType);
					afSubIns.getReportData().setSubscriberSegment(subscriberSegment);
					afSubIns.getReportData().setNewSuspendFlag(newSuspendFlag);
					afSubIns.getReportData().setsMSLanguage(langMap.get(afSubIns.getInquirySubParam().getResultInfo().get(0).getSmsLanguage()));
					afSubIns.getReportData().setuSSDLanguage(langMap.get(afSubIns.getInquirySubParam().getResultInfo().get(0).getUssdLanguage()));
					afSubIns.getReportData().setiVRLanguage(langMap.get(afSubIns.getInquirySubParam().getResultInfo().get(0).getIvrLanguage()));
					afSubIns.getReportData().setEmailLanguage(langMap.get(afSubIns.getInquirySubParam().getResultInfo().get(0).getEmailLanguage()));

					if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2brandId().equals("5")) {
						afSubIns.getReportData().setCurrentUserState(afSubIns.getInquirySubParam().getResultInfo().get(0).getTimeDrivenState());
					} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2brandId().equals("4")) {
						afSubIns.getReportData().setCurrentUserState(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2state());
					}
					afSubIns.setSubNextState(ESubState.SSB_WorkOrderFirstAct.toString());
					
				} else {
					
					if(afSubIns.getSubRecieveRet().equals("4") && afSubIns.getSubCountServerRequest() <= Config.DS2A_MAXRETRY ) {
						
						afSubIns.setSubNextState(afSubIns.getSubCurrentState());
						
					} else {
						
						afSubIns.setSubNextState(ESubState.SSB_WorkOrderFirstAct.toString());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						afSubIns.setSubHasErrorForWriteDetailLog(true);
					}
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.AMF_ManageCounters_AND_DS2A_InquirySubscriber.toString())) {
					
					afSubIns.setSubNextState(ESubState.SSB_WorkOrderFirstAct.toString());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());					

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.SSB_WorkOrderFirstAct.toString())) {

				if(ec02Instance.getAFInstance().getRmfTimeStamp() != null) {
					endOfFirstAct(abstractAF,ec02Instance, afSubIns, EResultCode.RE2001);
					
					//Report 80 -----------------------------------------------------------
					
					ReportData reportData = afSubIns.getReportData();
					
					String logName = "";
							
					if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2brandId().equals("4")) {
						logName = Config.REPORT80_POS_NAME;
					} else if(afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2brandId().equals("5")) {
						logName = Config.REPORT80_PPS_NAME;
					}
					
					//AppLog.d("Report 80 @"+logName+" : "+reportData.toString());
					
					abstractAF.getUtils().writeLog(logName, reportData.toReport().replaceAll("null", ""));
					
//					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
//					ec02Instance.getAFInstance().decrementMainCountProcess();
					//Report 80 -----------------------------------------------------------
					
//					afSubIns.setRequestReward(afSubIns.getRewardId().size());
//					if(afSubIns.getRewardId().size() > 0) {
//						
//						StringBuilder nextState = new StringBuilder();
//						
//						for(String rewardIdData:afSubIns.getRewardId()) {
//							nextState.append(ESubState.RMF_RewardMonitoring.toString()+",");
//						}
//						
//						nextState.delete(nextState.length() - 1, nextState.length());
//						
//						afSubIns.setSubNextState(nextState.toString());
//						
//						if(nextState.toString().contains(",")) {
//							afSubIns.setSubNextOfNextState(ESubState.RMF_RewardMonitoring.toString());
//							afSubIns.setSubStateHostWait(ESubState.RMF_RewardMonitoring.toString());
//						}
//						
//					} else {
//						ec02Instance.getAFInstance().setRMCR(true);
//						afSubIns.setSubNextState(ESubState.E01_Notification.toString());
//					}
//					
//					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						
				} else {
					
					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					ec02Instance.getAFInstance().decrementMainCountProcess();
					
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.RMF_RewardMonitoring.toString())) {
					
				DateTime rmfTimeStamp = new DateTime();
				DateTimeFormatter formatDate = Config.formatDateWithMiTz;
				ec02Instance.getAFInstance().setRmfTimeStamp(formatDate.print(rmfTimeStamp));

				if (StringUtils.isNotBlank(afSubIns.getSubHostInsNo())) {
				
					afSubIns.setSubNextState(ESubState.END.toString());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					
					if(!(afSubIns.getSubIsValid()
							&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode()))) {
				
						afSubIns.setSubHasErrorForWriteDetailLog(true);
					}
					
					if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
						return;
					}
					
				} else {
					
					
					afSubIns.setSubNextState(ESubState.E01_Notification.toString());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						
					if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
						return;
					}
					
				}
				
			} else if (afSubIns.getSubControlState().equals(ESubState.E01_Notification.toString())) {

				if (afSubIns.getSubIsValid()) {
					
					afSubIns.setSubNextState(ESubState.AMF_CheckBalance.toString());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
				
				} else {
				
					afSubIns.setSubNextState(ESubState.USMP_ModifySubscriber.toString());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.AMF_CheckBalance.toString())) {

				if (afSubIns.getSubIsValid()
						&& afSubIns.getSubResultCode().getResultCode().equals(EResultCode.RE20000.getResultCode())) {
					
					afSubIns.setSubNextState(ESubState.SRFC_SpecializedResource.toString());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
				
				} else {
					
					if(afSubIns.getSubRecieveRet().equals("4") && afSubIns.getSubCountServerRequest() <= Config.AMF_MAXRETRY ) {
						
						afSubIns.setSubNextState(afSubIns.getSubCurrentState());
						
					} else {
					
						afSubIns.setSubNextState(ESubState.USMP_ModifySubscriber.toString());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						afSubIns.setSubHasErrorForWriteDetailLog(true);
					}
				}

				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.SRFC_SpecializedResource.toString())) {


				if (afSubIns.getSubIsValid()) {
					
					afSubIns.setSubNextState(ESubState.USMP_ModifySubscriber.toString());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
				
				} else {
				
					afSubIns.setSubNextState(ESubState.USMP_ModifySubscriber.toString());
					afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
					afSubIns.setSubHasErrorForWriteDetailLog(true);
					
				}
				
				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else if (afSubIns.getSubControlState().equals(ESubState.RMF_RMCR.toString())) {

				afSubIns.setSubInternalCode(EResultCode.RE2001);
				
				afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());

				ec02Instance.getAFInstance().decrementMainCountProcess();
					
				if (!afSubIns.getSubNextState().equals(ESubState.Unknown.toString())) {
					return;
				}

			} else {
				
				AppLog.e("App Error FirstActivate");
				afSubIns.setSubInternalCode(EResultCode.RE50000);
				afSubIns.setSubHasErrorForWriteDetailLog(true);
				ec02Instance.getAFInstance().decrementMainCountProcess();
				
			}

		} else if(afSubIns.getSubFunctionGroup().equals(EFunctionGroup.RTBS.toString())) {
			
			afSubIns.setSubControlState(ESubState.SGSCPSMOI_ForwardCommand.toString());
			afSubIns.setSubNextState(ESubState.SGSCPSMOI_ForwardCommand.toString());
			afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
			
		} else {
			
			AppLog.e("App Error No FunctionGroup");
			afSubIns.setSubInitCmd(ECommand.UNKNOWN.getCommand());
			afSubIns.setSubControlState(ESubState.Unknown.toString());
			afSubIns.setSubInternalCode(EResultCode.RE50000);
			afSubIns.setSubHasErrorForWriteDetailLog(true);
			ec02Instance.getAFInstance().decrementMainCountProcess();
			
		}

	}
	
	public void endOfFirstAct(AbstractAF abstractAF,EC02Instance ec02Instance, AFSubInstance afSubIns ,EResultCode resultCode) {
		
		if(ec02Instance.getAFInstance().isRMCR()) {

			for(String subInsNo:ec02Instance.getAFInstance().getMainSubInstance().keySet()) {
				
				AFSubInstance afsub = ec02Instance.getAFInstance().getMainSubInstance(subInsNo);
				
				if(afsub.getSubInitCmd().equals(ECommand.RMC.getCommand())) {
					
					afsub.setSubNextState("End_RMC");

					ec02Instance.getAFInstance().decrementMainCountWait();
//					new IncomingManager().doActionSubIns(abstractAF, ec02Instance, afsub);
					
				}
				
			}
			
			afSubIns.setSubInternalCode(resultCode);
			
			afSubIns.setSubNextState(ESubState.END.toString());
			
			ec02Instance.getAFInstance().decrementMainCountProcess();
			
		} else {
			
			DateTime srfcTimeStamp = new DateTime();
			DateTimeFormatter formatDate = Config.formatDateWithMiTz;
			
			if(!StringUtils.isBlank(ec02Instance.getAFInstance().getRmfTimeStamp())) {
				
				Seconds diffTimeout = Seconds.secondsBetween(formatDate.parseDateTime(ec02Instance.getAFInstance().getRmfTimeStamp()), formatDate.parseDateTime(formatDate.print(srfcTimeStamp)));
				int diff = diffTimeout.getSeconds();
				
				if(diff < Config.RMF_CONFIRM_TIMEOUT) {
					afSubIns.setDiffTimeout(Integer.toString(Config.RMF_CONFIRM_TIMEOUT - diff));
					afSubIns.setSubNextState(ESubState.RMF_RMCR.toString());
				} else {
					afSubIns.setSubInternalCode(resultCode);

					afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());
					
					ec02Instance.getAFInstance().decrementMainCountProcess();
				}
				
			} else {
				afSubIns.setSubInternalCode(resultCode);
				
				afSubIns.setSubNextState("End_" + afSubIns.getSubInitCmd());

				ec02Instance.getAFInstance().decrementMainCountProcess();
			}
			
		}
		
	}

}
