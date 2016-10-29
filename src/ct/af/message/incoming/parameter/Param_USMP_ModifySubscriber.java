package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name = "soap:Envelope", strict = false)

public class Param_USMP_ModifySubscriber {
	
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
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/OperationStatus")
	private String isSuccess;
	public String getIsSuccess() {
		return isSuccess;
	}

	@Element(name = "Code",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/OperationStatus")
	private String code;
	public String getCode() {
		return code;
	}

	@Element(name = "Description",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/OperationStatus")
	private String description;
	public String getDescription() {
		return description;
	}

	@Element(name = "TransactionID",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/OperationStatus")
	private String transactionID;
	public String getTransactionID() {
		return transactionID;
	}

	@Element(name = "OrderRef",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/OperationStatus")
	private String orderRef;
	public String getOrderRef() {
		return orderRef;
	}

	@Element(name = "Msisdn",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String msisdn;
	public String getMsisdn() {
		return msisdn;
	}

	@Element(name = "CustomerID",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String customerID;
	public String getCustomerID() {
		return customerID;
	}

	@Element(name = "Cos",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String cos;
	public String getCos() {
		return cos;
	}

	@Element(name = "Time",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String time;
	public String getTime() {
		return time;
	}

	@Element(name = "Lang",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String lang;
	public String getLang() {
		return lang;
	}

	@Element(name = "IVRLanguageLang",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String IVRLanguageLang;
	public String getIVRLanguageLang() {
		return IVRLanguageLang;
	}
					
	@Element(name = "SMSLanguage",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String SMSLanguage;
	public String getSMSLanguage() {
		return SMSLanguage;
	}

	@Element(name = "USSDLanguage",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String USSDLanguage;
	public String getUSSDLanguage() {
		return USSDLanguage;
	}

	@Element(name = "EmailLanguage",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String EmailLanguage;
	public String getEmailLanguage() {
		return EmailLanguage;
	}

	@Element(name = "Model",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String model;
	public String getModel() {
		return model;
	}
	
	@Element(name = "State",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String state;
	public String getState() {
		return state;
	}

	@Element(name = "VscpState",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String vscpState;
	public String getVscpState() {
		return vscpState;
	}

	@Element(name = "INstate",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String INstate;
	public String getINstate() {
		return INstate;
	}

	@Element(name = "ServicePackageId",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String servicePackageID;
	public String getServicePackageID() {
		return servicePackageID;
	}

	@Element(name = "Hack",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String hack;
	public String getHack() {
		return hack;
	}

	@Element(name = "Gtcf",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String gtcf;
	public String getGtcf() {
		return gtcf;
	}

	@Element(name = "ScpId",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String scpID;
	public String getScpID() {
		return scpID;
	}

	@Element(name = "CbpId",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String cbpID;
	public String getCbpID() {
		return cbpID;
	}

	@Element(name = "Bso",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String bso;
	public String getBso() {
		return bso;
	}

	@Element(name = "NumImsi",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String numImsi;
	public String getNumImsi() {
		return numImsi;
	}

	@Element(name = "PrepaidFlag",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String prepaidFlag;
	public String getPrepaidFlag() {
		return prepaidFlag;
	}

	@Element(name = "VirtualFlag",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String virtualFlag;
	public String getVirtualFlag() {
		return virtualFlag;
	}

	@Element(name = "AltPrefixFlag",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String altPrefixFlag;
	public String getAltPrefixFlag() {
		return altPrefixFlag;
	}

	@Element(name = "AltPrefixCos",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String altPrefixCos;
	public String getAltPrefixCos() {
		return altPrefixCos;
	}

	@Element(name = "ServiceLocation",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String serviceLocation;
	public String getServiceLocation() {
		return serviceLocation;
	}

	@Element(name = "HasOneTimePassword",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String hasOneTimePassword;
	public String getHasOneTimePassword() {
		return hasOneTimePassword;
	}

	@Element(name = "HasPassword",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String hasPassword;
	public String getHasPassword() {
		return hasPassword;
	}

	@Element(name = "ToggleF",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String toggleF;
	public String getToggleF() {
		return toggleF;
	}

	@Element(name = "ToggleState",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String toggleState;
	public String getToggleState() {
		return toggleState;
	}

	@Element(name = "ToggleCos",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String toggleCos;
	public String getToggleCos() {
		return toggleCos;
	}

	@Element(name = "CosEffectiveDate",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String cosEffectiveDate;
	public String getCosEffectiveDate() {
		return cosEffectiveDate;
	}

	@Element(name = "CosEffectiveValue",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String cosEffectiveValue;
	public String getCosEffectiveValue() {
		return cosEffectiveValue;
	}

	@Element(name = "GtcfEffectiveDate",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String gtcfEffectiveDate;
	public String getGtcfEffectiveDate() {
		return gtcfEffectiveDate;
	}

	@Element(name = "GtcfEffectiveValue",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String gtcfEffectiveValue;
	public String getGtcfEffectiveValue() {
		return gtcfEffectiveValue;
	}

	@Element(name = "FirstActDate",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String firstActDate;
	public String getFirstActDate() {
		return firstActDate;
	}

	@Element(name = "imsi",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String imsi;
	public String getImsi() {
		return imsi;
	}

	@Element(name = "imsiS1",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String imsiS1;
	public String getImsiS1() {
		return imsiS1;
	}

	@Element(name = "msisdnS1",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String msisdnS1;
	public String getMsisdnS1() {
		return msisdnS1;
	}

	@Element(name = "imsiS2",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String imsiS2;
	public String getImsiS2() {
		return imsiS2;
	}

	@Element(name = "msisdnS2",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String msisdnS2;
	public String getMsisdnS2() {
		return msisdnS2;
	}

	@Element(name = "imsiS3",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String imsiS3;
	public String getImsiS3() {
		return imsiS3;
	}

	@Element(name = "msisdnS3",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String msisdnS3;
	public String getMsisdnS3() {
		return msisdnS3;
	}

	@Element(name = "imsiS4",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String imsiS4;
	public String getImsiS4() {
		return imsiS4;
	}

	@Element(name = "msisdnS4",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String msisdnS4;
	public String getMsisdnS4() {
		return msisdnS4;
	}

	@Element(name = "imsiX",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String imsiX;
	public String getImsiX() {
		return imsiX;
	}

	@Element(name = "msisdnImsiX",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String msisdnImsiX;
	public String getMsisdnImsiX() {
		return msisdnImsiX;
	}

	@Element(name = "imsiFlag",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber/imsiArray")
	private String imsiFlag;
	public String getImsiFlag() {
		return imsiFlag;
	}

	@Element(name = "spName",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String spName;
	public String getSpName() {
		return spName;
	}

	@Element(name = "brandName",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String brandName;
	public String getBrandName() {
		return brandName;
	}

	@Element(name = "customerCategory",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String customerCategory;
	public String getCustomerCategory() {
		return customerCategory;
	}

	@Element(name = "customerSubCategory",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String customerSubCategory;
	public String getCustomerSubCategory() {
		return customerSubCategory;
	}

	@Element(name = "customerSegment",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String customerSegment;
	public String getCustomerSegment() {
		return customerSegment;
	}

	@Element(name = "HomeLocation",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String homeLocation;
	public String getHomeLocation() {
		return homeLocation;
	}

	@Element(name = "ActiveStopTime",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String activeStopTime;
	public String getActiveStopTime() {
		return activeStopTime;
	}

	@Element(name = "SuspendStopTime",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String suspendStopTime;
	public String getSuspendStopTime() {
		return suspendStopTime;
	}

	@Element(name = "DisableStopTime",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String disableStopTime;
	public String getDisableStopTime() {
		return disableStopTime;
	}

	@Element(name = "TerminateStopTime",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String terminateStopTime;
	public String getTerminateStopTime() {
		return terminateStopTime;
	}

	@Element(name = "FreezeTime",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String freezeTime;
	public String getFreezeTime() {
		return freezeTime;
	}

	@Element(name = "FraudFlag",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String fraudFlag;
	public String getFraudFlag() {
		return fraudFlag;
	}

	@Element(name = "PinFraudFlag",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String pinFraudFlag;
	public String getPinFraudFlag() {
		return pinFraudFlag;
	}

	@Element(name = "CustomerRequestF",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String customerRequestF;
	public String getCustomerRequestF() {
		return customerRequestF;
	}

	@Element(name = "DPFlag",required = false)
	@Path("Body/ModifySubscriberResponse/ModifySubscriberResult/Subscriber")
	private String DPFlag;
	public String getDPFlag() {
		return DPFlag;
	}
}
