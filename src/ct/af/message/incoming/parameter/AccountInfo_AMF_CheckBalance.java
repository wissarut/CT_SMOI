package ct.af.message.incoming.parameter;

public class AccountInfo_AMF_CheckBalance {

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
	
	private String state;
	private String activeStopTime;
	private String suspendStopTime;
	private String disableStopTime;
	private String terminateStopTime;
	private String maxValidity;
	private String fraudFlag;
	private String pinFraudFlag;
	private String dpMode;
	private String userRequestSuspend;

	public String getState() {
		return state;
	}
	public String getActiveStopTime() {
		return activeStopTime;
	}
	public String getSuspendStopTime() {
		return suspendStopTime;
	}
	public String getDisableStopTime() {
		return disableStopTime;
	}
	public String getTerminateStopTime() {
		return terminateStopTime;
	}
	public String getMaxValidity() {
		return maxValidity;
	}
	public String getFraudFlag() {
		return fraudFlag;
	}
	public String getPinFraudFlag() {
		return pinFraudFlag;
	}
	public String getDpMode() {
		return dpMode;
	}
	public String getUserRequestSuspend() {
		return userRequestSuspend;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setActiveStopTime(String activeStopTime) {
		this.activeStopTime = activeStopTime;
	}
	public void setSuspendStopTime(String suspendStopTime) {
		this.suspendStopTime = suspendStopTime;
	}
	public void setDisableStopTime(String disableStopTime) {
		this.disableStopTime = disableStopTime;
	}
	public void setTerminateStopTime(String terminateStopTime) {
		this.terminateStopTime = terminateStopTime;
	}
	public void setMaxValidity(String maxValidity) {
		this.maxValidity = maxValidity;
	}
	public void setFraudFlag(String fraudFlag) {
		this.fraudFlag = fraudFlag;
	}
	public void setPinFraudFlag(String pinFraudFlag) {
		this.pinFraudFlag = pinFraudFlag;
	}
	public void setDpMode(String dpMode) {
		this.dpMode = dpMode;
	}
	public void setUserRequestSuspend(String userRequestSuspend) {
		this.userRequestSuspend = userRequestSuspend;
	}
	
}
