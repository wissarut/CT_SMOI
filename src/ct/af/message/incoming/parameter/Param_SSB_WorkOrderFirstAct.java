package ct.af.message.incoming.parameter;

import org.json.simple.JSONObject;

import ct.af.enums.EResultCode;

public class Param_SSB_WorkOrderFirstAct {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
	private String resultCode;
	private String developerMessage;

	public Param_SSB_WorkOrderFirstAct() {
		resultCode = null;
		developerMessage = null;
	}
	
	public Param_SSB_WorkOrderFirstAct(JSONObject object) {
		this.resultCode = (String) object.get("resultCode");
		this.developerMessage = (String) object.get("developerMessage");
	}
	
	public boolean isReceived() {
		return isReceived;
	}
	public void setRecieved(boolean isReceived) {
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
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	
}
