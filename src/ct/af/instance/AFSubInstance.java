package ct.af.instance;

import java.util.ArrayList;
import java.util.List;

import ct.af.core.log.DestinaionLog;
import ct.af.core.log.InputLog;
import ct.af.core.log.OutputLog;
import ct.af.enums.ECType;
import ct.af.enums.EFinalCode;
import ct.af.enums.EFunctionGroup;
import ct.af.enums.EMethod;
import ct.af.enums.EResultCode;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.enums.EValue;
import ct.af.message.incoming.parameter.Param_AMF_CheckBalance;
import ct.af.message.incoming.parameter.Param_AMF_ObtainCounter;
import ct.af.message.incoming.parameter.Param_DS2A_InquirySubscriber;
import ct.af.message.incoming.parameter.Param_Idle_Request;
import ct.af.message.incoming.parameter.Param_SDF_GetAmfCounter;
import ct.af.message.incoming.parameter.Param_SDF_GetAmfSubProfile;
import ct.af.message.incoming.parameter.Param_SDF_GetSidNotificationElement;
import ct.af.message.incoming.parameter.Param_SDF_GetSidRatingElement;
import ct.af.message.incoming.parameter.Param_USMP_FirstActivate;
import ct.af.message.incoming.parameter.Param_USMP_InquiryCounter;
import ct.af.message.incoming.parameter.Param_USMP_InquirySubscriberInfo;
import ct.af.message.incoming.parameter.Param_USMP_ModifySubscriber;
import ct.af.report.ReportData;

public class AFSubInstance {
	
	//Default Parameter
	private String subInstanceNo;
	private String subInitTimeStampIn;
	
	private String subInitOrig = EValue.NONE.toString();
	private String subInitCType = ECType.TEXTPLAIN.getCType();
	private String subInitInvoke = EValue.NONE.toString();
	private String subInitURL = EValue.NONE.toString();
	private String subInitEvent = EValue.NONE.toString();
	private String subInitCmd = EValue.NONE.toString();
	private String subInitMethod = EMethod.UNKNOWN.toString();
	
	private String subMSISDN;
	
	private String subTimeStampOutgoing;
	private String subExpireTime;

	private String subFunctionGroup = EFunctionGroup.Unknown.toString();
	private String subCurrentState = ESubState.Unknown.toString();
	private String subControlState = ESubState.Unknown.toString();
	private String subNextState = ESubState.Unknown.toString();
	private String subNextOfNextState = ESubState.Unknown.toString();
	private ArrayList<String> subInvoke = new ArrayList<String>();
	
	private String subHostInsNo;
	private String subStateHostWait;
	private int subCountChild = 0;
	private boolean subChildHasError = false;
	private String subChildErrorState;
	
	private boolean subIsValid = false;
	private boolean subIsRetNormal = false;
	private String subRecieveRet = "0";
	
	private String subTimeout;
	private int subCountServerRequest = 0;
	
	private String subResultCodeStr;
	private EResultCode subResultCode;
	private EResultCode subInternalCode;
	private EFinalCode subFinalCode;
	
	private String subRealResultCode;
	
	private boolean subHasErrorForWriteDetailLog = false;
	
	private ArrayList<InputLog> inputLogs = new ArrayList<InputLog>();
	private ArrayList<OutputLog> outputLogs = new ArrayList<OutputLog>();
	
	private List<String> detailLog = new ArrayList<String>();

	private DestinaionLog destinationLogTemp;
	private List<String> destinationList = new ArrayList<String>();
	
	private List<EStats> statsIn = new ArrayList<EStats>();
	private List<EStats> statsOut = new ArrayList<EStats>();
	
	//End Default Parameter

	private Param_Idle_Request clientData;
	private Param_DS2A_InquirySubscriber inquirySubParam;
	private Object subResponseParam;
	
	private Param_AMF_ObtainCounter subResponseParamObtain;
	private Param_AMF_CheckBalance subResponseParamChkBalance;
	private Param_USMP_FirstActivate subResponseParamFirstAct;
	private Param_USMP_InquirySubscriberInfo subResponseParamInquirySubscriberInfo;
	private Param_USMP_InquiryCounter subResponseParamInquiryCounter;
	private Param_USMP_ModifySubscriber subResponseParamModifySubscriber;
	
	private Param_SDF_GetAmfSubProfile subRespGetAmfSubProfile;
	private Param_SDF_GetAmfCounter subRespGetAmfCounter;
	private Param_SDF_GetSidRatingElement subRespGetSidRatingElement;
	private Param_SDF_GetSidNotificationElement subRespGetSidNotificationElement;
	
