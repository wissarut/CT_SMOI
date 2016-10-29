package ct.af.message.incoming.parameter;

import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(strict = false)

public class Param_BMP_DispScrScreenNo {
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


	@Path("Body/QuerySubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "CommandId")
	private String commandID;
	public String getCommandID() {
		return commandID;
	}
	
	@Path("Body/QuerySubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "Version")
	private String version;
	public String getVersion() {
		return version;
	}
	
	@Path("Body/QuerySubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "TransactionId")
	private String transactionID;
	public String getTransactionID() {
		return transactionID;
	}
	
	@Path("Body/QuerySubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "SequenceId")
	private String sequenceID;
	public String getSequenceID() {
		return sequenceID;
	}
	
	@Path("Body/QuerySubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "ResultCode", required = false)
	private String resultCode;
	public String getResultCode() {
		return resultCode;
	}

	@Path("Body/QuerySubCallScreenNoResultMsg/ResultHeader/")
	@Element(name = "ResultDesc")
	private String resultDesc;
	public String getResultDesc() {
		return resultDesc;
	}
	
	@Path("Body/QuerySubCallScreenNoResultMsg/QuerySubCallScreenNoResult/")
	@Element(name = "CallScreenType")
	private String CallScreenType;
	public String getCallScreenType() {
		return CallScreenType;
	}
	
	@Path("Body/QuerySubCallScreenNoResultMsg/QuerySubCallScreenNoResult/")
	@Element(name = "CLIRFlag", required = false)
	private String CLIRFlag;
	public String getCLIRFlag() {
		return CLIRFlag;
	}
	
	@Path("Body/QuerySubCallScreenNoResultMsg/QuerySubCallScreenNoResult/")
	@Element(name = "ICSSendSMBlockNum")
	private String ICSSendSMBlockNum;
	public String getICSSendSMBlockNum() {
		return ICSSendSMBlockNum;
	}
	
	@ElementList(entry = "CallScreenNoInfo",inline = true, required = false)
	@Path("Body/QuerySubCallScreenNoResultMsg/QuerySubCallScreenNoResult")
	private List<CallScreenNoInfo> callScreenNoInfo;
	public List<CallScreenNoInfo> getCallScreenNoInfo() {
		return callScreenNoInfo;
	}
	
	@ElementList(entry = "NonCallScreenNoInfo",inline = true, required = false)
	@Path("Body/QuerySubCallScreenNoResultMsg/QuerySubCallScreenNoResult")
	private List<CallScreenNoInfo> nonCallScreenNoInfo;
	public List<CallScreenNoInfo> getNonCallScreenNoInfo() {
		return nonCallScreenNoInfo;
	}
}
