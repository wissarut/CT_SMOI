package ct.af.message.incoming.parameter;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name = "soap:Envelope", strict = false)

public class Param_USMP_InquiryCounter {
	
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
	
	@Element(name = "IsSuccess",required = false)
	@Path("Body/InquiryCounterResponse/InquiryCounterResult/OperationStatus")
	private String isSuccess;
	public String getIsSuccess() {
		return isSuccess;
	}

	@Element(name = "Code",required = false)
	@Path("Body/InquiryCounterResponse/InquiryCounterResult/OperationStatus")
	private String code;
	public String getCode() {
		return code;
	}

	@Element(name = "Description",required = false)
	@Path("Body/InquiryCounterResponse/InquiryCounterResult/OperationStatus")
	private String description;
	public String getDescription() {
		return description;
	}

	@Element(name = "TransactionID",required = false)
	@Path("Body/InquiryCounterResponse/InquiryCounterResult/OperationStatus")
	private String transactionID;
	public String getTransactionID() {
		return transactionID;
	}

	@Element(name = "OrderRef",required = false)
	@Path("Body/InquiryCounterResponse/InquiryCounterResult/OperationStatus")
	private String orderRef;
	public String getOrderRef() {
		return orderRef;
	}
	
	@Element(name = "NumCounter",required = false)
	@Path("Body/InquiryCounterResponse/InquiryCounterResult")
	private String numCounter;
	public String getNumCounter() {
		return numCounter;
	}
	
	@ElementList(entry = "CounterInfo",inline = true)
	@Path("Body/InquiryCounterResponse/InquiryCounterResult/CounterArray")
	private List<CounterInfo> counterInfo;
	public List<CounterInfo> getCounterInfo() {
		return counterInfo;
	}

}
