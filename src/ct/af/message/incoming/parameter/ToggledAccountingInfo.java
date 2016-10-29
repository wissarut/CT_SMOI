package ct.af.message.incoming.parameter;

public class ToggledAccountingInfo {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	
	public boolean isReceived() {
		return isReceived;
	}
	public void setRecive(boolean isReceived) {
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

	private String ds2toggleF;
	private String ds2stateT;
	private String ds2classOfServiceT;
	private String ds2serviceLocationT;
	private String gt;
	private String dpc;
	private String ds2shareAccSwitchT;
	private String ds2cbpLocationT;
	private String ds2ipAdress;
	private String ds2ipPort;
	
	public String getDs2toggleF() {
		return ds2toggleF;
	}
	public void setDs2toggleF(String ds2toggleF) {
		this.ds2toggleF = ds2toggleF;
	}
	public String getDs2stateT() {
		return ds2stateT;
	}
	public void setDs2stateT(String ds2stateT) {
		this.ds2stateT = ds2stateT;
	}
	public String getDs2classOfServiceT() {
		return ds2classOfServiceT;
	}
	public void setDs2classOfServiceT(String ds2classOfServiceT) {
		this.ds2classOfServiceT = ds2classOfServiceT;
	}
	public String getDs2serviceLocationT() {
		return ds2serviceLocationT;
	}
	public void setDs2serviceLocationT(String ds2serviceLocationT) {
		this.ds2serviceLocationT = ds2serviceLocationT;
	}
	public String getGt() {
		return gt;
	}
	public void setGt(String gt) {
		this.gt = gt;
	}
	public String getDpc() {
		return dpc;
	}
	public void setDpc(String dpc) {
		this.dpc = dpc;
	}
	public String getDs2shareAccSwitchT() {
		return ds2shareAccSwitchT;
	}
	public void setDs2shareAccSwitchT(String ds2shareAccSwitchT) {
		this.ds2shareAccSwitchT = ds2shareAccSwitchT;
	}
	public String getDs2cbpLocationT() {
		return ds2cbpLocationT;
	}
	public void setDs2cbpLocationT(String ds2cbpLocationT) {
		this.ds2cbpLocationT = ds2cbpLocationT;
	}
	public String getDs2ipAdress() {
		return ds2ipAdress;
	}
	public void setDs2ipAdress(String ds2ipAdress) {
		this.ds2ipAdress = ds2ipAdress;
	}
	public String getDs2ipPort() {
		return ds2ipPort;
	}
	public void setDs2ipPort(String ds2ipPort) {
		this.ds2ipPort = ds2ipPort;
	}
	
}
