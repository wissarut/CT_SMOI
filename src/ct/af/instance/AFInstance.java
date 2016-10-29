package ct.af.instance;

import java.util.HashMap;
import org.joda.time.DateTime;
import ct.af.enums.EValue;
import ct.af.utils.Config;

public class AFInstance {
	
	private int mainSubSessionRunningNo = 0;

	private String mainTimeStampIncoming;
	
	private String mainApplyTime = Config.formatDateWithMiTz.print(new DateTime());
	
	private int mainCountWait = 0;
	private int mainCountProcess = 0;
	
	private HashMap<String, AFSubInstance> mainSubInstance = new HashMap<String, AFSubInstance>();
	
	private String mainHasProfile = EValue.NO.toString();
	private boolean mainIsPrepaid = false;
	private boolean mainIsNonBos = false;
	private String mainNetworkType = null;
	private String mainLanguage = EValue.EN.toString();
	
	private String mainProfile = null;
	private String mainLocation = null;
	
	private boolean isRMCR = false;
	private String rmfTimeStamp;

	public AFInstance() {
	}

	public int getMainSubSessionRunningNo() {
		return mainSubSessionRunningNo;
	}

	public void countSubSessionRunningNo() {
		
		if(this.mainSubSessionRunningNo < 50000) {
			this.mainSubSessionRunningNo++;
		} else {
			this.mainSubSessionRunningNo = 0;
		}
	}
	
	public String getMainApplyTime() {
		return mainApplyTime;
	}

	public void setMainApplyTime(String mainApplyTime) {
		this.mainApplyTime = mainApplyTime;
	}

	public String getMainTimeStampIncoming() {
		return mainTimeStampIncoming;
	}

	public void setMainTimeStampIncoming(String mainTimeStampIncoming) {
		this.mainTimeStampIncoming = mainTimeStampIncoming;
	}

	public int getMainCountWait() {
		return mainCountWait;
	}

	public void setMainCountWait(int mainCountWait) {
		this.mainCountWait = mainCountWait;
	}

	public void incrementMainCountWait(){
		this.mainCountWait++;
	}
	
	public void decrementMainCountWait(){
		
		if(this.mainCountWait > 0) {
			this.mainCountWait--;
		} else {
			this.mainCountWait = 0;
		}
		
	}
	
	public int getMainCountProcess() {
		return mainCountProcess;
	}

	public void setMainCountProcess(int mainCountProcess) {
		this.mainCountProcess = mainCountProcess;
	}
	
	public void incrementMainCountProcess(){
		this.mainCountProcess++;
	}
	
	public void decrementMainCountProcess(){

		if(this.mainCountProcess > 0) {
			this.mainCountProcess--;
		} else {
			this.mainCountProcess = 0;
		}
		
	}
	
	//HashMap Method//
	public void putMainSubInstance(String key,AFSubInstance value) {
		this.mainSubInstance.put(key, value);
	}
	
	public HashMap<String, AFSubInstance> getMainSubInstance() {
		return this.mainSubInstance;
	}
	
	public AFSubInstance getMainSubInstance(String key) {
		return this.mainSubInstance.get(key);
	}

	public int getMainSubInstanceSize() {
		return this.mainSubInstance.size();
	}
	
	public void removeMainSubInstance(String key) {
		this.mainSubInstance.remove(key);
	}
	
	public void clearMainSubInstance() {
		this.mainSubInstance.clear();
	}
	//End HashMap Method//

	
	public boolean getMainIsPrepaid() {
		return mainIsPrepaid;
	}
	
	public String getMainProfile() {
		return mainProfile;
	}

	public void setMainProfile(String mainProfile) {
		this.mainProfile = mainProfile;
	}

	public String mainHasProfile() {
		return mainHasProfile;
	}

	public void setMainHasProfile(String mainHasProfile) {
		this.mainHasProfile = mainHasProfile;
	}

	public void setMainIsPrepaid(boolean mainIsPrepaid) {
		this.mainIsPrepaid = mainIsPrepaid;
	}

	public boolean getMainIsNonBos() {
		return mainIsNonBos;
	}

	public void setMainIsNonBos(boolean mainIsNonBos) {
		this.mainIsNonBos = mainIsNonBos;
	}

	public String getMainNetworkType() {
		return mainNetworkType;
	}

	public void setMainNetworkType(String mainNetworkType) {
		this.mainNetworkType = mainNetworkType;
	}

	public String getMainLanguage() {
		return mainLanguage;
	}

	public void setMainLanguage(String mainLanguage) {
		this.mainLanguage = mainLanguage;
	}

	public String getMainLocation() {
		return mainLocation;
	}

	public void setMainLocation(String mainLocation) {
		this.mainLocation = mainLocation;
	}

	public boolean isRMCR() {
		return isRMCR;
	}

	public void setRMCR(boolean isRMCR) {
		this.isRMCR = isRMCR;
	}

	public String getRmfTimeStamp() {
		return rmfTimeStamp;
	}

	public void setRmfTimeStamp(String rmfTimeStamp) {
		this.rmfTimeStamp = rmfTimeStamp;
	}

	
	
}
