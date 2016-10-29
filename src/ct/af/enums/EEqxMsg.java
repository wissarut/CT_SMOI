package ct.af.enums;

public enum EEqxMsg {
	
	ORIG("orig"),
	INVOKE("invoke"),
	NAME("name"),
	TYPE("type"),
	CTYPE("ctype"),
	METHOD("method"),
	URL("url"),
	VAL("val"),
	FILTER("filter"),
	OID("oid"),
	SOAPACTION("SOAPAction"),
    ;

	private String name;
	EEqxMsg(String name){
        this.name = name;
    }

    public String getEqxMsg() {
        return name;
    }
}
