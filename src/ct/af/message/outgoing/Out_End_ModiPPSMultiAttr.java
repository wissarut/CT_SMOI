package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EFinalCode;
import ct.af.enums.EFunctionGroup;
import ct.af.enums.EMethod;
import ct.af.enums.EProtocal;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_BOS_FreeResourceAdjustment;
import ct.af.message.incoming.parameter.Param_BOS_MainbalanceAccountAdjustment;
import ct.af.message.incoming.parameter.Param_TMF_AccountAdjustment;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import phoebe.eqx.model.dcc.cca.AccountInfo;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;
import phoebe.eqx.model.dcc.cca.FreeResInfo;

public class Out_End_ModiPPSMultiAttr {
	
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
		
		if(finaleCode.getFinalResultCode().equals("000")){
			
			if(ec02Instance.getAFInstance().getMainProfile().equals(EFunctionGroup.INS.toString())) 
			{
				Param_TMF_AccountAdjustment tmfAccountAdjustmentParameter = (Param_TMF_AccountAdjustment) afSubIns.getSubResponseParam();
				
				int bookItemAmount = 0;
				String expireDate = "";
				
				for(int i=0; i<tmfAccountAdjustmentParameter.getAccountInfo().size();i++) {
					
					if(tmfAccountAdjustmentParameter.getAccountInfo().get(i).getBookItemType().equals("2000")) {
						
						String validityMsg = AFUtils.hexToString(tmfAccountAdjustmentParameter.getAccountInfo().get(i).getValidityDate());
						String expireMsg = AFUtils.hexToString(tmfAccountAdjustmentParameter.getAccountInfo().get(i).getExpireDate());
						
						DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMddHHmmss");
		
						DateTime validityDate = format.parseDateTime(validityMsg);
						DateTime ExpireDate = format.parseDateTime(expireMsg);
						
						if(AFUtils.dateTimeisInRange(validityDate, ExpireDate)) {
							
							bookItemAmount = bookItemAmount+Integer.parseInt(tmfAccountAdjustmentParameter.getAccountInfo().get(i).getBookItemAmount());
							
						}
						
						expireDate = expireMsg.substring(0, 8);
							
					}
					
				}
			
				strbuilder.append("<newbalance>" + bookItemAmount + "</newbalance>"
						+ "<newprmmoney></newprmmoney>"
						+ "<newprmsm></newprmsm>"
						+ "<newprmminute></newprmminute>"
						+ "<newpoint></newpoint>"
						+ "<newfreecalltimes></newfreecalltimes>"
						+ "<newfreerbtsong></newfreerbtsong>"
						+ "<newfreerbtmf></newfreerbtmf>"
						+ "<newscore></newscore>"
						+ "<newprmscore></newprmscore>"
						+ "<newsmusage></newsmusage>"
						+ "<newactivestop>"+expireDate+"</newactivestop>");
				
			} else if(ec02Instance.getAFInstance().getMainProfile().equals(EFunctionGroup.BOS.toString())) {
				
				String currentState = afSubIns.getSubCurrentState();

				DiameterCreditControlAnswer diameterResp = null;
				
				if(currentState.equals(ESubState.BOS_FreeResourceAdjustment.toString())) {
					Param_BOS_FreeResourceAdjustment respParam = (Param_BOS_FreeResourceAdjustment) afSubIns.getSubResponseParam();
					diameterResp  = respParam.getDiamAns();
				} else if(currentState.equals(ESubState.BOS_MainBalanceAccountAdjustment.toString())) {
					Param_BOS_MainbalanceAccountAdjustment respParam = (Param_BOS_MainbalanceAccountAdjustment) afSubIns.getSubResponseParam();
					diameterResp  = respParam.getDiamAns();
				}
				
				int balance = 0;
				String newbalance = "";
				String newprmmoney = "";
				String newprmsm = "";
				String newprmminute = "";
				String newfreecalltime = "";
				String newfreerbtsong = "";
				String expireDateAns = "";
				
				if(currentState.equals(ESubState.BOS_MainBalanceAccountAdjustment.toString())) {
					
					List<AccountInfo> accountInfo = diameterResp.getServiceInformation().getInInformation().getAccountInfo();
					
					for(AccountInfo aacInfos:accountInfo) {
						
						String bookItemType = aacInfos.getBookItemType();
						
						String validityDateStr = AFUtils.hexToString(aacInfos.getValidityDate());
						String expireDateStr = AFUtils.hexToString(aacInfos.getExpireDate());
						
						DateTime validityDate = Config.formatDate.parseDateTime(validityDateStr);
						DateTime expireDate = Config.formatDate.parseDateTime(expireDateStr);

						if(bookItemType.equals("2000")
								|| bookItemType.equals("2100")) {
							
							DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyyMMdd");
							
							expireDateAns = formatDate.print(expireDate);
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								
								int amount = Integer.parseInt(aacInfos.getBookItemAmount());
								
								balance = balance+amount;
							}
							
						}
		
					}
					
					newbalance = String.valueOf(balance);
				
				}
				
				List<FreeResInfo> freeResInfo = diameterResp.getServiceInformation().getInInformation().getFreeResInfo();
				
				if(freeResInfo != null) {
					
					int remainingAmountNewprmmoney = 0;
					int remainingAmountNewprmsm = 0;
					int remainingAmountNewprmminute = 0;
					int remainingAmountNewfreecalltime = 0;
					int remainingAmountNewfreerbtsong = 0;
					
					for(FreeResInfo freeResInfos:freeResInfo) {
						
						String validityDateStr = AFUtils.hexToString(freeResInfos.getValidityDate());
						String expireDateStr = AFUtils.hexToString(freeResInfos.getExpireDate());
						
						DateTime validityDate = Config.formatDate.parseDateTime(validityDateStr);
						DateTime expireDate = Config.formatDate.parseDateTime(expireDateStr);

						DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyyMMdd");
						
						expireDateAns = formatDate.print(expireDate);
						
						if(freeResInfos.getResourceGroupId().startsWith("10")) {
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								int amount = Integer.parseInt(freeResInfos.getRemainingAmount());
								remainingAmountNewprmmoney = remainingAmountNewprmmoney+amount;
							}
							
						} else if(freeResInfos.getResourceGroupId().startsWith("08")) {
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								int amount = Integer.parseInt(freeResInfos.getRemainingAmount());
								remainingAmountNewprmsm = remainingAmountNewprmsm+amount;
							}
							
						} else if(freeResInfos.getResourceGroupId().startsWith("02")) {
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								int amount = Integer.parseInt(freeResInfos.getRemainingAmount());
								remainingAmountNewprmminute = remainingAmountNewprmminute+amount;
							}
							
						} else if(freeResInfos.getResourceGroupId().startsWith("11")) {
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								int amount = Integer.parseInt(freeResInfos.getRemainingAmount());
								remainingAmountNewfreecalltime = remainingAmountNewfreecalltime+amount;
							}
							
						} else if(freeResInfos.getResourceGroupId().startsWith("29")) {
							
							if(AFUtils.dateTimeisInRange(validityDate, expireDate)) {
								int amount = Integer.parseInt(freeResInfos.getRemainingAmount());
								remainingAmountNewfreerbtsong = remainingAmountNewfreerbtsong+amount;
							}
							
						}
						
					}
					
					if(remainingAmountNewprmmoney > 0) {
						newprmmoney = String.valueOf(remainingAmountNewprmmoney);
					}
					
					if(remainingAmountNewprmsm > 0) {
						newprmsm = String.valueOf(remainingAmountNewprmsm);
					}
					
					if(remainingAmountNewprmminute > 0) {
						newprmminute = String.valueOf(remainingAmountNewprmminute);
					}
					
					if(remainingAmountNewfreecalltime > 0) {
						newfreecalltime = String.valueOf(remainingAmountNewfreecalltime);
					}
					
					if(remainingAmountNewfreerbtsong > 0) {
						newfreerbtsong = String.valueOf(remainingAmountNewfreerbtsong);
					}
					
					
				}

				strbuilder.append("<newbalance>"+newbalance+"</newbalance>"
						+ "<newprmmoney>"+newprmmoney+"</newprmmoney>" // Pending
						+ "<newprmsm>"+newprmsm+"</newprmsm>" // Pending
						+ "<newprmminute>"+newprmminute+"</newprmminute>" // Pending
						+ "<newpoint></newpoint>"
						+ "<newfreecalltime>"+newfreecalltime+"</newfreecalltime>" // Pending
						+ "<newfreerbtsong>"+newfreerbtsong+"</newfreerbtsong>" // Pending
						+ "<newfreerbtmf></newfreerbtmf>"
//							+ "<newscore>"+"</newscore>" // Pending [ Not Support but shown in example. ]
//							+ "<newprmscore>"+"</mewprmscore>" // Pending [ Not Support but shown in example. ]
//							+ "<newsmsusage>"+"</newsmsusage>" // Pending [ Not Support but shown in example. ]
						+ "<newactivestop>"+expireDateAns+"</newactivestop>"
					);
					
			
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
