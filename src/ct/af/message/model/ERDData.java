package ct.af.message.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class ERDData {
 
	@Path("ERDData[1]")
	@Attribute(name="value")
	private String value;
	
	public String getValue() {
		return value;
	}

}
