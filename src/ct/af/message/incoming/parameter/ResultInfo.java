package ct.af.message.incoming.parameter;

public class ResultInfo {
	
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
	              
	private String msisdn;
	private String customerId;
	private String ds2state;
	private String ds2language;
	private String ds2pin;
	private String ds2hackTime;
	private String ds2hints;
	private String ds2gprsTBCF;
	private String ds2servPackageId;
	private String ds2pin2;
	private String ds2brandId;
	private String ds2spName;
	private String ds2customerCategory;
	private String ds2customerSubCategory;
	private String ds2customerSegment;
	private String emailLanguage;
	private String ivrLanguage;
	private String ussdLanguage;
	private String smsLanguage;
	private String registrationDate;
	private String timeDrivenState;
	private String fraudFlag;
	private String pinFraudFlag;
	private String userReqSuspend;
	private String DPmode;
	private String activeStopTime;
	private String suspendStopTime;
	private String disableStopTime;
	private String terminateStopTime;
	private String freezeTime;
	private String baId;
	private String caId;
	
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getDs2state() {
		return ds2state;
	}
	public void setDs2state(String ds2state) {
		this.ds2state = ds2state;
	}
	public String getDs2language() {
		return ds2language;
	}
	public void setDs2language(String ds2language) {
		this.ds2language = ds2language;
	}
	public String getDs2pin() {
		return ds2pin;
	}
	public void setDs2pin(String ds2pin) {
		this.ds2pin = ds2pin;
	}
	public String getDs2hackTime() {
		return ds2hackTime;
	}
	public void setDs2hackTime(String ds2hackTime) {
		this.ds2hackTime = ds2hackTime;
	}
	public String getDs2hints() {
		return ds2hints;
	}
	public void setDs2hints(String ds2hints) {
		this.ds2hints = ds2hints;
	}
	public String getDs2gprsTBCF() {
		return ds2gprsTBCF;
	}
	public void setDs2gprsTBCF(String ds2gprsTBCF) {
		this.ds2gprsTBCF = ds2gprsTBCF;
	}
	public String getDs2servPackageId() {
		return ds2servPackageId;
	}
	public void setDs2servPackageId(String ds2servPackageId) {
		this.ds2servPackageId = ds2servPackageId;
	}
	public String getDs2pin2() {
		return ds2pin2;
	}
	public void setDs2pin2(String ds2pin2) {
		this.ds2pin2 = ds2pin2;
	}
	public String getDs2brandId() {
		return ds2brandId;
	}
	public void setDs2brandId(String ds2brandId) {
		this.ds2brandId = ds2brandId;
	}
	public String getDs2spName() {
		return ds2spName;
	}
	public void setDs2spName(String ds2spName) {
		this.ds2spName = ds2spName;
	}
	public String getDs2customerCategory() {
		return ds2customerCategory;
	}
	public void setDs2customerCategory(String ds2customerCategory) {
		this.ds2customerCategory = ds2customerCategory;
	}
	public String getDs2customerSubCategory() {
		return ds2customerSubCategory;
	}
	public void setDs2customerSubCategory(String ds2customerSubCategory) {
		this.ds2customerSubCategory = ds2customerSubCategory;
	}
	public String getDs2customerSegment() {
		return ds2customerSegment;
	}
	public void setDs2customerSegment(String ds2customerSegment) {
		this.ds2customerSegment = ds2customerSegment;
	}
	public String getEmailLanguage() {
		return emailLanguage;
	}
	public void setEmailLanguage(String emailLanguage) {
		this.emailLanguage = emailLanguage;
	}
	public String getIvrLanguage() {
		return ivrLanguage;
	}
	public void setIvrLanguage(String ivrLanguage) {
		this.ivrLanguage = ivrLanguage;
	}
	public String getUssdLanguage() {
		return ussdLanguage;
	}
	public void setUssdLanguage(String ussdLanguage) {
		this.ussdLanguage = ussdLanguage;
	}
	public String getSmsLanguage() {
		return smsLanguage;
	}
	public void setSmsLanguage(String smsLanguage) {
		this.smsLanguage = smsLanguage;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getTimeDrivenState() {
		return timeDrivenState;
	}
	public void setTimeDrivenState(String timeDrivenState) {
		this.timeDrivenState = timeDrivenState;
	}
	public String getFraudFlag() {
		return fraudFlag;
	}
	public void setFraudFlag(String fraudFlag) {
		this.fraudFlag = fraudFlag;
	}
	public String getPinFraudFlag() {
		return pinFraudFlag;
	}
	public void setPinFraudFlag(String pinFraudFlag) {
		this.pinFraudFlag = pinFraudFlag;
	}
	public String getUserReqSuspend() {
		return userReqSuspend;
	}
	public void setUserReqSuspend(String userReqSuspend) {
		this.userReqSuspend = userReqSuspend;
	}
	public String getDPmode() {
		return DPmode;
	}
	public void setDPmode(String DPmode) {
		this.DPmode = DPmode;
	}
	public String getActiveStopTime() {
		return activeStopTime;
	}
	public void setActiveStopTime(String activeStopTime) {
		this.activeStopTime = activeStopTime;
	}
	public String getSuspendStopTime() {
		return suspendStopTime;
	}
	public void setSuspendStopTime(String suspendStopTime) {
		this.suspendStopTime = suspendStopTime;
	}
	public String getDisableStopTime() {
		return disableStopTime;
	}
	public void setDisableStopTime(String disableStopTime) {
		this.disableStopTime = disableStopTime;
	}
	public String getTerminateStopTime() {
		return terminateStopTime;
	}
	public void setTerminateStopTime(String terminateStopTime) {
		this.terminateStopTime = terminateStopTime;
	}
	public String getFreezeTime() {
		return freezeTime;
	}
	public void setFreezeTime(String freezeTime) {
		this.freezeTime = freezeTime;
	}
	public String getBaId() {
		return baId;
	}
	public void setBaId(String baId) {
		this.baId = baId;
	}
	public String getCaId() {
		return caId;
	}
	public void setCaId(String caId) {
		this.caId = caId;
	}
	

}
