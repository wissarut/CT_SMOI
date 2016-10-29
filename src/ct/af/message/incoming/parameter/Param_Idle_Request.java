package ct.af.message.incoming.parameter;

public class Param_Idle_Request {
	
	boolean isReceived = false;
	boolean isNotMissing = false;
	boolean isValid = false;
	
	private String sgw;
	private String ms;
	private String balance = "";
	private String prmmoney;
	private String prmsm;
	private String prmminute;
	private String point;
	private String freecalltimes;
	private String freerbtsong;
	private String freerbtmf;
	private String score;
	private String prmscore;
	private String smusage;
	private String validity = "";
	private String flag;
	private String merchant;
	private String service;
	private String expire;
	private String ssid;
	private String timeout;
	private String smp;
	private String channel = "";
	private String dat;
	private String scpid = "";	
	private String mode;
	private String screentype = "1";
	private String pkgid;
	private String rtnerr;
	private String type;
	private String value;
	private String delmode;
	private String packageid;
	private String cancel;
	private String charge;                           
	private String prmPoint;                          
	private String rbtSong;   
	private String smDis;            
	private String rewTariff;
	private String fee;
	private String batchno;
	private String pin;
	private String lang;
	private String location = "";
	private String ivrLang;
	private String smsLang;
	private String ussdLang;
	private String emailLang;
	private String page;
	private String increment;
	private String rbtMF;
	private String msgRequest;
	private String transparent_data1 = "";
	private String transparent_data2 = "";
	private String transparent_data3 = "";
	private String prmmoneyusageinfo = "0";
	private String flagCreditlimit = "0";
	private String resetBalance = "0";
	private String resetSecondpocket = "0";
	private String resetPrmmoney = "0";
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIvrLang() {
		return ivrLang;
	}
	public void setIvrLang(String ivrLang) {
		this.ivrLang = ivrLang;
	}
	public String getSmsLang() {
		return smsLang;
	}
	public void setSmsLang(String smsLang) {
		this.smsLang = smsLang;
	}
	public String getUssdLang() {
		return ussdLang;
	}
	public void setUssdLang(String ussdLang) {
		this.ussdLang = ussdLang;
	}
	public String getEmailLang() {
		return emailLang;
	}
	public void setEmailLang(String emailLang) {
		this.emailLang = emailLang;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
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
	public String getSgw() {
		return sgw;
	}
	public void setSgw(String sgw) {
		this.sgw = sgw;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getPrmmoney() {
		return prmmoney;
	}
	public void setPrmmoney(String prmmoney) {
		this.prmmoney = prmmoney;
	}
	public String getPrmsm() {
		return prmsm;
	}
	public void setPrmsm(String prmsm) {
		this.prmsm = prmsm;
	}
	public String getPrmminute() {
		return prmminute;
	}
	public void setPrmminute(String prmminute) {
		this.prmminute = prmminute;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getFreecalltimes() {
		return freecalltimes;
	}
	public void setFreecalltimes(String freecalltimes) {
		this.freecalltimes = freecalltimes;
	}
	public String getFreerbtsong() {
		return freerbtsong;
	}
	public void setFreerbtsong(String freerbtsong) {
		this.freerbtsong = freerbtsong;
	}
	public String getFreerbtmf() {
		return freerbtmf;
	}
	public void setFreerbtmf(String freerbtmf) {
		this.freerbtmf = freerbtmf;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getPrmscore() {
		return prmscore;
	}
	public void setPrmscore(String prmscore) {
		this.prmscore = prmscore;
	}
	public String getSmusage() {
		return smusage;
	}
	public void setSmusage(String smusage) {
		this.smusage = smusage;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public String getSmp() {
		return smp;
	}
	public void setSmp(String smp) {
		this.smp = smp;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	public String getScpid() {
		return scpid;
	}
	public void setScpid(String scpid) {
		this.scpid = scpid;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getScreentype() {
		return screentype;
	}
	public void setScreentype(String screentype) {
		this.screentype = screentype;
	}
	public String getPkgid() {
		return pkgid;
	}
	public void setPkgid(String pkgid) {
		this.pkgid = pkgid;
	}
	public String getRtnerr() {
		return rtnerr;
	}
	public void setRtnerr(String rtnerr) {
		this.rtnerr = rtnerr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDelmode() {
		return delmode;
	}
	public void setDelmode(String delmode) {
		this.delmode = delmode;
	}
	public String getPackageid() {
		return packageid;
	}
	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getPrmPoint() {
		return prmPoint;
	}
	public void setPrmPoint(String prmPoint) {
		this.prmPoint = prmPoint;
	}
	public String getRbtSong() {
		return rbtSong;
	}
	public void setRbtSong(String rbtSong) {
		this.rbtSong = rbtSong;
	}
	public String getSmDis() {
		return smDis;
	}
	public void setSmDis(String smDis) {
		this.smDis = smDis;
	}
	public String getRewTariff() {
		return rewTariff;
	}
	public void setRewTariff(String rewTariff) {
		this.rewTariff = rewTariff;
	}    
	public String getIncrement() {
		return increment;
	}
	public void setIncrement(String increment) {
		this.increment = increment;
	}
	public String getRbtMF() {
		return rbtMF;
	}
	public void setRbtMF(String rbtMF) {
		this.rbtMF = rbtMF;
	}
	public String getMsgRequest() {
		return msgRequest;
	}
	public void setMsgRequest(String msgRequest) {
		this.msgRequest = msgRequest;
	}
	public String getTransparent_data1() {
		return transparent_data1;
	}
	public void setTransparent_data1(String transparent_data1) {
		this.transparent_data1 = transparent_data1;
	}
	public String getTransparent_data2() {
		return transparent_data2;
	}
	public void setTransparent_data2(String transparent_data2) {
		this.transparent_data2 = transparent_data2;
	}
	public String getTransparent_data3() {
		return transparent_data3;
	}
	public void setTransparent_data3(String transparent_data3) {
		this.transparent_data3 = transparent_data3;
	}
	public String getPrmmoneyusageinfo() {
		return prmmoneyusageinfo;
	}
	public void setPrmmoneyusageinfo(String prmmoneyusageinfo) {
		this.prmmoneyusageinfo = prmmoneyusageinfo;
	}
	public String getFlagCreditlimit() {
		return flagCreditlimit;
	}
	public void setFlagCreditlimit(String flagCreditlimit) {
		this.flagCreditlimit = flagCreditlimit;
	}
	public String getResetBalance() {
		return resetBalance;
	}
	public void setResetBalance(String resetBalance) {
		if(resetBalance.equals("1")) {
			this.resetBalance = resetBalance;
		}
	}
	public String getResetSecondpocket() {
		return resetSecondpocket;
	}
	public void setResetSecondpocket(String resetSecondpocket) {
		if(resetSecondpocket.equals("1") || resetSecondpocket.equals("0")) {
			this.resetSecondpocket = resetSecondpocket;
		}
	}
	public String getResetPrmmoney() {
		return resetPrmmoney;
	}
	public void setResetPrmmoney(String resetPrmmoney) {
		if(resetPrmmoney.equals("1") || resetSecondpocket.equals("0")){
			this.resetPrmmoney = resetPrmmoney;
		}
	}
	@Override
	public String toString() {
		return "Param_Idle_Request [sgw=" + sgw + ", ms=" + ms + ", balance=" + balance + ", prmmoney=" + prmmoney
				+ ", prmsm=" + prmsm + ", prmminute=" + prmminute + ", point=" + point + ", freecalltimes="
				+ freecalltimes + ", freerbtsong=" + freerbtsong + ", freerbtmf=" + freerbtmf + ", score=" + score
				+ ", prmscore=" + prmscore + ", smusage=" + smusage + ", validity=" + validity + ", flag=" + flag
				+ ", merchant=" + merchant + ", service=" + service + ", expire=" + expire + ", ssid=" + ssid
				+ ", timeout=" + timeout + ", smp=" + smp + ", channel=" + channel + ", dat=" + dat + ", scpid=" + scpid
				+ ", mode=" + mode + ", screentype=" + screentype + ", pkgid=" + pkgid + ", rtnerr=" + rtnerr
				+ ", type=" + type + ", value=" + value + ", delmode=" + delmode + ", packageid=" + packageid
				+ ", cancel=" + cancel + ", charge=" + charge + ", prmPoint=" + prmPoint + ", rbtSong=" + rbtSong
				+ ", smDis=" + smDis + ", rewTariff=" + rewTariff + ", fee=" + fee + ", batchno=" + batchno + ", pin="
				+ pin + ", lang=" + lang + ", location=" + location + ", ivrLang=" + ivrLang + ", smsLang=" + smsLang
				+ ", ussdLang=" + ussdLang + ", emailLang=" + emailLang + ", page=" + page + ", increment=" + increment
				+ ", rbtMF=" + rbtMF + ", msgRequest=" + msgRequest + "]";
	}
	
	
}
