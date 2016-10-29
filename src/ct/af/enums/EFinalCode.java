package ct.af.enums;

public enum EFinalCode {

	DISIF_IDLE_BAD(ECommand.DISPPPSINFO.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_INCOMPLETE_DATA),
	DISGK_IDLE_BAD(ECommand.DISPPPSPKGREW.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_INCOMPLETE_DATA),
	MODIP_IDLE_BAD(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_INCOMPLETE_DATA),
	MODAT_IDLE_BAD(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_INCOMPLETE_DATA),
	ADDSN_IDLE_BAD(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_INCOMPLETE_DATA),
	DLSNN_IDLE_BAD(ECommand.DELESCRSCREENNO.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_INCOMPLETE_DATA),
	MODST_IDLE_BAD(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_INCOMPLETE_DATA),
	MODWL_IDLE_BAD(ECommand.MODISCRWHITELIST.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_INCOMPLETE_DATA),
	DISSN_IDLE_BAD(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_INCOMPLETE_DATA),
	ASRBT_IDLE_BAD(ECommand.ACTPPSRBT.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_ACTPPSRBT_RESPONSE_INCOMPLETE_DATA),
	DLKGS_IDLE_BAD(ECommand.DELEPPSPKGRES.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_DELEPPSPKGRES_RESPONSE_INCOMPLETE_DATA),
	DLPID_IDLE_BAD(ECommand.DELEPPSPACKAGEID.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_DELEPPSPACKAGEID_RESPONSE_INCOMPLETE_DATA),
	PCPKG_IDLE_BAD(ECommand.PURCHASEPPSPACKAGE.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_PURCHASEPPSPACKAGE_RESPONSE_INCOMPLETE_DATA),
	SREWD_IDLE_BAD(ECommand.SETPPSREWARD.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_INCOMPLETE_DATA),
	DISNT_IDLE_BAD(ECommand.DISPPPSFNTELNO.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_DISPPPSFNTELNO_RESPONSE_INCOMPLETE_DATA),
	MODLG_IDLE_BAD(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_INCOMPLETE_DATA),
	MODSL_IDLE_BAD(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_INCOMPLETE_DATA),
	MODCM_IDLE_BAD(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_INCOMPLETE_DATA),
	CHGCA_IDLE_BAD(ECommand.CHGTRIGCHRGACNT.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_CHGTRIGCHRGACNT_RESPONSE_INCOMPLETE_DATA),
	ACPPS_IDLE_BAD(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_INCOMPLETE_DATA),
	ACPPS_IDLE_UNK(ECommand.UNKNOWN.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"999","INGateway Receive Unknown Request", EStats.INGATEWAY_RECEIVE_UNKNOWN_REQUEST),

