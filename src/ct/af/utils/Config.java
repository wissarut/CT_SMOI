package ct.af.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import ct.af.enums.EConfig;
import ec02.af.abstracts.AbstractAF;
import ec02.utils.AppLog;

public class Config {

	public static boolean hasReloadConfig = true;
    
    public static DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    public static DateTimeFormatter formatDateWithMi = DateTimeFormat.forPattern("yyyyMMdd HH:mm:ss.SSS"); //Log
    public static DateTimeFormatter formatDateWithMiTz = DateTimeFormat.forPattern("yyyyMMdd HH:mm:ss.SSSZ"); //System logic
    
    public static DateTimeFormatter formatDateWithHyphen = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter formatDateWithSemiCo = DateTimeFormat.forPattern("YYYYMMdd;HHmmss;SSS");
    public static DateTimeFormatter formatDateWithBetDT = DateTimeFormat.forPattern("YYYYMMdd:HHmmss");
    public static DateTimeFormatter formatDateWithT = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZ");
    
    public static String DETAILLOG_NAME;
    public static String SUMMARYLOG_NAME;
    
    public static int DETAIL_LOG_ENABLED;
    public static boolean DETAIL_LOG_RAWDATA_ENABLED;
    public static boolean DETAIL_LOG_DATA_ENABLED;
    
    public static String USMP_INTERFACE;
    public static String DS2A_INTERFACE;
    public static String AMF_INTERFACE;
    public static String BMP_INTERFACE;
    public static String BOS_INTERFACE;
    public static String BSS_INTERFACE;
    public static String RMF_INTERFACE;
    public static String SGSCPPPGW_INTERFACE;
    public static String SGSCPSMOI_INTERFACE;
    public static String SRFC_INTERFACE;
    public static String SSB_INTERFACE;
    public static String SDF_INTERFACE;
    public static String TMF_INTERFACE;

    public static int USMP_TIMEOUT;
    public static int DS2A_TIMEOUT;
    public static int AMF_TIMEOUT;
    public static int BMP_TIMEOUT;
    public static int BOS_TIMEOUT;
    public static int BSS_TIMEOUT;
    public static int RMF_TIMEOUT;
    public static int SGSCPPPGW_TIMEOUT;
    public static int SGSCPSMOI_TIMEOUT;
    public static int SRFC_TIMEOUT;
    public static int SSB_TIMEOUT;
    public static int SDF_TIMEOUT;
    public static int TMF_TIMEOUT;
    public static int E01_TIMEOUT;
    public static int RMF_CONFIRM_TIMEOUT;
    
    public static int DS2A_MAXRETRY;
    public static int USMP_MAXRETRY;
    public static int AMF_MAXRETRY;
    
    public static String INGW_SMOI_ORIGIN_HOST;
	public static String INGW_SMOI_ORIGIN_REALM;
	
	public static String BOS_DESTINATION_REALM;
	public static String BOS_DESTINATION_HOST;
	public static String BOS_LOCATION;
	public static String CCR_ACCESS_METHOD;
	public static String RESOURCE_ID_PRMMONEY;
	public static String RESOURCE_ID_PRMSM;
	public static String RESOURCE_ID_PRMMINUTE;
	public static String RESOURCE_ID_FREECALLTIMES;
	public static String RESOURCE_ID_FREERBTSONG;
	public static String SETPPSREWARD_BOS_DCC;
	
//	public static String MEASURE_ID_PRMMONEY;
//	public static String MEASURE_ID_PRMSM;
//	public static String MEASURE_ID_PRMMINUTE;
//	public static String MEASURE_ID_FREECALLTIMES;
//	public static String MEASURE_ID_FREERBTSONG;
	
	public static String SRFC_ORIGIN_HOST;
    public static String SRFC_ORIGIN_REALM;
    public static String SRFC_DESTINATION_REALM;
	
    public static String INS_LOCATION;
    
    public static String COUNTERID_CHRGING;
    
    public static String ADDCALLSCREENNO;
    
    public static String WORKORDER_LANG_MAPPING;

    public static String DISP_SCREEN_NO_FILTER_OUT;
    
    public static String URL_USMP_MODIFYSUBSCRIBER;
	
