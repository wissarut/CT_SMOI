package ct.af.message.incoming.parameter;

import org.json.simple.JSONObject;

import ct.af.enums.EResultCode;

public class Param_RMF_RewardAdjustment {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
	private String resultCode;
	private String developerMessage;
	
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
	
	public Param_RMF_RewardAdjustment(JSONObject object) {
		resultCode = (String) object.get("resultCode");
		developerMessage = (String) object.get("developerMessage");
	}
	public String getResultCode() {
		return resultCode;
	}
	
	public String getDeveloperMessage() {
		return developerMessage;
	}

}
