package ct.af.message.incoming.parameter;

import ct.af.enums.EResultCode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Param_SDF_GetAmfCounter {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
	private String resultCode;
	private String developerMessage;
	private List<SDFAmfCounter> amfCounter = new ArrayList<SDFAmfCounter>();

	public Param_SDF_GetAmfCounter() {
		resultCode = null;
		developerMessage = null;
	}
	
	public Param_SDF_GetAmfCounter(JSONObject object) {
		
		this.resultCode = (String) object.get("resultCode");
		this.developerMessage = (String) object.get("resultDescription");
		
		JSONArray amfCounter = (JSONArray)object.get("amfCounter");
		
		for(int i = 0;i < amfCounter.size();i++) {
			
			SDFAmfCounter sdfCounter = new SDFAmfCounter();

			JSONObject counter = (JSONObject) amfCounter.get(i);
		
			sdfCounter.setDn(counter.get("dn").toString());
			sdfCounter.setCounterId(counter.get("counterId").toString());
			sdfCounter.setCounterType(counter.get("counterType").toString());
			sdfCounter.setCounterValue(counter.get("counterValue").toString());
			sdfCounter.setRefillStopTime(counter.get("refillStopTime").toString());
			sdfCounter.setCounterLowerLimit(counter.get("counterLowerLimit").toString());
//			JSONArray personalization = (JSONArray) counter.get("personalization");
//
//			Iterator<String> iterator = personalization.iterator();
//			
//			while (iterator.hasNext()) {
//				
//				//if(StringUtils.isNotBlank(iterator.next())) {
//					sdfCounter.addPersonalization(iterator.next());
//				//}
//			
//			}
//			
//			AppLog.d("Personalization : "+sdfCounter.getPersonalization().toString());

			this.amfCounter.add(sdfCounter);
			
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

	public List<SDFAmfCounter> getAmfCounter() {
		return amfCounter;
	}

	public void setAmfCounter(List<SDFAmfCounter> amfCounter) {
		this.amfCounter = amfCounter;
	}
	
	
}
