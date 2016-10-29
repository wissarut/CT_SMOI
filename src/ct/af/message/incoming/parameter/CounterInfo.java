package ct.af.message.incoming.parameter;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name="CounterInfo", strict = false)
public class CounterInfo {
	
		@Element(name = "Msisdn", required = false)
		private String msisdn;
		
		@Element(name = "ProductNumber", required = false)
		private String productNumber;
		
		@Element(name = "CounterName", required = false)
		private String counterName;
		
		@Element(name = "EParameterReplenishment", required = false)
		private String eParameterReplenishment;

		@Element(name = "PoliteDegree", required = false)
		private String eoliteDegree;
		
		@Element(name = "SyncManagementFlag", required = false)
		private String syncManagementFlag;

		@Element(name = "StartTime", required = false)
		private String startTime;
		
		@Element(name = "LockTime", required = false)
		private String lockTime;

		@Element(name = "CounterState", required = false)
		private String counterState;
		
		@Element(name = "ExpiryTime", required = false)
		private String expiryTime;

		@Element(name = "ExpiryT2", required = false)
		private String expiryT2;
		
		@Element(name = "ExpiryRecurringFlag", required = false)
		private String expiryRecurringFlag;

		@Element(name = "ExpiryNotificationTime", required = false)
		private String expiryNotificationTime;
		
		@Element(name = "PersistentCycleFlag", required = false)
		private String persistentCycleFlag;

		@Element(name = "LifeCycle", required = false)
		private String lifeCycle;
		
		@Element(name = "CycleNumber", required = false)
		private String cycleNumber;

		@Element(name = "TimeResolution", required = false)
		private String timeResolution;
		
		@Element(name = "RollOverFlag", required = false)
		private String rollOverFlag;

		@Element(name = "RefillStopTime", required = false)
		private String refillStopTime;
		
		@Element(name = "ExtendRefillStopFlag", required = false)
		private String extendRefillStopFlag;

		@Element(name = "RefillNotifyTime", required = false)
		private String refillNotifyTime;
		
		@Element(name = "ValueNotifyTime", required = false)
		private String valueNotifyTime;

		@Element(name = "TemplateVersion", required = false)
		private String templateVersion;
		
		@Element(name = "NumChildCounter", required = false)
		private String numChildCounter;

		@Element(name = "StartTimeOper", required = false)
		private String startTimeOper;
		
		@Element(name = "ExpiryTimeOper", required = false)
		private String expiryTimeOper;

		@Element(name = "MarketingName", required = false)
		private String marketingName;
		
		@Element(name = "MainPromotionF", required = false)
		private String mainPromotionF;
		
		@Element(name = "InterlockCode", required = false)
		private String interlockCode;
		
		@Element(name = "FriendIds", required = false)
		private String friendIds;
		
		@Element(name = "SharerIds", required = false)
		private String sharerIds;
		
		@Element(name = "PahIdentity", required = false)
		private String pahIdentity;
		
		@Element(name = "PahProductNo", required = false)
		private String pahProductNo;
		
		@Element(name = "RechargeOnDepletion", required = false)
		private String rechargeOnDepletion;
		
		@Element(name = "Shareable", required = false)
		private String shareable;
		
		@Element(name = "SharingMode", required = false)
		private String sharingMode;
		
		@ElementList(entry = "ChildCounterInfo",inline = true, required = false)
		@Path("ChildCounterArray")
		protected List<ChildCounterInfo> childCounterInfo;
		
		public String getMsisdn() {
			return msisdn;
		}

		public void setMsisdn(String msisdn) {
			this.msisdn = msisdn;
		}

		public String getProductNumber() {
			return productNumber;
		}

		public void setProductNumber(String productNumber) {
			this.productNumber = productNumber;
		}

		public String getCounterName() {
			return counterName;
		}

		public void setCounterName(String counterName) {
			this.counterName = counterName;
		}

		public String geteParameterReplenishment() {
			return eParameterReplenishment;
		}

		public void seteParameterReplenishment(String eParameterReplenishment) {
			this.eParameterReplenishment = eParameterReplenishment;
		}

		public String getEoliteDegree() {
			return eoliteDegree;
		}

		public void setEoliteDegree(String eoliteDegree) {
			this.eoliteDegree = eoliteDegree;
		}

		public String getSyncManagementFlag() {
			return syncManagementFlag;
		}

