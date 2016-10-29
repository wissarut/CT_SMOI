package ct.af.message.incoming.parameter;

import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="AVP")
public class AVP {
	
	@Attribute
	private String type;
	
	@ElementList(entry = "vals",inline = true)
	protected List<VAL> val;
	
	public String data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<VAL> getVal() {
		return val;
	}

	public void setVal(List<VAL> val) {
		this.val = val;
	}

}