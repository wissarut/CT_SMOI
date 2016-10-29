package ct.af.message.incoming.parameter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="ParameterList")
public class ParameterList {
	
	@Element(name = "ParameterName",required = false)
	private String parameterName;
	
	@Element(name = "ParameterValue",required = false)
	private String parameterValue;

	public String getParameterName() {
		return parameterName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	@Override
	public String toString() {
		return "Parameter [name=" + parameterName + ", value=" + parameterValue + "]";
	}
}
