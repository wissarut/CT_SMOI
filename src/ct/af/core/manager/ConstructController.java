package ct.af.core.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.joda.time.format.DateTimeFormatter;
import ct.af.core.log.OutputLog;
import ct.af.enums.ECommand;
import ct.af.enums.EFinalCode;
import ct.af.enums.EProtocal;
import ct.af.enums.EResultCode;
import ct.af.enums.EStats;
import ct.af.enums.ESubState;
import ct.af.instance.AFSubInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.AppInfo;
import ct.af.utils.Config;
import ct.af.utils.InitalData;
import ct.af.utils.StringUtils;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.EquinoxRawData;
import ec02.utils.AppLog;

public class ConstructController {
	
	public void enterConstructERD(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubIns){
		
		String subNextState = afSubIns.getSubNextState();
		EquinoxRawData eqxRawData = null;
		
		if(!subNextState.equals(ESubState.END.toString())) {
			
			try {
				
				if(subNextState.contains("End_")) {
					
					if(!subNextState.contains("End_FeedForward")) {
						String cmd = afSubIns.getSubInitCmd();
						subNextState = "End_"+cmd.substring(0, 1).toUpperCase() + cmd.substring(1);
					}
					
					String keySearch = afSubIns.getSubInitCmd()+afSubIns.getSubControlState()+afSubIns.getSubInternalCode().getResultCode();
					
					AppLog.d("KeySearch FinalCode [1] : "+keySearch);
					
					EFinalCode finaleCode =  InitalData.mapFinalCode.get(keySearch);
					
					if(finaleCode == null) {
						
						keySearch = afSubIns.getSubInitCmd()+afSubIns.getSubControlState()+"ERROR";
						
						AppLog.d("KeySearch FinalCode [2] : "+keySearch);
						finaleCode =  InitalData.mapFinalCode.get(keySearch);
						
						if(finaleCode == null) {
					
							keySearch = ECommand.UNKNOWN.getCommand()+ESubState.Unknown.toString()+EResultCode.RE50000.getResultCode();
							
							AppLog.d("KeySearch FinalCode [3] : "+keySearch);
							finaleCode =  InitalData.mapFinalCode.get(keySearch);
							
						}
					}
					
					AppLog.d("Result Search FinalCode : "+finaleCode);
				
					afSubIns.setSubFinalCode(finaleCode);
			
				}
				
				AppLog.d("Construct class : Out_"+subNextState);
				
				String constructClassStr = "ct.af.message.outgoing.Out_"+subNextState;
				
				Class<?> constructClass = Class.forName(constructClassStr);
				Object constructOut = constructClass.newInstance(); 
				String methodName = "messageBuilder";
		        Method getNameMethod = constructOut.getClass().getMethod(methodName , AbstractAF.class,EC02Instance.class,AFSubInstance.class);
		        eqxRawData = (EquinoxRawData) getNameMethod.invoke(constructOut , abstractAF,ec02Instance,afSubIns); 
		        
		        //-- Count Request --//
				afSubIns.incrementSubCountServerRequest();
				
			} catch (IllegalAccessException e) {
				AppLog.e("[Ex:IllegalAccessException] Cannot call class : Out_"+subNextState);
				AppLog.e(e);
			} catch (ClassNotFoundException e) {
				AppLog.e("[Ex:ClassNotFoundException] Cannot call class : Out_"+subNextState);
				AppLog.e(e);
			} catch (InstantiationException e) {
				AppLog.e("[Ex:InstantiationException] Cannot call class : Out_"+subNextState);
				AppLog.e(e);
			} catch (NoSuchMethodException e) {
				AppLog.e("[Ex:NoSuchMethodException] Cannot call class : Out_"+subNextState);
				AppLog.e(e);
			} catch (SecurityException e) {
				AppLog.e("[Ex:SecurityException] Cannot call class : Out_"+subNextState);
				AppLog.e(e);
			} catch (InvocationTargetException e) {
				AppLog.e("[Ex:InvocationTargetException] Cannot call class : Out_"+subNextState);
				AppLog.e(e);
			}

		}
	
		keepOutputLogAndKeepStat(abstractAF, ec02Instance, afSubIns, eqxRawData);
		
	}
	
