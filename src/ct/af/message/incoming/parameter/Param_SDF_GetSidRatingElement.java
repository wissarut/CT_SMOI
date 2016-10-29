package ct.af.message.incoming.parameter;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ct.af.enums.EResultCode;

public class Param_SDF_GetSidRatingElement {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
	private String resultCode;
	private String developerMessage;
	private List<SDFSidRatingElement> sidRatingElement = new ArrayList<SDFSidRatingElement>();

	public Param_SDF_GetSidRatingElement() {
		resultCode = null;
		developerMessage = null;
	}
	
	public Param_SDF_GetSidRatingElement(JSONObject object) {
		this.resultCode = (String) object.get("resultCode");
		this.developerMessage = (String) object.get("resultDescription");
		
		JSONArray sidRatingElement = (JSONArray) object.get("sidRatingElement");
		
		for(int i = 0;i < sidRatingElement.size();i++) {
			
			SDFSidRatingElement sdfSidRatingElement = new SDFSidRatingElement();

			JSONObject sidRating = (JSONObject) sidRatingElement.get(i);
			
			sdfSidRatingElement.setDn(sidRating.get("dn").toString());
			sdfSidRatingElement.setObjectClass(sidRating.get("objectClass").toString());
			sdfSidRatingElement.setSidRatingRuleXML(sidRating.get("sidRatingRuleXML").toString());
			sdfSidRatingElement.setSidGlobalRatingInfoDN(sidRating.get("sidGlobalRatingInfoDN").toString());
			sdfSidRatingElement.setEffectiveDate(sidRating.get("effectiveDate").toString());
			sdfSidRatingElement.setExpirationDate(sidRating.get("expirationDate").toString());
			sdfSidRatingElement.setActivePeriod(sidRating.get("activePeriod").toString());
			sdfSidRatingElement.setPriority(sidRating.get("priority").toString());
			sdfSidRatingElement.setSidPrice(sidRating.get("sidPrice").toString());
			sdfSidRatingElement.setSidBillingInfo(sidRating.get("sidBillingInfo").toString());
			sdfSidRatingElement.setSidBasicPrice(sidRating.get("sidBasicPrice").toString());
			sdfSidRatingElement.setSidValidTimeUnits(sidRating.get("sidValidTimeUnits").toString());
			sdfSidRatingElement.setSidValidInputOctets(sidRating.get("sidValidInputOctets").toString());
			sdfSidRatingElement.setSidValidOutputOctets(sidRating.get("sidValidOutputOctets").toString());
			sdfSidRatingElement.setSidValidTotalOctets(sidRating.get("sidValidTotalOctets").toString());
			sdfSidRatingElement.setSidCurrencyCode(sidRating.get("sidCurrencyCode").toString());
			sdfSidRatingElement.setSidScaleFactor(sidRating.get("sidScaleFactor").toString());
			sdfSidRatingElement.setSidRateElement(sidRating.get("sidRateElement").toString());
			sdfSidRatingElement.setSidAdviceOfChargeNotif(sidRating.get("sidAdviceOfChargeNotif").toString());
			sdfSidRatingElement.setSidFinalUnitRedirect(sidRating.get("sidFinalUnitRedirect").toString());
			sdfSidRatingElement.setSidSyncMgmt(sidRating.get("sidSyncMgmt").toString());
			sdfSidRatingElement.setSidSyncMgmt(sidRating.get("sidChargeableCounters").toString());
			sdfSidRatingElement.setShareable(sidRating.get("shareable").toString());
			
			this.sidRatingElement.add(sdfSidRatingElement);
			
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

	public List<SDFSidRatingElement> getSidRatingElement() {
		return sidRatingElement;
	}

	public void setSidRatingElement(List<SDFSidRatingElement> sidRatingElement) {
		this.sidRatingElement = sidRatingElement;
	}

}
