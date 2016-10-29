package ct.af.instance;

import java.util.ArrayList;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxProperties;
import ec02.af.data.EquinoxRawData;

public class EC02Instance {
	
	private String timeout = "";
	
	private EquinoxProperties eqxProp = null;
	private AFInstance afIns = null;
	private AbstractAF abstractAF = null;
	
	private ArrayList<EquinoxRawData> eqxRawDataList = new ArrayList<EquinoxRawData>();
	
	private StringBuilder appLogInOutDebug = null;
	private StringBuilder appLogDebug = null;
	private StringBuilder appLogCDR = null;
	
	
	public StringBuilder getAppLogCDR() {
		
		if(appLogCDR == null) {
			appLogCDR = new StringBuilder(); 
		}
		
		return appLogCDR;
	}
	public void setAppLogCDR(StringBuilder appLogCDR) {
		this.appLogCDR = appLogCDR;
	}
	public StringBuilder getAppLogInOutDebug() {
		
		if(appLogInOutDebug == null) {
			appLogInOutDebug = new StringBuilder(); 
		}
		
		return appLogInOutDebug;
	}
	public void setAppLogInOutDebug(StringBuilder appLogInOutDebug) {
		this.appLogInOutDebug = appLogInOutDebug;
	}
	public StringBuilder getAppLogDebug() {
		
		if(appLogDebug == null) {
			appLogDebug = new StringBuilder(); 
		}
		
		return appLogDebug;
	}
	public void setAppLogDebug(StringBuilder appLogDebug) {
		this.appLogDebug = appLogDebug;
	}

	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public ArrayList<EquinoxRawData> getEqxRawDataList() {
		return eqxRawDataList;
	}
	public void setEqxRawDataList(EquinoxRawData eqxRawDataList) {
		this.eqxRawDataList.add(eqxRawDataList);
	}
	public AFInstance getAFInstance() {
		return afIns;
	}
	public void setAFInstance(AFInstance afIns) {
		this.afIns = afIns;
	}
	public EquinoxProperties getEquinoxProperties() {
		return eqxProp;
	}
	public void setEquinoxProperties(EquinoxProperties eqxProp) {
		this.eqxProp = eqxProp;
	}
	public AbstractAF getAbstractAF() {
		return abstractAF;
	}
	public void setAbstractAF(AbstractAF abstractAF) {
		this.abstractAF = abstractAF;
	}
	 
}
