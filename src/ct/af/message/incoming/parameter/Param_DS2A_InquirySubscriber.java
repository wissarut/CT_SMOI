package ct.af.message.incoming.parameter;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name="xml",strict = false)
public class Param_DS2A_InquirySubscriber {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
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
	public EResultCode getRespResultCode() {
		return respResultCode;
	}
	public void setRespResultCode(EResultCode respResultCode) {
		this.respResultCode = respResultCode;
	}
	
	@ElementList(entry = "AVP",inline = true)
	protected List<AVP> avp;
	public List<AVP> getAvp(){
		return avp;
	}
	public void setAvp(List<AVP> avp){
		this.avp = avp;
	}
	
	
	protected String methodVersion;
	public String getMethodVersion(){
		return methodVersion;
	}
	public void setMethodVersion(String methodVersion){
		this.methodVersion = methodVersion;
	}
	
	protected List<ResultInfo> resultInfo = new ArrayList<ResultInfo>();
	public List<ResultInfo> getResultInfo(){
		return resultInfo;
	}
	public void setResultInfo(List<ResultInfo> resultInfo){
		this.resultInfo = resultInfo;
	}

	protected List<AccountingInfo> accountingInfo = new ArrayList<AccountingInfo>();
	public List<AccountingInfo> getAccountingInfo(){
		return accountingInfo;
	}
	public void setAccountingInfo(List<AccountingInfo> accountingInfo){
		this.accountingInfo = accountingInfo;
	}

	/*
	protected List<ToggledAccountingInfo> toggledAccountingInfo = new ArrayList<ToggledAccountingInfo>();
	public List<ToggledAccountingInfo> getToggledAccountingInfo(){
		return toggledAccountingInfo;
	}
	public void setToggledAccountingInfo(List<ToggledAccountingInfo> toggledAccountingInfo){
		this.toggledAccountingInfo = toggledAccountingInfo;
	}
	
	protected List<PrefixAccountingInfo> prefixAccountingInfo = new ArrayList<PrefixAccountingInfo>();
	public List<PrefixAccountingInfo> getPrefixAccountingInfo(){
		return prefixAccountingInfo;
	}
	public void setPrefixAccountingInfo(List<PrefixAccountingInfo> prefixAccountingInfo){
		this.prefixAccountingInfo = prefixAccountingInfo;
	}
	*/
	
}

