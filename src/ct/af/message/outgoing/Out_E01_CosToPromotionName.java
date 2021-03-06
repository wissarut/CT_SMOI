package ct.af.message.outgoing;

import org.joda.time.DateTime;

import ct.af.enums.EProtocal;
import ct.af.enums.EStats;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AFUtils;
import ct.af.utils.Config;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.af.data.KeyObject;

public class Out_E01_CosToPromotionName {

	@SuppressWarnings("deprecation")
	public EquinoxRawData messageBuilder(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns) {
		
		//-- Invoke --//
		String invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
		afSubIns.addSubInvoke(invoke);
		abstractAF.getUtils().getGlobalData().setMessageId(invoke);
				
		String e01Object = "CosToPromotionName";
		KeyObject keyObj = new KeyObject(e01Object,new String[] {"000",afSubIns.getOldCos(),"def","def","def"});
		abstractAF.getUtils().getGlobalData().search(keyObj,invoke);
		
		StringBuilder msg = new StringBuilder();
        msg.append("[ObjectType=").append(keyObj.getObjectType())
                .append("&messageId=").append(invoke)
                .append("&key0=").append(keyObj.getKey0())
                .append("&key1=").append(keyObj.getKey1())
                .append("&key2=").append(keyObj.getKey2())
                .append("&key3=").append(keyObj.getKey3())
                .append("&key4=").append(keyObj.getKey4()+"]");
        
        EquinoxRawData eqxRawData = new EquinoxRawData();
        eqxRawData.setName(EProtocal.DB.toString());
        
		
		if(!afSubIns.getOldCos().equals(afSubIns.getNewCos())) {
			
			new AFUtils();
			//-- Invoke --//
			invoke = AFUtils.invokeGenerator(abstractAF,afSubIns.getSubInitInvoke(),afSubIns.getSubInitCmd(),afSubIns.getSubNextState(),afSubIns.getSubInstanceNo(),abstractAF.getEquinoxProperties().getSession());
			afSubIns.addSubInvoke(invoke);
			abstractAF.getUtils().getGlobalData().setMessageId(invoke);
			
			KeyObject keyObj2 = new KeyObject(e01Object,new String[] {"000",afSubIns.getNewCos(),"def","def","def"});
			abstractAF.getUtils().getGlobalData().search(keyObj2,invoke);
			
			msg.append(",[ObjectType=").append(keyObj2.getObjectType())
             .append("&messageId=").append(invoke)
					.append("&key0=").append(keyObj2.getKey0())
					.append("&key1=").append(keyObj2.getKey1())
					.append("&key2=").append(keyObj2.getKey2())
					.append("&key3=").append(keyObj2.getKey3())
             .append("&key4=").append(keyObj.getKey4()+"]");
			
			afSubIns.addStatsOut(EStats.INGATEWAY_SEND_E01_QUERY_COSTOPROMOTIONNAME_REQUEST);
		}
		
		eqxRawData.setRawMessage(msg.toString());
		
		//-- Timeout --//
		afSubIns.setSubTimeout(Config.formatDateWithMiTz.print(new DateTime().plusSeconds(Config.E01_TIMEOUT)));
		
		//-- Stats --//
		afSubIns.addStatsOut(EStats.INGATEWAY_SEND_E01_QUERY_COSTOPROMOTIONNAME_REQUEST);
		
		return eqxRawData;
	}

}
