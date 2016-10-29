package ct.af.enums;

public enum EResultCode {

	REClient_send_request("Client_send_request","Request success"), 					 //RE10200
	REClient_send_Bad_request("Client_send_Bad_request","Missing or invalid parameter"), //RE10403

	RE503("503","Server busy"), //RE10503
	REret4("ret4","Timeout"), 	//RE10004
	REret3("ret3","Abort"), 	//RE10003
	REret2("ret2","Reject"), 	//RE10002
	REret1("ret1","Error"),

	RE32("32","No such object"),
	
	REEcodeNot0("Ecode","Ecode"),

	RE000("000","Success"),
	RE322("322","Missing or invalid parameter"),
	RE221("221","Key Unknown"),

	RE5004("5004", "Missing AVP value"),
	RE5005("5005", "Invalid AVP value"),

	RE20000("20000","Success"),
	RE20001("20001","Success without access network publicId"),
	RE20010("20010","Success on both publicId"),
	RE20011("20011","Success only on access network publicId"),
	RE20012("20012","Success only on PublicId"),
	RE20013("20013","Success without reservation but reuse both PublicId and access network publicId which have the same uId"),
	RE20014("20014","Success without reservation but reuse both PublicId and access network publicId which have the different uId"),

	RE20100("20100","Created success"),

	RE40080("40080","Unknown state"),
	RE40081("40081","Unknown invoke"),
	RE40082("40082","Invalid data on E01"),
	RE40100("40100","Access is denied due to invalid credentials"),
	RE40101("40101","Access denied"),
	RE40102("40102","SessionId expired"),
	RE40103("40103","Access token expired"),
	RE40104("40104","Invalid Header"),
	RE40110("40110","User or password has expired"),
	RE40111("40111","Invalid user or password"),
	RE40112("40112","Invalid user or password more than 3 times"),

	RE40301("40301","Missing or invalid parameter"),
	RE40302("40302","Unauthorized to access production"),
	RE40303("40303","Unauthorized to access pre production"),
	RE40304("40304","Data existed"),
	RE40310("40310","Inactive User"),
	RE40311("40311","Msisdn is not matched"),
	RE40350("40350","Inactive Partner"),
	RE40351("40351","Registration account attempt was unsuccessful"),
	RE40352("40352","Registration package attempt was unsuccessful"),

	RE40400("40400","Unknown URL"),
	RE40401("40401","Data not found"),

	RE50000("50000","System error"),
	RE50001("50001","DB error"),
	RE50002("50002","Connection timeout"),
	RE50003("50003","Connection error"),
	RE50011("50011","Application is shutting down on the web server"),
	RE50012("50012","Application is busy restarting on the web server"),
	RE50013("50013","Web server is too busy"),
	RE50019("50019","Configuration data is invalid"),
	RE50060("50060","Unknown error"),
	RE50080("50080","Data not found on E01"),
	RE50100("50100","Invalid url structure"),
	RE50200("50200","Bad gateway error"),
	RE50300("50300","Server busy"),
	RE50301("50301","Server unavailable"),
	RE50400("50400","Gateway Timeout error"),

	RE101010("101010",""),

	//Server ResultCode
	RE405000000("405000000","The request is successfully executed."),

	REVSMP00000000("VSMP-00000000","OK."),
	RE2001("2001","Success"),
	RE0000000("0000000","SUCCESS"),
	RE1000000("1000000","SUCCESS"),
	RE1000001("1000001","CANNOT_CONNECT_DB"),
	;

	private String resultCode;
    private String eDesc;

    EResultCode(String resultCode, String eDesc) {
        this.resultCode = resultCode;
        this.eDesc = eDesc;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String geteDesc() {
        return eDesc;
    }

}
