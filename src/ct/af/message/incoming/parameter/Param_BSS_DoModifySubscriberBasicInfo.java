package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name = "soapenv:Envelope", strict = false)
public class Param_BSS_DoModifySubscriberBasicInfo {
	
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
	
	@Element(name = "_bos_so_nbr")
	@Path("Body/do_ModifySubscriberBasicInfoResponse/sResult")
	private String bosSoNbr;
	public String getBosSoNbr() {
		return bosSoNbr;
	}

	@Element(name = "_so_nbr")
	@Path("Body/do_ModifySubscriberBasicInfoResponse/sResult")
	private String soNbr;
	public String getSoNbr() {
		return soNbr;
	}

	@Element(name = "_finish_date")
	@Path("Body/do_ModifySubscriberBasicInfoResponse/sResult")
	private String finishDate;
	public String getfinishDate() {
		return finishDate;
	}

	@Element(name = "_result_code")
	@Path("Body/do_ModifySubscriberBasicInfoResponse/sResult")
	private String resultCode;
	public String getResultCode() {
		return resultCode;
	}
}
