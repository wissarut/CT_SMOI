package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EFinalCode;
import ct.af.enums.EFunctionGroup;
import ct.af.enums.EMethod;
import ct.af.enums.EProtocal;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.AccountInfo_AMF_CheckBalance;
import ct.af.message.incoming.parameter.AccountingInfo;
import ct.af.message.incoming.parameter.Counters_AMF_CheckBalance;
import ct.af.message.incoming.parameter.Counters_AMF_ObtainNoneBalance;
import ct.af.message.incoming.parameter.Param_BOS_SubscriberAccountEnquiry;
import ct.af.message.incoming.parameter.Param_Idle_Request;
import ct.af.message.incoming.parameter.Param_SDF_GetAmfSubProfile;
import ct.af.message.incoming.parameter.ResultInfo;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ct.af.utils.HexDecode;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;
import phoebe.eqx.model.dcc.cca.AccountInfo;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;
import phoebe.eqx.model.dcc.cca.FreeResInfo;

public class Out_End_DispPPSInfo {

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

		boolean isValid = afSubIns.getSubIsValid();
		EFinalCode finaleCode = afSubIns.getSubFinalCode();

		strbuilder.append("<vcrr>"
				+ "<res>"+finaleCode.getFinalResultCode()+"</res>"
				+ "<desc>"+finaleCode.getFinalDesc()+"</desc>");