    public static String URL_USMP_FIRSTACTIVATE;
	
    public static String URL_USMP_INQUIRYSUBSCRIBERINFO;
    
    public static String URL_USMP_MODIFYCOUNTER;
    
    public static String URL_USMP_INQUIRYCOUNTER;
    
    public static String URL_BMP_SERVICE;
	
    public static String URL_SSB_WORKORDER;
    
    public static String URL_RMF_REWARDMONITORING;
    
    public static String URL_RMF_REWARDOFFERING;
    
    public static String URL_RMF_REWARDADJUST;
	
    public static String INS_SSB_CHANNEL;
	
    public static String COUNTID_PRMMONEYID;
    
    public static String REPORT80_POS_NAME;
    public static String REPORT80_PPS_NAME;
    
    public static int DEFAULT_REFILLSTOPTIME_MONTH;
    
    public static int MODIVALIDITY_TMF_FLAG;
    
    public static String  COUNTID_MONETARYUSAGEREWARD;
    
    public static String  TMF_DESTINATION_HOST;
    public static String  TMF_DESTINATION_REALM;
    
    public static HashMap<String,String> SERVICE_TYPE_MAPPING = new HashMap<String,String>();
    
    public static String RMF_SMOI_IP;
    public static String RMF_SMOI_PORT;
    
    public static String DEFAULT_PRMMONEYNAME;
    public static String DEFAULT_PRMMONEYUSAGENAME;
    
    public static String DEFAULT_VALIDITY;
    public static String DEFAULT_PACKNAMEPRMSM;
    public static String DEFAULT_PACKNAMEPRMMINUTE;
    
    public static String RMF_ADJUSTBALANCE;
    
    public static String INS_RESOURCE_ID_PRMMONEY;
    
    public static Boolean  APP_LOG_DEBUG_FLAG = false;

    public static boolean verifyConfig(AbstractAF abstractAF) {
   
    	try {
		 	
            for (EConfig conf : EConfig.values()) {
            	
                String confName = conf.getConfig().trim();
                
            	if (!abstractAF.getUtils().getHmWarmConfig().containsKey(confName)) {
                    AppLog.e("Configuration named : " + confName + "[ NOT FOUND ], please check EC02 config !");
                    AppLog.e("Verify Config Failed!!");
                    return false;
                    
                }
                
            }
            
            hasReloadConfig = true;
            
            Config.loadConfig(abstractAF);
            
            AppLog.d("Verify Config Success!!");
            return true;
        } catch (Exception e) {
        	AppLog.e("Verify Config Failed!!"+e);
            return false;
        }

    }
    
    public static String getWarmConfig(AbstractAF abstractAF, String name) {
        return abstractAF.getUtils().getHmWarmConfig().get(name).get(0);
    }
	
	public static int getSizeMultiWarmConfig(AbstractAF abstractAF, String name) {
        return abstractAF.getUtils().getHmWarmConfig().get(name).size();
    }
	
	public static ArrayList<String> getMultiWarmConfig(AbstractAF abstractAF, String name) {
        return abstractAF.getUtils().getHmWarmConfig().get(name);
    }
    
