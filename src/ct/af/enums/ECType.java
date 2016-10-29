package ct.af.enums;

public enum ECType {
	
	TEXTPLAIN("text/plain"),
	TEXTXML("text/xml"),
	TEXTHTML("text/html"),
	APPJSON("application/json"),
	
	SPECIALIZED_RESOURCE("Specialized-Resource"),
	CREDIT_CONTROL("Credit-Control"), 
	EXTENDED("extended"),
    ;

	private String name;
	ECType(String name){
        this.name = name;
    }

    public String getCType() {
        return name;
    }
}