	private ReportData reportData;
	
	private String e01Data = "";
	
	private String oldCos;
	private String newCos;
	
	private String statePromoName; 
	private String oldPromoName;
	private String newPromoName;
	private ArrayList<String> rewardId = new ArrayList<String>();
	private ArrayList<String> promoNameNoti = new ArrayList<String>();
	private String refillStopTime;
	private String refillStopTimeMonth;
	
	private String firstActDate;
	
	private String refillStopTimeModifyCounterReq;
	
	private boolean subHasDelayFlag;
	private String subDataForward;
	
	private String diffTimeout;
	
	private String rmfOffering;
	
	private String amfManageCounter;

	public String getSubInstanceNo() {
		return subInstanceNo;
	}
	public void setSubInstanceNo(String subInstanceNo) {
		this.subInstanceNo = subInstanceNo;
	}
	
	public String getSubInitOrig() {
		return subInitOrig;
	}
	
	public void setSubInitOrig(String subInitOrig) {
		this.subInitOrig = subInitOrig;
	}
	
	public String getSubInitCType() {
		return subInitCType;
	}
	public void setSubInitCType(String subInitCType) {
		this.subInitCType = subInitCType;
	}
	public String getSubInitInvoke() {
		return subInitInvoke;
	}
	
	public void setSubInitInvoke(String subInitInvoke) {
		this.subInitInvoke = subInitInvoke;
	}
	
	public String getSubInitURL() {
		return subInitURL;
	}
	
	public void setSubInitURL(String subInitURL) {
		this.subInitURL = subInitURL;
	}
	
	public String getSubInitEvent() {
		return subInitEvent;
	}
	
	public void setSubInitEvent(String subInitEvent) {
		this.subInitEvent = subInitEvent;
	}
	
	public String getSubInitTimeStampIn() {
		return subInitTimeStampIn;
	}
	
	public void setSubInitTimeStampIn(String subInitTimeStampIn) {
		this.subInitTimeStampIn = subInitTimeStampIn;
	}
	
	
	public String getSubInitCmd() {
		return subInitCmd;
	}
	public void setSubInitCmd(String subInitCmd) {
		this.subInitCmd = subInitCmd;
	}
	public String getSubInitMethod() {
		return subInitMethod;
	}

	public void setSubInitMethod(String subInitMethod) {
		this.subInitMethod = subInitMethod;
	}
	public String getSubTimeStampOutgoing() {
		return subTimeStampOutgoing;
	}
	
	public void setSubTimeStampOutgoing(String subTimeStampOutgoing) {
		this.subTimeStampOutgoing = subTimeStampOutgoing;
	}
	
	public String getSubExpireTime() {
		return subExpireTime;
	}
	
	public void setSubExpireTime(String subExpireTime) {
		this.subExpireTime = subExpireTime;
	}
	
	public String getSubCurrentState() {
		return subCurrentState;
	}
	
	public void setSubCurrentState(String subCurrentState) {
		this.subCurrentState = subCurrentState;
	}
	
	public String getSubControlState() {
		return subControlState;
	}
	
	public void setSubControlState(String subControlState) {
		this.subControlState = subControlState;
	}
	
	public String getSubNextState() {
		return subNextState;
	}
	
	public void setSubNextState(String subNextState) {
		this.subNextState = subNextState;
	}
	
	public String getSubNextOfNextState() {
		return subNextOfNextState;
	}
	
	public void setSubNextOfNextState(String subNextOfNextState) {
		this.subNextOfNextState = subNextOfNextState;
	}
	
	public ArrayList<String> getSubInvoke() {
		return subInvoke;
	}
	
	public void addSubInvoke(String invoke) {
		this.subInvoke.add(invoke);
	}
	
	public String getSubFunctionGroup() {
		return subFunctionGroup;
	}
	
	public void setSubFunctionGroup(String subFunctionGroup) {
		this.subFunctionGroup = subFunctionGroup;
	}
	
	public boolean getSubIsValid() {
		return subIsValid;
	}
	
	public void setSubIsValid(boolean subIsValid) {
		this.subIsValid = subIsValid;
	}
	
	public boolean isSubIsRetNormal() {
		return subIsRetNormal;
	}
	
	public void setSubIsRetNormal(boolean subIsRetNormal) {
		this.subIsRetNormal = subIsRetNormal;
	}
	
	public String getSubHostInsNo() {
		return subHostInsNo;
	}
	
	public void setSubHostInsNo(String subHostInsNo) {
		this.subHostInsNo = subHostInsNo;
	}
	
