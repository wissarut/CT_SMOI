package ct.af.message.incoming.parameter;

public class AccountInfo_AMF_ModifyCounters {
	
	private String state;
	private String activeStopTime;
	private String suspendStopTime;
	private String disableStopTime;
	private String terminateStopTime;

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
	
}
