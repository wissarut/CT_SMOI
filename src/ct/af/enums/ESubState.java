package ct.af.enums;

public enum ESubState {
	
	Idle_Unknown,
	
	Idle_Request,
	Idle_RMC,
	
	DS2A_InquirySubscriber,
	
	E01_Destination,
	E01_NewClassOfService,
	E01_CosToPromotionName,
	E01_Notification,
	
	AMF_ModifyCounters,
	AMF_ObtainCounter,
	AMF_CheckBalance,
	AMF_ManageCounters,
	AMF_ModifyCounters_AND_AMF_ObtainNoneBalanceCounter,
	
	BMP_CallScreening,
	BMP_DispScrScreenNo,

	BSS_AccountRechargeCCR,
	
	BSS_DoAdjustBalance,
	BSS_DoChangeService, 
	BSS_DoCallScreenNo,
	BSS_DoQueryCallScreen,
	BSS_DoQueryFN,
	BSS_DoModifySubscriberBasicInfo,
	BSS_DoSetNegativeBalance,
	BSS_DoFirstActivationCRM,
	
	BOS_AccountRecharge,
	BOS_MainBalanceAccountAdjustment,
	BOS_FreeResourceAdjustment,
	BOS_SubscriberAccountEnquiry,
	BOS_AccountQuery,
	
	SGSCPSMOI_ForwardCommand,
	SGSCPPPGW_RechargeBalance,
	
	USMP_ModifySubscriber,
	USMP_FirstActivate,
	USMP_InquirySubscriberInfo,
	USMP_ModifyCounter,
	USMP_InquiryCounter,
	
	RMF_RewardMonitoring,
	RMF_RewardOffering,
	RMF_RewardAdjustment,
	RMF_RMCR,
	SRFC_SpecializedResource,
	
	SSB_WorkOrderFirstAct,
	
	SDF_GetAmfCounter,
	SDF_PutAmfCounter,
	SDF_GetAmfSubProfile,
	SDF_GetSidRatingElement,
	SDF_GetSidNotificationElement,
	
	TMF_AccountAdjustment,
	
	SRFC_SpecializedResource_AND_DS2A_InquirySubscriber,
	
	AMF_ManageCounters_AND_DS2A_InquirySubscriber,
	
	AMF_ManageCounters_AND_RMF_RewardMonitoring,
	
	End_DispPPSInfo,
	End_DispPPSPkgrew,
	End_ModiPPSMultiAttr,
	End_ModiPPSValidity,
	End_SetPPSReward,
	End_ActivatePPSSingSub,
	End_AddScrScreenNo,
	End_DeleScrScreenNo,
	End_DispScrScreenNo,
	End_ModiPPSCreditLimit,
	End_ModiScrWhiteList,
	End_PurchasePPSPackage,
	End_ActPPSRBT,
	End_ChgtrigChrgAcnt,
	End_DelePPSPkgres,
	End_ModiScrScreenType,
	End_DelePPSPackageID,
	End_DispPPSFntelNo,
	End_ModiPPSLanguage,
	End_ModiPPSSMSLanguage,
	End_ForwardCommand,
	End_ModiPPSMultiWallet,

	WAIT,
	END,
	
	Unknown, 
	End_Unknow, 
	;
	
}
