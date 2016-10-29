package ct.af.message.incoming.parameter;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name="xml")
public class Param_AMF_ModifyCounters {
	
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
		
		protected List<Counters_AMF_ModifyCounters> countersAMFModifyCounters = new ArrayList<Counters_AMF_ModifyCounters>();
		public List<Counters_AMF_ModifyCounters > getCountersAMFModifyCounters (){
			return countersAMFModifyCounters;
		}
		public void setCountersAMFModifyCounters(List<Counters_AMF_ModifyCounters> countersAMFModifyCounters){
			this.countersAMFModifyCounters = countersAMFModifyCounters;
		}
		
		protected List<AccountInfo_AMF_ModifyCounters> accountInfoAMFModifyCounters = new ArrayList<AccountInfo_AMF_ModifyCounters>();
		public List<AccountInfo_AMF_ModifyCounters> getAccountInfoAMFModifyCounters(){
			return accountInfoAMFModifyCounters;
		}
		public void setAccountInfoAMFModifyCounters(List<AccountInfo_AMF_ModifyCounters> accountInfoAMFModifyCounters){
			this.accountInfoAMFModifyCounters = accountInfoAMFModifyCounters;
		}
		
}

