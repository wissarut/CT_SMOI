package ct.af.message.incoming.parameter;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ct.af.enums.EResultCode;

public class Param_SDF_GetSidNotificationElement {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
	private String resultCode;
	private String developerMessage;
	private List<SDFSidNotificationElement> sidNotificationElement = new ArrayList<SDFSidNotificationElement>();

	public Param_SDF_GetSidNotificationElement() {
		resultCode = null;
		developerMessage = null;
	}
	
	public Param_SDF_GetSidNotificationElement(JSONObject object) {
		this.resultCode = (String) object.get("resultCode");
		this.developerMessage = (String) object.get("resultDescription");

		JSONArray sidNotificationElement = (JSONArray) object.get("sidNotificationSpec");

		for(int i = 0;i < sidNotificationElement.size();i++) {
			
			SDFSidNotificationElement sdfSidNotificationElement = new SDFSidNotificationElement();

			JSONObject sidNotification = (JSONObject) sidNotificationElement.get(i);
			
			sdfSidNotificationElement.setDn(sidNotification.get("dn").toString());
			sdfSidNotificationElement.setObjectClass(sidNotification.get("objectClass").toString());
			
			if(sidNotification.get("sidTextMessage") != null) {
				sdfSidNotificationElement.setSidTextMessage(sidNotification.get("sidTextMessage").toString());
			}
			if(sidNotification.get("sidTextUnicode") != null) {
				sdfSidNotificationElement.setSidTextUnicode(sidNotification.get("sidTextUnicode").toString());
			}
			
			this.sidNotificationElement.add(sdfSidNotificationElement);
			
		}
		
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

	public List<SDFSidNotificationElement> getSidNotificationElement() {
		return sidNotificationElement;
	}

	public void setSidNotificationElement(List<SDFSidNotificationElement> sidNotificationElement) {
		this.sidNotificationElement = sidNotificationElement;
	}

}
