package ct.af.message.incoming.parser;

import java.util.HashMap;
import ct.af.utils.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.extracterd.ValidatorCommand;
import ct.af.message.incoming.parameter.Param_Idle_Request;
import ct.af.message.model.ERDData;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class In_Idle_RequestParser {
	
	private static In_Idle_RequestParser parser = null;
	
	protected  In_Idle_RequestParser() {
	  
	}
	public static In_Idle_RequestParser getInstance() {
		if(parser == null) {
			parser = new In_Idle_RequestParser();
		}
		return parser;
	}

	public Param_Idle_Request doParser(EquinoxRawData eqxRawData) {

		Param_Idle_Request paramReq = new Param_Idle_Request();
		String eqxMsg = null;

		if(eqxRawData.getCType().equals(ECType.TEXTPLAIN.getCType())) {

			eqxMsg = eqxRawData.getRawDataAttribute(EEqxMsg.VAL.getEqxMsg());

		} else {

			try {

				Serializer serializer = new Persister();

			     ERDData erdData = serializer.read(ERDData.class, "<xml>"+eqxRawData.getRawDataMessage()+"</xml>");
			     eqxMsg = erdData.getValue();

			} catch (Exception e) {
			     AppLog.e("[Exception] Error xml parser");
			     paramReq.setValid(false);
				 return paramReq;
			}

		}

		eqxMsg = eqxMsg.replaceAll("\\s+", "");
		paramReq.setMsgRequest(eqxMsg);

		if(eqxMsg != null
				&& eqxMsg.contains("page")) {

			try {

				HashMap<String, String> attributes = new HashMap<String, String>();

				String[] inputArr = eqxMsg.split("&",-1);
				String currentKey = "";

				for(String att:inputArr) {

					if(att.contains("=")) {
						String[] inputVal = att.split("=",-1);

						if(!StringUtils.isBlank(inputVal[0])) {

							attributes.put(inputVal[0].toLowerCase(), inputVal[1]);
							currentKey = inputVal[0];

						} else {
							AppLog.e("[Missing] Can not get key.");
							return paramReq;
						}

					} else {

						if(currentKey.equalsIgnoreCase("dat")) {

							String dat = attributes.get("dat");
							attributes.put("dat",dat+"&"+att);

						} else {

							AppLog.e("[Missing] Unknow paramter in message request");
							return paramReq;

						}

					}

				}

				if (attributes.containsKey("page")
						&& !StringUtils.isBlank(attributes.get("page"))) {
					paramReq.setPage(attributes.get("page"));
				} else {
					AppLog.e("[Missing] page is null");
					return paramReq;
				}

				if (attributes.containsKey("ms")
						&& !StringUtils.isBlank(attributes.get("ms"))) {

					if(!Validator.isMsisdnFormat(attributes.get("ms"), "66")) {
						AppLog.e("[Invalid] Msisdn: ");
						return paramReq;
					} else {
						paramReq.setMs(attributes.get("ms"));
					}

				} else {
					AppLog.e("[Missing] ms is null");
					return paramReq;
				}

				if (attributes.containsKey("ssid")
						&& !StringUtils.isBlank(attributes.get("ssid"))) {
					paramReq.setSsid(attributes.get("ssid"));
				} else {
					AppLog.e("[Missing] ssid is null");
					return paramReq;
				}

				if (attributes.containsKey("sgw")
						&& !StringUtils.isBlank(attributes.get("sgw"))) {
					paramReq.setSgw(attributes.get("sgw"));
				} else {
					AppLog.e("[Missing] sgw is null");
					return paramReq;
				}
				
				if (attributes.containsKey("sgw")
						&& !StringUtils.isBlank(attributes.get("sgw"))) {
					paramReq.setSgw(attributes.get("sgw"));
				} else {
					AppLog.e("[Missing] sgw is null");
					return paramReq;
				}
				
				if (attributes.get("page").equals("purchasePPSPackage")
						|| attributes.get("page").equals("delePPSPackageID"))
				{
					if (attributes.containsKey("packageid")) {
						paramReq.setPkgid(attributes.get("packageid"));
					} else {
						AppLog.e("[Missing] packageid is null");
						return paramReq;
					}
				}

				Boolean validParam = new ValidatorCommand().validateParam(paramReq, attributes, attributes.get("page"));

				AppLog.d(paramReq.toString().replaceAll("=null", ""));

				paramReq.setValid(validParam);
				return paramReq;

			} catch (Exception e) {
				AppLog.e("[Exception] Error request parser");
				paramReq.setValid(false);
				return paramReq;
			}

		} else {
			paramReq.setValid(false);
			return paramReq;
		}

	}
}
