package ct.af.message.incoming.parameter;

public class Counters_AMF_ModifyCounters {

	private String ID;
	private String reserve;
	private String operationstate;
	private String newvalue;
	private String newexpirytime;
	private String abmftid;
	private String valueapplied;
	private String validityapplied;

	public String getID() {
		return ID;
	}
	public String getReserve() {
		return reserve;
	}
	public String getOperationstate() {
		return operationstate;
	}
	public String getNewvalue() {
		return newvalue;
	}
	public String getNewexpirytime() {
		return newexpirytime;
	}
	public String getAbmftid() {
		return abmftid;
	}
	public String getValueapplied() {
		return valueapplied;
	}
	public String getValidityapplied() {
		return validityapplied;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public void setOperationstate(String operationstate) {
		this.operationstate = operationstate;
	}
	public void setNewvalue(String newvalue) {
		this.newvalue = newvalue;
	}
	public void setNewexpirytime(String newexpirytime) {
		this.newexpirytime = newexpirytime;
	}
	public void setAbmftid(String abmftid) {
		this.abmftid = abmftid;
	}
	public void setValueapplied(String valueapplied) {
		this.valueapplied = valueapplied;
	}
	public void setValidityapplied(String validityapplied) {
		this.validityapplied = validityapplied;
	}
	
}
