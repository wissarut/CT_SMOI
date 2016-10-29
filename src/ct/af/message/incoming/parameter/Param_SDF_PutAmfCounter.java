package ct.af.message.incoming.parameter;

import org.json.simple.JSONObject;

import ct.af.enums.EResultCode;

public class Param_SDF_PutAmfCounter {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
	private String resultCode;
	private String resultDescription;
	private String matchedDN;
	private String diagnosticMessage;
	
	public Param_SDF_PutAmfCounter() {
		resultCode = null;
		resultDescription = null;
	}
	
	public Param_SDF_PutAmfCounter(JSONObject object) {
		this.resultCode = (String) object.get("resultCode");
		this.resultDescription = (String) object.get("resultDescription");
	}
	
	
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
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	public String getMatchedDN() {
		return matchedDN;
	}
	public void setMatchedDN(String matchedDN) {
		this.matchedDN = matchedDN;
	}
	public String getDiagnosticMessage() {
		return diagnosticMessage;
	}
	public void setDiagnosticMessage(String diagnosticMessage) {
		this.diagnosticMessage = diagnosticMessage;
	}
	
	

}