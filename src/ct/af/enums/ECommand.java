package ct.af.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ECommand {

	ALL_COMMANCD("allCommand"),
	DISPPPSINFO("dispPPSInfo"),
	DISPPPSPKGREW("dispPPSPkgrew"),
	MODIPPSVALIDITY("modiPPSValidity"),
	MODIPPSMULTIATTR("modiPPSMultiAttr"),
	ADDSCRSCREENNO("addScrScreenNo"),
	DELESCRSCREENNO("deleScrScreenNo"),
	MODISCRSCREENTYPE("modiScrScreenType"),
	MODISCRWHITELIST("modiScrWhiteList"),
	DISPSCRSCREENNO("dispScrScreenNo"),
	ACTPPSRBT("actPPSRBT"),
	DELEPPSPKGRES("delePPSPkgres"),
	DELEPPSPACKAGEID("delePPSPackageID"),
	PURCHASEPPSPACKAGE("purchasePPSPackage"),
	SETPPSREWARD("setPPSReward"),
	DISPPPSFNTELNO("dispPPSFntelNo"),
	MODIPPSLANGUAGE("modiPPSLanguage"),
	MODIPPSSMSLANGUAGE("modiPPSSMSLanguage"),
	MODIPPSCREDITLIMIT("modiPPSCreditLimit"),
	CHGTRIGCHRGACNT("chgtrigChrgAcnt"),
	ACTIVATEPPSSINGSUB("activatePPSSingSub"),
	MODIPPSMULTIWALLET("modiPPSMultiWallet"),
	
	FEEDFORWARD("feedForward"),
	
	RMC("RMC"),
    UNKNOWN("UNKNOWN"),
    ;
	
	private String name;
	ECommand(String name){
        this.name = name;
    }

    public String getCommand() {
        return name;
    }
    
    private static final Map<String, ECommand> ecommandMap = new HashMap<String, ECommand>();

    static {
        for (ECommand value : EnumSet.allOf(ECommand.class)) {
        	ecommandMap.put(value.name(), value);
        }
    }

    public static ECommand forName(String name) {
        return ecommandMap.get(name);
    }
    
    
}
