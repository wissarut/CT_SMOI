package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="vals")
public class VAL {
	
	@Attribute
	private String value;
	
	public String getValue() {
		return value;
	}

	
}