    public static void loadConfig(AbstractAF abstractAF){
    	
    	DETAILLOG_NAME = getWarmConfig(abstractAF, EConfig.DETAILLOG_NAME.getConfig());
    	SUMMARYLOG_NAME = getWarmConfig(abstractAF, EConfig.SUMMARYLOG_NAME.getConfig());
    	
    	DETAIL_LOG_ENABLED = Integer.parseInt(getWarmConfig(abstractAF, EConfig.DETAIL_LOG_ENABLED.getConfig()));
        DETAIL_LOG_RAWDATA_ENABLED = Boolean.valueOf(getWarmConfig(abstractAF, EConfig.DETAIL_LOG_RAWDATA_ENABLED.getConfig()));
        DETAIL_LOG_DATA_ENABLED = Boolean.valueOf(getWarmConfig(abstractAF, EConfig.DETAIL_LOG_DATA_ENABLED.getConfig()));
    	    
        USMP_INTERFACE = getWarmConfig(abstractAF, EConfig.USMP_INTERFACE.getConfig());
        DS2A_INTERFACE = getWarmConfig(abstractAF, EConfig.DS2A_INTERFACE.getConfig());
        AMF_INTERFACE = getWarmConfig(abstractAF, EConfig.AMF_INTERFACE.getConfig());
        BMP_INTERFACE = getWarmConfig(abstractAF, EConfig.BMP_INTERFACE.getConfig());
        BOS_INTERFACE = getWarmConfig(abstractAF, EConfig.BOS_INTERFACE.getConfig());
        BSS_INTERFACE = getWarmConfig(abstractAF, EConfig.BSS_INTERFACE.getConfig());
        RMF_INTERFACE = getWarmConfig(abstractAF, EConfig.RMF_INTERFACE.getConfig());
        SGSCPPPGW_INTERFACE = getWarmConfig(abstractAF, EConfig.SGSCPPPGW_INTERFACE.getConfig());
        SGSCPSMOI_INTERFACE = getWarmConfig(abstractAF, EConfig.SGSCPSMOI_INTERFACE.getConfig());
        SRFC_INTERFACE = getWarmConfig(abstractAF, EConfig.SRFC_INTERFACE.getConfig());
        SSB_INTERFACE = getWarmConfig(abstractAF, EConfig.SSB_INTERFACE.getConfig());
        SDF_INTERFACE = getWarmConfig(abstractAF, EConfig.SDF_INTERFACE.getConfig());
        TMF_INTERFACE = getWarmConfig(abstractAF, EConfig.TMF_INTERFACE.getConfig());
        
        USMP_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.USMP_TIMEOUT.getConfig()));
        DS2A_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.DS2A_TIMEOUT.getConfig()));
        AMF_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.AMF_TIMEOUT.getConfig()));
        BMP_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.BMP_TIMEOUT.getConfig()));
        BOS_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.BOS_TIMEOUT.getConfig()));
        BSS_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.BSS_TIMEOUT.getConfig()));
        RMF_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.RMF_TIMEOUT.getConfig()));
        SGSCPPPGW_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.SGSCPPPGW_TIMEOUT.getConfig()));
        SGSCPSMOI_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.SGSCPSMOI_TIMEOUT.getConfig()));
        SRFC_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.SRFC_TIMEOUT.getConfig()));
        SSB_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.SSB_TIMEOUT.getConfig()));
        SDF_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.SDF_TIMEOUT.getConfig()));
        TMF_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.TMF_TIMEOUT.getConfig()));
        E01_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.E01_TIMEOUT.getConfig()));
        RMF_CONFIRM_TIMEOUT = Integer.parseInt(getWarmConfig(abstractAF, EConfig.RMF_CONFIRM_TIMEOUT.getConfig()));
        
        DS2A_MAXRETRY = Integer.parseInt(getWarmConfig(abstractAF, EConfig.DS2A_MAXRETRY.getConfig()));
        USMP_MAXRETRY = Integer.parseInt(getWarmConfig(abstractAF, EConfig.USMP_MAXRETRY.getConfig()));
        AMF_MAXRETRY = Integer.parseInt(getWarmConfig(abstractAF, EConfig.AMF_MAXRETRY.getConfig()));

        INGW_SMOI_ORIGIN_HOST = getWarmConfig(abstractAF, EConfig.INGW_SMOI_ORIGIN_HOST.getConfig());
    	INGW_SMOI_ORIGIN_REALM = getWarmConfig(abstractAF, EConfig.INGW_SMOI_ORIGIN_REALM.getConfig());
    	
    	BOS_DESTINATION_REALM = getWarmConfig(abstractAF, EConfig.BOS_DESTINATION_REALM.getConfig());
    	BOS_DESTINATION_HOST = getWarmConfig(abstractAF, EConfig.BOS_DESTINATION_HOST.getConfig());
    	BOS_LOCATION = getWarmConfig(abstractAF, EConfig.BOS_LOCATION.getConfig());
    	CCR_ACCESS_METHOD = getWarmConfig(abstractAF, EConfig.CCR_ACCESS_METHOD.getConfig());
    	RESOURCE_ID_PRMMONEY = getWarmConfig(abstractAF, EConfig.RESOURCE_ID_PRMMONEY.getConfig());
    	RESOURCE_ID_PRMSM = getWarmConfig(abstractAF, EConfig.RESOURCE_ID_PRMSM.getConfig());
    	RESOURCE_ID_PRMMINUTE = getWarmConfig(abstractAF, EConfig.RESOURCE_ID_PRMMINUTE.getConfig());
    	RESOURCE_ID_FREECALLTIMES = getWarmConfig(abstractAF, EConfig.RESOURCE_ID_FREECALLTIMES.getConfig());
    	RESOURCE_ID_FREERBTSONG = getWarmConfig(abstractAF, EConfig.RESOURCE_ID_FREERBTSONG.getConfig());
    	SETPPSREWARD_BOS_DCC = getWarmConfig(abstractAF, EConfig.SETPPSREWARD_BOS_DCC.getConfig());
    	
