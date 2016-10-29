package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name="FreeRes-Info")
public class FreeResInfo {
	@Path("Resource-Id")
	@Attribute(name = "value", required = false)
	private String resourceID;
	public String getResourceID() {
		return resourceID;
	}
	
	@Path("Resource-Amount")
	@Attribute(name = "value", required = false)
	private String resourceAmount;
	public String getResourceAmount() {
		return resourceAmount;
	}
	
	@Path("Remaining-Amount")
	@Attribute(name = "value", required = false)
	private String remainingAmount;
	public String getRemainingAmount() {
		return remainingAmount;
	}
	
	@Path("Validity-Date")
	@Attribute(name = "value", required = false)
	private String freeResInfovalidityDate;
	public String getFreeResInfoValidityDate() {
		return freeResInfovalidityDate;
	}
	
	@Path("Expire-Date")
	@Attribute(name = "value", required = false)
	private String freeResInfoexpireDate;
	public String getFreeResInfoExpireDate() {
		return freeResInfoexpireDate;
	}
	
	@Path("Resource-Group-ID")
	@Attribute(name = "value", required = false)
	private String resourceGroupID;
	public String getResourceGroupID() {
		return resourceGroupID;
	}
	
	@Path("Product-ID")
	@Attribute(name = "value", required = false)
	private String productID;
	public String getProductID() {
		return productID;
	}
	
	@Path("Product-Sequence-ID")
	@Attribute(name = "value", required = false)
	private String productSequenceID;
	public String getProductSequenceID() {
		return productSequenceID;
	}
	
	@Path("Resource-Mode")
	@Attribute(name = "value", required = false)
	private String resourceMode;
	public String getResourceMode() {
		return resourceMode;
	}

}
