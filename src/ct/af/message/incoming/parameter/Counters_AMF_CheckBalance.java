package ct.af.message.incoming.parameter;

public class Counters_AMF_CheckBalance {

	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;

	public boolean isReceived() {
		return isReceived;
	}
	public void setRecive(boolean isReceived) {
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
	private String counterID;
	private String currentBalance;
	private String activationDate;
	private String expiryTime;
	private String upperLimit;
	private String lowerLimit;
	private String serviceId_resourceId_reourceName;
	private String productNo;
	private String promoNameNotifElem;

	public String getCounterID() {
		return counterID;
	}
	public String getCurrentBalance() {
		return currentBalance;
	}
	public String getActivationDate() {
		return activationDate;
	}
	public String getExpiryTime() {
		return expiryTime;
	}
	public String getUpperLimit() {
		return upperLimit;
	}
	public String getLowerLimit() {
		return lowerLimit;
	}
	public void setCounterID(String counterID) {
		this.counterID = counterID;
	}
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}
	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}
	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}
	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public String getServiceId_resourceId_reourceName() {
		return serviceId_resourceId_reourceName;
	}
	public void setServiceId_resourceId_reourceName(String serviceId_resourceId_reourceName) {
		this.serviceId_resourceId_reourceName = serviceId_resourceId_reourceName;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getPromoNameNotifElem() {
		return promoNameNotifElem;
	}
	public void setPromoNameNotifElem(String promoNameNotifElem) {
		this.promoNameNotifElem = promoNameNotifElem;
	}
	
}
