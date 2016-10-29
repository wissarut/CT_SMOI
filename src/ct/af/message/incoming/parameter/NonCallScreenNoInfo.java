package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="NonCallScreenNoInfo")
public class NonCallScreenNoInfo {
	
	@Element(name = "effectiveDate",required = false)
	private String effectiveDate;
	
	@Element(name = "expireDate",required = false)
	private String expireDate;
	
	@Element(name = "effectiveTime",required = false)
	private String effectiveTime;
	
	@Element(name = "expireTime",required = false)
	private String expireTime;
	
	@Element(name = "weekStart",required = false)
	private String weekStart;
	
	@Element(name = "weekStop",required = false)
	private String weekStop;
	
	@Element(name = "IRRouteFlag",required = false)
	private String iRRouteFlag;
	
	@Element(name = "RoutingMethod",required = false)
	private String RoutingMethod;

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public String getEffectiveTime() {
		return effectiveTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public String getWeekStart() {
		return weekStart;
	}

	public String getWeekStop() {
		return weekStop;
	}

	public String getiRRouteFlag() {
		return iRRouteFlag;
	}

	public String getRoutingMethod() {
		return RoutingMethod;
	}

}