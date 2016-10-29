package ct.af.enums;

public enum EConfig {
	
	DETAILLOG_NAME("LogName-Detail")
	,SUMMARYLOG_NAME("LogName-Summary")
	,DETAIL_LOG_ENABLED("DETAIL_LOG-Enabled")
	,DETAIL_LOG_RAWDATA_ENABLED("DETAIL_LOG-RawData-Enabled")
	,DETAIL_LOG_DATA_ENABLED("DETAIL_LOG-Data-Enabled")
	
	,USMP_INTERFACE("Resource-Name-Active-USMP")
	,DS2A_INTERFACE("Resource-Name-Active-DS2A")
    ,AMF_INTERFACE("Resource-Name-Active-AMF")
    ,BMP_INTERFACE("Resource-Name-Active-BMP")
    ,BOS_INTERFACE("Resource-Name-Active-BOS")
    ,BSS_INTERFACE("Resource-Name-Active-BSSBroker")
    ,RMF_INTERFACE("Resource-Name-Active-RMF")
    ,SGSCPPPGW_INTERFACE("Resource-Name-Active-PPGW_FET")
    ,SGSCPSMOI_INTERFACE("Resource-Name-Active-SMOI_FET")
    ,SRFC_INTERFACE("Resource-Name-Active-SRFC")
    ,SSB_INTERFACE("Resource-Name-Active-SSB")
    ,SDF_INTERFACE("Resource-Name-Active-SDF")
    ,TMF_INTERFACE("Resource-Name-Active-TMF")
    
	,USMP_TIMEOUT("Tm-USMP")
	,DS2A_TIMEOUT("Tm-DS2A")
    ,AMF_TIMEOUT("Tm-AMF")
    ,BMP_TIMEOUT("Tm-BMP")
    ,BOS_TIMEOUT("Tm-BOS")
    ,BSS_TIMEOUT("Tm-BSSBroker")
    ,RMF_TIMEOUT("Tm-RMF")
    ,SGSCPPPGW_TIMEOUT("Tm-PPGW_FET")
    ,SGSCPSMOI_TIMEOUT("Tm-SMOI_FET")
    ,SRFC_TIMEOUT("Tm-SRFC")
    ,SSB_TIMEOUT("Tm-SSB")
    ,SDF_TIMEOUT("Tm-SDF")
    ,TMF_TIMEOUT("Tm-TMF")
    ,E01_TIMEOUT("Tm-E01")
    ,RMF_CONFIRM_TIMEOUT("Tm-RMF-Confirmation")
    
    ,DS2A_MAXRETRY("Resource-Name-Standby-DS2A-MaxRetry")
    ,USMP_MAXRETRY("Resource-Name-Standby-USMP-MaxRetry")
    ,AMF_MAXRETRY("Resource-Name-Standby-AMF-MaxRetry")

	,INS_LOCATION("INS-Location")
    ,BOS_LOCATION("BOS-Location")

    ,SETPPSREWARD_BOS_DCC("Set-PPSReward-BOS-DCC")
    ,AddCallScreenNo("Add-Call-Screen-No")
    ,COUNTERID_CHRGING("CountID-Charging")
    ,WORKORDER_LANG_MAPPING("Workorder-Lang-Mapping")
    ,SRFC_ORIGIN_HOST("SRFC-Origin-Host")
    ,SRFC_ORIGIN_REALM("SRFC-Origin-Realm")
    ,SRFC_DESTINATION_REALM("SRFC-Destination-Realm")
    
    ,RESOURCE_ID_PRMMONEY("Resource-Id-PRMMONEY")
	,RESOURCE_ID_PRMSM("Resource-Id-PRMSM")
	,RESOURCE_ID_PRMMINUTE("Resource-Id-PRMMINUTE")
	,RESOURCE_ID_FREECALLTIMES("Resource-Id-FREECALLTIMES")
	,RESOURCE_ID_FREERBTSONG("Resource-Id-FREERBTSONG")
	
//	,MEASURE_ID_PRMMONEY("Measure-Id-PRMMONEY")
//	,MEASURE_ID_PRMSM("Measure-Id-PRMSM")
//	,MEASURE_ID_PRMMINUTE("Measure-Id-PRMMINUTE")
//	,MEASURE_ID_FREECALLTIMES("Measure-Id-FREECALLTIMES")
//	,MEASURE_ID_FREERBTSONG("Measure-Id-FREERBTSONG")
    
    ,INGW_SMOI_ORIGIN_HOST("INGW_SMOI-Origin-Host")
    ,INGW_SMOI_ORIGIN_REALM("INGW_SMOI-Origin-Realm")
    ,CCR_ACCESS_METHOD("CCR-Access-Method")
    ,BOS_DESTINATION_REALM("BOS-Destination-Realm")
    ,BOS_DESTINATION_HOST("BOS-Destination-Host")
    
    ,Disp_Screen_No_Filter_Out("Disp-Screen-No-Filter-Out")
    ,URL_USMP_ModifySubscriber("URL-USMP-ModifySubscriber")
    ,URL_USMP_FirstActivate("URL-USMP-FirstActivate")
    ,URL_USMP_InquirySubscriberInfo("URL-USMP-InquirySubscriberInfo")
    ,URL_USMP_ModifyCounter("URL-USMP-ModifyCounter")
    ,URL_USMP_InquiryCounter("URL-USMP-InquiryCounter")
    ,URL_BMP_SERVICE("URL-BMP-Service")
    ,URL_SSB_WorkOrder("URL-SSB-WorkOrder")
    ,URL_RMF_REWARDMONITORING("URL-RMF-RewardMonitoring")
    ,URL_RMF_REWARDOFFERING("URL-RMF-RewardOffering")
    ,URL_RMF_REWARDADJUST("URL-RMF-RewardAdjustment")
    ,INS_SSB_Channel("INS-SSB-Channel")
    ,CountID_Prmmoneyid("CountID-Prmmoneyid")
    ,REPORT80_POS_NAME("Report-80-POS-Name")
    ,REPORT80_PPS_NAME("Report-80-PPS-Name")
    ,DEFAULT_REFILLSTOPTIME_MONTH("Default-RefillStopTime-Month")
    ,MODIVALIDITY_TMF_FLAG("ModiValidity-TMF-Flag")
    ,COUNTID_MONETARYUSAGEREWARD("CountID-Monetaryusagereward")
    ,TMF_DESTINATION_HOST("TMF-Destination-Host")
	,TMF_DESTINATION_REALM("TMF-Destination-Realm")
	,SERVICE_TYPE_MAPPING("Service-Type-Mapping")
	,RMF_SMOI_IP("RMF-SMOI-IP")
	,RMF_SMOI_PORT("RMF-SMOI-PORT")
	,DEFAULT_PRMMONEYNAME("Default-prmmoneyName")
	,DEFAULT_PRMMONEYUSAGENAME("Default-prmmoneyusageName")
	,DEFAULT_PACKNAMEPRMSM("INS-Default-PackName-prmSM")
	,DEFAULT_PACKNAMEPRMMINUTE("INS-Default-PackName-prmMinute")
	,DEFAULT_VALIDITY("INS-Free-Resource-Default-Adjust-Validity")
	,INS_RESOURCE_ID_PRMMONEY("INS-Resource-Id-PRMMONEY")
	,RMF_ADJUSTBALANCE("RMF-Adjust-Balance");
	
	;
	
	private String name;
    EConfig(String name){
        this.name = name;
    }

    public String getConfig() {
        return name;
    }
}
