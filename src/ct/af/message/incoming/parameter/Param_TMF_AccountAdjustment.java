package ct.af.message.incoming.parameter;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(strict = false)
public class Param_TMF_AccountAdjustment {
	
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
	
	@Path("Result-Code")
	@Attribute(name = "value")
	private String resultCode;
	public String getResultCode() {
		return resultCode;
	}
	
	@Path("Origin-Host")
	@Attribute(name = "value")
	private String originHost;
	public String getOriginHost() {
		return originHost;
	}
	
	@Path("Origin-Realm")
	@Attribute(name = "value")
	private String originRealm;
	public String getOriginRealm() {
		return originRealm;
	}
	
	@Path("Auth-Application-Id")
	@Attribute(name = "value")
	private String authApplicationId;
	public String getAuthApplicationId() {
		return authApplicationId;
	}
	
	@Path("CC-Request-Type")
	@Attribute(name = "value")
	private String ccRequestType;
	public String getCCRequestType() {
		return ccRequestType;
	}
	
	@Path("CC-Request-Number")
	@Attribute(name = "value")
	private String ccRequestNumber;
	public String getCCRequestNumber() {
		return ccRequestNumber;
	}
	
	@Path("Service-Information/IN-Information/Description")
	@Attribute(name = "value")
	private String description;
	public String getDescription() {
		return description;
	}
	
	@ElementList(entry = "Account-Info",inline = true)
	@Path("Service-Information/IN-Information")
	private List<Account_Info> accountInfo;
	public List<Account_Info> getAccountInfo() {
		return accountInfo;
	}
	
	@Path("Service-Information/IN-Information/Customer-Life-Cycle-State")
	@Attribute(name = "value")
	private String customerLifeCycleState;
	public String getCustomerLifeCycleState() {
		return customerLifeCycleState;
	}
	
}
