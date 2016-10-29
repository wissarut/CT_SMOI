package ct.af.utils;

public class KeyFinalCode {
	
	public String cmd;
	public String currentState;
	public String internalCode;
	
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getInternalCode() {
		return internalCode;
	}
	public void setInternalCode(String internalCode) {
		this.internalCode = internalCode;
	}
	@Override
	public String toString() {
		return cmd + currentState + internalCode;
	}
	
}
