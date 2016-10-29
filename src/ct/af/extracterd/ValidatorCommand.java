package ct.af.extracterd;

import java.util.HashMap;
import ct.af.enums.ECommand;
import ct.af.message.incoming.parameter.Param_Idle_Request;
import ct.af.message.incoming.parser.Validator;
import ct.af.utils.StringUtils;
import ec02.utils.AppLog;

public class ValidatorCommand {

	public boolean validateParam(Param_Idle_Request paramReq, HashMap<String, String> attributes, String command) {

		paramReq.setDat(attributes.get("dat"));
		paramReq.setTimeout(attributes.get("timeout"));
		paramReq.setSmp(attributes.get("smp"));
		paramReq.setScpid(attributes.get("scpid"));
		paramReq.setChannel(attributes.get("channel"));
		
		if(StringUtils.isNotBlank(attributes.get("resetbalance"))){
			paramReq.setResetBalance(attributes.get("resetbalance"));
		}
		if(StringUtils.isNotBlank(attributes.get("resetsecondpocket"))){
			paramReq.setResetSecondpocket(attributes.get("resetsecondpocket"));
		}
		if(StringUtils.isNotBlank(attributes.get("resetprmmoney"))){
			paramReq.setResetPrmmoney(attributes.get("resetprmmoney"));
		}

		if (command.equalsIgnoreCase(ECommand.DISPPPSINFO.getCommand())) {
			paramReq.setPrmmoneyusageinfo(attributes.get("prmmoneyusageinfo"));
		} else if (command.equalsIgnoreCase(ECommand.DISPPPSPKGREW.getCommand())) {
			// No Check
		} else if (command.equalsIgnoreCase(ECommand.MODIPPSVALIDITY.getCommand())) {

			if (attributes.containsKey("increment")) {
				if(!StringUtils.isBlank(attributes.get("increment"))) {
					paramReq.setIncrement(attributes.get("increment"));
				} else {
					AppLog.e("[Missing] increment parameter");
					return false;
				}
			} else {
				AppLog.e("[Missing] increment parameter");
				return false;
			}

			paramReq.setFlag(attributes.get("flag"));

		} else if (command.equalsIgnoreCase(ECommand.MODIPPSMULTIATTR.getCommand())) {
			
			paramReq.setBalance(attributes.get("balance"));
			paramReq.setPrmmoney(attributes.get("prmmoney"));
			paramReq.setPrmsm(attributes.get("prmsm"));
			paramReq.setPrmminute(attributes.get("prmminute"));
			paramReq.setPoint(attributes.get("point"));
			paramReq.setFreecalltimes(attributes.get("freecalltimes"));
			paramReq.setFreerbtsong(attributes.get("freerbtsong"));
			paramReq.setFreerbtmf(attributes.get("freerbtmf"));
			paramReq.setScore(attributes.get("score"));
			paramReq.setPrmscore(attributes.get("prmscore"));
			paramReq.setSmusage(attributes.get("smusage"));
			paramReq.setValidity(attributes.get("validity"));
			paramReq.setFlag(attributes.get("flag"));
			paramReq.setMerchant(attributes.get("merchant"));
			paramReq.setService(attributes.get("service"));
			paramReq.setExpire(attributes.get("expire"));
			paramReq.setTransparent_data1(attributes.get("transparent_data1"));
			paramReq.setTransparent_data2(attributes.get("transparent_data2"));
			paramReq.setTransparent_data3(attributes.get("transparent_data3"));

		} else if (command.equalsIgnoreCase(ECommand.ADDSCRSCREENNO.getCommand())
				|| command.equalsIgnoreCase(ECommand.DELESCRSCREENNO.getCommand())) {

			if (!attributes.get("dat").isEmpty()) {
				
				if(attributes.get("dat").contains("&")) {
					
					try {
				
						String[] datData = attributes.get("dat").split("&",-1);
						
						for(String str:datData) {
							
							if(str.length() > 20 && Validator.isAlphaNumeric(str)) {
								AppLog.e("[Invalid] dat parameter is more 20 digit or is not 0-1,a-z,A-Z.");
								return false;
							}
							
						}
						
						paramReq.setDat(attributes.get("dat"));
					
					} catch(Exception ex) {
						AppLog.e("[Error] Cannot parse dat parameter.");
						return false;
					}
					
				} else {
					
					if(attributes.get("dat").length() > 20 && Validator.isAlphaNumeric(attributes.get("dat"))) {
						AppLog.e("[Invalid] dat parameter is more 20 digit or is not 0-1,a-z,A-Z.");
						return false;
					}
					
					paramReq.setDat(attributes.get("dat"));
					
				}
				
			} else {
				AppLog.e("[Missing] dat parameter.");
				return false;
			}

			paramReq.setMode(attributes.get("mode"));
			paramReq.setScreentype(attributes.get("screentype"));

		} else if (command.equalsIgnoreCase(ECommand.MODISCRSCREENTYPE.getCommand())) {

			if (attributes.containsKey("dat")) {
				paramReq.setDat(attributes.get("dat"));
			} else {
				AppLog.e("[Missing] dat parameter.");
				return false;
			}
			
			paramReq.setMode(attributes.get("mode"));

		} else if (command.equalsIgnoreCase(ECommand.MODISCRWHITELIST.getCommand())) {

			if (attributes.containsKey("dat")) {
				paramReq.setDat(attributes.get("dat"));
			} else {
				AppLog.e("[Missing] dat parameter.");
				return false;
			}
			
			paramReq.setMode(attributes.get("mode"));

		} else if (command.equalsIgnoreCase(ECommand.DISPSCRSCREENNO.getCommand())) {

			if (attributes.containsKey("dat")) {
				paramReq.setDat(attributes.get("dat"));
			} else {
				AppLog.e("[Missing] dat parameter.");
				return false;
			}
			
			paramReq.setMode(attributes.get("mode"));

		} else if (command.equalsIgnoreCase(ECommand.ACTPPSRBT.getCommand())) {

			if (attributes.containsKey("dat")) {
				paramReq.setDat(attributes.get("dat"));
			} else {
				AppLog.e("[Missing] dat parameter.");
				return false;
			}

			paramReq.setMode(attributes.get("mode"));

		} else if (command.equalsIgnoreCase(ECommand.DELEPPSPKGRES.getCommand())) {

			if (attributes.containsKey("pkgid")) {
				paramReq.setPkgid(attributes.get("pkgid"));
			} else {
				AppLog.e("[Missing] pkgid parameter.");
				return false;
			}

			paramReq.setRtnerr(attributes.get("rtnerr"));

		} else if (command.equalsIgnoreCase(ECommand.DELEPPSPACKAGEID.getCommand())) {

			if (attributes.containsKey("packageid")) {
				paramReq.setPackageid(attributes.get("packageid"));
			} else {
				AppLog.e("[Missing] packageid parameter.");
				return false;
			}

			paramReq.setCancel(attributes.get("cancel"));

		} else if (command.equalsIgnoreCase(ECommand.PURCHASEPPSPACKAGE.getCommand())) {

			if (attributes.containsKey("packageid")) {
				paramReq.setPackageid(attributes.get("packageid"));
			} else {
				AppLog.e("[Missing] packageid parameter.");
				return false;
			}

		} else if (command.equalsIgnoreCase(ECommand.SETPPSREWARD.getCommand())) {

			paramReq.setBalance(attributes.get("balance"));
			paramReq.setPrmmoney(attributes.get("prmmoney"));
			paramReq.setPrmsm(attributes.get("prmsm"));
			paramReq.setPrmminute(attributes.get("prmminute"));
			paramReq.setPrmPoint(attributes.get("prmpoint"));
			paramReq.setFreecalltimes(attributes.get("freecalltimes"));
			paramReq.setRbtSong(attributes.get("rbtsong"));
			paramReq.setRbtMF(attributes.get("rbtmf"));
			paramReq.setValidity(attributes.get("validity"));
			paramReq.setExpire(attributes.get("expire"));
			paramReq.setFlag(attributes.get("flag"));

		} else if (command.equalsIgnoreCase(ECommand.DISPPPSFNTELNO.getCommand())) {

			if (attributes.containsKey("dat")) {
				paramReq.setDat(attributes.get("dat"));
			} else {
				AppLog.e("[Missing] dat parameter.");
				return false;
			}

		} else if (command.equalsIgnoreCase(ECommand.MODIPPSLANGUAGE.getCommand())) {

			if (attributes.containsKey("lang")) {
				
				if(attributes.get("lang").length() < 2) {
					
					paramReq.setIvrLang(attributes.get("lang"));
					paramReq.setSmsLang(attributes.get("lang"));
					paramReq.setUssdLang(attributes.get("lang"));
					paramReq.setEmailLang(attributes.get("lang"));
					
				} else {
					
					paramReq.setIvrLang(attributes.get("lang"));
					paramReq.setSmsLang(attributes.get("lang").substring(1, 2));
					paramReq.setUssdLang(attributes.get("lang").substring(1, 2));
					paramReq.setEmailLang(attributes.get("lang").substring(1, 2));
					
				}
				
			} else {
				AppLog.e("[Missing] lang parameter.");
				return false;
			}

		} else if (command.equalsIgnoreCase(ECommand.MODIPPSSMSLANGUAGE.getCommand())) {
			
			if (attributes.containsKey("lang")) {
				
				if(attributes.get("lang").length() < 2) {
					
					paramReq.setIvrLang(attributes.get("lang"));
					paramReq.setSmsLang(attributes.get("lang"));
					paramReq.setUssdLang(attributes.get("lang"));
					paramReq.setEmailLang(attributes.get("lang"));
					
				} else {
					
					paramReq.setIvrLang(attributes.get("lang").substring(0, 1));
					paramReq.setSmsLang(attributes.get("lang"));
					paramReq.setUssdLang(attributes.get("lang").substring(1, 2));
					paramReq.setEmailLang(attributes.get("lang").substring(1, 2));
					
				}
				
			} else {
				AppLog.e("[Missing] lang parameter.");
				return false;
			}
			
			paramReq.setMode(attributes.get("mode"));

		} else if (command.equalsIgnoreCase(ECommand.MODIPPSCREDITLIMIT.getCommand())) {

			paramReq.setFlagCreditlimit(attributes.get("flag"));
			if (attributes.containsKey("increment")) {
				if(StringUtils.isInteger(attributes.get("increment"))) {
					paramReq.setIncrement(attributes.get("increment"));
				} else {
					
					AppLog.e("[Invalid] increment parameter.");
					return false;
				}
			} else {
				AppLog.e("[Missing] increment parameter.");
				return false;

			}

			paramReq.setMode(attributes.get("mode"));

		} else if (command.equalsIgnoreCase(ECommand.CHGTRIGCHRGACNT.getCommand())) {

			if (attributes.containsKey("pin")) {
				paramReq.setPin(attributes.get("pin"));
			} else {
				AppLog.e("[Missing] pin parameter.");
				return false;
			}

			if (attributes.containsKey("dat")) {
				paramReq.setDat(attributes.get("dat"));
			} else {
				AppLog.e("[Missing] dat parameter.");
				return false;
			}

		} else if (command.equalsIgnoreCase(ECommand.ACTIVATEPPSSINGSUB.getCommand())) {
			
			int langSize = 0;
			
			if (attributes.containsKey("lang")) {

				langSize = attributes.get("lang").length();
				paramReq.setLang(attributes.get("lang"));

			}
			
			AppLog.d("langSize : "+langSize);
			
			if (attributes.containsKey("ivrlang")) {
				paramReq.setIvrLang(attributes.get("ivrlang"));
			} else {
				if (langSize > 0) {
					paramReq.setIvrLang(paramReq.getLang().substring(0, 1));
				}
			}
			
			if (attributes.containsKey("smslang")) {
				paramReq.setSmsLang(attributes.get("smslang"));
			} else {
				if (langSize == 2) {
					paramReq.setSmsLang(paramReq.getLang().substring(1, 2));
				}
			}
			

			if (attributes.containsKey("ussdlang")) {
				paramReq.setUssdLang(attributes.get("ussdlang"));
			}

			if (attributes.containsKey("emaillang")) {
				paramReq.setEmailLang(attributes.get("emaillang"));
			}
			
			AppLog.d("Lang : ivrlang["+paramReq.getIvrLang()+"],smslang["+paramReq.getSmsLang()+"],ussdlang["+paramReq.getUssdLang()+"],emaillang["+paramReq.getEmailLang()+"]");

			paramReq.setDat(attributes.get("dat"));
			paramReq.setLocation(attributes.get("location"));

		}

		return true;
		
	}

}
