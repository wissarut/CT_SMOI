package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ct.af.enums.ECType;
import ct.af.enums.EEqxMsg;
import ct.af.enums.EEventType;
import ct.af.enums.EMethod;
import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.message.incoming.parameter.Param_Idle_Request;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;

public class Out_BSS_DoAdjustBalance {

public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Construct EquinoxRawData --//
		EquinoxRawData eqxRawData = new EquinoxRawData();
		eqxRawData.setType(EEventType.REQUEST.getEventType());
		eqxRawData.setCType(ECType.TEXTXML.getCType());
		eqxRawData.setName(EProtocal.HTTP.toString());
		eqxRawData.setTo(Config.BSS_INTERFACE);
		
		Map<String, String> map = new HashMap<String, String>();
			map.put(EEqxMsg.METHOD.getEqxMsg(), EMethod.POST.toString());
		eqxRawData.setRawDataAttributes(map);
		
		StringBuilder strbuilder = new StringBuilder();
		
		Param_Idle_Request inqIdleParameter = (Param_Idle_Request) afSubIns.getClientData();

		boolean balanceChk = StringUtils.isBlank(afSubIns.getClientData().getBalance()); // Balance is null or empty.
		boolean validityChk = StringUtils.isBlank(afSubIns.getClientData().getValidity()); // Validity is null or empty.
		boolean paramChk =  StringUtils.isBlank(inqIdleParameter.getPrmmoney()) // All param is null or empty.
				&& StringUtils.isBlank(inqIdleParameter.getPrmminute())
				&& StringUtils.isBlank(inqIdleParameter.getPoint())
				&& StringUtils.isBlank(inqIdleParameter.getFreecalltimes())
				&& StringUtils.isBlank(inqIdleParameter.getFreerbtsong())
				&& StringUtils.isBlank(inqIdleParameter.getFreerbtmf())
				&& StringUtils.isBlank(inqIdleParameter.getScore())
				&& StringUtils.isBlank(inqIdleParameter.getPrmscore())
				&& StringUtils.isBlank(inqIdleParameter.getSmusage());
		
		String soMode = (StringUtils.isBlank(afSubIns.getClientData().getChannel()))? "IVR" : afSubIns.getClientData().getChannel();
		String ms = afSubIns.getClientData().getMs().substring(2, 11);
		
