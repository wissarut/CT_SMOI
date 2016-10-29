package ct.af.core.log;

import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EStats;
import ec02.af.data.EquinoxRawData;

public class OutputLog {
	
	String initInvoke;
    String invoke;
    String cmd;
    String event;
    String type;
    String rawDataMsg;
    String customMsg;
    String method;
    String url;
    EStats statOut;

    public OutputLog(EquinoxRawData rawData,EStats statOut,String cmd) {
    	
    	try {
    		this.initInvoke = rawData.getInvoke().split("\\.")[0];
    	} catch(Exception ex) {
    		this.initInvoke = rawData.getInvoke();
    	}
    	
    	this.invoke = rawData.getInvoke();
        this.cmd = cmd;
        this.event = statOut.getStatName().toString();
        
        if(rawData.getType().equals("request")) {
        	this.type = "REQ";
        } else {
        	this.type = "RES";
        }
        
        if(rawData.getCType().equals(ECType.TEXTPLAIN.getCType())
        		|| rawData.getCType().equals(ECType.APPJSON.getCType())) {
        	this.rawDataMsg = rawData.getRawDataAttribute(EEqxMsg.VAL.getEqxMsg());
        } else {
        	this.rawDataMsg = rawData.getRawDataMessage();
        }
        
        try {
        	this.method = rawData.getRawDataAttribute("method");
        	this.url = rawData.getRawDataAttribute("url").replaceAll("=","\\\\u003d");
        } catch(Exception ex) {
    		
    	}
        
        this.statOut = statOut;
    }
    
    public String getInvoke() {
        return invoke;
    }
    
    public String getInitInvoke() {
		return initInvoke;
	}

	public void setInitInvoke(String initInvoke) {
		this.initInvoke = initInvoke;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getEvent() {
        return event;
    }

    public String getType() {
        return type;
    }

    public String getRawDataMsg() {
        return rawDataMsg;
    }

    public String getCustomMsg() {
        return customMsg;
    }

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    

}