		if(isValid) {

			if(ec02Instance.getAFInstance().getMainProfile().equals(EFunctionGroup.INS.toString())) {

				DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyyMMdd:HHmmss");
				DateTimeFormatter formatDate2 = DateTimeFormat.forPattern("yyyyMMddHHmmssZ");

				DateTime date = new DateTime();

				List<Counters_AMF_CheckBalance> countersChkBalance = afSubIns.getSubResponseParamCheckBalance().getCountersAMFCheckBalance();

				AccountInfo_AMF_CheckBalance accInfo = afSubIns.getSubResponseParamCheckBalance().getAccountInfoAMFCheckBalance().get(0);

				ResultInfo resultInfo = afSubIns.getInquirySubParam().getResultInfo().get(0);
				AccountingInfo accountingInfo = afSubIns.getInquirySubParam().getAccountingInfo().get(0);

				String balance = "";
				String accountstate = "";

				String activestop = accInfo.getActiveStopTime().split(":",-1)[0];
				String suspendstop = accInfo.getSuspendStopTime().split(":",-1)[0];
				String disablestop = accInfo.getDisableStopTime().split(":",-1)[0];
				String servicestop = disablestop.split(":",-1)[0];
				String maxactivatedays = accInfo.getMaxValidity();
				String maxcounttotal = "";
				String craditlimit = "0";
				String timeenteractive = "";
				String[] timeenteractiveSpt = null;
				String timeenteractiveDate = "";
				String timeenteractiveTime = "";
				String prmsm = "";
				String prmminute = "";

				DateTime currentTime = formatDate.parseDateTime(formatDate.print(date));
				AppLog.d("currentTime : "+currentTime.toString());

				if(afSubIns.getSubResponseParamObtain() != null) {

					List<Counters_AMF_ObtainNoneBalance> counterObtain = afSubIns.getSubResponseParamObtain().getCountersAMFObtainNoneBalance();

					DateTime registrationTime =  formatDate.parseDateTime(counterObtain.get(0).getRegistrationTime());
					DateTime expiryTime  =  formatDate.parseDateTime(counterObtain.get(0).getExpiryTime());

					AppLog.d("registrationTime : "+registrationTime.toString());
					AppLog.d("expiryTime : "+expiryTime.toString());
					AppLog.d("Type : "+counterObtain.get(0).getType());

					if(counterObtain.get(0).getType().toLowerCase().equals("sms")){
						if(registrationTime.isBefore(currentTime) && currentTime.isBefore(expiryTime)){
							prmsm = counterObtain.get(0).getValue();
						}

					} else if(counterObtain.get(0).getType().toLowerCase().equals("voice")){
						if(registrationTime.isBefore(currentTime) && currentTime.isBefore(expiryTime)){
							prmminute = counterObtain.get(0).getValue();
						}

					}
				}

				AppLog.d("resultInfo : "+resultInfo);
				String cos = accountingInfo.getDs2classOfservice();
				String brandid = resultInfo.getDs2brandId();
				String ivrLang = resultInfo.getIvrLanguage();
				String smsLang = resultInfo.getSmsLanguage();
				String timeDrivenState = resultInfo.getTimeDrivenState();
				String pinFraudFlag = resultInfo.getPinFraudFlag();
				String userReqSuspend = resultInfo.getUserReqSuspend();
				String state = null;
				String firstModifiedTime = null;
				String servicepackageid = resultInfo.getDs2servPackageId();

				if (timeDrivenState.equalsIgnoreCase("Idle")) {
					state = "01";
				} else if (timeDrivenState.equalsIgnoreCase("Active")
						&& pinFraudFlag.equalsIgnoreCase("FALSE")
						&& (userReqSuspend.equalsIgnoreCase("noSuspend") || userReqSuspend.equalsIgnoreCase(""))) {
					state = "02";
				} else if (timeDrivenState.equalsIgnoreCase("Suspend")
						|| userReqSuspend.equalsIgnoreCase("Suspend")
						|| userReqSuspend.equalsIgnoreCase("suspendNoValidityExt")) {
					state = "03";
				} else if (timeDrivenState.equalsIgnoreCase("Disable")) {
					state = "04";
				} else if (timeDrivenState.equalsIgnoreCase("Pool")) {
					state = "05";
				} else {
					state = accountstate;
				}

				Param_SDF_GetAmfSubProfile paramSDFSubPro = afSubIns.getSubRespGetAmfSubProfile();

				firstModifiedTime = paramSDFSubPro.getAmfSubProfile().get(0).getFirstModifiedTime();

				String[] workOrder = Config.WORKORDER_LANG_MAPPING.split(",");
				String ivr = "1";
				String sms = "1";

				for (int i = 0; i < workOrder.length;) {

					String[] lang = workOrder[i].split("\\|");

					if (ivrLang.equals(lang[0])) {
						ivr = lang[1];
						if(ivr.equalsIgnoreCase("THA")) {
							ivr = "3";
						} else if(ivr.equalsIgnoreCase("ENG")) {
							ivr = "1";
						} else if(ivr.equalsIgnoreCase("CHN")) {
							ivr = "2";
						} else {
							ivr = "1";
						}
					}
					if (smsLang.equals(lang[0])) {
						sms = lang[1];
						if(sms.equalsIgnoreCase("THA")) {
							sms = "3";
						} else if(ivr.equalsIgnoreCase("ENG")) {
							sms = "1";
						} else if(ivr.equalsIgnoreCase("CHN")) {
							sms = "2";
						} else {
							sms = "1";
						}
					}

					i++;
				}

				JSONArray prmmoneyinfoArr = new JSONArray();				
				JSONArray prmmoneyusageinfoArr = new JSONArray();

				int prmmoney = 0;
				int prmmoneyusage = 0;

				for (int i = 0; i < countersChkBalance.size(); i++) {
					String countIDAMF = countersChkBalance.get(i).getCounterID();
					String countCurrentBalance = countersChkBalance.get(i).getCurrentBalance();
					String[] countID = Config.COUNTID_PRMMONEYID.split(",", -1);
					String[] countMonetaryusagereward = Config.COUNTID_MONETARYUSAGEREWARD.split(",", -1);

					AppLog.d("COUNTID PRMMONEYID : "+countIDAMF);
					AppLog.d("COUNTID MONETARYUSAGEREWARD : "+countCurrentBalance);

					if (Integer.parseInt(countID[0]) <= Integer.parseInt(countIDAMF)
							&& Integer.parseInt(countIDAMF) <= Integer.parseInt(countID[1])) {

						String[] effectiveDate = countersChkBalance.get(i).getActivationDate().split(":",-1);
						String[] expirationDate = countersChkBalance.get(i).getExpiryTime().split(":",-1);
						JSONObject prmmoneyinfoObj = new JSONObject();
						
						prmmoneyinfoObj.put("prmmoneyid", countIDAMF);
						prmmoneyinfoObj.put("amount", countersChkBalance.get(i).getCurrentBalance());
						prmmoneyinfoObj.put("effectdate", effectiveDate[0] + effectiveDate[1]);
						prmmoneyinfoObj.put("expdate", expirationDate[0] + expirationDate[1]);
						
						prmmoneyinfoArr.add(prmmoneyinfoObj);
						
						prmmoney += Integer.parseInt(countersChkBalance.get(i).getCurrentBalance());

					}
					
					if(Integer.parseInt(countMonetaryusagereward[0]) <= Integer.parseInt(countIDAMF)
							&& Integer.parseInt(countIDAMF) <= Integer.parseInt(countMonetaryusagereward[1])) {
						
						String[] effectiveDate = countersChkBalance.get(i).getActivationDate().split(":",-1);
						String[] expirationDate = countersChkBalance.get(i).getExpiryTime().split(":",-1);
						
						if(afSubIns.getClientData().getPrmmoneyusageinfo().equals("1")) {
							
							JSONObject prmmoneyusageinfoObj = new JSONObject();
							prmmoneyusageinfoObj.put("prmmoneyid", countIDAMF);
							prmmoneyusageinfoObj.put("amount", countersChkBalance.get(i).getCurrentBalance());
							prmmoneyusageinfoObj.put("effectdate", effectiveDate[0] + effectiveDate[1]);
							prmmoneyusageinfoObj.put("expdate", expirationDate[0] + expirationDate[1]);
							if(!StringUtils.isBlank(countersChkBalance.get(i).getServiceId_resourceId_reourceName())) {	
								if(countersChkBalance.get(i).getServiceId_resourceId_reourceName().contains("|")) {
									String[] serviceId_resourceId_reourceName = countersChkBalance.get(i).getServiceId_resourceId_reourceName().split("\\|");
									StringBuilder serviceType = new StringBuilder();
									String resourceid = serviceId_resourceId_reourceName[0].split(":", -1)[1];
									for(int z = 0;z < serviceId_resourceId_reourceName.length;z++) {
										if(!StringUtils.isBlank(serviceId_resourceId_reourceName[z].split(":", -1)[0])) {
											String serviceid = Config.SERVICE_TYPE_MAPPING.get(serviceId_resourceId_reourceName[z].split(":", -1)[0]);
											
											if(serviceid == null) {
												serviceid = serviceId_resourceId_reourceName[z].split(":", -1)[0];
											}
											
											serviceType.append(serviceid+"|");
										}
									}
									serviceType.delete(serviceType.length() - 1, serviceType.length());
									prmmoneyusageinfoObj.put("freeresourceid", resourceid);
									prmmoneyusageinfoObj.put("servicetype", serviceType.toString());
								} else {
									if(!StringUtils.isBlank(countersChkBalance.get(i).getServiceId_resourceId_reourceName().split(":", -1)[1])) {
										prmmoneyusageinfoObj.put("freeresourceid", countersChkBalance.get(i).getServiceId_resourceId_reourceName().split(":", -1)[1]);
									}
									if(!StringUtils.isBlank(countersChkBalance.get(i).getServiceId_resourceId_reourceName().split(":", -1)[0])) {
										if(Config.SERVICE_TYPE_MAPPING.get(countersChkBalance.get(i).getServiceId_resourceId_reourceName().split(":", -1)[0]) == null) {
											prmmoneyusageinfoObj.put("servicetype", countersChkBalance.get(i).getServiceId_resourceId_reourceName().split(":", -1)[0]);
										} else {
											prmmoneyusageinfoObj.put("servicetype", Config.SERVICE_TYPE_MAPPING.get(countersChkBalance.get(i).getServiceId_resourceId_reourceName().split(":", -1)[0]));
										}
									}
								}
							}
							
							if(!StringUtils.isBlank(countersChkBalance.get(i).getProductNo())) {
								prmmoneyusageinfoObj.put("productnum", countersChkBalance.get(i).getProductNo());
							}
			
							if(afSubIns.getSubRespGetSidNotificationElement() != null
									&& StringUtils.isNotBlank(countersChkBalance.get(i).getPromoNameNotifElem())) {
								
								for(int j=0;j < afSubIns.getSubRespGetSidNotificationElement().getSidNotificationElement().size();j++) {
									
									String promoNameNotifElem = countersChkBalance.get(i).getPromoNameNotifElem();
									
									String[] promoNameNotifElemArr = promoNameNotifElem.split("\\|");
									
									if(afSubIns.getSubRespGetSidNotificationElement().getSidNotificationElement().get(j).getDn().contains(promoNameNotifElemArr[0])) {
										
										if(!StringUtils.isBlank(afSubIns.getSubRespGetSidNotificationElement().getSidNotificationElement().get(j).getSidTextMessage())) {
											prmmoneyusageinfoObj.put("packagenameENG", afSubIns.getSubRespGetSidNotificationElement().getSidNotificationElement().get(j).getSidTextMessage());
										}
										if(!StringUtils.isBlank(afSubIns.getSubRespGetSidNotificationElement().getSidNotificationElement().get(j).getSidTextUnicode())) {
											prmmoneyusageinfoObj.put("packagenameTHA", new HexDecode().decodeHex(afSubIns.getSubRespGetSidNotificationElement().getSidNotificationElement().get(j).getSidTextUnicode()));
										}
										
									}
									
								}
							}
		
							prmmoneyusageinfoArr.add(prmmoneyusageinfoObj);
							prmmoneyusage += Integer.parseInt(countersChkBalance.get(i).getCurrentBalance());
						}
					}

					if (countIDAMF.equals("200010")) {

						AppLog.d("is 200010");
						balance = countersChkBalance.get(i).getCurrentBalance();
						AppLog.d("balance : "+balance);
						DateTime activationDate =  formatDate.parseDateTime(countersChkBalance.get(i).getActivationDate());
						DateTime expiryDate =  formatDate.parseDateTime(countersChkBalance.get(i).getExpiryTime());
						DateTime currentDate = formatDate.parseDateTime(formatDate.print(date));
						timeenteractive = countersChkBalance.get(i).getActivationDate();
						timeenteractiveSpt = timeenteractive.split(":",-1);

						timeenteractiveDate = timeenteractiveSpt[0];
						timeenteractiveTime = timeenteractiveSpt[1];

						AppLog.d("timeenteractive : "+timeenteractive.toString());
						AppLog.d("activationDate : "+activationDate.toString());
						AppLog.d("expiryDate : "+expiryDate.toString());
						AppLog.d("currentTime : "+currentTime.toString());

						if(activationDate.isBefore(currentDate) && currentDate.isBefore(expiryDate)){
							maxcounttotal = countersChkBalance.get(i).getUpperLimit();
							craditlimit = countersChkBalance.get(i).getLowerLimit();

						}
					}
				}

				int creditlimitRemaining = 0;
				int creditlimitUsed = 0;

				String counterValue = balance;
				String lowerLimit = craditlimit;
				int counterValueInt = Integer.parseInt(counterValue);
				int lowerLimitInt = Integer.parseInt(lowerLimit);

				if(counterValueInt >= 0) {
					creditlimitRemaining = lowerLimitInt;
				}

				if(counterValueInt < 0) {
					creditlimitRemaining = lowerLimitInt - counterValueInt;
					creditlimitUsed = counterValueInt;
				}

				strbuilder.append("<msisdn>" + afSubIns.getSubMSISDN() + "</msisdn>"
						+ "<balance>" + balance + "</balance>"
						+ "<subspid></subspid>"
						+ "<subcosid>" + cos + "</subcosid>"
						+ "<accountstate>" + state + "</accountstate>"
						+ "<activestop>" + activestop + "</activestop>"
						+ "<suspendstop>" + suspendstop + "</suspendstop>"
						+ "<disablestop>" + disablestop + "</disablestop>"
						+ "<servicestop>" + servicestop + "</servicestop>"
						+ "<callingscreenflag></callingscreenflag>"
						+ "<roamflag></roamflag>"
						+ "<servicearea></servicearea>"
						+ "<maxactivedays>" + maxactivatedays + "</maxactivedays>"
						+ "<maxcounttotal>" + maxcounttotal + "</maxcounttotal>"
						+ "<languagetype>"+ivr+"</languagetype>"
						+ "<creditlimit>" + Math.abs(Integer.parseInt(craditlimit)) + "</creditlimit>"
						+ "<creditlimitRemaining>" + Math.abs(creditlimitRemaining) + "</creditlimitRemaining>"
						+ "<creditlimitUsed>" + Math.abs(creditlimitUsed) + "</creditlimitUsed>"
						+ "<score></score>"
						+ "<voicemail></voicemail>"
						+ "<activecac></activecac>"
						+ "<groupid></groupid>"
						+ "<prmsm>"+prmsm+"</prmsm>"
						+ "<prmminute>"+prmminute+"</prmminute>"
						+ "<querytimes></querytimes>"
						+ "<rbtflag></rbtflag>"
						+ "<agingdis></agingdis>"
						+ "<timeenteractive>" + timeenteractiveDate + timeenteractiveTime + "</timeenteractive>"
						+ "<ccc></ccc>"
						+ "<volumediscountflag></volumediscountflag>"
						+ "<fphflag></fphflag>"
						+ "<lastfph></lastfph>"
						+ "<callscreenflag></callscreenflag>"
						+ "<interroamflag></interroamflag>"
						+ "<smslangtype>"+sms+"</smslangtype>"
						+ "<smusage></smusage>"
						+ "<smusagetoptime></smusagetoptime>"
						+ "<prmscore></prmscore>"
						+ "<prmscorestoptime></prmscorestoptime>"
						+ "<scorestoptime></scorestoptime>"
						+ "<prmpointexp></prmpointexp>"
						+ "<calldis></calldis>"
						+ "<calldisexp></calldisexp>"
						+ "<smdis></smdis>"
						+ "<smdisexp></smdisexp>"
						+ "<smstamp></smstamp>"
						+ "<ivrprmtimes></ivrprmtimes>"
						+ "<ussdbtimes></ussdbtimes>"
						+ "<ussdprmtimes></ussdprmtimes>"
						+ "<prmpoint></prmpoint>"
						+ "<paytype></paytype>"
						+ "<brandid>" + brandid + "</brandid>"
						+ "<mlmmsisdn></mlmmsisdn>"
						+ "<fnflag></fnflag>"
						+ "<freecalltimes></freecalltimes>"
						+ "<calltimesexp></calltimesexp>"
						+ "<lastrechargetime></lastrechargetime>"
						+ "<prmmoney>" + prmmoney + "</prmmoney>"
						+ "<prmmoneyName>" + Config.DEFAULT_PRMMONEYNAME + "</prmmoneyName>"
						+ "<prmmoneyinfo>");
				
						if(prmmoneyinfoArr.size() > 0) {
							strbuilder.append(prmmoneyinfoArr.toJSONString());
						} 
						
				strbuilder.append("</prmmoneyinfo>"
						+ "<prmmoneyusage>"+ prmmoneyusage + "</prmmoneyusage>"
						+ "<prmmoneyusageName>" + Config.DEFAULT_PRMMONEYUSAGENAME + "</prmmoneyusageName>"
						+ "<prmmoneyusageinfo>");
				
						if(prmmoneyusageinfoArr.size() > 0) {
							strbuilder.append(prmmoneyusageinfoArr.toJSONString());
						}
						
				strbuilder.append("</prmmoneyusageinfo>"
						+ "<effectivedate>" +Config.formatDate.print(formatDate2.parseDateTime(firstModifiedTime)) + "</effectivedate>"
						+ "<servicepackageid>"+servicepackageid+"</servicepackageid>"
						+ "<caid>"+resultInfo.getCaId()+"</caid>"
						+ "<baid>"+resultInfo.getBaId()+"</baid>"
						+ "<spname>"+accountingInfo.getDs2cbpLocation()+"</spname>"
						+ "<customerid>"+resultInfo.getCustomerId()+"</customerid>"
						+ "<fraudlock>"+accInfo.getPinFraudFlag()+"</fraudlock>"
						+ "<customerFraudlock>"+accInfo.getFraudFlag()+"</customerFraudlock>"
						+ "<userReqSuspend>"+accInfo.getUserRequestSuspend()+"</userReqSuspend>");
						
						
			} else if(ec02Instance.getAFInstance().getMainProfile().equals(EFunctionGroup.BOS.toString())) {

				Param_Idle_Request clientData = afSubIns.getClientData();
				Param_BOS_SubscriberAccountEnquiry respParam = (Param_BOS_SubscriberAccountEnquiry) afSubIns.getSubResponseParam();

				DiameterCreditControlAnswer diameterResp = respParam.getDiamAns();
				
				int balance = 0;
				String subcosid = "0";
				String accountstate = "";
				String customerLifeCycleState = "";
				String activePeriod = "";
				String suspendPeriod = "";
				String disablePeriod = "";
				String fraudLock = "";
				String maxValidity = "";
				String maxCountTotal = "";
				String ivrLanguage = "";
				String paymentMode = "";
				int creditlimit = 0;
				String validityDateAns = "";
				String smsLanguage = "";
				String brandid = "";
				String prmsm = "";
				String prmminute = "";
				
				try {
					
					subcosid = diameterResp.getServiceInformation().getInInformation().getMainPromotion();
					customerLifeCycleState = AFUtils.hexToString(diameterResp.getServiceInformation().getInInformation().getCustomerLifeCycleState());
					activePeriod = AFUtils.hexToString(diameterResp.getServiceInformation().getInInformation().getActivePeriod());
					suspendPeriod = AFUtils.hexToString(diameterResp.getServiceInformation().getInInformation().getSuspendPeriod());
					disablePeriod = AFUtils.hexToString(diameterResp.getServiceInformation().getInInformation().getDisablePeriod());
					fraudLock = diameterResp.getServiceInformation().getInInformation().getFraudLock();
					maxValidity = diameterResp.getServiceInformation().getInInformation().getMaxValidity();
					maxCountTotal = diameterResp.getServiceInformation().getInInformation().getMaxCountTotal();
					ivrLanguage = diameterResp.getServiceInformation().getInInformation().getIVRLanguage();
					paymentMode = diameterResp.getServiceInformation().getInInformation().getPaymentMode();
					smsLanguage = diameterResp.getServiceInformation().getInInformation().getSMSLanguage();
					brandid = diameterResp.getServiceInformation().getInInformation().getBrandId();
					
				} catch(Exception ex) {
					AppLog.w("Cannot get value !");
				}
				
				List<AccountInfo> accountInfo = diameterResp.getServiceInformation().getInInformation().getAccountInfo();
				List<FreeResInfo> freeResInfo = diameterResp.getServiceInformation().getInInformation().getFreeResInfo();
				
				for(AccountInfo aacInfos:accountInfo) {
					
					String bookItemType = aacInfos.getBookItemType();
					
					String validityDateStr = AFUtils.hexToString(aacInfos.getValidityDate());
					String expireDateStr = AFUtils.hexToString(aacInfos.getExpireDate());
					
					DateTime validityDate = Config.formatDate.parseDateTime(validityDateStr);
					DateTime expireDate = Config.formatDate.parseDateTime(expireDateStr);

					if(bookItemType.equals("2000")
							|| bookItemType.equals("2100")) {
						
						validityDateAns = validityDateStr;
						
						if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
							
							int amount = Integer.parseInt(aacInfos.getBookItemAmount());
							
							balance = balance+amount;
						}
						
					}
					
					if(StringUtils.isBlank(paymentMode)) {
						
						if(bookItemType.equals("4000")
								|| bookItemType.equals("4100")
								|| bookItemType.equals("3100")) {
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								int amount = Integer.parseInt(aacInfos.getBookItemAmount());
								creditlimit = creditlimit+amount;
							}
						}
						
					} else if(paymentMode.equals("0")) {
						
						if(bookItemType.equals("4000")
								|| bookItemType.equals("4100")) {
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								int amount = Integer.parseInt(aacInfos.getBookItemAmount());
								creditlimit = creditlimit+amount;
							}
						}
						
					}  else if(paymentMode.equals("1")
							|| paymentMode.equals("2")) {
						
						if(bookItemType.equals("3100")) {
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								int amount = Integer.parseInt(aacInfos.getBookItemAmount());
								creditlimit = creditlimit+amount;
							}
						}
						
					}
					
					
				}
			
				if(freeResInfo != null) {
					
					int remainingAmountPrmsm = 0;
					int remainingAmountprmminute = 0;
					
					for(FreeResInfo freeResInfos:freeResInfo) {
						
						String validityDateStr = AFUtils.hexToString(freeResInfos.getValidityDate());
						String expireDateStr = AFUtils.hexToString(freeResInfos.getExpireDate());
						
						DateTime validityDate = Config.formatDate.parseDateTime(validityDateStr);
						DateTime expireDate = Config.formatDate.parseDateTime(expireDateStr);

						
						if(freeResInfos.getResourceGroupId().startsWith("17")) {
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								int amount = Integer.parseInt(freeResInfos.getRemainingAmount());
								remainingAmountPrmsm = remainingAmountPrmsm+amount;
							}
							
						} else if(freeResInfos.getResourceGroupId().startsWith("11")) {
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								int amount = Integer.parseInt(freeResInfos.getRemainingAmount());
								remainingAmountprmminute = remainingAmountprmminute+amount;
							}
							
						}
						
					}
					
					if(remainingAmountPrmsm > 0) {
						prmsm = String.valueOf(remainingAmountPrmsm);
					}
					
					if(remainingAmountprmminute > 0) {
						prmminute = String.valueOf(remainingAmountprmminute);
					}
					
				}
				
				
				if(customerLifeCycleState.equals("0")) {
					accountstate = "01";
				} else if(customerLifeCycleState.equals("1")
						|| customerLifeCycleState.equals("2")) {
					accountstate = "02";
				} else if(customerLifeCycleState.equals("3")
						|| customerLifeCycleState.equals("4")
						|| customerLifeCycleState.equals("5")) {
					accountstate = "03";
				} else if(customerLifeCycleState.equals("6")
						|| customerLifeCycleState.equals("7")) {
					accountstate = "04";
				} else if(customerLifeCycleState.equals("8")
						|| customerLifeCycleState.equals("9")) {
					accountstate = "05";
				}
				
				strbuilder.append("<msisdn>"+clientData.getMs()+"</msisdn>"
									+ "<balance>"+balance+"</balance>" // Pending
									+ "<subspid>"+"</subspid>"
									+ "<subcosid>"+subcosid+"</subcosid>" // Pending [ default = ? ]
									+ "<accountstate>"+accountstate+"</accountstate>" // Pending
									+ "<activestop>"+activePeriod+"</activestop>"
									+ "<suspendstop>"+suspendPeriod+"</suspendstop>"
									+ "<disablestop>"+disablePeriod+"</disablestop>"
									+ "<servicestop>"+disablePeriod+"</servicestop>"
									+ "<callingscreenflag>"+"</callingscreenflag>"
									+ "<roamflag>"+"</roamflag>"
									+ "<fraudlock>"+fraudLock+"</fraudlock>"
									+ "<servicearea>"+""+"</servicearea>"
									+ "<maxactivedays>"+maxValidity+"</maxactivedays>"
									+ "<maxcounttotal>"+maxCountTotal+"</maxcounttotal>"
									+ "<languagetype>"+ivrLanguage+"</languagetype>"
									+ "<creditlimit>"+creditlimit+"</creditlimit>" // Pending
									+ "<score>"+"</score>"
									+ "<voicemail>"+"</voicemail>"
									+ "<activecac>"+"</activecac>"
									+ "<groupid>"+"</groupid>"
									+ "<prmsm>"+prmsm+"</prmsm>" // Pending
									+ "<prmminute>"+prmminute+"</prmminute>" // Pending
									+ "<querytimes>"+"</querytimes>"
									+ "<rbtflag>"+"</rbtflag>"
									+ "<agingdis>"+"</agingdis>"
									+ "<timeenteractive>"+validityDateAns+"</timeenteractive>"
									+ "<ccc>"+"</ccc>"
									+ "<volumndiscountflag>"+"</volumndiscountflag>"
									+ "<fphflag>"+"</fphflag>"
									+ "<lastfph>"+"</lastfph>"
									+ "<callscreenflag>"+"</callscreenflag>"
									+ "<interroamflag>"+"</interroamflag>"
									+ "<smslangtype>"+smsLanguage+"</smslangtype>"
									+ "<smusage>"+"</smusage>"
									+ "<smusagetoptime>"+"</smusagetoptime>"
									+ "<prmscore>"+"</prmscore>"
									+ "<prmscorestoptime>"+"</prmscorestoptime>"
									+ "<scorestoptime>"+"</scorestoptime>"
									+ "<prmpointexp>"+"</prmpointexp>"
									+ "<calldis>"+"</calldis>"
									+ "<calldisexp>"+"</calldisexp>"
									+ "<smdis>"+"</smdis>"
									+ "<smdisexp>"+"</smdisexp>"
									+ "<smstamp>"+"</smstamp>"
									+ "<ivrprmtimes>"+"</ivrprmtimes>"
									+ "<ussdbtimes>"+"</ussdbtimes>"
									+ "<ussdprmtimes>"+"</ussdprmtimes>"
									+ "<prmpoint>"+"</prmpoint>"
									+ "<paytype>"+"</paytype>"
									+ "<brandid>"+brandid+"</brandid>"
						);
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