	public String getSubStateHostWait() {
		return subStateHostWait;
	}
	public void setSubStateHostWait(String subStateHostWait) {
		this.subStateHostWait = subStateHostWait;
	}
	public int getSubCountChild() {
		return subCountChild;
	}
	
	public void setSubCountChild(int subCountChild) {
		this.subCountChild = subCountChild;
	}
	
	public void incrementSubCountChild(){
		this.subCountChild++;
	}
	
	public void decrementSubCountChild(){
		
		if(this.subCountChild > 0) {
			this.subCountChild--;
		} else {
			this.subCountChild = 0;
		}
		
		if(this.subCountChild == 0) {
			this.subStateHostWait = null;
		}
		
	}
	
	public boolean isSubChildHasError() {
		return subChildHasError;
	}
	public void setSubChildHasError(boolean subChildHasError) {

		if(this.subChildHasError == false && subChildHasError == true) {
			this.subChildHasError = true;
		}
		
	}
	public String getSubChildErrorState() {
		return subChildErrorState;
	}
	public void setSubChildErrorState(String subChildErrorState) {
		this.subChildErrorState = subChildErrorState;
	}
	public void subChildHasError(boolean subChildHasError,String currentState) {
		
		if(this.subChildHasError == false && subChildHasError == true) {
			this.subChildHasError = true;
			this.subChildErrorState = currentState;
		}
		
	}
	public String getSubTimeout() {
		return subTimeout;
	}
	
	public void setSubTimeout(String subTimeout) {
		this.subTimeout = subTimeout;
	}
	public EResultCode getSubResultCode() {
		return subResultCode;
	}
	public void setSubResultCode(EResultCode subResultCode) {
		this.subResultCode = subResultCode;
	}
	public String getSubResultCodeStr() {
		return subResultCodeStr;
	}
	public void setSubResultCode(String subResultCode) {
		this.subResultCodeStr = subResultCode;
	}
	public EResultCode getSubInternalCode() {
		return subInternalCode;
	}
	public void setSubInternalCode(EResultCode subInternalCode) {
		this.subInternalCode = subInternalCode;
	}
	public EFinalCode getSubFinalCode() {
		return subFinalCode;
	}
	public void setSubFinalCode(EFinalCode subFinalCode) {
		this.subFinalCode = subFinalCode;
	}
	public String getSubRealResultCode() {
		return subRealResultCode;
	}
	public void setSubRealResultCode(String subRealResultCode) {
		this.subRealResultCode = subRealResultCode;
	}
	public boolean isSubHasErrorForWriteDetailLog() {
		return subHasErrorForWriteDetailLog;
	}
	
	public void setSubHasErrorForWriteDetailLog(boolean subHasErrorForWriteDetailLog) {
		this.subHasErrorForWriteDetailLog = subHasErrorForWriteDetailLog;
	}
	
	public ArrayList<InputLog> getSubInputLogs() {
	    return inputLogs;
	}

	public void addSubInputLogs(InputLog inputLogs) {
	    this.inputLogs.add(inputLogs);
	}
	 
	public ArrayList<OutputLog> getSubOutputLogs() {
		return outputLogs;
	}
	public void setSubOutputLogs(OutputLog outputLogs) {
		this.outputLogs.add(outputLogs);
	}
	public List<String> getDetailLog() {
		return detailLog;
	}
	public void setDetailLog(String detailLog) {
		this.detailLog.add(detailLog);
	}
	public List<EStats> getStatsIn() {
		return statsIn;
	}
	public void addStatsIn(EStats statsIn) {
		this.statsIn.add(statsIn);
	}
	public List<EStats> getStatsOut() {
		return statsOut;
	}
	public void addStatsOut(EStats statsOut) {
		this.statsOut.add(statsOut);
	}
	public Object getSubResponseParam() {
		return subResponseParam;
	}
	public void setSubResponseParam(Object subResponseParam) {
		this.subResponseParam = subResponseParam;
	}
	
	public Param_AMF_ObtainCounter getSubResponseParamObtain() {
		return subResponseParamObtain;
	}
	public void setSubResponseParamObtain(Param_AMF_ObtainCounter subResponseParamObtain) {
		this.subResponseParamObtain = subResponseParamObtain;
	}
	
