package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="ChildCounterInfo", strict = false)
public class ChildCounterInfo {

	@Element(name = "CounterId", required = false)
	private String counterId;

	@Element(name = "WappId", required = false)
	private String wappId;

	@Element(name = "EParameterServiceCharging", required = false)
	private String eParameterServiceCharging;

	@Element(name = "MinimumUnit", required = false)
	private String minimumUnit;

	@Element(name = "ReplenishValue", required = false)
	private String replenishValue;

	@Element(name = "ValueType", required = false)
	private String valueType;

	@Element(name = "CounterValue", required = false)
	private String counterValue;

	@Element(name = "ReserveValue", required = false)
	private String reserveValue;

	@Element(name = "ReserveExpiryTime", required = false)
	private String reserveExpiryTime;

	@Element(name = "CDSEParam", required = false)
	private String cDSEParam;

	@Element(name = "WSubTtType", required = false)
	private String wSubTtType;

	@Element(name = "MappingWSubttType", required = false)
	private String mappingWSubttType;

	@Element(name = "WTime", required = false)
	private String wTime;
	
	@Element(name = "BlockId", required = false)
	private String blockId;
	
	@Element(name = "Shareable", required = false)
	private String shareable;
	
	@Element(name = "counterHistory", required = false)
	private String counterHistory;
	
	@Element(name = "MeteringMethod", required = false)
	private String meteringMethod;


	public String getCounterId() {
		return counterId;
	}

	public String getWappId() {
		return wappId;
	}

	public String geteParameterServiceCharging() {
		return eParameterServiceCharging;
	}

	public String getMinimumUnit() {
		return minimumUnit;
	}

	public String getReplenishValue() {
		return replenishValue;
	}

	public String getValueType() {
		return valueType;
	}

	public String getCounterValue() {
		return counterValue;
	}

	public String getReserveValue() {
		return reserveValue;
	}

	public String getReserveExpiryTime() {
		return reserveExpiryTime;
	}

	public String getcDSEParam() {
		return cDSEParam;
	}

	public String getwSubTtType() {
		return wSubTtType;
	}

	public String getMappingWSubttType() {
		return mappingWSubttType;
	}

	public String getwTime() {
		return wTime;
	}

	public String getBlockId() {
		return blockId;
	}

	public String getShareable() {
		return shareable;
	}

	public String getCounterHistory() {
		return counterHistory;
	}

	public String getMeteringMethod() {
		return meteringMethod;
	}
	
}
