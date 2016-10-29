package ct.af.message.incoming.parameter;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name = "soap:Envelope", strict = false)

public class Param_USMP_FirstActivate {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
	public boolean isReceived() {
		return isReceived;
	}
	public void setReceived(boolean isReceived) {
		this.isReceived = isReceived;
	}
	public boolean isNotMissing() {
		return isNotMissing;
	}
	public void setNotMissing(boolean isNotMissing) {
		this.isNotMissing = isNotMissing;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public EResultCode getRespResultCode() {
		return respResultCode;
	}
	public void setRespResultCode(EResultCode respResultCode) {
		this.respResultCode = respResultCode;
	}
	
	@Element(name = "IsSuccess", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/OperationStatus")
	private String isSuccess = "false";
	public String getIsSuccess() {
		return isSuccess;
	}

	@Element(name = "Code", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/OperationStatus")
	private String code;
	public String getCode() {
		return code;
	}

	@Element(name = "Description")
	@Path("Body/FirstActivateResponse/FirstActivateResult/OperationStatus")
	private String description;
	public String getDescription() {
		return description;
	}

	@Element(name = "TransactionID")
	@Path("Body/FirstActivateResponse/FirstActivateResult/OperationStatus")
	private String transactionID;
	public String getTransactionID() {
		return transactionID;
	}

	@Element(name = "OrderRef", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/OperationStatus")
	private String orderRef;
	public String getOrderRef() {
		return orderRef;
	}

	@Element(name = "NumCounter", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult")
	private String numCounter;
	public String getNumCounter() {
		return numCounter;
	}
	
	@ElementList(entry = "CounterInfo",inline = true)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray")
	private List<CounterInfo> counterInfo;
	public List<CounterInfo> getCounterInfo() {
		return counterInfo;
	}
	
	/*
	@ElementList(entry = "Msisdn", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> msisdn;
	public List<CounterInfo> getMsisdn() {
		return msisdn;
	}
	
	@ElementList(entry = "MainPromotionF", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> mainPromotionF;
	public List<CounterInfo> getMainPromotionF() {
		return mainPromotionF;
	}
	
	@ElementList(entry = "ProductNumber", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> productNumber;
	public List<CounterInfo> getProductNumber() {
		return productNumber;
	}
	
	@ElementList(entry = "CounterName", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> counterName;
	public List<CounterInfo> getCounterName() {
		return counterName;
	}
	
	@ElementList(entry = "EParameterReplenishment", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> EParameterReplenishment;
	public List<CounterInfo> getEParameterReplenishment() {
		return EParameterReplenishment;
	}
	
	@ElementList(entry = "SyncManagementFlag", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> SyncManagementFlag;
	public List<CounterInfo> getSyncManagementFlag() {
		return SyncManagementFlag;
	}
	
	@ElementList(entry = "StartTime", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> StartTime;
	public List<CounterInfo> getStartTime() {
		return StartTime;
	}
	
	@ElementList(entry = "LockTime", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> LockTime;
	public List<CounterInfo> getLockTime() {
		return LockTime;
	}
	
	@ElementList(entry = "PoliteDegree", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> PoliteDegree;
	public List<CounterInfo> getPoliteDegree() {
		return PoliteDegree;
	}
	
	@ElementList(entry = "CounterState", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> CounterState;
	public List<CounterInfo> getCounterState() {
		return CounterState;
	}
	@ElementList(entry = "ExpiryTime", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> ExpiryTime;
	public List<CounterInfo> getExpiryTime() {
		return ExpiryTime;
	}
	
	@ElementList(entry = "ExpiryT2", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> ExpiryT2;
	public List<CounterInfo> getExpiryT2() {
		return ExpiryT2;
	}
	
	@ElementList(entry = "ExpiryRecurringFlag", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> ExpiryRecurringFlag;
	public List<CounterInfo> getExpiryRecurringFlag() {
		return ExpiryRecurringFlag;
	}
	
	@ElementList(entry = "ExpiryNotificationTime", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> ExpiryNotificationTime;
	public List<CounterInfo> getExpiryNotificationTime() {
		return ExpiryNotificationTime;
	}
	
	@ElementList(entry = "PersistentCycleFlag", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> PersistentCycleFlag;
	public List<CounterInfo> getPersistentCycleFlag() {
		return PersistentCycleFlag;
	}
	
	@ElementList(entry = "LifeCycle", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> LifeCycle;
	public List<CounterInfo> getLifeCycle() {
		return LifeCycle;
	}
	
	@ElementList(entry = "CycleNumber", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> CycleNumber;
	public List<CounterInfo> getCycleNumber() {
		return CycleNumber;
	}
	
	@ElementList(entry = "TimeResolution", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> TimeResolution;
	public List<CounterInfo> getTimeResolution() {
		return TimeResolution;
	}
	
	@ElementList(entry = "RollOverFlag", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> RollOverFlag;
	public List<CounterInfo> getRollOverFlag() {
		return RollOverFlag;
	}
	
	@ElementList(entry = "RefillStopTime", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> RefillStopTime;
	public List<CounterInfo> getRefillStopTime() {
		return RefillStopTime;
	}
	
	@ElementList(entry = "ExtendRefillStopFlag", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> ExtendRefillStopFlag;
	public List<CounterInfo> getExtendRefillStopFlag() {
		return ExtendRefillStopFlag;
	}
	
	@ElementList(entry = "RefillNotifyTime", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> RefillNotifyTime;
	public List<CounterInfo> getRefillNotifyTime() {
		return RefillNotifyTime;
	}
	
	@ElementList(entry = "ExtendRefillStopFlag", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> ValueNotifyTime;
	public List<CounterInfo> getValueNotifyTime() {
		return ValueNotifyTime;
	}
	
	@ElementList(entry = "ExtendRefillStopFlag", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> TemplateVersion;
	public List<CounterInfo> getTemplateVersion() {
		return TemplateVersion;
	}
	
	@ElementList(entry = "NumChildCounter", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> NumChildCounter;
	public List<CounterInfo> getNumChildCounter() {
		return NumChildCounter;
	}
	
	@ElementList(entry = "StartTimeOper", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> StartTimeOper;
	public List<CounterInfo> getStartTimeOper() {
		return StartTimeOper;
	}
	
	@ElementList(entry = "ExtendRefillStopFlag", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> ExpiryTimeOper;
	public List<CounterInfo> getExpiryTimeOper() {
		return ExpiryTimeOper;
	}
	
	@ElementList(entry = "InterlockCode", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> InterlockCode;
	public List<CounterInfo> getInterlockCode() {
		return InterlockCode;
	}
	
	@ElementList(entry = "FriendIds", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> FriendIds;
	public List<CounterInfo> getFriendIds() {
		return FriendIds;
	}
	
	@ElementList(entry = "SharerIds", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> SharerIds;
	public List<CounterInfo> getSharerIds() {
		return SharerIds;
	}
	
	@ElementList(entry = "PahIdentity", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> PahIdentity;
	public List<CounterInfo> getPahIdentity() {
		return PahIdentity;
	}
	
	@ElementList(entry = "ExtendRefillStopFlag", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> PahProductNo;
	public List<CounterInfo> getPahProductNo() {
		return PahProductNo;
	}
	
	@ElementList(entry = "RechargeOnDepletion", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> RechargeOnDepletion;
	public List<CounterInfo> getRechargeOnDepletion() {
		return RechargeOnDepletion;
	}
	
	@ElementList(entry = "Shareable", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> Shareable;
	public List<CounterInfo> getShareable() {
		return Shareable;
	}
	
	@ElementList(entry = "SharingMode", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> SharingMode;
	public List<CounterInfo> getSharingMode() {
		return SharingMode;
	}
	
	
	@ElementList(entry = "ChildCounterArray", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo")
	private List<CounterInfo> ChildCounterArray;
	public List<CounterInfo> getChildCounterArray() {
		return ChildCounterArray;
	}
	
	@ElementList(entry = "ChildCounterInfo", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo/ChildCounterArray")
	private List<CounterInfo> ChildCounterInfo;
	public List<CounterInfo> getChildCounterInfo() {
		return ChildCounterInfo;
	}
	
	@ElementList(entry = "CounterId", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo/ChildCounterArray")
	private List<CounterInfo> CounterId;
	public List<CounterInfo> getCounterId() {
		return CounterId;
	}
	
	@ElementList(entry = "WappId", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo/ChildCounterArray")
	private List<CounterInfo> WappId;
	public List<CounterInfo> getWappId() {
		return WappId;
	}
	
	@ElementList(entry = "EParameterServiceCharging", required = false)
	@Path("Body/FirstActivateResponse/FirstActivateResult/CounterArray/CounterInfo/ChildCounterArray")
	private List<CounterInfo> EParameterServiceCharging;
	public List<CounterInfo> getEParameterServiceCharging() {
		return EParameterServiceCharging;
	}
	*/
	
	
}
