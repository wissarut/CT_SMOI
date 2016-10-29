package ct.af.extracterd;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ct.af.enums.ECommand;
import ct.af.enums.EFunctionGroup;
import ct.af.enums.EResultCode;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_Idle_Request;
import ct.af.message.incoming.parser.In_Idle_RequestParser;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class In_Idle_Request {

	public AFSubInstance extractRawData(AbstractAF abstractAF, EC02Instance ec02Instance, AFSubInstance afSubIns,
			EquinoxRawData eqxRawData) {

		DateTimeFormatter formatDateWithMiTz = Config.formatDateWithMiTz;
		DateTime timeStampIn = formatDateWithMiTz
				.parseDateTime(ec02Instance.getAFInstance().getMainTimeStampIncoming());

		String command = ECommand.UNKNOWN.toString();
		EResultCode resultCode = EResultCode.REClient_send_Bad_request;
		EStats statIn = null;
		boolean isValid = false;

		afSubIns = new AFSubInstance();
		afSubIns.setSubInitTimeStampIn(formatDateWithMiTz.print(timeStampIn));

		afSubIns.setSubInitOrig(eqxRawData.getOrig());
		afSubIns.setSubInitInvoke(eqxRawData.getInvoke());
		afSubIns.setSubInitCType(eqxRawData.getCType());
		afSubIns.setSubInitURL(eqxRawData.getRawDataAttribute("url"));
		afSubIns.setSubResultCode(resultCode);
		afSubIns.setSubInitMethod(eqxRawData.getRawDataAttribute("method"));
		afSubIns.setSubTimeout(formatDateWithMiTz.print(timeStampIn.plusYears(1)));

		ESubState currentState = ESubState.Idle_Request;
		Param_Idle_Request paramReq = null;
		
		paramReq = In_Idle_RequestParser.getInstance().doParser(eqxRawData);
		isValid = paramReq.isValid();

		//if(isValid) {
		if(paramReq.getPage() != null) {
			
			if (paramReq.getPage().equals(ECommand.DISPPPSINFO.getCommand())) {
				command = ECommand.DISPPPSINFO.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DISPPPSINFO_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DISPPPSINFO_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.ACTIVATEPPSSINGSUB.getCommand())) {
				command = ECommand.ACTIVATEPPSSINGSUB.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_ACTIVATEPPSSINGSUB_REQUEST;
					resultCode = EResultCode.REClient_send_request;
					
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_ACTIVATEPPSSINGSUB_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.DISPPPSPKGREW.getCommand())) {
				command = ECommand.DISPPPSPKGREW.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DISPPPSPKGREW_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DISPPPSPKGREW_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.MODIPPSVALIDITY.getCommand())) {
				command = ECommand.MODIPPSVALIDITY.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSVALIDITY_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSVALIDITY_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.MODIPPSMULTIATTR.getCommand())) {
				command = ECommand.MODIPPSMULTIATTR.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSMULTIATTR_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSMULTIATTR_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.ADDSCRSCREENNO.getCommand())) {
				command = ECommand.ADDSCRSCREENNO.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_ADDSCRSCREENNO_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_ADDSCRSCREENNO_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.DELESCRSCREENNO.getCommand())) {
				command = ECommand.DELESCRSCREENNO.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DELESCRSCREENNO_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DELESCRSCREENNO_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.MODISCRSCREENTYPE.getCommand())) {
				command = ECommand.MODISCRSCREENTYPE.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODISCRSCREENTYPE_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODISCRSCREENTYPE_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.MODISCRWHITELIST.getCommand())) {
				command = ECommand.MODISCRWHITELIST.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_REQUEST_MODISCRWHITELIST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODISCRWHITELIST_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.DISPSCRSCREENNO.getCommand())) {
				command = ECommand.DISPSCRSCREENNO.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DISPSCRSCREENNO_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DISPSCRSCREENNO_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.ACTPPSRBT.getCommand())) {
				command = ECommand.ACTPPSRBT.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_ACTPPSRBT_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_ACTPPSRBT_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.DELEPPSPKGRES.getCommand())) {
				command = ECommand.DELEPPSPKGRES.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DELEPPSPKGRES_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DELEPPSPKGRES_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.DELEPPSPACKAGEID.getCommand())) {
				command = ECommand.DELEPPSPACKAGEID.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DELEPPSPACKAGEID_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DELEPPSPACKAGEID_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.PURCHASEPPSPACKAGE.getCommand())) {
				command = ECommand.PURCHASEPPSPACKAGE.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_PURCHASEPPSPACKAGE_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_PURCHASEPPSPACKAGE_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.SETPPSREWARD.getCommand())) {
				command = ECommand.SETPPSREWARD.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_SETPPSREWARD_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_SETPPSREWARD_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.DISPPPSFNTELNO.getCommand())) {
				command = ECommand.DISPPPSFNTELNO.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DISPPPSFNTELNO_REQUEST;
					resultCode = EResultCode.REClient_send_request;

				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_DISPPPSFNTELNO_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.MODIPPSLANGUAGE.getCommand())) {
				command = ECommand.MODIPPSLANGUAGE.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSLANGUAGE_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSLANGUAGE_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.MODIPPSSMSLANGUAGE.getCommand())) {
				command = ECommand.MODIPPSSMSLANGUAGE.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSSMSLANGUAGE_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSSMSLANGUAGE_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.MODIPPSCREDITLIMIT.getCommand())) {
				command = ECommand.MODIPPSCREDITLIMIT.getCommand();
				
				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSCREDITLIMIT_REQUEST;
					resultCode = EResultCode.REClient_send_request;
				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSCREDITLIMIT_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else if (paramReq.getPage().equals(ECommand.CHGTRIGCHRGACNT.getCommand())) {
				command = ECommand.CHGTRIGCHRGACNT.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_CHGTRIGCHRGACNT_REQUEST;
					resultCode = EResultCode.REClient_send_request;

					afSubIns.setSubDataForward(paramReq.getMsgRequest());

				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_CHGTRIGCHRGACNT_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}
			
			else if (paramReq.getPage().equals(ECommand.MODIPPSMULTIWALLET.getCommand())) {
				command = ECommand.MODIPPSMULTIWALLET.getCommand();

				if (isValid) {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSMULTIWALLET_REQUEST;
					resultCode = EResultCode.REClient_send_request;

					afSubIns.setSubDataForward(paramReq.getMsgRequest());

				} else {
					statIn = EStats.INGATEWAY_RECEIVE_HTTP_MODIPPSMULTIWALLET_REQUEST_MISSING;
					resultCode = EResultCode.REClient_send_Bad_request;

					isValid = false;
				}
			}

			else {

				resultCode = EResultCode.REClient_send_request;
				statIn = EStats.INGATEWAY_RECEIVE_UNKNOWN_REQUEST;

				command = ECommand.FEEDFORWARD.getCommand();
				currentState = ESubState.Idle_Request;

				afSubIns.setSubDataForward(paramReq.getMsgRequest());

			}
		
		} else if(paramReq.getMsgRequest().contains("rewardInfo")) {
			
			if(ec02Instance.getAFInstance().getRmfTimeStamp() != null) {
				command = ECommand.RMC.getCommand();
				currentState = ESubState.Idle_RMC;
				try {
					
					JSONParser parser = new JSONParser();
					JSONObject object = (JSONObject)parser.parse(paramReq.getMsgRequest());
					
					JSONObject userIdObj = (JSONObject) object.get("userId");
					
					paramReq.setMs(userIdObj.get("userIdData").toString());
					
//					currentState = ESubState.Idle_RMC;
					
					isValid = true;
					statIn = EStats.INGATEWAY_SEND_RMF_RMCR_REQUEST;
					resultCode = EResultCode.REClient_send_request;
					
				} catch (Exception e) {
					statIn = EStats.INGATEWAY_SEND_HTTP_INCOMPLETE_DATA_RESPONSE;
					resultCode = EResultCode.REClient_send_Bad_request;
	
					isValid = false;
				}
			} else {
				statIn = EStats.INGATEWAY_RECEIVE_UNKNOWN_REQUEST;
				resultCode = EResultCode.REClient_send_Bad_request;

				isValid = false;
				
				afSubIns.setSubNextState("END");
			}
			
		} else {
			statIn = EStats.INGATEWAY_RECEIVE_UNKNOWN_REQUEST;
			resultCode = EResultCode.REClient_send_Bad_request;

			isValid = false;
		}
		
		
		afSubIns.setSubInstanceNo(AFUtils.subInsNoGenerator(ec02Instance, command));

		afSubIns.setSubInitEvent(command);
		afSubIns.setSubInitCmd(command);
		afSubIns.setSubFunctionGroup(EFunctionGroup.IncomingFromClient.toString());

		if(paramReq != null) {
			afSubIns.setClientData(paramReq);
			afSubIns.setSubMSISDN(paramReq.getMs());
			afSubIns.setSubDataForward(paramReq.getMsgRequest());
		}
		afSubIns.setSubCurrentState(currentState.toString());
		afSubIns.setSubControlState(currentState.toString());

		afSubIns.setSubIsValid(isValid);
		afSubIns.setSubIsRetNormal(true);
		afSubIns.setSubResultCode(resultCode);
		afSubIns.addStatsIn(statIn);
		
		ec02Instance.getAFInstance().incrementMainCountWait();

		return afSubIns;

	}

}