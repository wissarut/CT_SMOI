package ct.af.message.incoming.parameter;

import java.util.ArrayList;

public class SDFAmfCounter {
	
	private String dn;
	private String counterId;
	private String objectClass;
	private String counterType;
	private String counterValue = "0";
	private String counterValueType;
	private String activationDate;
	private String counterConsumeVector;
	private String firstModifiedTime;
	private String lastModifiedTime;
	private String expiryTime;
	private String expiryGracePeriod;
	private String preExpiryThr;
	private String postExpiryThr;
	private String expiryNotifyTime;
	private String counterLowerLimit = "0";
	private String counterUpperLimit;
	private String lifecycleUnit;
	private String lifecycleNo;
	private String recurringLifecycle;
	private String persistCycle;
	private String counterRollover;
	private String refillStopTime;
	private String refillStopExt;
	private String refillNotifyTime;
	private String replenishValue;
	private String counterThreshold;
	private String activePeriod;
	private String amfReserve;
	private String amfReserveExpiry;
	private String counterReservations;
	private String counterHistory;
	private String counterState;
	private String counterUnit;
	private String ds3notificationPolicyControl;
	private String ds3deselectPolicyControl;
	private String shareable;
	private ArrayList<String> personalization = new ArrayList<String>();

	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public String getCounterId() {
		return counterId;
	}
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}
	public String getObjectClass() {
		return objectClass;
	}
	public void setObjectClass(String objectClass) {
		this.objectClass = objectClass;
	}
	public String getCounterType() {
		return counterType;
	}
	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}
	public String getCounterValue() {
		return counterValue;
	}
	public void setCounterValue(String counterValue) {
		this.counterValue = counterValue;
	}
	public String getCounterValueType() {
		return counterValueType;
	}
	public void setCounterValueType(String counterValueType) {
		this.counterValueType = counterValueType;
	}
	public String getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}
	public String getCounterConsumeVector() {
		return counterConsumeVector;
	}
	public void setCounterConsumeVector(String counterConsumeVector) {
		this.counterConsumeVector = counterConsumeVector;
	}
	public String getFirstModifiedTime() {
		return firstModifiedTime;
	}
	public void setFirstModifiedTime(String firstModifiedTime) {
		this.firstModifiedTime = firstModifiedTime;
	}
	public String getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public String getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}
	public String getExpiryGracePeriod() {
		return expiryGracePeriod;
	}
	public void setExpiryGracePeriod(String expiryGracePeriod) {
		this.expiryGracePeriod = expiryGracePeriod;
	}
	public String getPreExpiryThr() {
		return preExpiryThr;
	}
	public void setPreExpiryThr(String preExpiryThr) {
		this.preExpiryThr = preExpiryThr;
	}
	public String getPostExpiryThr() {
		return postExpiryThr;
	}
	public void setPostExpiryThr(String postExpiryThr) {
		this.postExpiryThr = postExpiryThr;
	}
	public String getExpiryNotifyTime() {
		return expiryNotifyTime;
	}
	public void setExpiryNotifyTime(String expiryNotifyTime) {
		this.expiryNotifyTime = expiryNotifyTime;
	}
	public String getCounterLowerLimit() {
		return counterLowerLimit;
	}
	public void setCounterLowerLimit(String counterLowerLimit) {
		this.counterLowerLimit = counterLowerLimit;
	}
	public String getCounterUpperLimit() {
		return counterUpperLimit;
	}
	public void setCounterUpperLimit(String counterUpperLimit) {
		this.counterUpperLimit = counterUpperLimit;
	}
	public String getLifecycleUnit() {
		return lifecycleUnit;
	}
	public void setLifecycleUnit(String lifecycleUnit) {
		this.lifecycleUnit = lifecycleUnit;
	}
	public String getLifecycleNo() {
		return lifecycleNo;
	}
	public void setLifecycleNo(String lifecycleNo) {
		this.lifecycleNo = lifecycleNo;
	}
	public String getRecurringLifecycle() {
		return recurringLifecycle;
	}
	public void setRecurringLifecycle(String recurringLifecycle) {
		this.recurringLifecycle = recurringLifecycle;
	}
	public String getPersistCycle() {
		return persistCycle;
	}
	public void setPersistCycle(String persistCycle) {
		this.persistCycle = persistCycle;
	}
	public String getCounterRollover() {
		return counterRollover;
	}
	public void setCounterRollover(String counterRollover) {
		this.counterRollover = counterRollover;
	}
	public String getRefillStopTime() {
		return refillStopTime;
	}
	public void setRefillStopTime(String refillStopTime) {
		this.refillStopTime = refillStopTime;
	}
	public String getRefillStopExt() {
		return refillStopExt;
	}
	public void setRefillStopExt(String refillStopExt) {
		this.refillStopExt = refillStopExt;
	}
	public String getRefillNotifyTime() {
		return refillNotifyTime;
	}
	public void setRefillNotifyTime(String refillNotifyTime) {
		this.refillNotifyTime = refillNotifyTime;
	}
	public String getReplenishValue() {
		return replenishValue;
	}
	public void setReplenishValue(String replenishValue) {
		this.replenishValue = replenishValue;
	}
	public String getCounterThreshold() {
		return counterThreshold;
	}
	public void setCounterThreshold(String counterThreshold) {
		this.counterThreshold = counterThreshold;
	}
	public String getActivePeriod() {
		return activePeriod;
	}
	public void setActivePeriod(String activePeriod) {
		this.activePeriod = activePeriod;
	}
	public String getAmfReserve() {
		return amfReserve;
	}
	public void setAmfReserve(String amfReserve) {
		this.amfReserve = amfReserve;
	}
	public String getAmfReserveExpiry() {
		return amfReserveExpiry;
	}
	public void setAmfReserveExpiry(String amfReserveExpiry) {
		this.amfReserveExpiry = amfReserveExpiry;
	}
	public String getCounterReservations() {
		return counterReservations;
	}
	public void setCounterReservations(String counterReservations) {
		this.counterReservations = counterReservations;
	}
	public String getCounterHistory() {
		return counterHistory;
	}
	public void setCounterHistory(String counterHistory) {
		this.counterHistory = counterHistory;
	}
	public String getCounterState() {
		return counterState;
	}
	public void setCounterState(String counterState) {
		this.counterState = counterState;
	}
	public String getCounterUnit() {
		return counterUnit;
	}
	public void setCounterUnit(String counterUnit) {
		this.counterUnit = counterUnit;
	}
	public String getDs3notificationPolicyControl() {
		return ds3notificationPolicyControl;
	}
	public void setDs3notificationPolicyControl(String ds3notificationPolicyControl) {
		this.ds3notificationPolicyControl = ds3notificationPolicyControl;
	}
	public String getDs3deselectPolicyControl() {
		return ds3deselectPolicyControl;
	}
	public void setDs3deselectPolicyControl(String ds3deselectPolicyControl) {
		this.ds3deselectPolicyControl = ds3deselectPolicyControl;
	}
	public String getShareable() {
		return shareable;
	}
	public void setShareable(String shareable) {
		this.shareable = shareable;
	}
	public ArrayList<String> getPersonalization() {
		return personalization;
	}
	public void addPersonalization(String personalization) {
		this.personalization.add(personalization);
	}
	
}
