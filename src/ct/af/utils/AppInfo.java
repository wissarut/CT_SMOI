package ct.af.utils;

import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class AppInfo {
	
	String AB_NORMAL = "[ABNORMAL]";
	
	public String composeIncommingInfo(String timeStampIn,boolean isRetTimeout,boolean isE01Data,int eqxSize) {

		String isRetTimeoutStatus = "";
		
		if(isRetTimeout) {
			isRetTimeoutStatus = AB_NORMAL;
		}
		
		StringBuilder incommingInfoStr = new StringBuilder();
		
		incommingInfoStr.append("\n===================================================================");
		incommingInfoStr.append("\n   Incomming Message");
		incommingInfoStr.append("\n===================================================================");
		incommingInfoStr.append("\n   Timestamp     : "+timeStampIn);
		incommingInfoStr.append("\n   is Timeout    : "+isRetTimeout+" "+isRetTimeoutStatus);
		incommingInfoStr.append("\n   is E01 Data   : "+isE01Data);
		incommingInfoStr.append("\n   count EQX Msg : "+eqxSize);
		incommingInfoStr.append("\n===================================================================");
		
		AppLog.d(incommingInfoStr.toString());
		
		return "App Infomation Debug [IncommingInfo]"; 
	}

	public String composeExtractInfo(EquinoxRawData eqxRawData,AFSubInstance afSubIns,boolean isEDBPurge,boolean isDelayResp) {
		
		String isEDBPurgeStatus = "";
		String isDelayRespStatus = "";
		String isRetNormalStatus = "";
		String isValidStatus = "";
		
		if(isEDBPurge) {
			isEDBPurgeStatus = AB_NORMAL;
		}
		
		if(isDelayResp) {
			isDelayRespStatus = AB_NORMAL;
		}
		
		if(!afSubIns.isSubIsRetNormal()) {
			isRetNormalStatus = AB_NORMAL;
		}
		
		if(!afSubIns.getSubIsValid()) {
			isValidStatus = AB_NORMAL;
		}
		
		StringBuilder extInfoStr = new StringBuilder();

		extInfoStr.append("\n===================================================================");
		extInfoStr.append("\n  Extract Info @invoke : "+eqxRawData.getInvoke());
		extInfoStr.append("\n===================================================================");
		extInfoStr.append("\n  isEDBPurge     : "+isEDBPurge+" "+isEDBPurgeStatus);
		extInfoStr.append("\n  isDelayResp    : "+isDelayResp+" "+isDelayRespStatus);
		extInfoStr.append("\n  isRetNormal    : "+afSubIns.isSubIsRetNormal()+" "+isRetNormalStatus);
		extInfoStr.append("\n  isValid		 : "+afSubIns.getSubIsValid()+" "+isValidStatus);
		extInfoStr.append("\n  resultCode     : "+afSubIns.getSubResultCode().getResultCode());
		extInfoStr.append("\n===================================================================");

		AppLog.d(extInfoStr.toString());
		
		return "App Infomation Debug [ExtractInfo]"; 
	}
	
	public String composeInputDebug(EC02Instance ec02Ins,EquinoxRawData eqxRawData,AFSubInstance afSubInstance) {

		StringBuilder eqxRawDataStr = new StringBuilder();
		
		eqxRawDataStr.append("\n  Invoke   : "+eqxRawData.getInvoke());
		eqxRawDataStr.append("\n  Ret      : "+eqxRawData.getRet());
		eqxRawDataStr.append("\n  Orig     : "+eqxRawData.getOrig());
		eqxRawDataStr.append("\n  Protocol : "+eqxRawData.getName());
		eqxRawDataStr.append("\n  Type     : "+eqxRawData.getType());
		eqxRawDataStr.append("\n  CType    : "+eqxRawData.getCType());
		eqxRawDataStr.append("\n  Method   : "+eqxRawData.getRawDataAttribute(EEqxMsg.METHOD.getEqxMsg()));
		eqxRawDataStr.append("\n  Url      : "+eqxRawData.getRawDataAttribute(EEqxMsg.URL.getEqxMsg()));
		
		String data = eqxRawData.getRawDataMessage().replaceAll(">\\s*<", "><").trim();
		
		if(eqxRawData.getCType().equals(ECType.TEXTPLAIN.getCType())
				|| eqxRawData.getCType().equals(ECType.APPJSON.getCType())) {
			data = eqxRawData.getRawDataAttribute(EEqxMsg.VAL.getEqxMsg());
		}
		
		eqxRawDataStr.append("\n  Data     : "+data);
		eqxRawDataStr.append("\n  Stat-In  : "+afSubInstance.getStatsIn().get(afSubInstance.getStatsIn().size() - 1).getStatName());
		eqxRawDataStr.append("\n-------------------------------------------------------------------");
		
		ec02Ins.getAppLogInOutDebug().append(eqxRawDataStr);
		
		return "App Infomation Debug [Input]";
	}
	
	public String composeOutputDebug(EC02Instance ec02Ins,EquinoxRawData eqxRawData,AFSubInstance afSubInstance) {
		
		StringBuilder eqxRawDataStr = new StringBuilder();
		
		eqxRawDataStr.append("\n  Invoke   : "+eqxRawData.getInvoke());
		eqxRawDataStr.append("\n  To       : "+eqxRawData.getTo());
		eqxRawDataStr.append("\n  Protocol : "+eqxRawData.getName());
		
		if(eqxRawData.getName().equals(EProtocal.LDAP.toString())) {
			eqxRawDataStr.append("\n  OID      : "+eqxRawData.getRawDataAttribute(EEqxMsg.OID.getEqxMsg()));
		}
		
		eqxRawDataStr.append("\n  Type     : "+eqxRawData.getType());
		eqxRawDataStr.append("\n  CType    : "+eqxRawData.getCType());
		
		String data = eqxRawData.getRawDataMessage().replaceAll(">\\s*<", "><").trim();
		
		if(eqxRawData.getCType().equals(ECType.TEXTPLAIN.getCType())
				|| eqxRawData.getCType().equals(ECType.APPJSON.getCType())) {
			data = eqxRawData.getRawDataAttribute(EEqxMsg.VAL.getEqxMsg());
		}
		
		EStats statOut = afSubInstance.getStatsOut().get(afSubInstance.getStatsOut().size() - 1);
		
		eqxRawDataStr.append("\n  Data     : "+data);
		eqxRawDataStr.append("\n  Stat-out : "+statOut.getStatName());
		eqxRawDataStr.append("\n-------------------------------------------------------------------");
		
		ec02Ins.getAppLogInOutDebug().append(eqxRawDataStr);
		
		return "App Infomation Debug [Output]";
	}
	
	public String composeDetailDebug(EC02Instance ec02Ins,String detailPrint) {
		
		StringBuilder logCDR = new StringBuilder();
		
		logCDR.append("\n  Detail     : "+detailPrint);
		logCDR.append("\n-------------------------------------------------------------------");
		
		ec02Ins.getAppLogCDR().append(logCDR);
		
		return "App Infomation Debug [Detail]";
	}
	
	public String composeSummaryDebug(EC02Instance ec02Ins,String detailPrint) {
		
		StringBuilder logCDR = new StringBuilder();
		
		logCDR.append("\n  Summary    : "+detailPrint);
		logCDR.append("\n-------------------------------------------------------------------");
		
		ec02Ins.getAppLogCDR().append(logCDR);
		
		return "App Infomation Debug [Summary]";
	}
	
	public String composeInfoDebug(EC02Instance ec02Ins,AFSubInstance afSubInstance) {
		
		StringBuilder ifoBody = new StringBuilder();
		
		ifoBody.append("\n  SunIns No.       : "+afSubInstance.getSubInstanceNo());
		
		if(afSubInstance.getSubHostInsNo() != null) {
			ifoBody.append("\n  SunIns Host No.  : "+afSubInstance.getSubHostInsNo());
		}
		
		ifoBody.append("\n  Init Command     : "+afSubInstance.getSubInitCmd());
		ifoBody.append("\n  Current State    : "+afSubInstance.getSubCurrentState());
		ifoBody.append("\n  Next State       : "+afSubInstance.getSubNextState());
		ifoBody.append("\n---------------------------------------------------------------------");
		
		ec02Ins.getAppLogDebug().append(ifoBody);
		
		return "App Infomation Debug [Info]";
	}
	
	public String showInfoDebug(AbstractAF abstractAF,EC02Instance ec02Instance) {
		
		StringBuilder infoHeader = new StringBuilder();
		
		infoHeader.append("\n===================================================================");
		infoHeader.append("\n Session Information @ Lib Version : "+abstractAF.getUtils().getHmColdconfig().get("Library").get("name"));
		infoHeader.append("\n===================================================================");
		infoHeader.append("\n Session           : "+abstractAF.getEquinoxProperties().getSession());
		infoHeader.append("\n SubIns Processing : "+ec02Instance.getAFInstance().getMainCountProcess());
		infoHeader.append("\n SubIns Waiting    : "+ec02Instance.getAFInstance().getMainCountWait());
		infoHeader.append("\n===================================================================");
		infoHeader.append("\n Equinox Raw Data Information");
		infoHeader.append("\n-------------------------------------------------------------------");
		infoHeader.append(ec02Instance.getAppLogInOutDebug().toString());
		infoHeader.append("\n===================================================================");
		infoHeader.append("\n SubInstance Information");
		infoHeader.append("\n-------------------------------------------------------------------");
		
		if(ec02Instance.getAppLogDebug().length() > 0) {
			infoHeader.append(ec02Instance.getAppLogDebug().toString());
		} else {
			infoHeader.append("\n No Activity");
		}
		
		infoHeader.append("\n===================================================================");
		infoHeader.append("\n Log Detail & Summary");
		infoHeader.append("\n-------------------------------------------------------------------");
		if(ec02Instance.getAppLogCDR().length() > 0) {
			infoHeader.append(ec02Instance.getAppLogCDR().toString());
		} else {
			infoHeader.append("\n No Activity");
		}
		
		infoHeader.append("\n===================================================================");

		ec02Instance.getAppLogDebug().setLength(0);
		
		return infoHeader.toString();
				
	}

}