	public Param_AMF_CheckBalance getSubResponseParamCheckBalance() {
		return subResponseParamChkBalance;
	}
	public void setSubResponseParamCheckBalance(Param_AMF_CheckBalance subResponseParamChkBalance) {
		this.subResponseParamChkBalance = subResponseParamChkBalance;
	}
	public Param_USMP_FirstActivate getSubResponseParamFirstAct() {
		return subResponseParamFirstAct;
	}
	public void setSubResponseParamFirstAct(Param_USMP_FirstActivate subResponseParamFirstAct) {
		this.subResponseParamFirstAct = subResponseParamFirstAct;
	}
	public Param_USMP_InquirySubscriberInfo getSubResponseParamInquirySubscriberInfo() {
		return subResponseParamInquirySubscriberInfo;
	}
	public void setSubResponseParamInquirySubscriberInfo(Param_USMP_InquirySubscriberInfo subResponseParamInquirySubscriberInfo) {
		this.subResponseParamInquirySubscriberInfo = subResponseParamInquirySubscriberInfo;
	}
	public Param_USMP_InquiryCounter getSubResponseParamInquiryCounter() {
		return subResponseParamInquiryCounter;
	}
	public void setSubResponseParamInquiryCounter(Param_USMP_InquiryCounter subResponseParamInquiryCounter) {
		this.subResponseParamInquiryCounter = subResponseParamInquiryCounter;
	}
	public Param_USMP_ModifySubscriber getSubResponseParamModifySubscriber() {
		return subResponseParamModifySubscriber;
	}
	public void setSubResponseParamModifySubscriber(Param_USMP_ModifySubscriber subResponseParamModifySubscriber) {
		this.subResponseParamModifySubscriber = subResponseParamModifySubscriber;
	}
	public ReportData getReportData() {
		return reportData;
	}
	public void setReportData(ReportData reportData) {
		this.reportData = reportData;
	}
	public String getSubDestinationString() {
		
		StringBuilder destinationListStr = new StringBuilder();
		
		destinationListStr.append("[");
		
		if(this.destinationList.size() != 0) {
		
			for (String temp : this.destinationList)
			{
				destinationListStr.append(temp+"|");  
			}
		
			destinationListStr.delete(destinationListStr.length() - 1, destinationListStr.length());
			
		} else {
			destinationListStr.append("null;null;null");
		}
		
		destinationListStr.append("]");
		
		return destinationListStr.toString();
	}
	public List<String> getDestinationList() {
		return destinationList;
	}
	public void setDestinationList(ArrayList<String> destinationList) {
		this.destinationList = destinationList;
	}
	public void setSubDestinationList(String destinationList) {
		this.destinationList.add(destinationList);
	}
	public String getSubMSISDN() {
		return subMSISDN;
	}
	public void setSubMSISDN(String subMSISDN) {
		this.subMSISDN = subMSISDN;
	}
	public String getE01Data() {
		return e01Data;
	}
	public void setE01Data(String e01Data) {
		this.e01Data = e01Data;
	}
	public String getE01DestHost()
	{
		String temp = this.e01Data.replace("|", ",");
		return temp.split(",")[1];
	}
	public String  getE01DestRealm()
	{
		String temp = this.e01Data.replace("|", ",");
		return temp.split(",")[2];
	}
	public String getOldCos() {
		return oldCos;
	}
	public void setOldCos(String oldCos) {
		this.oldCos = oldCos;
	}
	public String getNewCos() {
		return newCos;
	}
	public void setNewCos(String newCos) {
		this.newCos = newCos;
	}
	public String getStatePromoName() {
		return statePromoName;
	}
	public void setStatePromoName(String statePromoName) {
		this.statePromoName = statePromoName;
	}
	public String getOldPromoName() {
		return oldPromoName;
	}
	public void setOldPromoName(String oldPromoName) {
		this.oldPromoName = oldPromoName;
	}
	public String getNewPromoName() {
		return newPromoName;
	}
	public void setNewPromoName(String newPromoName) {
		this.newPromoName = newPromoName;
	}
	public ArrayList<String> getRewardId() {
		return rewardId;
	}
	public void addRewardId(String rewardId) {
		this.rewardId.add(rewardId);
	}
	
