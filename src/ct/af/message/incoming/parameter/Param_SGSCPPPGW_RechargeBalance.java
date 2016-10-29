package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name = "vcrr",strict = false)
public class Param_SGSCPPPGW_RechargeBalance {
	
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
	
	@Element(name = "res")
	private String res;
	public String getRes() {
		return res;
	}
	
	@Element(name = "status")
	private String status;
	public String getStatus() {
		return status;
	}
	
	@Element(name = "balance")
	private String balance;
	public String getBalance() {
		return balance;
	}

	@Element(name = "expire")
	private String expire;
	public String getExpire() {
		return expire;
	}
	
	@Element(name = "voice")
	private String voice;
	public String getVoice() {
		return voice;
	}

}

