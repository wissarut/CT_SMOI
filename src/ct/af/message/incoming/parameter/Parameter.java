package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Parameter")
public class Parameter {
	
	@Element(name = "Name",required = false)
	private String name;
	
	@Element(name = "Value",required = false)
	private String value;

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Parameter [name=" + name + ", value=" + value + "]";
	}

}