	public ArrayList<String> getPromoNameNoti() {
		return promoNameNoti;
	}
	public void addPromoNameNoti(String promoNameNoti) {
		this.promoNameNoti.add(promoNameNoti);
	}
	public String getRefillStopTime() {
		return refillStopTime;
	}
	public void setRefillStopTime(String refillStopTime) {
		this.refillStopTime = refillStopTime;
	}
	public String getRefillStopTimeMonth() {
		return refillStopTimeMonth;
	}
	public void setRefillStopTimeMonth(String refillStopTimeMonth) {
		this.refillStopTimeMonth = refillStopTimeMonth;
	}
	public String getFirstsActDate() {
		return firstActDate;
	}
	public void setFirstActDate(String firstActDate) {
		this.firstActDate = firstActDate;
	}
	public String getRefillStopTimeModifyCounterReq() {
		return refillStopTimeModifyCounterReq;
	}
	public void setRefillStopTimeModifyCounterReq(String refillStopTimeModifyCounterReq) {
		this.refillStopTimeModifyCounterReq = refillStopTimeModifyCounterReq;
	}
	public boolean isSubHasDelayFlag() {
		return subHasDelayFlag;
	}
	public void setSubHasDelayFlag(boolean subHasDelayFlag) {
		this.subHasDelayFlag = subHasDelayFlag;
	}
	public String getSubDataForward() {
		return subDataForward;
	}
	public void setSubDataForward(String subDataForward) {
		this.subDataForward = subDataForward;
	}
	public Param_Idle_Request getClientData() {
		return clientData;
	}
	public void setClientData(Param_Idle_Request clientData) {
		this.clientData = clientData;
	}
	public Param_DS2A_InquirySubscriber getInquirySubParam() {
		return inquirySubParam;
	}
	public void setInquirySubParam(Param_DS2A_InquirySubscriber inquirySubParam) {
		this.inquirySubParam = inquirySubParam;
	}
	public int getSubCountServerRequest() {
		return subCountServerRequest;
	}
	public void setSubCountServerRequest(int subCountServerRequest) {
		this.subCountServerRequest = subCountServerRequest;
	}
	public void incrementSubCountServerRequest() {
		this.subCountServerRequest++;
	}
	public void decrementSubCountServerRequest() {
		if(this.subCountServerRequest > 0) {
			this.subCountServerRequest--;
		} else {
			this.subCountServerRequest = 0;
		}
	}
	public DestinaionLog getDestinationLogTemp() {
		return destinationLogTemp;
	}
	public void setDestinationLogTemp(DestinaionLog destinationLogTemp) {
		this.destinationLogTemp = destinationLogTemp;
	}
	public Param_SDF_GetAmfSubProfile getSubRespGetAmfSubProfile() {
		return subRespGetAmfSubProfile;
	}
	public void setSubRespGetAmfSubProfile(Param_SDF_GetAmfSubProfile subRespGetAmfSubProfile) {
		this.subRespGetAmfSubProfile = subRespGetAmfSubProfile;
	}
	public Param_SDF_GetAmfCounter getSubRespGetAmfCounter() {
		return subRespGetAmfCounter;
	}
	public void setSubRespGetAmfCounter(Param_SDF_GetAmfCounter subRespGetAmfCounter) {
		this.subRespGetAmfCounter = subRespGetAmfCounter;
	}
	public Param_SDF_GetSidRatingElement getSubRespGetSidRatingElement() {
		return subRespGetSidRatingElement;
	}
	public void setSubRespGetSidRatingElement(Param_SDF_GetSidRatingElement subRespGetSidRatingElement) {
		this.subRespGetSidRatingElement = subRespGetSidRatingElement;
	}
	public Param_SDF_GetSidNotificationElement getSubRespGetSidNotificationElement() {
		return subRespGetSidNotificationElement;
	}
	public void setSubRespGetSidNotificationElement(Param_SDF_GetSidNotificationElement subRespGetSidNotificationElement) {
		this.subRespGetSidNotificationElement = subRespGetSidNotificationElement;
	}
	public String getDiffTimeout() {
		return diffTimeout;
	}
	public void setDiffTimeout(String diffTimeout) {
		this.diffTimeout = diffTimeout;
	}
	public String getSubRecieveRet() {
		return subRecieveRet;
	}
	public void setSubRecieveRet(String subRecieveRet) {
		this.subRecieveRet = subRecieveRet;
	}
	public String getRmfOffering() {
		return rmfOffering;
	}
	public void setRmfOffering(String rmfOffering) {
		this.rmfOffering = rmfOffering;
	}
	public String getAmfManageCounter() {
		return amfManageCounter;
	}
	public void setAmfManageCounter(String amfManageCounter) {
		this.amfManageCounter = amfManageCounter;
	}
	
	
//	public int getRequestReward() {
//		return requestReward;
//	}
//	public void setRequestReward(int requestReward) {
//		this.requestReward = requestReward;
//	}
//	public void incrementSubRequestReward(){
//		this.requestReward++;
//	}
//	
//	public void decrementSubRequestReward(){
//
//		if(this.requestReward > 0) {
//			this.requestReward--;
//		} else {
//			this.requestReward = 0;
//		}
//		
//	}
}
