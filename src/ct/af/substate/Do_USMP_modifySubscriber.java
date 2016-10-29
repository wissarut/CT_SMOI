package ct.af.substate;

import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_USMP_ModifySubscriber;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;

public class Do_USMP_modifySubscriber {
	
	public void doBusinessLogic(AbstractAF abstractAF, EC02Instance ec02Instance, AFSubInstance afSubIns) {

		Param_USMP_ModifySubscriber respParam = (Param_USMP_ModifySubscriber) afSubIns.getSubResponseParam();
		
		afSubIns.setFirstActDate(Config.formatDateWithMiTz.print(new DateTime()));

		if(!StringUtils.isBlank(respParam.getFirstActDate())) {
			
			DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZ");
			DateTimeFormatter formatDateForReport = Config.formatDate;
			DateTime MainProductValidDt = formatDate.parseDateTime(respParam.getFirstActDate());
			String MainProductValid = formatDate.print(MainProductValidDt);
			DateTime MainProduct = formatDate.parseDateTime(MainProductValid);
			
			afSubIns.getReportData().setNewActiveBegin(formatDateForReport.print(MainProduct));
			afSubIns.getReportData().setMainProductValidDt(formatDateForReport.print(MainProduct));
		}
		
		if(afSubIns.getOldCos().equals(afSubIns.getNewCos())){
			afSubIns.setSubNextState(ESubState.USMP_InquiryCounter.toString());
			afSubIns.setSubControlState(ESubState.USMP_InquiryCounter.toString());
		} else{
			afSubIns.setSubNextState(ESubState.USMP_FirstActivate.toString());
			afSubIns.setSubControlState(ESubState.USMP_FirstActivate.toString());
		}

	}

}
