package ct.af.message.incoming.parameter;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ct.af.enums.EResultCode;

public class Param_SDF_GetAmfSubProfile {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
	private String resultCode;
	private String developerMessage;
	private List<SDFAmfSubProfile> amfSubProfile = new ArrayList<SDFAmfSubProfile>();

	public Param_SDF_GetAmfSubProfile() {
		resultCode = null;
		developerMessage = null;
	}
	
	public Param_SDF_GetAmfSubProfile(JSONObject object) {
		
		this.resultCode = (String) object.get("resultCode");
		this.developerMessage = (String) object.get("resultDescription");

		JSONArray amfSubProfile = (JSONArray) object.get("amfSubProfile");
		
		for(int i = 0;i < amfSubProfile.size();i++) {
			
			SDFAmfSubProfile sdfSubProfile = new SDFAmfSubProfile();
		
			JSONObject counter = (JSONObject) amfSubProfile.get(i);
			
			sdfSubProfile.setDn(counter.get("dn").toString());
			
			sdfSubProfile.setSubdata(counter.get("subdata").toString());
			sdfSubProfile.setObjectClass(counter.get("objectClass").toString());
			sdfSubProfile.setSubscriptionState(counter.get("subscriptionState").toString());
			sdfSubProfile.setMeteringMethod(counter.get("meteringMethod").toString());
			sdfSubProfile.setRegistrationDate(counter.get("registrationDate").toString());
			sdfSubProfile.setExpirationDate(counter.get("expirationDate").toString());
			sdfSubProfile.setFirstModifiedTime(counter.get("firstModifiedTime").toString());
			sdfSubProfile.setLastModifiedTime(counter.get("lastModifiedTime").toString());
			sdfSubProfile.setAmfProductRunningNo(counter.get("amfProductRunningNo").toString());
			
			this.amfSubProfile.add(sdfSubProfile);
			
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

	public List<SDFAmfSubProfile> getAmfSubProfile() {
		return amfSubProfile;
	}

	public void setAmfSubProfile(List<SDFAmfSubProfile> amfSubProfile) {
		this.amfSubProfile = amfSubProfile;
	}

}
