package ct.af.message.incoming.parameter;

public class CounterPackInfo_AMF_ManageCounters {

	private String productNo;
	private String syncflag;
	private String startTime;
	private String productState;
	private String expiryTime;
	private String resultState;

	public String getProductNo() {
		return productNo;
	}
	public String getSyncflag() {
		return syncflag;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getProductState() {
		return productState;
	}
	public String getExpiryTime() {
		return expiryTime;
	}
	public String getResultState() {
		return resultState;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public void setSyncflag(String syncflag) {
		this.syncflag = syncflag;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setProductState(String productState) {
		this.productState = productState;
	}
	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}
	public void setResultState(String resultState) {
		this.resultState = resultState;
	}
	
}
