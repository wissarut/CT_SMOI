package ct.af.message.incoming.parameter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import ct.af.enums.EResultCode;

@Root(name = "soapenv:Envelope", strict = false)
public class Param_BSS_DoAdjustBalance {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	EResultCode respResultCode = EResultCode.RE50000;
	
	public boolean isReceived() {
	    return isReceived;
	}
	public void setReceived(boolean isReceived) {
	    this.isReceived = isReceived;
	}
	public boolean isNotMissing() {
	    return isNotMissing;
	}
	public void setNotMissing(boolean isNotMissing) {
	    this.isNotMissing = isNotMissing;
	}
	public boolean isValid() {
	    return isValid;
	}
	public void setValid(boolean isValid) {
	    this.isValid = isValid;
	}
	public EResultCode getRespResultCode() {
		return respResultCode;
	}
	public void setRespResultCode(EResultCode respResultCode) {
		this.respResultCode = respResultCode;
	}
	
	@Element(name = "_so_nbr")
	@Path("Body/do_AdjustBalanceResponse/sResult")
	private String SoNbr;
	public String getSoNbr() {
	    return SoNbr;
	}

	@Element(name = "_bos_so_nbr", required = false)
	@Path("Body/do_AdjustBalanceResponse/sResult")
	private String bosSoNbr;
	public String getBosSoNbr() {
	    return bosSoNbr;
	}

	@Element(name = "_finish_date")
	@Path("Body/do_AdjustBalanceResponse/sResult")
	private String finishDate;
	public String getFinishDate() {
	    return finishDate;
	}

	@Element(name = "_result_code")
	@Path("Body/do_AdjustBalanceResponse/sResult")
	private String resultCode;
	public String getResultCode() {
	    return resultCode;
	}
	
	@Element(name = "_error_msg", required = false)
	@Path("Body/do_AdjustBalanceResponse/sResult")
	private String errorMsg;
	public String getErrorMsg() {
	    return errorMsg;
	}
	
	@Element(name = "_hint", required = false)
	@Path("Body/do_AdjustBalanceResponse/sResult")
	private String hint;
	public String getHint() {
	    return hint;
	}

	@Element(name = "_book_item", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String bookItem;
	public String getBookItem() {
	    return bookItem;
	}

	@Element(name = "_amount_after", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String amountAfter;
	public String getAmountAfter() {
	    return amountAfter;
	}

	@Element(name = "_days_request", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String daysRequest;
	public String getDaysRequest() {
	    return daysRequest;
	}

	@Element(name = "_days_adjust", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String daysAdjust;
	public String getDaysAdjust() {
	    return daysAdjust;
	}

	@Element(name = "_acct_id", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String acctID;
	public String getAcctID() {
	    return acctID;
	}

	@Element(name = "_user_id", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String userID;
	public String getUserID() {
	    return userID;
	}

	@Element(name = "_phone_id", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String phoneID;
	public String getPhoneID() {
	    return phoneID;
	}

	@Element(name = "_valid_date", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String validDate;
	public String getValidDate() {
	    return validDate;
	}

	@Element(name = "_expiredate_after", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String expiredateAfter;
	public String getExpiredateAfter() {
	    return expiredateAfter;
	}

	@Element(name = "_expiredate_before", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String expiredateBefore;
	public String getExpiredateBefore() {
	    return expiredateBefore;
	}

	@Element(name = "_amount_before", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String amountBefore;
	public String getAmountBefore() {
	    return amountBefore;
	}

	@Element(name = "_amount_request", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String amountRequest;
	public String getAmountRequest() {
	    return amountRequest;
	}

	@Element(name = "_amount_adjust", required = false)
	@Path("Body/do_AdjustBalanceResponse/SBalanceAdjustResponse")
	private String amountAdjust;
	public String getAmountAdjust() {
	    return amountAdjust;
	}

	@Element(name = "FreeResourceListAdjustResponse", required = false)
	@Path("Body/do_AdjustBalanceResponse")
	private String freeResourceListAdjustResponse;
	public String getFreeResourceListAdjustResponse() {
	    return freeResourceListAdjustResponse;
	}
	
}
