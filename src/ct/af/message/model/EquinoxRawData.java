package ct.af.message.model;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "EquinoxRawData", strict = false)
public class EquinoxRawData {
	
	@Attribute
	private String ctype;
	
	@Attribute
	private String invoke;
	
	@Attribute
	private String method;
	
	@Attribute
	private String name;
	
	@Attribute
	private String orig;
	
	@Attribute
	private String ret;
	
	@Attribute
	private String to;
	
	@Attribute
	private String type;
	
	@Attribute
	private String url;
	
	@Attribute(required = false)
	private String val;
	
	@ElementList(entry = "ERDHeader",inline = true,required = false)
	private List<ERDHeader> erdHeader;
	
	@Element(name="ERDData",required = false)
	private ERDData erdData;

	public String getCtype() {
		return ctype;
	}

	public String getInvoke() {
		return invoke;
	}

	public String getMethod() {
		return method;
	}

	public String getName() {
		return name;
	}

	public String getOrig() {
		return orig;
	}

	public String getRet() {
		return ret;
	}

	public String getTo() {
		return to;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public String getVal() {
		return val;
	}

	public List<ERDHeader> getErdHeader() {
		return erdHeader;
	}

	public ERDData getErdData() {
		return erdData;
	}

	
	
}
