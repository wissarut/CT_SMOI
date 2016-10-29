package ct.af.message.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="Header")
public class Header {
	
	@Attribute(name="name")
	private String name;
	
	@Attribute(name="value")
	private String value;

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	

}
