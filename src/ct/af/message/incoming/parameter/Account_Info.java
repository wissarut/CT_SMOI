package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name="Account-Info", strict = false)
public class Account_Info {
	
	@Path("Book-Item-Id")
	@Attribute(name = "value")
	private String bookItemId;
	public String getBookItemId() {
		return bookItemId;
	}
	
	@Path("Book-Item-Type")
	@Attribute(name = "value")
	private String bookItemType;
	public String getBookItemType() {
		return bookItemType;
	}
	
	@Path("Book-Item-Amount")
	@Attribute(name = "value")
	private String bookItemAmount;
	public String getBookItemAmount() {
		return bookItemAmount;
	}
	
	@Path("Create-Date")
	@Attribute(name = "value")
	private String createDate;
	public String getCreateDate() {
		return createDate;
	}
	
	@Path("Validity-Date")
	@Attribute(name = "value")
	private String validityDate;
	public String getValidityDate() {
		return validityDate;
	}
	
	@Path("Expire-Date")
	@Attribute(name = "value")
	private String expireDate;
	public String getExpireDate() {
		return expireDate;
	}
	
	@Path("Old-Amount")
	@Attribute(name = "value")
	private String oldAmount;
	public String getOldAmount() {
		return oldAmount;
	}
	
	@Path("Old-Validity")
	@Attribute(name = "value")
	private String oldValidity;
	public String getOldValidity() {
		return oldValidity;
	}

}
