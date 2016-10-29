package ct.af.extracterd;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import ct.af.enums.ECommand;
import ct.af.enums.EResultCode;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_Idle_Unknown {

	public AFSubInstance extractRawData(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns,EquinoxRawData eqxRawData) {
				
		DateTimeFormatter formatDateWithMiTz = Config.formatDateWithMiTz;
		
		DateTime timeStampIn = formatDateWithMiTz.parseDateTime(ec02Instance.getAFInstance().getMainTimeStampIncoming());
		
		Boolean isValid = false;
		boolean isRetNormal = true;
		
		if(!eqxRawData.getRet().equals("0")) {
			isRetNormal = false;
		}
		
		EResultCode resultCode = EResultCode.RE40400;
		EStats statIn;
		
		if(abstractAF.getEquinoxProperties().getState().equals(ESubState.RMF_RMCR.toString())) {
			statIn = EStats.INGATEWAY_RECEIVE_RMCR_TIMEOUT;
		} else {
			statIn = EStats.INGATEWAY_RECEIVE_UNKNOWN_REQUEST;
		}	
		
 		afSubIns = new AFSubInstance();
 		afSubIns.setSubInitTimeStampIn(formatDateWithMiTz.print(timeStampIn));
 		
 		afSubIns.setSubInstanceNo(AFUtils.subInsNoGenerator(ec02Instance,ECommand.UNKNOWN.toString()));
		afSubIns.setSubInitOrig(eqxRawData.getOrig());
		afSubIns.setSubInitInvoke(eqxRawData.getInvoke());
		afSubIns.setSubInitEvent(statIn.getStatName());
		afSubIns.setSubInitCmd(ECommand.UNKNOWN.toString());
		afSubIns.setSubTimeout(formatDateWithMiTz.print(timeStampIn.plusYears(1)));
 		
 		afSubIns.setSubCurrentState(ESubState.Idle_Unknown.toString());
		afSubIns.setSubNextState(ESubState.END.toString());
		afSubIns.setSubControlState(ESubState.Idle_Unknown.toString());
		
		afSubIns.setSubResultCode(resultCode);
		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(isRetNormal);
		afSubIns.addStatsIn(statIn);
		
		ec02Instance.getAFInstance().putMainSubInstance(afSubIns.getSubInstanceNo(), afSubIns);

		return afSubIns;
			
	}

}
