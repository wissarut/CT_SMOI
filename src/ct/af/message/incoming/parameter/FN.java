package ct.af.message.incoming.parameter;

//import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="_FN_list")
public class FN {
	
//	@Element(name = "FN")
	private String fn;
	public String getFn() {
		return fn;
	}

}
