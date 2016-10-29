package ct.af.message.model;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="ERDHeader")
public class ERDHeader {

	@ElementList(entry = "Header",inline = true)
	protected List<Header> Header;

	public List<Header> getList() {
		return Header;
	}
	
}