//    	MEASURE_ID_PRMMONEY = getWarmConfig(abstractAF, EConfig.MEASURE_ID_PRMMONEY.getConfig());
//    	MEASURE_ID_PRMSM = getWarmConfig(abstractAF, EConfig.MEASURE_ID_PRMSM.getConfig());
//    	MEASURE_ID_PRMMINUTE = getWarmConfig(abstractAF, EConfig.MEASURE_ID_PRMMINUTE.getConfig());
//    	MEASURE_ID_FREECALLTIMES = getWarmConfig(abstractAF, EConfig.MEASURE_ID_FREECALLTIMES.getConfig());
//    	MEASURE_ID_FREERBTSONG = getWarmConfig(abstractAF, EConfig.MEASURE_ID_FREERBTSONG.getConfig());
    	
    	SRFC_ORIGIN_HOST = getWarmConfig(abstractAF, EConfig.SRFC_ORIGIN_HOST.getConfig());
    	SRFC_ORIGIN_REALM = getWarmConfig(abstractAF, EConfig.SRFC_ORIGIN_REALM.getConfig());
    	SRFC_DESTINATION_REALM = getWarmConfig(abstractAF, EConfig.SRFC_DESTINATION_REALM.getConfig());
    	
        INS_LOCATION = getWarmConfig(abstractAF, EConfig.INS_LOCATION.getConfig());
        
        COUNTERID_CHRGING = getWarmConfig(abstractAF, EConfig.COUNTERID_CHRGING.getConfig());
    	
    	ADDCALLSCREENNO = getWarmConfig(abstractAF, EConfig.AddCallScreenNo.getConfig());
    	
    	WORKORDER_LANG_MAPPING = getWarmConfig(abstractAF, EConfig.WORKORDER_LANG_MAPPING.getConfig());

    	DISP_SCREEN_NO_FILTER_OUT = getWarmConfig(abstractAF, EConfig.Disp_Screen_No_Filter_Out.getConfig());
    	
    	URL_USMP_MODIFYSUBSCRIBER = getWarmConfig(abstractAF, EConfig.URL_USMP_ModifySubscriber.getConfig());
    	
    	URL_USMP_FIRSTACTIVATE = getWarmConfig(abstractAF, EConfig.URL_USMP_FirstActivate.getConfig());
    	
    	URL_USMP_INQUIRYSUBSCRIBERINFO = getWarmConfig(abstractAF, EConfig.URL_USMP_InquirySubscriberInfo.getConfig());
    	
    	URL_USMP_MODIFYCOUNTER = getWarmConfig(abstractAF, EConfig.URL_USMP_ModifyCounter.getConfig());
    	
    	URL_USMP_INQUIRYCOUNTER = getWarmConfig(abstractAF, EConfig.URL_USMP_InquiryCounter.getConfig());
    	
    	URL_BMP_SERVICE = getWarmConfig(abstractAF, EConfig.URL_BMP_SERVICE.getConfig());
    	
    	URL_SSB_WORKORDER = getWarmConfig(abstractAF, EConfig.URL_SSB_WorkOrder.getConfig());
    	
    	URL_RMF_REWARDMONITORING = getWarmConfig(abstractAF, EConfig.URL_RMF_REWARDMONITORING.getConfig());
    	
    	URL_RMF_REWARDOFFERING = getWarmConfig(abstractAF, EConfig.URL_RMF_REWARDOFFERING.getConfig());
    	
    	URL_RMF_REWARDADJUST = getWarmConfig(abstractAF, EConfig.URL_RMF_REWARDADJUST.getConfig());
    	
    	INS_SSB_CHANNEL = getWarmConfig(abstractAF, EConfig.INS_SSB_Channel.getConfig());
    	
    	COUNTID_PRMMONEYID = getWarmConfig(abstractAF, EConfig.CountID_Prmmoneyid.getConfig());
    	
    	REPORT80_POS_NAME = getWarmConfig(abstractAF, EConfig.REPORT80_POS_NAME.getConfig());
    	REPORT80_PPS_NAME = getWarmConfig(abstractAF, EConfig.REPORT80_PPS_NAME.getConfig());
    	
    	DEFAULT_REFILLSTOPTIME_MONTH = Integer.parseInt(getWarmConfig(abstractAF, EConfig.DEFAULT_REFILLSTOPTIME_MONTH.getConfig()));
    	
    	MODIVALIDITY_TMF_FLAG = Integer.parseInt(getWarmConfig(abstractAF, EConfig.MODIVALIDITY_TMF_FLAG.getConfig()));
    	
    	COUNTID_MONETARYUSAGEREWARD = getWarmConfig(abstractAF, EConfig.COUNTID_MONETARYUSAGEREWARD.getConfig());
    	
    	TMF_DESTINATION_HOST = getWarmConfig(abstractAF, EConfig.TMF_DESTINATION_HOST.getConfig());
    	TMF_DESTINATION_REALM = getWarmConfig(abstractAF, EConfig.TMF_DESTINATION_REALM.getConfig());
    	    
    	ArrayList<String> SERVICE_TYPE_MAPPING_ARR = getMultiWarmConfig(abstractAF, EConfig.SERVICE_TYPE_MAPPING.getConfig());
    	
    	RMF_SMOI_IP = getWarmConfig(abstractAF, EConfig.RMF_SMOI_IP.getConfig());
    	RMF_SMOI_PORT = getWarmConfig(abstractAF, EConfig.RMF_SMOI_PORT.getConfig());
    	
    	DEFAULT_PRMMONEYNAME = getWarmConfig(abstractAF, EConfig.DEFAULT_PRMMONEYNAME.getConfig());
    	DEFAULT_PRMMONEYUSAGENAME = getWarmConfig(abstractAF, EConfig.DEFAULT_PRMMONEYUSAGENAME.getConfig());
    	
    	DEFAULT_VALIDITY = getWarmConfig(abstractAF, EConfig.DEFAULT_VALIDITY.getConfig());
    	
    	DEFAULT_PACKNAMEPRMSM = getWarmConfig(abstractAF, EConfig.DEFAULT_PACKNAMEPRMSM.getConfig());
    	DEFAULT_PACKNAMEPRMMINUTE = getWarmConfig(abstractAF, EConfig.DEFAULT_PACKNAMEPRMMINUTE.getConfig());
    	
    	RMF_ADJUSTBALANCE = getWarmConfig(abstractAF, EConfig.RMF_ADJUSTBALANCE.getConfig());
    	
    	INS_RESOURCE_ID_PRMMONEY = getWarmConfig(abstractAF, EConfig.INS_RESOURCE_ID_PRMMONEY.getConfig());
    	
    	for(String serviceType:SERVICE_TYPE_MAPPING_ARR) {
    		
    		if(serviceType.contains(",")) {
    			String[] serviceTypeArr = serviceType.split(",",-1);
        		
        		SERVICE_TYPE_MAPPING.put(serviceTypeArr[0], serviceTypeArr[1]);
        		
    		} else {
    			AppLog.w("One of Service-Type-Mapping wrong format !");
    		}
    		
    		
    	}
    	
    	try {
    		
    		if(getWarmConfig(abstractAF, "AFLOG").contains("DEBUG")) {
    			APP_LOG_DEBUG_FLAG = true;
    		}
    		
    	}catch(Exception ex) {
    		
    		APP_LOG_DEBUG_FLAG = false;
    		
    	}
   	  
    	
    	hasReloadConfig = false;
    }
    
}
