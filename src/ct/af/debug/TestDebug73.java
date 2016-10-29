package ct.af.debug;

import ec02.exception.MessageParserException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TestDebug73 extends Testamentor {

	public static void main(String[] param) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException, MessageParserException, ExecutionException, InterruptedException {

		pathScn = "/13.purchasePPSPackage/BSS/";

		endScn = 0;
		unitTest = false;
		
		//1.DispPPSInfo BOS
//		testCaseList.add("Client_dispPPSInfo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("E01_destination.xml");
//		testCaseList.add("BOS_subscriberAccountEnquiry.xml");
//		//DispPPSInfo INS	
//		testCaseList.add("Client_dispPPSInfo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("AMF_CheckBalance.xml");
//		testCaseList.add("AMF_Obtain.xml");
//		testCaseList.add("USMP_InquirySubscriberInfo.xml");
//		//DispPPSInfo RBTS
//		testCaseList.add("Client_dispPPSInfo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//		
//		//2.DispPPSPkgrew BOS
//		testCaseList.add("Client_dispPPSPkgrew.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("E01_destination.xml");
//		testCaseList.add("BOS_subscriberAccountEnquiry.xml");
//		//DispPPSPkgrew INS
//		testCaseList.add("Client_dispPPSPkgrew.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		//DispPPSPkgrew RBTS
//		testCaseList.add("Client_dispPPSPkgrew.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//		
//		//3.modiPPSValidity BOS
//		testCaseList.add("Client_modiPPSValidity.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("E01_destination.xml");
//		testCaseList.add("BOS_mainBalanceAccountAdjustment.xml");
//		//modiPPSValidity INS
//		testCaseList.add("Client_modiPPSValidity.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("AMF_modifyCounters.xml");
//		testCaseList.add("TMF_AccountAdjustment.xml");
//		//modiPPSValidity RBTS
//		testCaseList.add("Client_modiPPSValidity.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//		
//		//4.modiPPSMultiAttr BOS
//		testCaseList.add("Client_modiPPSMultiAttr_freeResourceAdjustment.xml");
//		testCaseList.add("Client_modiPPSMultiAttr_mainBalanceAccountAdjustment.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("E01_destination.xml");
//		testCaseList.add("BOS_freeResourceAdjustment.xml");
//		testCaseList.add("BOS_mainBalanceAccountAdjustment.xml");
//		//modiPPSMultiAttr INS
//		testCaseList.add("Client_modiPPSMultiAttr.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("TMF_AccountAdjustment.xml");
//		//modiPPSMultiAttr RBTS
//		testCaseList.add("Client_modiPPSMultiAttr.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//		
//		
//		//5.addScrScreenNo BSS
//		testCaseList.add("Client_addScrScreenNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doCallScreenNo_addScrScreenNo.xml");
//		//addScrScreenNo INS
//		testCaseList.add("Client_addScrScreenNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");	
//		testCaseList.add("BMP_callScreening.xml");
//		//addScrScreenNo RBTS
//		testCaseList.add("Client_addScrScreenNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//		
		//6.deleScrScreenNo BSS
//		testCaseList.add("Client_deleScrScreenNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doCallScreenNo_deleScrScreenNo.xml");
//		//deleScrScreenNo INS
//		testCaseList.add("Client_deleScrScreenNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BMP_callScreening.xml");
		//deleScrScreenNo RBTS
//		testCaseList.add("Client_deleScrScreenNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//		

//		//7.modiScrScreenType BSS
//		testCaseList.add("Client_modiScrScreenType.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doCallScreenNo_deleScrScreenNo.xml");
//		//modiScrScreenType INS
//		testCaseList.add("Client_modiScrScreenType.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BMP_callScreening.xml");
//		//modiScrScreenType RBTS
//		testCaseList.add("Client_modiScrScreenType.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//
//		//8.modiScrWhiteList BSS
//		testCaseList.add("Client_modiScrWhiteList.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doCallScreenNo_modiScrWhiteList.xml");
//		//modiScrWhiteList INS
//		testCaseList.add("Client_modiScrWhiteList.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BMP_callScreening.xml");
//		//modiScrWhiteList RBTS
//		testCaseList.add("Client_modiScrWhiteList.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//
//
//		//9.dispScrScreenNo BSS
//		testCaseList.add("Client_dispScrScreenNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doQueryCallScreen.xml");
		//dispScrScreenNo INS
//		testCaseList.add("Client_dispScrScreenNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BMP_dispScrScreenNo.xml");
		//dispScrScreenNo RBTS
//		testCaseList.add("Client_dispScrScreenNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//	
//		//10.actPPSRBT BSS
//		testCaseList.add("Client_actPPSRBT.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doChangeService.xml");
//		//actPPSRBT INS
//		testCaseList.add("Client_actPPSRBT.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		//actPPSRBT RBTS
//		testCaseList.add("Client_actPPSRBT.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//
//		//11.delePPSPkgres BSS
//		testCaseList.add("Client_delePPSPkgres.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doChangeService_delePPSPkgres.xml");
//		//delePPSPkgres INS
//		testCaseList.add("Client_delePPSPkgres.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		//delePPSPkgres RBTS
//		testCaseList.add("Client_delePPSPkgres.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//
//		//12.delePPSPackageID BSS
//		testCaseList.add("Client_delePPSPackageID.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doChangeService_delePPSPkgres.xml");
//		//delePPSPackageID INS
//		testCaseList.add("Client_delePPSPackageID.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		//delePPSPackageID RBTS
//		testCaseList.add("Client_delePPSPackageID.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//		
//		//13.purchasePPSPackage BSS
		testCaseList.add("Client_purchasePPSPackage.xml");
		testCaseList.add("DS2A_inquirySubscriber.xml");
		testCaseList.add("BSS_doChangeService_purchasePPSPackage.xml");
//		//purchasePPSPackage INS
//		testCaseList.add("Client_purchasePPSPackage.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		//purchasePPSPackage RBTS
//		testCaseList.add("Client_purchasePPSPackage.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//		

		//14.setPPSReward BOS
//		testCaseList.add("Client_setPPSReward_freeResourceAdjustment.xml");
//		testCaseList.add("Client_setPPSReward_mainBalanceAccountAdjustment.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("E01_destination.xml");
//		testCaseList.add("BOS_freeResourceAdjustment.xml");
//		testCaseList.add("BOS_mainBalanceAccountAdjustment.xml");
//		//setPPSReward BSS
//		testCaseList.add("Client_setPPSReward.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doAdjustBalance.xml");		
//		//setPPSReward INS
//		testCaseList.add("Client_setPPSReward.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		//setPPSReward RBTS
//		testCaseList.add("Client_setPPSReward.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//
//		//15.dispPPSFntelNo BSS
//		testCaseList.add("Client_dispPPSFntelNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doQueryFN.xml");
//		//dispPPSFntelNo INS
//		testCaseList.add("Client_dispPPSFntelNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		//dispPPSFntelNo RBTS
//		testCaseList.add("Client_dispPPSFntelNo.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//
//		//16.modiPPSLanguage BSS
//		testCaseList.add("Client_modiPPSLanguage.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doCallScreenNo_modiPPSLanguageSubscriberBasicInfo.xml");
//		//modiPPSLanguage INS
//		testCaseList.add("Client_modiPPSLanguage.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("USMP_Modi.xml");
//		//modiPPSLanguage RBTS
//		testCaseList.add("Client_modiPPSLanguage.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//
//		//17.modiPPSSMSLanguage BSS
//		testCaseList.add("Client_modiPPSSMSLanguage.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doCallScreenNo_modiPPSSmsLanguageModifySubscriberBasicInfo.xml");
//		//modiPPSSMSLanguage INS
//		testCaseList.add("Client_modiPPSSMSLanguage.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("USMP_Modi.xml");
//		//modiPPSSMSLanguage RBTS
//		testCaseList.add("Client_modiPPSSMSLanguage.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//		
//
//		//18.modiPPSCreditLimit BSS
//		testCaseList.add("Client_modiPPSCreditLimit.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doSetNegativeBalance.xml");
//		//modiPPSCreditLimit INS
//		testCaseList.add("Client_modiPPSCreditLimit.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SDF_GetAmfCounter.xml");
//		testCaseList.add("SDF_PutAmfCounter.xml");
//		//modiPPSCreditLimit RBTS
//		testCaseList.add("Client_modiPPSCreditLimit.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
//		
//
//		//19.chgtrigChrgAcnt BOS
//		testCaseList.add("Client_chgtrigChrgAcnt.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("E01_destination.xml");
//		testCaseList.add("BOS_accountRechargeCCR.xml");
//		testCaseList.add("SGSCP-PPGW_RechargeBalance.xml");
//		//chgtrigChrgAcnt INS
//		testCaseList.add("Client_chgtrigChrgAcnt.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		//chgtrigChrgAcnt RBTS
//		testCaseList.add("Client_chgtrigChrgAcnt.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP-PPGW_RechargeBalance.xml");
//		
//		
//		//20.firstAct BSS
//		testCaseList.add("1-Client-Request.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("BSS_doFirstActivationCRM.xml");
		
		//20.firstAct INS
//		testCaseList.add("1-Client-Request.xml");
//		testCaseList.add("2-DS2A-ResponseOldCOS.xml");
//		testCaseList.add("3-E01Cos-Response.xml");
//		testCaseList.add("4-E01Promo-Response_OldCOS.xml");
//		testCaseList.add("5-AMF-Response.xml");
//		testCaseList.add("6-USMP-response.xml");
//		testCaseList.add("7-USMP_InquiryCounter-response.xml");
//		testCaseList.add("7-USMP_ModiCounter-response.xml");
//		testCaseList.add("2-DS2A-ResponseEndFlow.xml");
//		testCaseList.add("8-SSB-response.xml");
//		testCaseList.add("9-RMF-response.xml");
//		testCaseList.add("10-E01Notify-Response.xml");
//		testCaseList.add("11-SRFC-response.xml");
//		
		//20.firstAct INS
//		testCaseList.add("1-Client-Request.xml");
//		testCaseList.add("2-DS2A-ResponseNewCOS.xml");
//		testCaseList.add("3-E01Cos-Response.xml");
//		testCaseList.add("4-E01Promo-Response_NewCOS.xml");
//		testCaseList.add("5-AMF-Response.xml");
//		testCaseList.add("6-USMP-response.xml");
//		testCaseList.add("7-USMP_FirstAct-response.xml");
//		testCaseList.add("2-DS2A-ResponseEndFlow.xml");
//		testCaseList.add("8-SSB-response.xml");
//		testCaseList.add("9-RMF-response.xml");
//		testCaseList.add("10-E01Notify-Response.xml");
//		testCaseList.add("11-SRFC-response.xml");
			
//		//First Act RBTS
//		testCaseList.add("Client_chgtrigChrgAcnt.xml");
//		testCaseList.add("DS2A_inquirySubscriber.xml");
//		testCaseList.add("SGSCP_SMOI_ForwardCommand.xml");
		
		runScn();

	}

}

