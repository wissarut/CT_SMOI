package ct.af.message.incoming.parameter;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name="xml")

public class Param_AMF_ObtainCounter {
	
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
	
	protected List<AccountInfo_AMF_ObtainNoneBalance> accountInfoAMFObtainNoneBalance = new ArrayList<AccountInfo_AMF_ObtainNoneBalance>();
	
	public List<AccountInfo_AMF_ObtainNoneBalance> getAccountInfoAMFObtainNoneBalance(){
		return accountInfoAMFObtainNoneBalance;
	}
	public void setAccountInfoAMFObtainNoneBalance(List<AccountInfo_AMF_ObtainNoneBalance> accountInfoAMFObtainNoneBalance){
		this.accountInfoAMFObtainNoneBalance = accountInfoAMFObtainNoneBalance;
	}
	
	protected List<Counters_AMF_ObtainNoneBalance> countersAMFObtainNoneBalance = new ArrayList<Counters_AMF_ObtainNoneBalance>();
	
	public List<Counters_AMF_ObtainNoneBalance> getCountersAMFObtainNoneBalance(){
		return countersAMFObtainNoneBalance;
	}
	public void setCountersAMFObtainNoneBalance(List<Counters_AMF_ObtainNoneBalance> countersAMFObtainNoneBalance){
		this.countersAMFObtainNoneBalance = countersAMFObtainNoneBalance;
	}


}
