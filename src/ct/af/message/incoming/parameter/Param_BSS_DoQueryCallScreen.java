package ct.af.message.incoming.parameter;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name = "soapenv:Envelope", strict = false)
public class Param_BSS_DoQueryCallScreen {
	
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
	
	@Element(name = "_so_nbr")
	@Path("Body/do_QueryCallScreenResponse/sResult")
	private String soNbr;
	public String getSoNbr() {
		return soNbr;
	}

	@Element(name = "_bos_so_nbr")
	@Path("Body/do_QueryCallScreenResponse/sResult")
	private String bosSoNbr;
	public String getBosSoNbr() {
		return bosSoNbr;
	}

	@Element(name = "_finish_date")
	@Path("Body/do_QueryCallScreenResponse/sResult")
	private String finishDate;
	public String getfinishDate() {
		return finishDate;
	}

	@Element(name = "_result_code")
	@Path("Body/do_QueryCallScreenResponse/sResult")
	private String resultCode;
	public String getResultCode() {
		return resultCode;
	}

	@ElementList(entry = "CallScreenNoInfoList_Item", inline = true)
	@Path("Body/do_QueryCallScreenResponse/CallScreenNoInfoList")
	private List<CallScreenNoInfoList_Item> callScreenNoInfoListItem;
	public List<CallScreenNoInfoList_Item> getCallScreenNoInfoListItem() {
		return callScreenNoInfoListItem;
	}
	
	@Element(name = "_CLIR_flag", required = false)
	@Path("Body/do_QueryCallScreenResponse")
	private String clirFlag;
	public String getClirFlag() {
		return clirFlag;
	}

	@Element(name = "_call_screen_no_type", required = false)
	@Path("Body/do_QueryCallScreenResponse")
	private String callScreenNoType;
	public String getCallScreenNoType() {
		return callScreenNoType;
	}

	@Element(name = "_routing_method", required = false)
	@Path("Body/do_QueryCallScreenResponse")
	private String callScreenNoInfoListRoutingMethod;
	public String getCallScreenNoInfoListRoutingMethod() {
		return callScreenNoInfoListRoutingMethod;
	}
	
	@Element(name = "ICSSendSMSFlag", required = false)
	@Path("Body/do_QueryCallScreenResponse")
	private String icsSendSMSFlag;
	public String getICSSendSMSFlagg() {
		return icsSendSMSFlag;
	}
	
}
