package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(strict = false)

public class Param_SRFC_SpecializedResource {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
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
	
	@Path("Session-Id")
	@Attribute(name = "value")
	private String sessionID;
	public String getSessionID() {
		return sessionID;
	}
	
	@Path("Auth-Application-Id")
	@Attribute(name = "value")
	private String authApplicationID;
	public String getAuthApplicationID() {
		return authApplicationID;
	}
	
	@Path("Auth-Request-Type")
	@Attribute(name = "value")
	private String authRequestType;
	public String getAuthRequestType() {
		return authRequestType;
	}
	
	@Path("Result-Code")
	@Attribute(name = "value")
	private String resultCode;
	public String getResultCode() {
		return resultCode;
	}
	
	@Path("Origin-Host")
	@Attribute(name = "value")
	private String originHost;
	public String getOriginHost () {
		return originHost;
	}
	
	@Path("Origin-Realm")
	@Attribute(name = "value")
	private String originRealm;
	public String getOriginRealm() {
		return originRealm;
	}
		
	@Path("TORO-UI-Overwriting-Info/Overwriting-Type")
	@Attribute(name = "value", required = false)
	private String overwritingType;
	public String getOverwritingType() {
		return overwritingType;
	}
	
	@Path("TORO-UI-Overwriting-Info/Overwriting-Data")
	@Attribute(name = "value", required = false)
	private String overwritingData;
	public String getOverwritingData() {
		return overwritingData;
	}
	
	@Path("Redirect-Address")
	@Attribute(name = "value", required = false)
	private String redirectAddress;
	public String getRedirectAddress() {
		return redirectAddress;
	}
	
	@Path("Error-Message")
	@Attribute(name = "value", required = false)
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Path("Failed-Parameter")
	@Attribute(name = "value", required = false)
	private String failedParameter;
	public String getFailedParameter() {
		return failedParameter;
	}
}
