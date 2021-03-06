package ct.af.message.incoming.parameter;

import ct.af.enums.EResultCode;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;

public class Param_BOS_FreeResourceAdjustment {
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
	
	DiameterCreditControlAnswer diamAns = null;

	public DiameterCreditControlAnswer getDiamAns() {
		return diamAns;
	}
	public void setDiamAns(DiameterCreditControlAnswer diamAns) {
		this.diamAns = diamAns;
	}

}
