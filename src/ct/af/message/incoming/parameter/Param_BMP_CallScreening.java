package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(strict = false)
public class Param_BMP_CallScreening {
	
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

	@Path("Body/ManSubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "CommandId")
	private String commandID;
	public String getCommandID() {
		return commandID;
	}
	
	@Path("Body/ManSubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "Version")
	private String version;
	public String getVersion() {
		return version;
	}
	
	@Path("Body/ManSubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "TransactionId")
	private String transactionID;
	public String getTransactionID() {
		return transactionID;
	}
	
	@Path("Body/ManSubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "SequenceId")
	private String sequenceID;
	public String getSequenceID() {
		return sequenceID;
	}
	
	@Path("Body/ManSubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "ResultCode", required = false)
	private String resultCode;
	public String getResultCode() {
		return resultCode;
	}

	@Path("Body/ManSubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "ResultDesc")
	private String resultDesc;
	public String getResultDesc() {
		return resultDesc;
	}

}
