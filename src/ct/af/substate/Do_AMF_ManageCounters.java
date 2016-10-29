package ct.af.substate;

import org.joda.time.DateTime;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;

public class Do_AMF_ManageCounters {
	
	public void doBusinessLogic(AbstractAF abstractAF, EC02Instance ec02Instance, AFSubInstance afSubIns) {
		
		
		if(afSubIns.getFirstsActDate() == null) {
			
			afSubIns.setFirstActDate(Config.formatDateWithMiTz.print(new DateTime()));
			
			if(afSubIns.getOldCos().equals(afSubIns.getNewCos())){
				afSubIns.setSubNextState(ESubState.USMP_InquiryCounter.toString());
				afSubIns.setSubControlState(ESubState.USMP_InquiryCounter.toString());
			} else{
				afSubIns.setSubNextState(ESubState.USMP_FirstActivate.toString());
				afSubIns.setSubControlState(ESubState.USMP_FirstActivate.toString());
			}
			
		} else {
			afSubIns.setSubNextState(ESubState.E01_Notification.toString());
			afSubIns.setSubControlState(ESubState.E01_Notification.toString());
		}
		
		

	}

}
