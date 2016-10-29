package ct.af.message.outgoing;

import java.util.HashMap;
import java.util.Map;
import ct.af.utils.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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

public class Out_BSS_DoChangeService {

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
		
		String cmd = afSubIns.getSubInitCmd();
		
		String soMode = (StringUtils.isBlank(afSubIns.getClientData().getChannel()))? "IVR" : afSubIns.getClientData().getChannel();
		String siteId = (StringUtils.isBlank(afSubIns.getClientData().getScpid()))? ec02Instance.getAFInstance().getMainLocation() : afSubIns.getClientData().getScpid();
		
		String operType = (StringUtils.isBlank(afSubIns.getClientData().getDat()))? "" : afSubIns.getClientData().getDat();
		String sourceSystem = (StringUtils.isBlank(afSubIns.getClientData().getSgw()))? "" : afSubIns.getClientData().getSgw();
		String remarkStr = "";
		String productIdStr = "";
		String productClassStr = "";
		if( cmd.equals(ECommand.DELEPPSPKGRES.getCommand())
				|| cmd.equals(ECommand.DELEPPSPACKAGEID.getCommand()) )
		{
			operType = "1";
			productIdStr = "<com:_product_id>"+afSubIns.getClientData().getPkgid()+"</com:_product_id>";
		} else if( cmd.equals(ECommand.PURCHASEPPSPACKAGE.getCommand()) ) {
			operType = "1";
			sourceSystem = "INGW";
			remarkStr = "<_remark>"+afSubIns.getClientData().getSgw()+"</_remark>";
			productIdStr = "<com:_product_id>"+afSubIns.getClientData().getPkgid()+"</com:_product_id>";
			productClassStr = "<com:_product_class>"+afSubIns.getClientData().getPkgid()+"</com:_product_class>";
		}
		
		String ms = afSubIns.getClientData().getMs().substring(2, 11);
		
		DateTimeFormatter formatMSGID = DateTimeFormat.forPattern(";yyyyMMdd;HHmmss;SSS");

		strbuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:inf=\"http://www.asiainfo.com/obd/InfoSyncDefines.obd\" xmlns:com=\"http://www.asiainfo.com/obd/CommonComponents.obd\">")
           .append("<soapenv:Header/>")
           .append("<soapenv:Body>")
              .append("<inf:do_ChangeService>")
                 .append("<inf:sOper>")
                 	
                    .append("<com:_so_nbr>"+afSubIns.getClientData().getSgw()+"@"+afSubIns.getClientData().getSsid()+formatMSGID.print(new DateTime())+"</com:_so_nbr>")
                    .append("<com:_busi_code>"+"1021"+"</com:_busi_code>")
                    .append("<com:_so_mode>"+soMode+"</com:_so_mode>")
                    .append("<com:_source_system>"+sourceSystem+"</com:_source_system>")
                    .append("<com:_charge_flag>"+"1"+"</com:_charge_flag>")
                    .append("<com:_so_date>"+new DateTime()+"</com:_so_date>")
                    .append("<com:_phone_id>"+"0"+ms+"</com:_phone_id>")
                    .append(remarkStr)
                    
                 .append("</inf:sOper>")
                 .append("<inf:ChangeService>")
                 
                    .append("<inf:_phone_id>"+"0"+ms+"</inf:_phone_id>")
                    .append("<inf:SProductOrderOperList>")
                       .append("<com:SProductOrderOperList_Item>")
                          .append("<com:_oper_type>"+operType+"</com:_oper_type>")
                          .append("<com:SProductOrderList>")
                             .append("<com:SProductOrderList_Item>")
                             	.append(productIdStr)
                                .append(productClassStr)
                             .append("</com:SProductOrderList_Item>")
                          .append("</com:SProductOrderList>")
                       .append("</com:SProductOrderOperList_Item>")
                    .append("</inf:SProductOrderOperList>")
                    
                 .append("</inf:ChangeService>")
                 .append("<inf:_brand_id>"+afSubIns.getInquirySubParam().getResultInfo().get(0).getDs2brandId()+"</inf:_brand_id>") // Pending prefix
                 .append("<inf:_site_id>"+siteId+"</inf:_site_id>") // Pending prefix 
              .append("</inf:do_ChangeService>")
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
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_BSS_DOCHANGESERVICE_REQUEST);
		
		return eqxRawData;
	}

}
