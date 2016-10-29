package ct.af.message.incoming.parameter;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name="xml")
public class Param_AMF_CheckBalance {
	
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
	
	protected List<AccountInfo_AMF_CheckBalance> accountInfoAMFCheckBalance = new ArrayList<AccountInfo_AMF_CheckBalance>();
	public List<AccountInfo_AMF_CheckBalance> getAccountInfoAMFCheckBalance(){
		return accountInfoAMFCheckBalance;
	}
	public void setAccountInfoAMFCheckBalance(List<AccountInfo_AMF_CheckBalance> accountInfoAMFCheckBalance){
		this.accountInfoAMFCheckBalance = accountInfoAMFCheckBalance;
	}
	
	protected List<Counters_AMF_CheckBalance> countersAMFCheckBalance = new ArrayList<Counters_AMF_CheckBalance>();
	public List<Counters_AMF_CheckBalance> getCountersAMFCheckBalance(){
		return countersAMFCheckBalance;
	}
	public void setCountersAMFCheckBalance(List<Counters_AMF_CheckBalance> countersAMFCheckBalance){
		this.countersAMFCheckBalance = countersAMFCheckBalance;
	}
}
