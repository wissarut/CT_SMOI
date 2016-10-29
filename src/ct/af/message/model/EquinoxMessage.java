package ct.af.message.model;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "EquinoxMessage", strict = false)
public class EquinoxMessage {
	
	@ElementList(entry = "EquinoxRawData",inline = true)
	private List<EquinoxRawData> equinoxRawData;
	
	@Attribute
	private String diag;
	
	@Attribute
	private String from;
	
	@Attribute
	private String ret;
	
	@Attribute
	private String session;
	
	@Attribute
	private String timeout;
	
	@Attribute
	private String via;
	
	public List<EquinoxRawData> getEquinoxRawDatas() {
		return equinoxRawData;
	}

	public String getDiag() {
		return diag;
	}

	public String getFrom() {
		return from;
	}

	public String getRet() {
		return ret;
	}

	public String getSession() {
		return session;
	}

	public String getTimeout() {
		return timeout;
	}

	public String getVia() {
		return via;
	}

}
