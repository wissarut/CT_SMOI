package phoebe.eqx.smoi.control;

import java.util.ArrayList;
import com.google.gson.Gson;
import ct.af.core.manager.IncomingManager;
import ct.af.instance.AFInstance;
import ct.af.instance.EC02Instance;
import ct.af.utils.Config;
import ct.af.utils.InitalData;
import ct.af.utils.Zip;
import ec02.af.abstracts.AbstractAF;
import ec02.af.data.ECDialogue;
import ec02.af.data.EquinoxProperties;
import ec02.af.data.EquinoxRawData;
import ec02.af.exception.ActionProcessException;
import ec02.af.exception.ComposeInstanceException;
import ec02.af.exception.ConstructRawDataException;
import ec02.af.exception.ExtractInstanceException;
import ec02.af.exception.ExtractRawDataException;
import ec02.interfaces.IEC02;
import ec02.utils.AppLog;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class SmoiMain extends AbstractAF implements IEC02{
	
	private static final Gson gson;
  
	static
	{
	
		gson = new Gson();
	
	}

	@Override
	public ECDialogue actionProcess(EquinoxProperties eqxProp, ArrayList<EquinoxRawData> eqxRawDataList, Object instance)
			throws ActionProcessException {
		
		//Long startTime = System.currentTimeMillis();
		
		AppLog.d("[CURRENT STATE] : " + eqxProp.getState());
		
		EC02Instance ec02Ins = (EC02Instance) instance;
		ec02Ins.setEquinoxProperties(eqxProp);
		ec02Ins.setAbstractAF((AbstractAF) this);
		
		if(Config.hasReloadConfig){

			Config.loadConfig(this);

        }
		
		if(InitalData.hasReloadInitalData){

			InitalData.loadInitalData();

        }

		String eqxState = new IncomingManager().start((AbstractAF) this, ec02Ins, eqxRawDataList);
		
		EquinoxProperties newEqxProp = new EquinoxProperties();
		newEqxProp.setState(eqxState);
		
		AppLog.d("[NEXT STATE] : " + eqxState);
		
		newEqxProp.setTimeout(ec02Ins.getTimeout());
		
		AppLog.d("[TIMEOUT] : " + ec02Ins.getTimeout());
		
		ECDialogue ecDialogue = new ECDialogue(newEqxProp, ec02Ins);
		
		//Long endTime = System.currentTimeMillis();
		
		//System.out.println("actionProcess Process : "+(endTime-startTime));
		
		return ecDialogue;
	}

	@Override
	public boolean verifyAFConfiguration(String arg0) {
		
		return Config.verifyConfig(this);
	}

	@Override
	public Object extractInstance(String instance) throws ExtractInstanceException {
		
		//Long startTime = System.currentTimeMillis();
		
		EC02Instance ec02Instance = new EC02Instance();
		AFInstance afInstance = null;
		
		if ((instance == null) || instance.isEmpty()) {
			afInstance = new AFInstance();
		} 
		else {
			try {
				afInstance = decodeInstance(instance);
			}
			catch (Exception e) {
				AppLog.e("[Exception] extractInstance error");
			}
		}
		
		ec02Instance.setAFInstance(afInstance);
		
		//Long endTime = System.currentTimeMillis();
		
		//System.out.println("extractInstance Process : "+(endTime-startTime));
		
		return (Object) ec02Instance;
	}
	
	@Override
	public String composeInstance(Object instance) throws ComposeInstanceException {
		
		//Long startTime = System.currentTimeMillis();
		
		EC02Instance ec02Instance = (EC02Instance) instance;
		AFInstance afInstance = ec02Instance.getAFInstance();		
		String encodeString = "";
		
		try {
			encodeString = encodeInstance(afInstance);
		}
		catch (Exception e) {
			AppLog.e("[Exception] composeInstance error");
		}
		
		//Long endTime = System.currentTimeMillis();
		
		//System.out.println("composeInstance Process : "+(endTime-startTime));

		return encodeString;
	}

	@Override
	public void extractRawData(Object instanceData, ArrayList<EquinoxRawData> rawDatas)
			throws ExtractRawDataException {

	}
	
	@Override
	public ArrayList<EquinoxRawData> constructRawData(Object instance)
		throws ConstructRawDataException {
		
		EC02Instance ins = (EC02Instance) instance;

		return ins.getEqxRawDataList();
	}

	private String encodeInstance(AFInstance instance) {
		String encodeString = "";
		try {
			//Gson gson = new Gson();
			String str = gson.toJson(instance);
			byte[] bytes = str.getBytes();
			byte[] zipBytes = Zip.compressBytes(bytes);
	        encodeString = Base64.encode(zipBytes);
		} 
		catch (Exception e) {
			AppLog.e("[Exception] encodeInstance error");
		}
		
		return encodeString;
	}

	private AFInstance decodeInstance(String instance) {
		AFInstance afInstance = null;
		try {
			byte[] simpleString = Base64.decode(instance);
			byte[] unZipString = Zip.extractBytes(simpleString);
			//Gson gson = new Gson();
			afInstance = gson.fromJson(new String(unZipString), AFInstance.class);
		} catch (Exception e) {
			AppLog.e("[Exception] decodeInstance error");
		}
		return afInstance;
	}
}
