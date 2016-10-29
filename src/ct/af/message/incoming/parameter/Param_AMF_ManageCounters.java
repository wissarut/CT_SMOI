package ct.af.message.incoming.parameter;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name="xml")

public class Param_AMF_ManageCounters {
	
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
		
		protected List<CounterPackInfo_AMF_ManageCounters> counterPackInfoAMFManageCounters = new ArrayList<CounterPackInfo_AMF_ManageCounters>();
		public List<CounterPackInfo_AMF_ManageCounters> getCounterPackInfoAMFManageCounters(){
			return counterPackInfoAMFManageCounters;
		}
		public void setCounterPackInfoAMFManageCounters(List<CounterPackInfo_AMF_ManageCounters> counterPackInfoAMFManageCounters){
			this.counterPackInfoAMFManageCounters = counterPackInfoAMFManageCounters;
		}
		
		protected List<UpgradePackInfo_AMF_ManageCounters> upgradePackInfoAMFManageCounters = new ArrayList<UpgradePackInfo_AMF_ManageCounters>();
		public List<UpgradePackInfo_AMF_ManageCounters> getUpgradePackInfoAMFManageCounters(){
			return upgradePackInfoAMFManageCounters;
		}
		public void setUpgradePackInfoAMFManageCounters(List<UpgradePackInfo_AMF_ManageCounters > upgradePackInfoAMFManageCounters){
			this.upgradePackInfoAMFManageCounters = upgradePackInfoAMFManageCounters;
		}
		
}

