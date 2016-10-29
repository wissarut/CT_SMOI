package ct.af.message.incoming.parameter;

public class AccountingInfo {

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

	private String ds2serviceLocation;
	private String gt;
	private String dpc;
	private String ds2shareAccSwitch;
	private String ds2cbpLocation;
	private String ds2ipAdress;
	private String ds2ipPort;
	private String ds2classOfservice;
	private String ds2dataServiceLifeStyle;
	private String ds2notificationPolicyControl;
	private String ds2recurringPolicyControl;
	private String ds2deselectPolicyControl;
	private String ds2offlineNotificationPolicyControl;
	private String homelocation;
	private String amf1stTopupRewardLifeTime;
	private String amf1stTopupRewardPromo;
	private String ds3NotifPolicyControlAS;
	
	public String getDs2classOfservice() {
		return ds2classOfservice;
	}
	public void setDs2classOfservice(String ds2classOfservice) {
		this.ds2classOfservice = ds2classOfservice;
	}
	public String getDs2serviceLocation() {
		return ds2serviceLocation;
	}
	public void setDs2serviceLocation(String ds2serviceLocation) {
		this.ds2serviceLocation = ds2serviceLocation;
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

	public String getDs2dataServiceLifeStyle() {
		return ds2dataServiceLifeStyle;
	}

	public void setDs2dataServiceLifeStyle(String ds2dataServiceLifeStyle) {
		this.ds2dataServiceLifeStyle = ds2dataServiceLifeStyle;
	}

	public String getDs2notificationPolicyControl() {
		return ds2notificationPolicyControl;
	}

	public void setDs2notificationPolicyControl(String ds2notificationPolicyControl) {
		this.ds2notificationPolicyControl = ds2notificationPolicyControl;
	}

	public String getDs2recurringPolicyControl() {
		return ds2recurringPolicyControl;
	}

	public void setDs2recurringPolicyControl(String ds2recurringPolicyControl) {
		this.ds2recurringPolicyControl = ds2recurringPolicyControl;
	}

	public String getDs2deselectPolicyControl() {
		return ds2deselectPolicyControl;
	}

	public void setDs2deselectPolicyControl(String ds2deselectPolicyControl) {
		this.ds2deselectPolicyControl = ds2deselectPolicyControl;
	}

	public String getDs2offlineNotificationPolicyControl() {
		return ds2offlineNotificationPolicyControl;
	}

	public void setDs2offlineNotificationPolicyControl(
			String ds2offlineNotificationPolicyControl) {
		this.ds2offlineNotificationPolicyControl = ds2offlineNotificationPolicyControl;
	}

	public String getHomelocation() {
		return homelocation;
	}

	public void setHomelocation(String homelocation) {
		this.homelocation = homelocation;
	}

	public String getAmf1stTopupRewardLifeTime() {
		return amf1stTopupRewardLifeTime;
	}

	public void setAmf1stTopupRewardLifeTime(String amf1stTopupRewardLifeTime) {
		this.amf1stTopupRewardLifeTime = amf1stTopupRewardLifeTime;
	}

	public String getAmf1stTopupRewardPromo() {
		return amf1stTopupRewardPromo;
	}

	public void setAmf1stTopupRewardPromo(String amf1stTopupRewardPromo) {
		this.amf1stTopupRewardPromo = amf1stTopupRewardPromo;
	}

	public String getDs3NotifPolicyControlAS() {
		return ds3NotifPolicyControlAS;
	}

	public void setDs3NotifPolicyControlAS(String ds3NotifPolicyControlAS) {
		this.ds3NotifPolicyControlAS = ds3NotifPolicyControlAS;
	}
	
	
}