	DISIF_DS2A_NOLO(ECommand.DISPPPSINFO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	DISGK_DS2A_NOLO(ECommand.DISPPPSPKGREW.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	MODIP_DS2A_NOLO(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	MODAT_DS2A_NOLO(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	ADDSN_DS2A_NOLO(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	DLSNN_DS2A_NOLO(ECommand.DELESCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	MODST_DS2A_NOLO(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	MODWL_DS2A_NOLO(ECommand.MODISCRWHITELIST.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	DISSN_DS2A_NOLO(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	ASRBT_DS2A_NOLO(ECommand.ACTPPSRBT.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	DLKGS_DS2A_NOLO(ECommand.DELEPPSPKGRES.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	DLPID_DS2A_NOLO(ECommand.DELEPPSPACKAGEID.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	PCPKG_DS2A_NOLO(ECommand.PURCHASEPPSPACKAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	SREWD_DS2A_NOLO(ECommand.SETPPSREWARD.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	DISNT_DS2A_NOLO(ECommand.DISPPPSFNTELNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	MODLG_DS2A_NOLO(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	MODSL_DS2A_NOLO(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	MODCM_DS2A_NOLO(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	CHGCA_DS2A_NOLO(ECommand.CHGTRIGCHRGACNT.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	ACPPS_DS2A_NOLO(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40401.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_DS2A_INQUIRYSUBSCRIBER_NOT_FOUND_SERVICE_LOCATION),
	ACPPS_DS2A_ERROR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40301.getResultCode(),"300","INGateway Receive DS2A InquirySubscriberRequest Error", EStats.INGATEWAY_RECEIVE_DS2A_INQUIRYSUBSCRIBER_ERROR),
	
	ACPPS_DS2A_UNKNOWN(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE221.getResultCode(),"002","INGateway Receive DS2 Unknown Msisdn", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACPPS_DS2A_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriberRequest Timeout", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACPPS_DS2A_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriberRequest Abort", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACPPS_DS2A_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriberRequest Reject", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACPPS_DS2A_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriberRequest Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACPPS_DS2A_BAD(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE40301.getResultCode(),"334","INGateway Receive DS2A Bad InquirySubscriber Response", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACPPS_DS2A_ER(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REEcodeNot0.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),

	//1
	DISIF_AMCHE_RET1(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive AMF CheckBalance Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMCHE_RET2(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive AMF CheckBalance Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMCHE_RET3(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive AMF CheckBalance Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMCHE_RET4(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive AMF CheckBalance Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMCHE_BAD(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive AMF Bad CheckBalance Response", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMCHE_UNKERR(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_CheckBalance.toString(),"ERROR","319","INGateway Receive AMF CheckBalance Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),

	DISIF_DS2AI_RET1(ECommand.DISPPPSINFO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_DS2AI_RET2(ECommand.DISPPPSINFO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_DS2AI_RET3(ECommand.DISPPPSINFO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_DS2AI_RET4(ECommand.DISPPPSINFO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_DS2AI_RE101010(ECommand.DISPPPSINFO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),

	DISIF_AMCKOB_RET1(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ModifyCounters_AND_AMF_ObtainNoneBalanceCounter.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive AMF ObtainCounter Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMCKOB_RET2(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ModifyCounters_AND_AMF_ObtainNoneBalanceCounter.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive AMF ObtainCounter Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMCKOB_RET3(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ModifyCounters_AND_AMF_ObtainNoneBalanceCounter.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive AMF ObtainCounter Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMCKOB_RET4(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ModifyCounters_AND_AMF_ObtainNoneBalanceCounter.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive AMF ObtainCounter Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMCKOB_UNKERR(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ModifyCounters_AND_AMF_ObtainNoneBalanceCounter.toString(),"ERROR","319","INGateway Receive AMF ObtainNonBalanceCounter Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),

	DISIF_AMOBT_RET1(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ObtainCounter.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive AMF ObtainCounter Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMOBT_RET2(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ObtainCounter.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive AMF ObtainCounter Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMOBT_RET3(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ObtainCounter.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive AMF ObtainCounter Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMOBT_RET4(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ObtainCounter.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive AMF ObtainCounter Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMOBT_BAD(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ObtainCounter.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive AMF Bad ObtainNonBalanceCounter Response", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMOBT_UNKERR(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ObtainCounter.toString(),"ERROR","319","INGateway Receive AMF ObtainNonBalanceCounter Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_AMOBT_ER(ECommand.DISPPPSINFO.getCommand(),ESubState.AMF_ObtainCounter.toString(),EResultCode.REEcodeNot0.getResultCode(),"319","INGateway Receive AMF ObtainNonBalanceCounter Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	
	DISIF_SDGCT_RET1(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfSubProfile.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive SDF Get AmfSubProfile Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_SDGCT_RET2(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfSubProfile.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive SDF Get AmfSubProfile Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_SDGCT_RET3(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfSubProfile.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive SDF Get AmfSubProfile Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_SDGCT_RET4(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfSubProfile.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive SDF Get AmfSubProfile Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_SDGCT_0000(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfSubProfile.toString(),EResultCode.RE20000.getResultCode(),"000","Query the subscriber's basic information successfully.", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_SUCCESS),
	DISIF_SDGCT_UNKERR(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfSubProfile.toString(),"ERROR","319","INGateway Receive SDF Get AmfSubProfile Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),

	DISIF_USISI_RET1(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfCounter.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive Get AmfCounter Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_USISI_RET2(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfCounter.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive Get AmfCounter Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_USISI_RET3(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfCounter.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive Get AmfCounter Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_USISI_RET4(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfCounter.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive Get AmfCounter Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_USISI_UNKERR(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetAmfCounter.toString(),"ERROR","319","INGateway Receive Get AmfCounter Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),

	DISIF_ESDGNE_RET1(ECommand.DISPPPSINFO.getCommand(),ESubState.End_DispPPSInfo.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive SDF Get SidNotificationElement Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_ESDGNE_RET2(ECommand.DISPPPSINFO.getCommand(),ESubState.End_DispPPSInfo.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive SDF Get SidNotificationElement Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_ESDGNE_RET3(ECommand.DISPPPSINFO.getCommand(),ESubState.End_DispPPSInfo.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive SDF Get SidNotificationElement Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_ESDGNE_RET4(ECommand.DISPPPSINFO.getCommand(),ESubState.End_DispPPSInfo.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive SDF Get SidNotificationElement Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_ESDGNE_0000(ECommand.DISPPPSINFO.getCommand(),ESubState.End_DispPPSInfo.toString(),EResultCode.RE20000.getResultCode(),"000","Query the subscriber's basic information successfully.", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_SUCCESS),
	DISIF_ESDGNE_UNKERR(ECommand.DISPPPSINFO.getCommand(),ESubState.End_DispPPSInfo.toString(),"ERROR","000","Query the subscriber's basic information successfully.", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_SUCCESS),

	DISIF_SDGNE_RET1(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetSidNotificationElement.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive SDF Get SidNotificationElement Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_SDGNE_RET2(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetSidNotificationElement.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive SDF Get SidNotificationElement Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_SDGNE_RET3(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetSidNotificationElement.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive SDF Get SidNotificationElement Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_SDGNE_RET4(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetSidNotificationElement.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive SDF Get SidNotificationElement Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_SDGNE_0000(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetSidNotificationElement.toString(),EResultCode.RE20000.getResultCode(),"000","Query the subscriber's basic information successfully.", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_SUCCESS),
	DISIF_SDGNE_UNKERR(ECommand.DISPPPSINFO.getCommand(),ESubState.SDF_GetSidNotificationElement.toString(),"ERROR","319","INGateway Receive SDF Get SidNotificationElement Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_SUCCESS),

	DISIF_E01DE_RET1(ECommand.DISPPPSINFO.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret1.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_E01DE_RET2(ECommand.DISPPPSINFO.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret2.getResultCode(),"354","INGateway Receive E01 Query DestinationResponse Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_E01DE_RET3(ECommand.DISPPPSINFO.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret3.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_E01DE_RET4(ECommand.DISPPPSINFO.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret4.getResultCode(),"354","INGateway Receive E01 Query Destination Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_E01DE_32(ECommand.DISPPPSINFO.getCommand(),ESubState.E01_Destination.toString(),EResultCode.RE32.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_E01DE_UNKERR(ECommand.DISPPPSINFO.getCommand(),ESubState.E01_Destination.toString(),"ERROR","354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),

	DISIF_BOSAE_RET1(ECommand.DISPPPSINFO.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BOS SubscriberAccountEnquiry Request Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_BOSAE_RET2(ECommand.DISPPPSINFO.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BOS SubscriberAccountEnquiry Request Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_BOSAE_RET3(ECommand.DISPPPSINFO.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BOS SubscriberAccountEnquiry Request Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_BOSAE_RET4(ECommand.DISPPPSINFO.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BOS SubscriberAccountEnquiry Request Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),
	DISIF_BOSAE_2001(ECommand.DISPPPSINFO.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),EResultCode.RE2001.getResultCode(),"000","Query the subscriber's basic information successfully.", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_SUCCESS),
	DISIF_BOSAE_UNKERR(ECommand.DISPPPSINFO.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),"ERROR","319","INGateway Receive BOS SubscriberAccountEnquiry Request Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_ERROR),

	DISIF_FORWARD_RE000(ECommand.DISPPPSINFO.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_DISPPPSINFO_RESPONSE_SUCCESS),

	//2
	DISPK_E01DE_RET1(ECommand.DISPPPSPKGREW.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret1.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),
	DISPK_E01DE_RET2(ECommand.DISPPPSPKGREW.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret2.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),
	DISPK_E01DE_RET3(ECommand.DISPPPSPKGREW.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret3.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),
	DISPK_E01DE_RET4(ECommand.DISPPPSPKGREW.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret4.getResultCode(),"354","INGateway Receive E01 Query Destination Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),
	DISPK_E01DE_32(ECommand.DISPPPSPKGREW.getCommand(),ESubState.E01_Destination.toString(),EResultCode.RE32.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),
	DISPK_E01DE_UNKERR(ECommand.DISPPPSPKGREW.getCommand(),ESubState.E01_Destination.toString(),"ERROR","354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),
//	INGateway Receive E01 Query Destination Response Error:No Such Object
	DISPK_BOSAE_RET1(ECommand.DISPPPSPKGREW.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BOS SubscriberAccountEnquiry Request Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),
	DISPK_BOSAE_RET2(ECommand.DISPPPSPKGREW.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BOS SubscriberAccountEnquiry Request Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),
	DISPK_BOSAE_RET3(ECommand.DISPPPSPKGREW.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BOS SubscriberAccountEnquiry Request Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),
	DISPK_BOSAE_RET4(ECommand.DISPPPSPKGREW.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BOS SubscriberAccountEnquiry Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),
	DISPK_BOSAE_2001(ECommand.DISPPPSPKGREW.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),EResultCode.RE2001.getResultCode(),"000","Query the subscriber's basic information successfully", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_SUCCESS),
	DISPK_BOSAE_UNKERR(ECommand.DISPPPSPKGREW.getCommand(),ESubState.BOS_SubscriberAccountEnquiry.toString(),"ERROR","354","INGateway Receive BOS SubscriberAccountEnquiry Request Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_ERROR),

	DISPK_FORWARD_RE000(ECommand.DISPPPSPKGREW.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_DISPPPSPKGREW_RESPONSE_SUCCESS),

	//3
	MODIV_E01DE_RET1(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret1.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_E01DE_RET2(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret2.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_E01DE_RET3(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret3.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_E01DE_RET4(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret4.getResultCode(),"354","INGateway Receive E01 Query Destination Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_E01DE_32(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.E01_Destination.toString(),EResultCode.RE32.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_E01DE_UNKERR(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.E01_Destination.toString(),"ERROR","354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),

	MODIV_DS2AI_RET1(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_DS2AI_RET2(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_DS2AI_RET3(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_DS2AI_RET4(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_DS2AI_UNKERR(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),"ERROR","333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
    MODIV_DS2AI_RE101010(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),

	MODIV_AMFMC_RET1(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.AMF_ModifyCounters.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive AMF ModifyCouters Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_AMFMC_RET2(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.AMF_ModifyCounters.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive AMF ModifyCouters Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_AMFMC_RET3(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.AMF_ModifyCounters.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive AMF ModifyCouters Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_AMFMC_RET4(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.AMF_ModifyCounters.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive AMF ModifyCouters Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_AMFMC_RE20(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.AMF_ModifyCounters.toString(),EResultCode.RE20000.getResultCode(),"000","Modifying the validity term succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_SUCCESS),
	MODIV_AMFMC_BAD(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.AMF_ModifyCounters.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive AMF Bad ModifyCouters Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_AMFMC_UNKERR(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.AMF_ModifyCounters.toString(),"ERROR","319","INGateway Receive AMF ModifyCouters Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_AMFMC_ER(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.AMF_ModifyCounters.toString(),EResultCode.REEcodeNot0.getResultCode(),"319","INGateway Receive AMF ModifyCouters Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	
	MODIV_TMFAA_RET1(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive TMF AccountAdjustment Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_TMFAA_RET2(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive TMF AccountAdjustment Request Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_TMFAA_RET3(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive TMF AccountAdjustment Request Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_TMFAA_RET4(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive TMF AccountAdjustment Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_TMFAA_RE20(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.RE2001.getResultCode(),"000","Modifying the validity term succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_SUCCESS),
	MODIV_TMFAA_BAD(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive TMF Bad AccountAdjustment Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_TMFAA_UNKERR(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.TMF_AccountAdjustment.toString(),"ERROR","319","INGateway Receive TMF AccountAdjustment Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),

	
	MODIV_BOSMB_RET1(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BOS MainBalanceAccountAdjustment Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_BOSMB_RET2(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BOS MainBalanceAccountAdjustment Request Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_BOSMB_RET3(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BOS MainBalanceAccountAdjustment Request Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_BOSMB_RET4(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BOS MainBalanceAccountAdjustment Request Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),
	MODIV_BOSMB_2001(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.RE2001.getResultCode(),"000","Modifying the validity term succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_SUCCESS),
	MODIV_BOSMB_UNKERR(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),"ERROR","319","INGateway Receive BOS MainBalanceAccountAdjustment Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_ERROR),

	MODIV_FORWARD_RE000(ECommand.MODIPPSVALIDITY.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_MODIPPSVALIDITY_RESPONSE_SUCCESS),

    //4
	DISIF_DS2AI_INCP(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_INCOMPLETE_DATA),
	MOPMAT_DS2AI_INCP(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE20000.getResultCode(),"319","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_INCOMPLETE_DATA),
	
	MOPMAT_DS2AI_RET1(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_DS2AI_RET2(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_DS2AI_RET3(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_DS2AI_RET4(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_DS2AI_UNKERR(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),"ERROR","333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_DS2AI_RE101010(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),

	MOPMAT_TMFAA_RET1(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive TMF AccountAdjustment Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_TMFAA_RET2(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive TMF AccountAdjustment Request Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_TMFAA_RET3(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive TMF AccountAdjustment Request Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_TMFAA_RET4(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive TMF AccountAdjustment Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_TMFAA_32(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.RE2001.getResultCode(),"000","Modifying the validity term succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_SUCCESS),
	MOPMAT_TMFAA_BAD(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive TMF Bad AccountAdjustment Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_TMFAA_UNKERR(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.TMF_AccountAdjustment.toString(),"ERROR","319","INGateway Receive TMF AccountAdjustment Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),

	MOPMAT_E01DE_RET1(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret1.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_E01DE_RET2(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret2.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_E01DE_RET3(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret3.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_E01DE_RET4(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret4.getResultCode(),"354","INGateway Receive E01 Query Destination Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_E01DE_32(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),EResultCode.RE32.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMAT_E01DE_UNKERR(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),"ERROR","354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),

	MOPMA_BOMAA_RET1(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BOS MainBalanceAccountAdjustment Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_BOMAA_RET2(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BOS MainBalanceAccountAdjustment Request Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_BOMAA_RET3(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BOS MainBalanceAccountAdjustment Request Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_BOMAA_RET4(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BOS MainBalanceAccountAdjustment Request Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_BOMAA_2001(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.RE2001.getResultCode(),"000","Modifying the validity term succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_SUCCESS),
	MOPMA_BOMAA_UNKERR(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),"ERROR","319","INGateway Receive BOS MainBalanceAccountAdjustment Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),

	MOPMA_E01DE_RET1(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret1.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_E01DE_RET2(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret2.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_E01DE_RET3(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret3.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_E01DE_RET4(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret4.getResultCode(),"354","INGateway Receive E01 Query Destination Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_E01DE_32(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),EResultCode.RE32.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_E01DE_UNKERR(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.E01_Destination.toString(),"ERROR","354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),

	MOPMA_BOFRA_RET1(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BOS FreeResourceAdjustment Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_BOFRA_RET2(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BOS FreeResourceAdjustment Request Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_BOFRA_RET3(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BOS FreeResourceAdjustment Request Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_BOFRA_RET4(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BOS FreeResourceAdjustment Request Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),
	MOPMA_BOFRA_2001(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),EResultCode.RE2001.getResultCode(),"000","Modifying the validity term succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_SUCCESS),
	MOPMA_BOFRA_UNKERR(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),"ERROR","319","INGateway Receive BOS FreeResourceAdjustment Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_ERROR),

	MOPMA_FORWARD_RE000(ECommand.MODIPPSMULTIATTR.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIATTR_RESPONSE_SUCCESS),

	//5
	ADDSS_DS2AI_RET1(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_DS2AI_RET2(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_DS2AI_RET3(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_DS2AI_RET4(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_DS2AI_UNKERR(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),"ERROR","333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_DS2AI_RE101010(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),
	ADDSN_DS2AI_BAD(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_INCOMPLETE_DATA),

	ADDSS_BSSDC_RET1(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Error", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_BSSDC_RET2(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Reject", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_BSSDC_RET3(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Abort", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_BSSDC_RET4(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoCallScreenNo Request Timeout", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_BSSDC_1000(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.RE1000000.getResultCode(),"000","The request is successfully executed.", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_SUCCESS),
	ADDSS_BSSDC_UNKERR(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),"ERROR","319","INGateway Receive BSS DoCallScreenNo Request Error", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),

	ADDSS_BMPCS_RET1(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BMP CallScreening Error", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_BMPCS_RET2(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BMP CallScreening Reject", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_BMPCS_RET3(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BMP CallScreening Abort", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_BMPCS_RET4(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BMP CallScreening Timeout", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_BMPCS_4050(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.RE405000000.getResultCode(),"000","The request is successfully executed.", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_SUCCESS),
	ADDSS_BMPCS_BAD(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive BMP Bad CallScreening Response", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),
	ADDSS_BMPCS_UNKERR(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),"ERROR","319","INGateway Receive BMP CallScreening Error", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_ERROR),

	ADDSS_FORWARD_RE000(ECommand.ADDSCRSCREENNO.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_ADDSCRSCREENNO_RESPONSE_SUCCESS),

	//6
	DELSS_DS2AI_RET1(ECommand.DELESCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_DS2AI_RET2(ECommand.DELESCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_DS2AI_RET3(ECommand.DELESCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_DS2AI_RET4(ECommand.DELESCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_DS2AI_UNKERR(ECommand.DELESCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),"ERROR","333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_DS2AI_RE101010(ECommand.DELESCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),

	DELSS_BSSDC_RET1(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Error", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_BSSDC_RET2(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Reject", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_BSSDC_RET3(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Abort", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_BSSDC_RET4(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoCallScreenNo Request Timeout", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_BSSDC_1000(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.RE1000000.getResultCode(),"000","The request is successfully executed.", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_SUCCESS),
	DELSS_BSSDC_UNKERR(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),"ERROR","319","INGateway Receive BSS DoCallScreenNo Request Error", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),

	DELSS_BMPCS_RET1(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BMP CallScreening Error", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_BMPCS_RET2(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BMP CallScreening Reject", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_BMPCS_RET3(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BMP CallScreening Abort", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_BMPCS_RET4(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BMP CallScreening Timeout", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_BMPCS_4050(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.RE405000000.getResultCode(),"000","The request is successfully executed.", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_SUCCESS),
	DELSS_BMPCS_BAD(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive BMP Bad CallScreening Response", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),
	DELSS_BMPCS_UNKERR(ECommand.DELESCRSCREENNO.getCommand(),ESubState.BMP_CallScreening.toString(),"ERROR","319","INGateway Receive BMP CallScreening Error", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_ERROR),

	DELSS_FORWARD_RE000(ECommand.DELESCRSCREENNO.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_DELESCRSCREENNO_RESPONSE_SUCCESS),

	//7
	MODST_DS2AI_RET1(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_DS2AI_RET2(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_DS2AI_RET3(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_DS2AI_RET4(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_DS2AI_UNKERR(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),"ERROR","333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_DS2AI_RE101010(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),

	MODST_BSSDC_RET1(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Error", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_BSSDC_RET2(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Reject", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_BSSDC_RET3(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Abort", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_BSSDC_RET4(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoCallScreenNo Request Timeout", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_BSSDC_1000(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.RE1000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_SUCCESS),
	MODST_BSSDC_UNKERR(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),"ERROR","319","INGateway Receive BSS DoCallScreenNo Request Error", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),

	MODST_BMPCS_RET1(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BMP CallScreening Error", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_BMPCS_RET2(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BMP CallScreening Reject", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_BMPCS_RET3(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BMP CallScreening Abort", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_BMPCS_RET4(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BMP CallScreening Timeout", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_BMPCS_4050(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.RE405000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_SUCCESS),
	MODST_BMPCS_BAD(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive BMP Bad CallScreening Response", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),
	MODST_BMPCS_UNKERR(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.BMP_CallScreening.toString(),"ERROR","319","INGateway Receive BMP CallScreening Error", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_ERROR),

	MODST_FORWARD_RE000(ECommand.MODISCRSCREENTYPE.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_MODISCRSCREENTYPE_RESPONSE_SUCCESS),

	//8
	MODWL_DS2AI_RET1(ECommand.MODISCRWHITELIST.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_DS2AI_RET2(ECommand.MODISCRWHITELIST.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_DS2AI_RET3(ECommand.MODISCRWHITELIST.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_DS2AI_RET4(ECommand.MODISCRWHITELIST.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_DS2AI_UNKERR(ECommand.MODISCRWHITELIST.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),"ERROR","333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_DS2AI_RE101010(ECommand.MODISCRWHITELIST.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),

	MODWL_BSSDC_RET1(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Error", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_BSSDC_RET2(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Reject", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_BSSDC_RET3(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoCallScreenNo Request Abort", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_BSSDC_RET4(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoCallScreenNo Request Timeout", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_BSSDC_1000(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),EResultCode.RE1000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_RESPONSE_MODISCRWHITELIST_SUCCESS),
	MODWL_BSSDC_UNKERR(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BSS_DoCallScreenNo.toString(),"ERROR","319","INGateway Receive BSS DoCallScreenNo Request Error", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),

	MODWL_BMPCS_RET1(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BMP CallScreening Error", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_BMPCS_RET2(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BMP CallScreening Reject", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_BMPCS_RET3(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BMP CallScreening Abort", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_BMPCS_RET4(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BMP CallScreening Timeout", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_BMPCS_4050(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.RE405000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_RESPONSE_MODISCRWHITELIST_SUCCESS),
	MODWL_BMPCS_BAD(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BMP_CallScreening.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive BMP Bad CallScreening Response", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),
	MODWL_BMPCS_UNKERR(ECommand.MODISCRWHITELIST.getCommand(),ESubState.BMP_CallScreening.toString(),"ERROR","319","INGateway Receive BMP CallScreening Error", EStats.INGATEWAY_SEND_HTTP_MODISCRWHITELIST_RESPONSE_ERROR),

	MODWL_FORWARD_RE000(ECommand.MODISCRWHITELIST.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_RESPONSE_MODISCRWHITELIST_SUCCESS),

	//9
	DISSN_DS2AI_RET1(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_DS2AI_RET2(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_DS2AI_RET3(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_DS2AI_RET4(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_DS2AI_UNKERR(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),"ERROR","333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_DS2AI_RE101010(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),

	DISSN_BSSDQ_RET1(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BSS_DoQueryCallScreen.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoQueryCallScreen Request Error", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_BSSDQ_RET2(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BSS_DoQueryCallScreen.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoQueryCallScreen Request Reject", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_BSSDQ_RET3(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BSS_DoQueryCallScreen.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoQueryCallScreen Request Abort", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_BSSDQ_RET4(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BSS_DoQueryCallScreen.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoQueryCallScreen Request Timeout", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_BSSDQ_1000(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BSS_DoQueryCallScreen.toString(),EResultCode.RE1000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_SUCCESS),
	DISSN_BSSDQ_UNKERR(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BSS_DoQueryCallScreen.toString(),"ERROR","319","INGateway Receive BSS DoQueryCallScreen Request Error", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),

	DISSN_BMPDN_RET1(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BMP_DispScrScreenNo.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BMP DispScrScreenNo Error", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_BMPDN_RET2(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BMP_DispScrScreenNo.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BMP DispScrScreenNo Reject", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_BMPDN_RET3(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BMP_DispScrScreenNo.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BMP DispScrScreenNo Abort", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_BMPDN_RET4(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BMP_DispScrScreenNo.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BMP DispScrScreenNo Timeout", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),
	DISSN_BMPDN_4050(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BMP_DispScrScreenNo.toString(),EResultCode.RE405000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_SUCCESS),
	DISSN_BMPDN_UNKERR(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.BMP_DispScrScreenNo.toString(),"ERROR","319","INGateway Receive BMP DispScrScreenNo Error", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_ERROR),

	DISSN_FORWARD_RE000(ECommand.DISPSCRSCREENNO.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_DISPSCRSCREENNO_RESPONSE_SUCCESS),

	//10
	ACTPP_BSDCS_RET1(ECommand.ACTPPSRBT.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Error", EStats.INGATEWAY_SEND_HTTP_ACTPPSRBT_RESPONSE_ERROR),
	ACTPP_BSDCS_RET2(ECommand.ACTPPSRBT.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Reject", EStats.INGATEWAY_SEND_HTTP_ACTPPSRBT_RESPONSE_ERROR),
	ACTPP_BSDCS_RET3(ECommand.ACTPPSRBT.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Abort", EStats.INGATEWAY_SEND_HTTP_ACTPPSRBT_RESPONSE_ERROR),
	ACTPP_BSDCS_RET4(ECommand.ACTPPSRBT.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoChangeService Request Timeout", EStats.INGATEWAY_SEND_HTTP_ACTPPSRBT_RESPONSE_ERROR),
	ACTPP_BSDCS_1000(ECommand.ACTPPSRBT.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.RE1000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_ACTPPSRBT_RESPONSE_SUCCESS),
	ACTPP_BSDCS_UNKERR(ECommand.ACTPPSRBT.getCommand(),ESubState.BSS_DoChangeService.toString(),"ERROR","319","INGateway Receive BSS DoChangeService Request Error", EStats.INGATEWAY_SEND_HTTP_ACTPPSRBT_RESPONSE_ERROR),

	ACTPP_FORWARD_RE000(ECommand.ACTPPSRBT.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_ACTPPSRBT_RESPONSE_SUCCESS),

	//11
	DELPK_BSDCS_RET1(ECommand.DELEPPSPKGRES.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Error", EStats.INGATEWAY_SEND_HTTP_DELEPPSPKGRES_RESPONSE_ERROR),
	DELPK_BSDCS_RET2(ECommand.DELEPPSPKGRES.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Reject", EStats.INGATEWAY_SEND_HTTP_DELEPPSPKGRES_RESPONSE_ERROR),
	DELPK_BSDCS_RET3(ECommand.DELEPPSPKGRES.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Abort", EStats.INGATEWAY_SEND_HTTP_DELEPPSPKGRES_RESPONSE_ERROR),
	DELPK_BSDCS_RET4(ECommand.DELEPPSPKGRES.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoChangeService Request Timeout", EStats.INGATEWAY_SEND_HTTP_DELEPPSPKGRES_RESPONSE_ERROR),
	DELPK_BSDCS_1000(ECommand.DELEPPSPKGRES.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.RE1000000.getResultCode(),"000","delete package succeeded.", EStats.INGATEWAY_SEND_HTTP_DELEPPSPKGRES_RESPONSE_SUCCESS),
	DELPK_BSDCS_UNKERR(ECommand.DELEPPSPKGRES.getCommand(),ESubState.BSS_DoChangeService.toString(),"ERROR","319","INGateway Receive BSS DoChangeService Request Error", EStats.INGATEWAY_SEND_HTTP_DELEPPSPKGRES_RESPONSE_ERROR),

	DELPK_FORWARD_RE000(ECommand.DELEPPSPKGRES.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_DELEPPSPKGRES_RESPONSE_SUCCESS),

	//12
	DEPID_BSDCS_RET1(ECommand.DELEPPSPACKAGEID.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Error", EStats.INGATEWAY_SEND_HTTP_DELEPPSPACKAGEID_RESPONSE_ERROR),
	DEPID_BSDCS_RET2(ECommand.DELEPPSPACKAGEID.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Reject", EStats.INGATEWAY_SEND_HTTP_DELEPPSPACKAGEID_RESPONSE_ERROR),
	DEPID_BSDCS_RET3(ECommand.DELEPPSPACKAGEID.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Abort", EStats.INGATEWAY_SEND_HTTP_DELEPPSPACKAGEID_RESPONSE_ERROR),
	DEPID_BSDCS_RET4(ECommand.DELEPPSPACKAGEID.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoChangeService Request Timeout", EStats.INGATEWAY_SEND_HTTP_DELEPPSPACKAGEID_RESPONSE_ERROR),
	DEPID_BSDCS_1000(ECommand.DELEPPSPACKAGEID.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.RE1000000.getResultCode(),"000","delete package succeeded.", EStats.INGATEWAY_SEND_HTTP_DELEPPSPACKAGEID_RESPONSE_SUCCESS),
	DEPID_BSDCS_UNKERR(ECommand.DELEPPSPACKAGEID.getCommand(),ESubState.BSS_DoChangeService.toString(),"ERROR","319","INGateway Receive BSS DoChangeService Request Error", EStats.INGATEWAY_SEND_HTTP_DELEPPSPACKAGEID_RESPONSE_ERROR),

	DEPID_FORWARD_RE000(ECommand.DELEPPSPACKAGEID.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_DELEPPSPACKAGEID_RESPONSE_SUCCESS),

	//13
	PURPK_BSDCS_RET1(ECommand.PURCHASEPPSPACKAGE.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Error", EStats.INGATEWAY_SEND_HTTP_PURCHASEPPSPACKAGE_RESPONSE_ERROR),
	PURPK_BSDCS_RET2(ECommand.PURCHASEPPSPACKAGE.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Reject", EStats.INGATEWAY_SEND_HTTP_PURCHASEPPSPACKAGE_RESPONSE_ERROR),
	PURPK_BSDCS_RET3(ECommand.PURCHASEPPSPACKAGE.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoChangeService Request Abort", EStats.INGATEWAY_SEND_HTTP_PURCHASEPPSPACKAGE_RESPONSE_ERROR),
	PURPK_BSDCS_RET4(ECommand.PURCHASEPPSPACKAGE.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoChangeService Request Timeout", EStats.INGATEWAY_SEND_HTTP_PURCHASEPPSPACKAGE_RESPONSE_ERROR),
	PURPK_BSDCS_1000(ECommand.PURCHASEPPSPACKAGE.getCommand(),ESubState.BSS_DoChangeService.toString(),EResultCode.RE1000000.getResultCode(),"000","Purchasing package succeeded.", EStats.INGATEWAY_SEND_HTTP_PURCHASEPPSPACKAGE_RESPONSE_SUCCESS),
	PURPK_BSDCS_UNKERR(ECommand.PURCHASEPPSPACKAGE.getCommand(),ESubState.BSS_DoChangeService.toString(),"ERROR","319","INGateway Receive BSS DoChangeService Request Error", EStats.INGATEWAY_SEND_HTTP_PURCHASEPPSPACKAGE_RESPONSE_ERROR),

	PURPK_FORWARD_RE000(ECommand.PURCHASEPPSPACKAGE.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_PURCHASEPPSPACKAGE_RESPONSE_SUCCESS),

	//14
	SETPR_E01DE_RET1(ECommand.SETPPSREWARD.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret1.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_E01DE_RET2(ECommand.SETPPSREWARD.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret2.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_E01DE_RET3(ECommand.SETPPSREWARD.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret3.getResultCode(),"354","INGateway Receive E01 Query Destination Response Abort", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_E01DE_RET4(ECommand.SETPPSREWARD.getCommand(),ESubState.E01_Destination.toString(),EResultCode.REret4.getResultCode(),"354","INGateway Receive E01 Query Destination Timeout", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_E01DE_32(ECommand.SETPPSREWARD.getCommand(),ESubState.E01_Destination.toString(),EResultCode.RE32.getResultCode(),"354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_E01DE_UNKERR(ECommand.SETPPSREWARD.getCommand(),ESubState.E01_Destination.toString(),"ERROR","354","INGateway Receive E01 Query Destination Response Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),

	SETPR_BOSMB_RET1(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BOS MainBalanceAccountAdjustment Request Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BOSMB_RET2(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BOS MainBalanceAccountAdjustment Request Reject", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BOSMB_RET3(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BOS MainBalanceAccountAdjustment Request Abort", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BOSMB_RET4(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BOS MainBalanceAccountAdjustment Request Timeout", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BOSMB_2001(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),EResultCode.RE2001.getResultCode(),"000","Modifying the validity term succeeded", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_SUCCESS),
	SETPR_BOSMB_UNKERR(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_MainBalanceAccountAdjustment.toString(),"ERROR","319","INGateway Receive BOS MainBalanceAccountAdjustment Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),

	SETPR_BOFRA_RET1(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BOS FreeResourceAdjustment Request Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BOFRA_RET2(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BOS FreeResourceAdjustment Request Reject", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BOFRA_RET3(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BOS FreeResourceAdjustment Request Abort", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BOFRA_RET4(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BOS FreeResourceAdjustment Request Timeout", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BOFRA_2001(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),EResultCode.RE2001.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_SUCCESS),
	SETPR_BOFRA_UNKERR(ECommand.SETPPSREWARD.getCommand(),ESubState.BOS_FreeResourceAdjustment.toString(),"ERROR","319","INGateway Receive BOS FreeResourceAdjustment Request Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),

	SETPR_BSDAB_RET1(ECommand.SETPPSREWARD.getCommand(),ESubState.BSS_DoAdjustBalance.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoAdjustBalance Request Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BSDAB_RET2(ECommand.SETPPSREWARD.getCommand(),ESubState.BSS_DoAdjustBalance.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoAdjustBalance Request Reject", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BSDAB_RET3(ECommand.SETPPSREWARD.getCommand(),ESubState.BSS_DoAdjustBalance.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoAdjustBalance Request Abort", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BSDAB_RET4(ECommand.SETPPSREWARD.getCommand(),ESubState.BSS_DoAdjustBalance.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoAdjustBalance Request Timeout", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SETPR_BSDAB_1000(ECommand.SETPPSREWARD.getCommand(),ESubState.BSS_DoAdjustBalance.toString(),EResultCode.RE1000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_SUCCESS),
	SETPR_BSDAB_UNKERR(ECommand.SETPPSREWARD.getCommand(),ESubState.BSS_DoAdjustBalance.toString(),"ERROR","319","INGateway Receive BSS DoAdjustBalance Request Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),

	SETPR_FORWARD_RE000(ECommand.SETPPSREWARD.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_SUCCESS),

	//15
	DPPFN_BSDQF_RET1(ECommand.DISPPPSFNTELNO.getCommand(),ESubState.BSS_DoQueryFN.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoQueryFN Request Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSFNTELNO_RESPONSE_ERROR),
	DPPFN_BSDQF_RET2(ECommand.DISPPPSFNTELNO.getCommand(),ESubState.BSS_DoQueryFN.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoQueryFN Request Reject", EStats.INGATEWAY_SEND_HTTP_DISPPPSFNTELNO_RESPONSE_ERROR),
	DPPFN_BSDQF_RET3(ECommand.DISPPPSFNTELNO.getCommand(),ESubState.BSS_DoQueryFN.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoQueryFN Request Abort", EStats.INGATEWAY_SEND_HTTP_DISPPPSFNTELNO_RESPONSE_ERROR),
	DPPFN_BSDQF_RET4(ECommand.DISPPPSFNTELNO.getCommand(),ESubState.BSS_DoQueryFN.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoQueryFN Request Timeout", EStats.INGATEWAY_SEND_HTTP_DISPPPSFNTELNO_RESPONSE_ERROR),
	DPPFN_BSDQF_1000(ECommand.DISPPPSFNTELNO.getCommand(),ESubState.BSS_DoQueryFN.toString(),EResultCode.RE1000000.getResultCode(),"000","Purchasing package succeeded.", EStats.INGATEWAY_SEND_HTTP_DISPPPSFNTELNO_RESPONSE_SUCCESS),
	DPPFN_BSDQF_UNKERR(ECommand.DISPPPSFNTELNO.getCommand(),ESubState.BSS_DoQueryFN.toString(),"ERROR","319","INGateway Receive BSS DoQueryFN Request Error", EStats.INGATEWAY_SEND_HTTP_DISPPPSFNTELNO_RESPONSE_ERROR),

	DPPFN_FORWARD_RE000(ECommand.DISPPPSFNTELNO.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_DISPPPSFNTELNO_RESPONSE_SUCCESS),

	//16
	MODLA_DS2AI_RET1(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_DS2AI_RET2(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_DS2AI_RET3(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_DS2AI_RET4(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_DS2AI_UNKERR(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),"ERROR","333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_DS2AI_RE101010(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),

	MODLA_USMOD_RET1(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive USMP ModifySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_USMOD_RET2(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive USMP ModifySubscriber Rejectt", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_USMOD_RET3(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive USMP ModifySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_USMOD_RET4(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive USMP ModifySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_USMOD_0000(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REVSMP00000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_SUCCESS),
	MODLA_USMOD_BAD(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive USMP Bad ModifySubscriber Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_USMOD_UNKERR(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),"ERROR","319","INGateway Receive USMP ModifySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),

	MODLA_DOMSB_RET1(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoModifySubscriberBasicInfo Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_DOMSB_RET2(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoModifySubscriberBasicInfo Request Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_DOMSB_RET3(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoModifySubscriberBasicInfo Request Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_DOMSB_RET4(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoModifySubscriberBasicInfo Request Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),
	MODLA_DOMSB_1000(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),EResultCode.RE1000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_SUCCESS),
	MODLA_DOMSB_UNKERR(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),"ERROR","319","INGateway Receive BSS DoModifySubscriberBasicInfo Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_ERROR),

	MODLA_FORWARD_RE000(ECommand.MODIPPSLANGUAGE.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_MODIPPSLANGUAGE_RESPONSE_SUCCESS),

	//17
	MOSMS_DS2AI_RET1(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_DS2AI_RET2(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_DS2AI_RET3(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_DS2AI_RET4(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_DS2AI_UNKERR(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),"ERROR","333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_DS2AI_RE101010(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),

	MOSMS_USMOD_RET1(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive USMP ModifySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_USMOD_RET2(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive USMP ModifySubscriber Rejectt", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_USMOD_RET3(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive USMP ModifySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_USMOD_RET4(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive USMP ModifySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_USMOD_0000(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REVSMP00000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_SUCCESS),
	MOSMS_USMOD_BAD(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive USMP Bad ModifySubscriber Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_USMOD_UNKERR(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.USMP_ModifySubscriber.toString(),"ERROR","319","INGateway Receive USMP ModifySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),

	MOSMS_DOMSB_RET1(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoModifySubscriberBasicInfo Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_DOMSB_RET2(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoModifySubscriberBasicInfo Request Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_DOMSB_RET3(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoModifySubscriberBasicInfo Request Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_DOMSB_RET4(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoModifySubscriberBasicInfo Request Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),
	MOSMS_DOMSB_1000(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),EResultCode.RE1000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_SUCCESS),
	MOSMS_DOMSB_UNKERR(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.BSS_DoModifySubscriberBasicInfo.toString(),"ERROR","319","INGateway Receive BSS DoModifySubscriberBasicInfo Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_ERROR),

	MOSMS_FORWARD_RE000(ECommand.MODIPPSSMSLANGUAGE.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_MODIPPSSMSLANGUAGE_RESPONSE_SUCCESS),

	//18
	MODCL_DS2AI_RET1(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_DS2AI_RET2(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_DS2AI_RET3(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_DS2AI_RET4(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_DS2AI_UNKERR(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),"ERROR","333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_DS2AI_RE101010(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE101010.getResultCode(),"334","INGateway Receive DS2A Not Found Service Location", EStats.INGATEWAY_SEND_HTTP_NOT_FOUND_SERVICE_LOCATION_RESPONSE),

	MODCL_SDFGAC_RET1(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_GetAmfCounter.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive SDF GET amfCounter Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_SDFGAC_RET2(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_GetAmfCounter.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive SDF GET amfCounter Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_SDFGAC_RET3(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_GetAmfCounter.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive SDF GET amfCounter Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_SDFGAC_RET4(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_GetAmfCounter.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive SDF GET amfCounter Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_SDFGAC_UNKERR(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_GetAmfCounter.toString(),"ERROR","319","INGateway Receive SDF GET amfCounter Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),

	MODCL_SDFPAC_RET1(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_PutAmfCounter.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive SDF PUT amfCounter Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_SDFPAC_RET2(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_PutAmfCounter.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive SDF PUT amfCounter Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_SDFPAC_RET3(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_PutAmfCounter.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive SDF PUT amfCounter Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_SDFPAC_RET4(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_PutAmfCounter.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive SDF PUT amfCounter Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_DOSNB_20000(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_PutAmfCounter.toString(),EResultCode.RE20000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_SUCCESS),
	MODCL_SDFPAC_UNKERR(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SDF_PutAmfCounter.toString(),"ERROR","319","INGateway Receive SDF PUT amfCounter Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),

	MODCL_DOSNB_RET1(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.BSS_DoSetNegativeBalance.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSS DoSetNegativeBalance Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_DOSNB_RET2(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.BSS_DoSetNegativeBalance.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSS DoSetNegativeBalance Request Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_DOSNB_RET3(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.BSS_DoSetNegativeBalance.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSS DoSetNegativeBalance Request Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_DOSNB_RET4(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.BSS_DoSetNegativeBalance.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSS DoSetNegativeBalance Request Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),
	MODCL_DOSNB_1000(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.BSS_DoSetNegativeBalance.toString(),EResultCode.RE1000000.getResultCode(),"000","Operation succeeded", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_SUCCESS),
	MODCL_DOSNB_UNKERR(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.BSS_DoSetNegativeBalance.toString(),"ERROR","319","INGateway Receive BSS DoSetNegativeBalance Request Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),

	MODCL_FORWARD_RE000(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_SUCCESS),
	MODCL_FORWARD_END(ECommand.MODIPPSCREDITLIMIT.getCommand(),ESubState.End_ModiPPSCreditLimit.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSCREDITLIMIT_RESPONSE_ERROR),

	//19
	CHTRI_BOACR_RET1(ECommand.CHGTRIGCHRGACNT.getCommand(),ESubState.BOS_AccountRecharge.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BOS AccountRecharge Request Error", EStats.INGATEWAY_SEND_HTTP_CHGTRIGCHRGACNT_RESPONSE_ERROR),
	CHTRI_BOACR_RET2(ECommand.CHGTRIGCHRGACNT.getCommand(),ESubState.BOS_AccountRecharge.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BOS AccountRecharge Request Reject", EStats.INGATEWAY_SEND_HTTP_CHGTRIGCHRGACNT_RESPONSE_ERROR),
	CHTRI_BOACR_RET3(ECommand.CHGTRIGCHRGACNT.getCommand(),ESubState.BOS_AccountRecharge.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BOS AccountRecharge Request Abort", EStats.INGATEWAY_SEND_HTTP_CHGTRIGCHRGACNT_RESPONSE_ERROR),
	CHTRI_BOACR_RET4(ECommand.CHGTRIGCHRGACNT.getCommand(),ESubState.BOS_AccountRecharge.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BOS AccountRecharge Request Timeout", EStats.INGATEWAY_SEND_HTTP_CHGTRIGCHRGACNT_RESPONSE_ERROR),
	CHTRI_BOACR_2001(ECommand.CHGTRIGCHRGACNT.getCommand(),ESubState.BOS_AccountRecharge.toString(),EResultCode.RE2001.getResultCode(),"000","Manual recharging succeeds", EStats.INGATEWAY_SEND_HTTP_CHGTRIGCHRGACNT_RESPONSE_SUCCESS),
	CHTRI_BOACR_UNKERR(ECommand.CHGTRIGCHRGACNT.getCommand(),ESubState.BOS_AccountRecharge.toString(),"ERROR","319","INGateway Receive BOS AccountRecharge Request Error", EStats.INGATEWAY_SEND_HTTP_CHGTRIGCHRGACNT_RESPONSE_ERROR),

	CHTRI_FORWARD_RE000(ECommand.CHGTRIGCHRGACNT.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_CHGTRIGCHRGACNT_RESPONSE_SUCCESS),

	//20
	ACTIV_BSSDF_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.BSS_DoFirstActivationCRM.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive BSSBroker Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_BSSDF_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.BSS_DoFirstActivationCRM.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive BSSBroker Reject", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_BSSDF_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.BSS_DoFirstActivationCRM.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive BSSBroker Abort", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_BSSDF_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.BSS_DoFirstActivationCRM.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive BSSBroker Timeout", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_BSSDF_1000(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.BSS_DoFirstActivationCRM.toString(),EResultCode.RE1000000.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_BSSDF_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.BSS_DoFirstActivationCRM.toString(),"ERROR","319","INGateway Receive BSSBroker Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),

	ACTIV_E01NC_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_NewClassOfService.toString(),EResultCode.REret1.getResultCode(),"354","INGateway Receive E01 Query NewClassOfService Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01NC_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_NewClassOfService.toString(),EResultCode.REret2.getResultCode(),"354","INGateway Receive E01 Query NewClassOfService Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01NC_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_NewClassOfService.toString(),EResultCode.REret3.getResultCode(),"354","INGateway Receive E01 Query NewClassOfService Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01NC_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_NewClassOfService.toString(),EResultCode.REret4.getResultCode(),"354","INGateway Receive E01 Query NewClassOfService Timeout", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01NC_RE32(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_NewClassOfService.toString(),EResultCode.RE32.getResultCode(),"354","INGateway Receive E01 Query NewClassOfService Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01NC_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_NewClassOfService.toString(),"ERROR","354","INGateway Receive E01 Query NewClassOfService Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01NC_50000(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_NewClassOfService.toString(),"ERROR","50000","INGateway Receive E01 Query NewClassOfService Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),

	ACTIV_E01CT_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_CosToPromotionName.toString(),EResultCode.REret1.getResultCode(),"354","INGateway Receive E01 Query CosToPromotionName Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01CT_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_CosToPromotionName.toString(),EResultCode.REret2.getResultCode(),"354","INGateway Receive E01 Query CosToPromotionName Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01CT_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_CosToPromotionName.toString(),EResultCode.REret3.getResultCode(),"354","INGateway Receive E01 Query CosToPromotionName Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01CT_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_CosToPromotionName.toString(),EResultCode.REret4.getResultCode(),"354","INGateway Receive E01 Query CosToPromotionName Timeout", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01CT_RE32(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_CosToPromotionName.toString(),EResultCode.RE32.getResultCode(),"354","INGateway Receive E01 Query CosToPromotionName Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_E01CT_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_CosToPromotionName.toString(),"ERROR","354","INGateway Receive E01 Query CosToPromotionName Response Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),

	ACTIV_AMFMC_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive AMF ManageCounters Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_AMFMC_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive AMF ManageCounters Reject", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_AMFMC_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive AMF ManageCounters Abort", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_AMFMC_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive AMF ManageCounters Timeout", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_AMFMC_BAD(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters.toString(),EResultCode.RE40301.getResultCode(),"319","INGateway Receive AMF Bad ManageCounters Response", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_AMFMC_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters.toString(),"ERROR","319","INGateway Receive AMF ManageCounters Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_AMFMC_ER(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters.toString(),EResultCode.REEcodeNot0.getResultCode(),"319","INGateway Receive AMF ManageCounters Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	
	ACTIV_USMPM_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret1.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_USMPM_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret2.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_USMPM_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret3.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_USMPM_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.REret4.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_USMPM_BAD(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.RE50000.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_USMPM_2001(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifySubscriber.toString(),EResultCode.RE2001.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_USMPM_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifySubscriber.toString(),"ERROR","000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),

	ACTIV_DS2A_2001(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE2001.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	
	ACTIV_USMPF_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_FirstActivate.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive USMP FirstActivate Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPF_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_FirstActivate.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive USMP FirstActivate Reject", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPF_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_FirstActivate.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive USMP FirstActivate Abort", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPF_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_FirstActivate.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive USMP FirstActivate Timeout", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPF_ER(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_FirstActivate.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive USMP FirstActivate Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPF_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_FirstActivate.toString(),"ERROR","319","INGateway Receive USMP FirstActivate Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPF_BAD(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_FirstActivate.toString(),EResultCode.RE40301.getResultCode(),"319","INGateway Receive USMP Bad FirstActivate Response", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),

	ACTIV_SSBWO_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SSB_WorkOrderFirstAct.toString(),EResultCode.REret1.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SSBWO_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SSB_WorkOrderFirstAct.toString(),EResultCode.REret2.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SSBWO_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SSB_WorkOrderFirstAct.toString(),EResultCode.REret3.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SSBWO_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SSB_WorkOrderFirstAct.toString(),EResultCode.REret4.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SSBWO_2001(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SSB_WorkOrderFirstAct.toString(),EResultCode.RE2001.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SSBWO_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SSB_WorkOrderFirstAct.toString(),"ERROR","000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),

	ACTIV_AMFRMF_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters_AND_RMF_RewardMonitoring.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive AMF ManageCounter or RMF RewardMonitoring Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_AMFRMF_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters_AND_RMF_RewardMonitoring.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive AMF ManageCounter or RMF RewardMonitoring Reject", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_AMFRMF_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters_AND_RMF_RewardMonitoring.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive AMF ManageCounter or RMF RewardMonitoring Abort", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_AMFRMF_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters_AND_RMF_RewardMonitoring.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive AMF ManageCounter or RMF RewardMonitoring Timeout", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_AMFRMF_20000(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters_AND_RMF_RewardMonitoring.toString(),EResultCode.RE20000.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_AMFRMF_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_ManageCounters_AND_RMF_RewardMonitoring.toString(),"ERROR","319","INGateway Receive AMF ManageCounter or RMF RewardMonitoring Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	
	ACTIV_RMFRM_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.RMF_RewardMonitoring.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive RMF RewardMonitoring Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_RMFRM_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.RMF_RewardMonitoring.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive RMF RewardMonitoring Reject", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_RMFRM_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.RMF_RewardMonitoring.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive RMF RewardMonitoring ABort", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_RMFRM_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.RMF_RewardMonitoring.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive RMF RewardMonitoring  Timeout", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_RMFRM_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.RMF_RewardMonitoring.toString(),"ERROR","319","INGateway Receive RMF RewardMonitoring Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),

	ACTIV_E01NO_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_Notification.toString(),EResultCode.REret1.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_E01NO_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_Notification.toString(),EResultCode.REret2.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_E01NO_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_Notification.toString(),EResultCode.REret3.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_E01NO_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_Notification.toString(),EResultCode.REret4.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_E01NO_RE32(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_Notification.toString(),EResultCode.RE32.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_E01NO_2000(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_Notification.toString(),EResultCode.RE20000.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_E01NO_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.E01_Notification.toString(),"ERROR","000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),


	ACTIV_AMFCB_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.REret1.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_AMFCB_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.REret2.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_AMFCB_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.REret3.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_AMFCB_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.REret4.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_AMFCB_2000(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.RE20000.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_AMFCB_BAD(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.RE50000.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_AMFCB_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_CheckBalance.toString(),"ERROR","000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_AMFCB_ER(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.AMF_CheckBalance.toString(),EResultCode.REEcodeNot0.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	
	
	ACTIV_SRFCS_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SRFC_SpecializedResource.toString(),EResultCode.REret1.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SRFCS_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SRFC_SpecializedResource.toString(),EResultCode.REret2.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SRFCS_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SRFC_SpecializedResource.toString(),EResultCode.REret3.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SRFCS_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SRFC_SpecializedResource.toString(),EResultCode.REret4.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SRUP_2000(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SRFC_SpecializedResource.toString(),EResultCode.RE2001.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SRFCS_BAD(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SRFC_SpecializedResource.toString(),EResultCode.RE50000.getResultCode(),"000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),
	ACTIV_SRFCS_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SRFC_SpecializedResource.toString(),"ERROR","000","Activating subscriber successfully.", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),

	ACTIV_USMPMC_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifyCounter.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive USMP ModifyCounter Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPMC_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifyCounter.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive USMP ModifyCounter Reject", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPMC_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifyCounter.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive USMP ModifyCounter Abort", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPMC_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifyCounter.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive USMP ModifyCounter Timeout", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPMC_BAD(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifyCounter.toString(),EResultCode.RE40301.getResultCode(),"319","INGateway Receive USMP Bad ModifyCounter Response", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPMC_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifyCounter.toString(),"ERROR","319","INGateway Receive USMP ModifyCounter Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPMC_ER(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_ModifyCounter.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive USMP ModifyCounter Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	
	ACTIV_USMPIC_RET1(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_InquiryCounter.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive USMP InquiryCounter Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPIC_RET2(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_InquiryCounter.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive USMP InquiryCounter Reject", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPIC_RET3(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_InquiryCounter.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive USMP InquiryCounter Abort", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPIC_RET4(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_InquiryCounter.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive USMP InquiryCounter Timeout", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPIC_BAD(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_InquiryCounter.toString(),EResultCode.RE40301.getResultCode(),"319","INGateway Receive USMP Bad InquiryCounter Response", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPIC_UNKERR(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_InquiryCounter.toString(),"ERROR","319","INGateway Receive USMP InquiryCounter Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	ACTIV_USMPIC_ER(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.USMP_InquiryCounter.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive USMP InquiryCounter Error", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_ERROR),
	
	SET_RMF_RET1(ECommand.SETPPSREWARD.getCommand(),ESubState.RMF_RewardOffering.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive RMF Offerring Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_RMF_RET2(ECommand.SETPPSREWARD.getCommand(),ESubState.RMF_RewardOffering.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive RMF Offerring Reject", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_RMF_RET3(ECommand.SETPPSREWARD.getCommand(),ESubState.RMF_RewardOffering.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive RMF Offerring Abort", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_RMF_RET4(ECommand.SETPPSREWARD.getCommand(),ESubState.RMF_RewardOffering.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive RMF Offerring Timeout", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_RMF_BAD(ECommand.SETPPSREWARD.getCommand(),ESubState.RMF_RewardOffering.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive RMF Bad Offerring Response", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_RMF_UNKERR(ECommand.SETPPSREWARD.getCommand(),ESubState.RMF_RewardOffering.toString(),"ERROR","319","INGateway Receive RMF Offerring Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_RMF_20000(ECommand.SETPPSREWARD.getCommand(),ESubState.RMF_RewardOffering.toString(),EResultCode.RE20000.getResultCode(),"000","Operation succeeded.", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_SUCCESS),
	
	SET_DS2_221(ECommand.SETPPSREWARD.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE221.getResultCode(),"002","INGateway Receive DS2A InquirySubscriber Response Unknown Msisdn", EStats.INGATEWAY_RECEIVE_DS2A_INQUIRYSUBSCRIBER_UNKNOWN_MSISDN),
	SET_DS2_ECODE(ECommand.SETPPSREWARD.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REEcodeNot0.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_DS2_RET1(ECommand.SETPPSREWARD.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_DS2_RET2(ECommand.SETPPSREWARD.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_DS2_RET3(ECommand.SETPPSREWARD.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_DS2_RET4(ECommand.SETPPSREWARD.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),
	SET_DS2_BAD(ECommand.SETPPSREWARD.getCommand(),ESubState.End_SetPPSReward.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_SETPPSREWARD_RESPONSE_ERROR),

	WAL_TMF_RET1(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive TMF AccountAdjustment Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_TMF_RET2(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive TMF AccountAdjustment Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_TMF_RET3(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive TMF AccountAdjustment Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_TMF_RET4(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive TMF AccountAdjustment Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_TMF_BAD(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive TMF Bad AccountAdjustment Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_TMF_UNKERR(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.TMF_AccountAdjustment.toString(),"ERROR","319","INGateway Receive TMF AccountAdjustment Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_TMF_2001(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.TMF_AccountAdjustment.toString(),EResultCode.RE2001.getResultCode(),"000","Operation succeeded.", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_SUCCESS),
	WAL_DS2_ECODE(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REEcodeNot0.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_DS2_BAD(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.Idle_Request.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"322","INGateway Send HTTP Incomplete Data Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),

	WAL_RMF_RET1(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.RMF_RewardAdjustment.toString(),EResultCode.REret1.getResultCode(),"319","INGateway Receive RMF RewardAdjustment Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_RMF_RET2(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.RMF_RewardAdjustment.toString(),EResultCode.REret2.getResultCode(),"319","INGateway Receive RMF RewardAdjustment Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_RMF_RET3(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.RMF_RewardAdjustment.toString(),EResultCode.REret3.getResultCode(),"319","INGateway Receive RMF RewardAdjustment Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_RMF_RET4(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.RMF_RewardAdjustment.toString(),EResultCode.REret4.getResultCode(),"328","INGateway Receive RMF RewardAdjustment Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_RMF_BAD(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.RMF_RewardAdjustment.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive RMF Bad RewardAdjustment Response", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_RMF_UNKERR(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.RMF_RewardAdjustment.toString(),"ERROR","319","INGateway Receive RMF RewardAdjustment Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_RMF_20000(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.RMF_RewardAdjustment.toString(),EResultCode.RE20000.getResultCode(),"000","Operation succeeded.", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_SUCCESS),
	
	WAL_DS2_221(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.RE221.getResultCode(),"002","INGateway Receive DS2A InquirySubscriber Response Unknown Msisdn", EStats.INGATEWAY_RECEIVE_DS2A_INQUIRYSUBSCRIBER_UNKNOWN_MSISDN),
	WAL_DS2_RET1(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret1.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Error", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_DS2_RET2(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret2.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Reject", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_DS2_RET3(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret3.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Abort", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	WAL_DS2_RET4(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.DS2A_InquirySubscriber.toString(),EResultCode.REret4.getResultCode(),"333","INGateway Receive DS2A InquirySubscriber Timeout", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_ERROR),
	
	WAL_SUC_20000(ECommand.MODIPPSMULTIWALLET.getCommand(),ESubState.End_ModiPPSMultiWallet.toString(),EResultCode.RE20000.getResultCode(),"000","Operation succeeded.", EStats.INGATEWAY_SEND_HTTP_MODIPPSMULTIWALLET_RESPONSE_SUCCESS),
	
	ACTIV_FORWARD_RE000(ECommand.ACTIVATEPPSSINGSUB.getCommand(),ESubState.SGSCPSMOI_ForwardCommand.toString(),EResultCode.RE20000.getResultCode(),"000","INGateway Receive SMOI Success", EStats.INGATEWAY_SEND_HTTP_ACTIVATEPPSSINGSUB_RESPONSE_SUCCESS),

	RMC_REQ_RE20000(ECommand.RMC.getCommand(),ESubState.Idle_RMC.toString(),EResultCode.REClient_send_request.getResultCode(),"20000","Success", EStats.INGATEWAY_RECEIVE_RMF_RMCA_RESPONSE),
	RMC_REQ_BAD(ECommand.RMC.getCommand(),ESubState.Idle_RMC.toString(),EResultCode.REClient_send_Bad_request.getResultCode(),"50000","Bad request", EStats.INGATEWAY_RECEIVE_RMF_RMCA_RESPONSE),

	UNKNOWN(ECommand.UNKNOWN.getCommand(),ESubState.Unknown.toString(),EResultCode.RE50000.getResultCode(),"319","INGateway Receive Error.", EStats.INGATEWAY_SEND_HTTP_RESPONSE_ERROR),

	;
	private String clientCommand;
	private String currentState;
	private String resultCode;
	private String finalResultCode;
	private String finalDesc;
	private EStats stat;

	EFinalCode(String clientCommand,String currentState, String resultCode, String finalResultCode, String finalDesc, EStats stat) {
        this.clientCommand = clientCommand;
        this.currentState = currentState;
        this.resultCode = resultCode;
        this.finalResultCode = finalResultCode;
        this.finalDesc = finalDesc;
        this.stat = stat;
    }

	public String getClientCommand() {
		return clientCommand;
	}

	public String getResultCode() {
		return resultCode;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getFinalResultCode() {
		return finalResultCode;
	}

	public String getFinalDesc() {
		return finalDesc;
	}

	public EStats getStat() {
		return stat;
	}

	public void setStat(EStats stat) {
		this.stat = stat;
	}

	/*
	public static EFinalCode mappingFnalCode(KeyFinalCode keyFinalCode) {

		int i = 0;
		
		for(KeyFinalCode keyFi : InitalData.mapFinalCode.keySet()) {

			if (keyFinalCode.getCmd().matches(keyFi.getCmd())
					&& keyFinalCode.getCurrentState().matches(keyFi.getCurrentState())
					&& keyFinalCode.getInternalCode().matches(keyFi.getInternalCode()) ) {

				System.out.println("Loop : "+i);
				
				return InitalData.mapFinalCode.get(keyFi);

			}
			
			i++;
			
		}
		
		return null;

	}
	*/

}