	public void constructGateway(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubInstance) {

		if(afSubInstance.getSubInvoke().size() == 0) {

			String subNextState = afSubInstance.getSubNextState();
			
			if(!(subNextState.equals(ESubState.WAIT.toString()) || subNextState.equals(ESubState.END.toString()))) {
				
				List<String> subNextStateArr =  new ArrayList<String>();
				
				if(subNextState.contains(",")) {
					subNextStateArr = Arrays.asList(subNextState.split(","));
				}
			
				if(subNextStateArr.size() > 1) {
					
					if(afSubInstance.getSubNextOfNextState().equals(ESubState.Unknown.toString())) {
						AppLog.w("SubIns Has 2 NextState but not set NextofNextState");
						
						if(StringUtils.isBlank(afSubInstance.getSubStateHostWait())) {
							AppLog.w("SubStateHostWait is not set.Use in log detail.[Not bussiness logic]");
						}
						
					}
					
					for(String nextState : subNextStateArr) {
					
						if(nextState.contains("End_")) {
							
							enterConstructERD(abstractAF, ec02Instance, afSubInstance);
							
						} else {
							
							DateTimeFormatter formatDateWithMiTz = Config.formatDateWithMiTz;
							Random random = new Random();
							long randomNumber = (long)(9000 * random.nextDouble());
						    int randomKey = (int)(randomNumber + 1000);
						    
							AFSubInstance afChildSubIns = new AFSubInstance();
							afChildSubIns.setSubInitTimeStampIn(ec02Instance.getAFInstance().getMainTimeStampIncoming());

							afChildSubIns.setSubInstanceNo(afSubInstance.getSubInstanceNo()+"child"+randomKey);
							afChildSubIns.setSubInitInvoke(afSubInstance.getSubInitInvoke());
							afChildSubIns.setSubInitCmd(afSubInstance.getSubInitCmd());
							afChildSubIns.setSubHostInsNo(afSubInstance.getSubInstanceNo());
							afChildSubIns.setSubFunctionGroup(afSubInstance.getSubFunctionGroup());
							afChildSubIns.setSubMSISDN(afSubInstance.getSubMSISDN());
							afChildSubIns.setSubNextState(nextState);
							
							afChildSubIns.setSubTimeout(formatDateWithMiTz.print(formatDateWithMiTz.parseDateTime(afChildSubIns.getSubInitTimeStampIn()).plusYears(1)));
							
							afSubInstance.incrementSubCountChild();
							afSubInstance.setSubTimeout(formatDateWithMiTz.print(formatDateWithMiTz.parseDateTime(afChildSubIns.getSubInitTimeStampIn()).plusYears(1)));
							
							ec02Instance.getAFInstance().putMainSubInstance(afChildSubIns.getSubInstanceNo(), afChildSubIns);
							
							enterConstructERD(abstractAF, ec02Instance, afChildSubIns);
							
							afSubInstance.getSubInvoke().addAll(afChildSubIns.getSubInvoke());
						
						}
					}
					
					afSubInstance.setSubNextState(ESubState.WAIT.toString());
				
				} else {
					
					enterConstructERD(abstractAF, ec02Instance, afSubInstance);
					
					if(StringUtils.isNotBlank(afSubInstance.getSubHostInsNo())) {
						
						ec02Instance.getAFInstance().getMainSubInstance(afSubInstance.getSubHostInsNo()).getSubInvoke().addAll(afSubInstance.getSubInvoke());
					
					}
				}
			}
		}
	}
	
	public void keepOutputLogAndKeepStat(AbstractAF abstractAF,EC02Instance ec02Instance,AFSubInstance afSubInstance,EquinoxRawData eqxRawData) {
		
		if(eqxRawData != null && afSubInstance.getStatsOut().size() > 0) {
			
			if(Config.APP_LOG_DEBUG_FLAG) {
				AppLog.d(new AppInfo().composeOutputDebug(ec02Instance, eqxRawData, afSubInstance));
			}
			
			EStats statOut = afSubInstance.getStatsOut().get(afSubInstance.getStatsOut().size() - 1);
			
			OutputLog outputLogObj = new OutputLog(eqxRawData,statOut,afSubInstance.getSubInitCmd());
			
			if(StringUtils.isBlank(afSubInstance.getSubHostInsNo())) {	
				afSubInstance.setSubOutputLogs(outputLogObj);
			} else {
				ec02Instance.getAFInstance().getMainSubInstance(afSubInstance.getSubHostInsNo()).setSubOutputLogs(outputLogObj);
			}
			
		}

		if(eqxRawData != null) {
			
			if(!eqxRawData.getName().equals(EProtocal.DB.toString())) {
				ec02Instance.setEqxRawDataList(eqxRawData);
			}
			
		}
			
	}
	
}
