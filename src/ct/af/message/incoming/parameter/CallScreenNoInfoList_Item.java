package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "CallScreenNoInfoList_Item")
public class CallScreenNoInfoList_Item {
	
	@Element(name = "_callscreen_no", required = false)
	private String callscreenNo;
	public String getCallscreenNo() {
		return callscreenNo;
	}

	@Element(name = "_effective_date", required = false)
	private String effectiveDate;
	public String getEffectiveDate() {
		return effectiveDate;
	}

	@Element(name = "_expire_date", required = false)
	private String expireDate;
	public String getExpireDate() {
		return expireDate;
	}

	@Element(name = "_week_start", required = false)
	private String weekStart;
	public String getWeekStart() {
		return weekStart;
	}

	@Element(name = "_week_stop", required = false)
	private String weekStop;
	public String getWeekStop() {
		return weekStop;
	}

	@Element(name = "_ir_route_flag", required = false)
	private String irRouteFlag;
	public String getIrRouteFlag() {
		return irRouteFlag;
	}

	@Element(name = "_routing_method", required = false)
	private String callScreenNoInfoListItemRoutingMethod;
	public String getCallScreenNoInfoListItemRoutingMethod() {
		return callScreenNoInfoListItemRoutingMethod;
	}

	@Element(name = "_list_type", required = false)
	private String listType;
	public String getListType() {
		return listType;
	}
}