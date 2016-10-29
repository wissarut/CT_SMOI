package ct.af.substate;

import ct.af.utils.StringUtils;
import ct.af.enums.ECommand;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ec02.af.abstracts.AbstractAF;

public class Do_E01_destination {
	
	public void doBusinessLogic(AbstractAF abstractAF, EC02Instance ec02Instance, AFSubInstance afSubIns) {

		if(afSubIns.getSubInitCmd().equals(ECommand.DISPPPSINFO.getCommand())) {
			
			afSubIns.setSubNextState(ESubState.BOS_SubscriberAccountEnquiry.toString());
			afSubIns.setSubControlState(ESubState.BOS_SubscriberAccountEnquiry.toString());
			
		} else if(afSubIns.getSubInitCmd().equals(ECommand.DISPPPSPKGREW.getCommand())) {
			
			afSubIns.setSubNextState(ESubState.BOS_SubscriberAccountEnquiry.toString());
			afSubIns.setSubControlState(ESubState.BOS_SubscriberAccountEnquiry.toString());
			
		} else if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSVALIDITY.getCommand())) {
			
			afSubIns.setSubNextState(ESubState.BOS_MainBalanceAccountAdjustment.toString());
			afSubIns.setSubControlState(ESubState.BOS_MainBalanceAccountAdjustment.toString());
			
		} else if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSMULTIATTR.getCommand())
				|| afSubIns.getSubInitCmd().equals(ECommand.SETPPSREWARD.getCommand())) {
			
			boolean balanceChk = StringUtils.isBlank(afSubIns.getClientData().getBalance()); // Balance is null or empty.
			boolean validityChk = StringUtils.isBlank(afSubIns.getClientData().getValidity()); // Validity is null or empty.
			boolean paramChk =  StringUtils.isBlank(afSubIns.getClientData().getPrmmoney()) // All param is null or empty.
					&& StringUtils.isBlank(afSubIns.getClientData().getPrmminute()) 
					&& StringUtils.isBlank(afSubIns.getClientData().getPoint()) 
					&& StringUtils.isBlank(afSubIns.getClientData().getFreecalltimes())
					&& StringUtils.isBlank(afSubIns.getClientData().getFreerbtsong()) 
					&& StringUtils.isBlank(afSubIns.getClientData().getFreerbtmf()) 
					&& StringUtils.isBlank(afSubIns.getClientData().getScore()) 
					&& StringUtils.isBlank(afSubIns.getClientData().getPrmscore());
			
			if( (!balanceChk || !validityChk) && paramChk) {
				
				afSubIns.setSubNextState(ESubState.BOS_MainBalanceAccountAdjustment.toString());
				afSubIns.setSubControlState(ESubState.BOS_MainBalanceAccountAdjustment.toString());
				
			} else  {
				
				if(balanceChk && (!validityChk || !paramChk)) {
					
					afSubIns.setSubNextState(ESubState.BOS_FreeResourceAdjustment.toString());
					afSubIns.setSubControlState(ESubState.BOS_FreeResourceAdjustment.toString());
					
				} else {
					
					if(afSubIns.getSubInitCmd().equals(ECommand.MODIPPSMULTIATTR.getCommand())) {
						
						afSubIns.setSubNextState(ESubState.End_ModiPPSMultiAttr.toString());
						afSubIns.setSubControlState(ESubState.End_ModiPPSMultiAttr.toString());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						
					} else {
						
						afSubIns.setSubNextState(ESubState.End_SetPPSReward.toString());
						afSubIns.setSubControlState(ESubState.End_SetPPSReward.toString());
						afSubIns.setSubInternalCode(afSubIns.getSubResultCode());
						
					}			
				}
			}
			
		} else if(afSubIns.getSubInitCmd().equals(ECommand.CHGTRIGCHRGACNT.getCommand())) {
			
			afSubIns.setSubNextState(ESubState.BOS_AccountRecharge.toString());
			afSubIns.setSubControlState(ESubState.BOS_AccountRecharge.toString());
			
		}

	}
	
}
