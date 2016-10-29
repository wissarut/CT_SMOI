package ct.af.message.incoming.parameter;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "ProductFNList")
public class ProductFNList {
	
	@Element(name = "_product_sequence_id")
	@Path("SProductOrder")
	private String productSequenceID;
	public String getProductSequenceID() {
		return productSequenceID;
	}
	
	@Element(name = "_product_id")
	@Path("SProductOrder")
	private String productID;
	public String getProductID() {
		return productID;
	}
	
	@ElementList(entry = "FN", inline = true)
	@Path("_FN_list")
	private List<FN> FN;
	public List<FN> getFN() {
		return FN;
	}
}
