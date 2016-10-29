package ct.af.core.log;

import java.util.LinkedHashMap;

import ct.af.enums.EResultCode;

public class DestinaionLog {
	
	private String node;
	private String event;
	private int countRequest = 1;
	private LinkedHashMap<EResultCode,Integer> resultMap = new LinkedHashMap<EResultCode, Integer>();
	
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getCountRequest() {
		return countRequest;
	}
	public void setCountRequest(int countRequest) {
		this.countRequest = countRequest;
	}
	public void countRequest() {
		this.countRequest++;
	}
	public LinkedHashMap<EResultCode, Integer> getResultMap() {
		return resultMap;
	}
	public void putResultMap(EResultCode resultCode) {
		
		boolean found = this.resultMap.containsKey(resultCode);
		
		if(found) {
			int count = this.resultMap.get(resultCode)+1;
			
			this.resultMap.put(resultCode, count);
			
		} else {
			this.resultMap.put(resultCode, 1);
		}
		
	}
	
	public String getDestination() {
		
		StringBuilder resultCodeMap = new StringBuilder();
		
		for(EResultCode resultCode:this.resultMap.keySet()) {
			resultCodeMap.append(resultCode.getResultCode()+";"+resultCode.geteDesc()+"("+this.resultMap.get(resultCode)+"),");
		}
		
		resultCodeMap.delete(resultCodeMap.length() - 1, resultCodeMap.length());
		
		String destinationLog = this.node+";"+this.event+"("+this.countRequest+");["+resultCodeMap.toString()+"]";
		
		return destinationLog;
	}

}
