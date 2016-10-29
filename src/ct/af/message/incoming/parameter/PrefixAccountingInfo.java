package ct.af.message.incoming.parameter;

public class PrefixAccountingInfo {
	
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
	
	private String ds2state;
	private String ds2classOfService;
	private String ds2serviceLocation;
	private String getDs2serviceLocation;
	private String gt;
	private String dpc;
	private String ds2shareAccSwitch;
	private String ds2cbpLocation;
	private String ds2ipAdress;
	private String ds2ipPort;

	public String getDs2state() {
		return ds2state;
	}
	public void setDs2state(String ds2state) {
		this.ds2state = ds2state;
	}
	public String getDs2classOfService() {
		return ds2classOfService;
	}
	public void setDs2classOfService(String ds2classOfService) {
		this.ds2classOfService = ds2classOfService;
	}
	public String getDs2serviceLocation() {
		return ds2serviceLocation;
	}
	public void setDs2serviceLocation(String ds2serviceLocation) {
		this.ds2serviceLocation = ds2serviceLocation;
	}
	public String getGetDs2serviceLocation() {
		return getDs2serviceLocation;
	}
	public void setGetDs2serviceLocation(String getDs2serviceLocation) {
		this.getDs2serviceLocation = getDs2serviceLocation;
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
	public String getDs2shareAccSwitch() {
		return ds2shareAccSwitch;
	}
	public void setDs2shareAccSwitch(String ds2shareAccSwitch) {
		this.ds2shareAccSwitch = ds2shareAccSwitch;
	}
	public String getDs2cbpLocation() {
		return ds2cbpLocation;
	}
	public void setDs2cbpLocation(String ds2cbpLocation) {
		this.ds2cbpLocation = ds2cbpLocation;
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
