package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;
import org.joda.time.DateTime;

import ct.af.enums.ECType;
import ct.af.enums.ECommand;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EMethod;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_SGSCPSMOI_ForwardCommand {
	
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
	
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTPLAIN.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.SGSCPSMOI_INTERFACE);
		
		StringBuilder strbuilder = new StringBuilder();
		
		String scp = "";
		if(ec02Instance.getAFInstance().getMainLocation() != null) {
			scp = "&amp;scpid="+ec02Instance.getAFInstance().getMainLocation();
		}
		
		strbuilder.append(StringEscapeUtils.escapeHtml4(afSubIns.getSubDataForward())+scp);
	
		Map<String, String> map = new HashMap<String, String>();
		map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
		map.put(EEqxMsg.VAL.getEqxMsg(), strbuilder.toString());
		eqxRawData.setRawDataAttributes(map);
	
		//eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd()
				,afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
	
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.SGSCPSMOI_TIMEOUT)));
		
		//-- Stats --//
		String cmd = afSubIns.getSubInitCmd();
		if(cmd.equals(ECommand.DISPPPSINFO.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_DISPPPSINFO_REQUEST);
		} else if(cmd.equals(ECommand.DISPPPSPKGREW.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_DISPPPSKGREW_REQUEST);
		} else if(cmd.equals(ECommand.MODIPPSVALIDITY.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_MODIPPSVALIDITY_REQUEST);
		} else if(cmd.equals(ECommand.MODIPPSMULTIATTR.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_MODIPPSMULTIATTR_REQUEST);
		} else if(cmd.equals(ECommand.ADDSCRSCREENNO.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_ADDSCRSCREENNO_REQUEST);
		} else if(cmd.equals(ECommand.DELESCRSCREENNO.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_DELESCRSCREENNO_REQUEST);
		} else if(cmd.equals(ECommand.MODISCRSCREENTYPE.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_MODISCRSCREENTYPE_REQUEST);
		} else if(cmd.equals(ECommand.MODISCRWHITELIST.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_MODISCRWHITELIST_REQUEST);
		} else if(cmd.equals(ECommand.DISPSCRSCREENNO.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_DISPSCRSCREENNO_REQUEST);
		} else if(cmd.equals(ECommand.ACTPPSRBT.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_ACTPPSRBT_REQUEST);
		} else if(cmd.equals(ECommand.DELEPPSPKGRES.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_DELEPPSPKGRES_REQUEST);
		} else if(cmd.equals(ECommand.DELEPPSPACKAGEID.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_DELEPPSPACKAGEID_REQUEST);
		} else if(cmd.equals(ECommand.PURCHASEPPSPACKAGE.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_PURCHASEPPSPACKAGE_REQUEST);
		} else if(cmd.equals(ECommand.SETPPSREWARD.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_SETPPSREWARD_REQUEST);
		} else if(cmd.equals(ECommand.DISPPPSFNTELNO.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_DISPPPSFNTELNO_REQUEST);
		} else if(cmd.equals(ECommand.MODIPPSLANGUAGE.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_MODIPPSLANGUAGE_REQUEST);
		} else if(cmd.equals(ECommand.MODIPPSSMSLANGUAGE.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_MODIPPSSMSLANGUAGE_REQUEST);
		} else if(cmd.equals(ECommand.MODIPPSCREDITLIMIT.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_MODIPPSCREDITLIMIT_REQUEST);
		} else if(cmd.equals(ECommand.CHGTRIGCHRGACNT.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_CHGTRIGCHRGACNT_REQUEST);
		} else if(cmd.equals(ECommand.ACTIVATEPPSSINGSUB.getCommand())) {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SGSCP_SMOI_ACTIVATEPPSSINGSUB_REQUEST);
		} else {
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_SMOI_FORWARDCOMMAND_REQUEST);
		}
	
		return eqxRawData;
	}

}
