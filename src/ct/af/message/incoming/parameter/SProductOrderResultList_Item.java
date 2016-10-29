package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "SProductOrderResultList_Item")
public class SProductOrderResultList_Item {
	

	@Element(name = "_product_sequence_id",required=false)
	private String productSequenceID;
	public String getProductSequenceID() {
		return productSequenceID;
	}

	@Element(name = "_product_id",required=false)
	private String productID;
	public String getProductID() {
		return productID;
	}

	@Element(name = "_so_product_id",required=false)
	private String soProductID;
	public String getSoProductID() {
		return soProductID;
	}

	@Element(name = "_valid_date",required=false)
	private String validDate;
	public String getValidDate() {
		return validDate;
	}

	@Element(name = "_expire_date",required=false)
	private String expireDate;
	public String getExpireDate() {
		return expireDate;
	}
}