		String adjustType = "4";
		String fooStr = "";
		if( (!balanceChk || !validityChk) && paramChk) {
			adjustType = "1"; // Pending 1 or 2 or 3?.
			fooStr = "<ns2:SBalance>"
						+ "<ns2:_book_item>"+"100000001"+"</ns2:_book_item>"
						+ "<ns2:_amount>"+afSubIns.getClientData().getBalance()+"</ns2:_amount>"
						+ "<ns2:_days>"+afSubIns.getClientData().getValidity()+"</ns2:_days>"
						+ "<ns2:_phone_id>"+"0"+ms+"</ns2:_phone_id>"
					+ "</ns2:SBalance>";
		} else  {
			if(afSubIns.getClientData().getPrmmoney() != null
					&& !afSubIns.getClientData().getPrmmoney().equals(""))
			{
				fooStr = fooStr + "<ns2:FreeResourceList>"
						+ "<ns2:_phone_id>"+"0"+ms+"</ns2:_phone_id>"
						+ "<ns2:_resource_id>"+Config.RESOURCE_ID_PRMMONEY+"</ns2:_resource_id>"
						+ "<ns2:_expire_date>"+afSubIns.getClientData().getExpire()+"</ns2:_expire_date>"
						+ "<ns2:_resource_request>"+afSubIns.getClientData().getPrmmoney()+"</ns2:_resource_request>"
						+ "<ns2:_resource_unit>"+"Satang"+"</ns2:_resource_unit>"
					+ "</ns2:FreeResourceList>";
			}
			if(afSubIns.getClientData().getPrmsm() != null
					&& !afSubIns.getClientData().getPrmsm().equals(""))
			{
				fooStr = fooStr + "<ns2:FreeResourceList>"
						+ "<ns2:_phone_id>"+"0"+ms+"</ns2:_phone_id>"
						+ "<ns2:_resource_id>"+Config.RESOURCE_ID_PRMSM+"</ns2:_resource_id>"
						+ "<ns2:_expire_date>"+afSubIns.getClientData().getExpire()+"</ns2:_expire_date>"
						+ "<ns2:_resource_request>"+afSubIns.getClientData().getPrmsm()+"</ns2:_resource_request>"
						+ "<ns2:_resource_unit>"+"Piece"+"</ns2:_resource_unit>"
					+ "</ns2:FreeResourceList>";
			}
			if(afSubIns.getClientData().getPrmminute() != null
					&& !afSubIns.getClientData().getPrmminute().equals(""))
			{
				fooStr = fooStr + "<ns2:FreeResourceList>"
						+ "<ns2:_phone_id>"+"0"+ms+"</ns2:_phone_id>"
						+ "<ns2:_resource_id>"+Config.RESOURCE_ID_PRMMINUTE+"</ns2:_resource_id>"
						+ "<ns2:_expire_date>"+afSubIns.getClientData().getExpire()+"</ns2:_expire_date>"
						+ "<ns2:_resource_request>"+afSubIns.getClientData().getPrmminute()+"</ns2:_resource_request>"
						+ "<ns2:_resource_unit>"+"Second"+"</ns2:_resource_unit>"
					+ "</ns2:FreeResourceList>";
			}
			if(afSubIns.getClientData().getFreerbtsong() != null
					&& !afSubIns.getClientData().getFreerbtsong().equals(""))
			{
				fooStr = fooStr + "<ns2:FreeResourceList>"
						+ "<ns2:_phone_id>"+"0"+ms+"</ns2:_phone_id>"
						+ "<ns2:_resource_id>"+Config.RESOURCE_ID_FREERBTSONG+"</ns2:_resource_id>"
						+ "<ns2:_expire_date>"+afSubIns.getClientData().getExpire()+"</ns2:_expire_date>"
						+ "<ns2:_resource_request>"+afSubIns.getClientData().getFreerbtsong()+"</ns2:_resource_request>"
						+ "<ns2:_resource_unit>"+"Kbyte"+"</ns2:_resource_unit>"
					+ "</ns2:FreeResourceList>";
			}
			
		}
		
		DateTimeFormatter formatMSGID = DateTimeFormat.forPattern(";yyyyMMdd;HHmmss;SSS");
		
		strbuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">")
			.append("<soapenv:Header />")
			.append("<soapenv:Body>")
			.append("<ns2:do_AdjustBalance xmlns=\"http://www.asiainfo.com/obd/CommonComponents.obd\" xmlns:ns2=\"http://www.asiainfo.com/obd/InfoSyncDefines.obd\">")
			.append("<ns2:sOper>")
			
				.append("<_so_nbr>"+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+formatMSGID.print(new DateTime())+"</_so_nbr>")
				.append("<_busi_code>"+"1042"+"</_busi_code>")
				.append("<_so_mode>"+soMode+"</_so_mode>") // Pending
				.append("<_source_system>"+"INGW"+"</_source_system>")
				.append("<_so_date>"+new DateTime()+"</_so_date>")
				.append("<_phone_id>"+"0"+ms+"</_phone_id>")
				.append("<_remark>"+afSubIns.getClientData().getSgw()+"</_remark>")
				
			.append("</ns2:sOper>")
			.append("<ns2:_adjust_type>"+adjustType+"</ns2:_adjust_type>") // Pending
			.append("<ns2:_phone_id>"+"0"+ms+"</ns2:_phone_id>")
			.append("<ns2:_flag>"+"0"+"</ns2:_flag>")
			.append("<ns2:_rtner>"+"1"+"</ns2:_rtner>")
				
				.append(fooStr)
			
			.append("</ns2:do_AdjustBalance>")
			.append("</soapenv:Body>")
			.append("</soapenv:Envelope>");
		
		eqxRawData.setRawMessage(strbuilder.toString());
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		eqxRawData.setInvoke(invoke);
		afSubIns.addSubInvoke(invoke);
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.BSS_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BSS_DOADJUSTBALANCE_REQUEST);
		
		return eqxRawData;
	}

}
