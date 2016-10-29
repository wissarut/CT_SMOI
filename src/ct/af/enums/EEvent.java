package ct.af.enums;

public enum EEvent {
	
	Unknown("Unknown"),
	ERROR("EQUINOX_EVENT_ERROR"),
	REJECT("EQUINOX_EVENT_REJECT"),
	ABORT("EQUINOX_EVENT_ABORT"),
    ;

	private String name;
	EEvent(String name){
        this.name = name;
    }

    public String getEvent() {
        return name;
    }
}