		public void setSyncManagementFlag(String syncManagementFlag) {
			this.syncManagementFlag = syncManagementFlag;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getLockTime() {
			return lockTime;
		}

		public void setLockTime(String lockTime) {
			this.lockTime = lockTime;
		}

		public String getCounterState() {
			return counterState;
		}

		public void setCounterState(String counterState) {
			this.counterState = counterState;
		}

		public String getExpiryTime() {
			return expiryTime;
		}

		public void setExpiryTime(String expiryTime) {
			this.expiryTime = expiryTime;
		}

		public String getExpiryT2() {
			return expiryT2;
		}

		public void setExpiryT2(String expiryT2) {
			this.expiryT2 = expiryT2;
		}

		public String getExpiryRecurringFlag() {
			return expiryRecurringFlag;
		}

		public void setExpiryRecurringFlag(String expiryRecurringFlag) {
			this.expiryRecurringFlag = expiryRecurringFlag;
		}

		public String getExpiryNotificationTime() {
			return expiryNotificationTime;
		}

		public void setExpiryNotificationTime(String expiryNotificationTime) {
			this.expiryNotificationTime = expiryNotificationTime;
		}

		public String getPersistentCycleFlag() {
			return persistentCycleFlag;
		}

		public void setPersistentCycleFlag(String persistentCycleFlag) {
			this.persistentCycleFlag = persistentCycleFlag;
		}

		public String getLifeCycle() {
			return lifeCycle;
		}

		public void setLifeCycle(String lifeCycle) {
			this.lifeCycle = lifeCycle;
		}

		public String getCycleNumber() {
			return cycleNumber;
		}

		public void setCycleNumber(String cycleNumber) {
			this.cycleNumber = cycleNumber;
		}

		public String getTimeResolution() {
			return timeResolution;
		}

		public void setTimeResolution(String timeResolution) {
			this.timeResolution = timeResolution;
		}

		public String getRollOverFlag() {
			return rollOverFlag;
		}

		public void setRollOverFlag(String rollOverFlag) {
			this.rollOverFlag = rollOverFlag;
		}

		public String getRefillStopTime() {
			return refillStopTime;
		}

		public void setRefillStopTime(String refillStopTime) {
			this.refillStopTime = refillStopTime;
		}

		public String getExtendRefillStopFlag() {
			return extendRefillStopFlag;
		}

		public void setExtendRefillStopFlag(String extendRefillStopFlag) {
			this.extendRefillStopFlag = extendRefillStopFlag;
		}

		public String getRefillNotifyTime() {
			return refillNotifyTime;
		}

		public void setRefillNotifyTime(String refillNotifyTime) {
			this.refillNotifyTime = refillNotifyTime;
		}

		public String getValueNotifyTime() {
			return valueNotifyTime;
		}

		public void setValueNotifyTime(String valueNotifyTime) {
			this.valueNotifyTime = valueNotifyTime;
		}

		public String getTemplateVersion() {
			return templateVersion;
		}

		public void setTemplateVersion(String templateVersion) {
			this.templateVersion = templateVersion;
		}

		public String getNumChildCounter() {
			return numChildCounter;
		}

		public void setNumChildCounter(String numChildCounter) {
			this.numChildCounter = numChildCounter;
		}

		public String getStartTimeOper() {
			return startTimeOper;
		}

		public void setStartTimeOper(String startTimeOper) {
			this.startTimeOper = startTimeOper;
		}

		public String getExpiryTimeOper() {
			return expiryTimeOper;
		}

		public void setExpiryTimeOper(String expiryTimeOper) {
			this.expiryTimeOper = expiryTimeOper;
		}

		public String getMarketingName() {
			return marketingName;
		}

		public void setMarketingName(String marketingName) {
			this.marketingName = marketingName;
		}

		public String getMainPromotionF() {
			return mainPromotionF;
		}

		public void setMainPromotionF(String mainPromotionF) {
			this.mainPromotionF = mainPromotionF;
		}

		public String getInterlockCode() {
			return interlockCode;
		}

		public void setInterlockCode(String interlockCode) {
			this.interlockCode = interlockCode;
		}

		public String getFriendIds() {
			return friendIds;
		}

		public void setFriendIds(String friendIds) {
			this.friendIds = friendIds;
		}

		public String getSharerIds() {
			return sharerIds;
		}

		public void setSharerIds(String sharerIds) {
			this.sharerIds = sharerIds;
		}

		public String getPahIdentity() {
			return pahIdentity;
		}

		public void setPahIdentity(String pahIdentity) {
			this.pahIdentity = pahIdentity;
		}

		public String getPahProductNo() {
			return pahProductNo;
		}

		public void setPahProductNo(String pahProductNo) {
			this.pahProductNo = pahProductNo;
		}

		public String getRechargeOnDepletion() {
			return rechargeOnDepletion;
		}

		public void setRechargeOnDepletion(String rechargeOnDepletion) {
			this.rechargeOnDepletion = rechargeOnDepletion;
		}

		public String getShareable() {
			return shareable;
		}

		public void setShareable(String shareable) {
			this.shareable = shareable;
		}

		public String getSharingMode() {
			return sharingMode;
		}

		public void setSharingMode(String sharingMode) {
			this.sharingMode = sharingMode;
		}

		public List<ChildCounterInfo> getChildCounterInfo() {
			return childCounterInfo;
		}
}
