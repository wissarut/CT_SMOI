package ct.af.debug;

/* 
 * Testamentor for Dev Version 1.7
 * Develop by Surasak Wajee [CT Team]
 * Last change : 10/09/2016
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ec02.exception.MessageParserException;
import ec02.server.EC02Handler;
import ec02.server.EC02Server;

public class Testamentor {
	
	public static String configEC02PrefixName = "App";
	public static String pathScn = "\\";

	public static List<String> testCaseList = new ArrayList<String>();

	public static ArrayList<String> nextInvoke = new ArrayList<String>();
	
	public static ArrayList<String> e01IDList = new ArrayList<String>();
	
	public static String nextIns;
	public static int lastTimeout = 0;
	
	public static int endScn = 0;
	public static boolean unitTest = false;
	public static boolean enableCDRLog_EQStat = true;
	public static int delaySecBetweenScn = 0;
	
	public static void runScn() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException, MessageParserException, ExecutionException, InterruptedException {
		
		int loopScn = 1;
		
		deleteLogFile();
		
		if(delaySecBetweenScn > 0) {
			System.out.println("Run with delay "+delaySecBetweenScn+" sec.");
		}
		
		System.out.println("[ N ] Testcase name.");
		
		for(String testCase:testCaseList) {
			
			System.out.println("[ "+loopScn+" ] "+testCase+" ");
		
			if(unitTest) {
				deleteLogFile();
			}
			
			Thread.sleep(delaySecBetweenScn*1000);
			
			if(testCase.contains(".sec")) {
				
				try {
					
					String[] strArr = testCase.split("\\.",-1);
				
					int tmSec = Integer.parseInt(strArr[0]);
					
					Thread.sleep(tmSec*1000);
					
					if(tmSec > lastTimeout) {
						nextInvoke.clear();
					}
					
				} catch(Exception ex) {
					System.err.println("Cannot get value continue next testcase...");
				}
				
			} else {
				
				printDetailEqxMsg(runTest(testCase));
				
			}

			if(loopScn == endScn) {
				break;
			}
			loopScn++;
		}
		
		System.out.println("Finish !!");
		System.exit(0);
	}
	
    public static String runTest(String testCase) throws IOException, 
    	TransformerException, XPathExpressionException, ParserConfigurationException, 
    	SAXException, MessageParserException, ExecutionException, InterruptedException {
    	
    	BufferedReader in = new BufferedReader(new FileReader("msg"+pathScn+testCase));

    	String str, reqMessage = "";
        String conf = "";
        String temp = "";
        while ((str = in.readLine()) != null) {
            reqMessage += str;
        }
        in.close();

        if(nextIns != null) {
        	reqMessage = reqMessage.replace("[:instance:]", nextIns);
        }

    	for(int i = 0;i < nextInvoke.size();i++) {
    		
    		reqMessage = reqMessage.replace("[:invoke:]", "[:invoke0:]");
    		
    		if (reqMessage.contains("[:invoke"+i+":]")) {

        		reqMessage = reqMessage.replace("[:invoke"+i+":]", nextInvoke.get(i));
        		 
    			nextInvoke.remove(i);
    		}
    		
    	}
        
    	if(e01IDList.size() != 0) {
    		
        	for(int i = 0;i < e01IDList.size();i++) {

        		reqMessage = reqMessage.replace("[:dbID:]", "[:dbID0:]");
        		
        		if (reqMessage.contains("[:dbID"+i+":]")) {

            		reqMessage = reqMessage.replace("[:dbID"+i+":]", e01IDList.get(i));
            		 
            		e01IDList.remove(i);
        		}
        	}
        	
        }

        in = new BufferedReader(new FileReader("conf/"+configEC02PrefixName+".EC02.0.0"));

        while ((temp = in.readLine()) != null) {
            conf += temp;
        }
        in.close();
        
        String[] a = {configEC02PrefixName, "0", "0", conf};
        try {
			EC02Server.main(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
        EC02Handler handler = new EC02Handler();

        String output = handler.handle(reqMessage,1024 * 1000 * 1000);
        
        return output;
    }
    
    public static void deleteLogFile() throws IOException {
    
    	File file = new File("log/"+configEC02PrefixName+".EC02LIB.0.0.log");
    	PrintWriter writer = new PrintWriter(file);
    	writer.print("");
    	writer.close();
    	
    	File path = new File("log/");
    	File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
        	
        	if(!files[i].getName().equals(configEC02PrefixName+".EC02LIB.0.0.log")) {
        		files[i].delete();
        	}
               
        }
    
    }
    
    public static void printDetailEqxMsg(String output)  {

    	try {
	    	DocumentBuilderFactory factory =
	    	DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	ByteArrayInputStream input =  new ByteArrayInputStream(output.toString().getBytes("UTF-8"));
	    	Document doc = builder.parse(input);
	    	doc.getDocumentElement().normalize();
	    	
	    	NodeList nList = doc.getElementsByTagName("EquinoxRawData");
	    	
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	    		
	    		Node nNode = nList.item(temp);
	    		String protocol = "unknown";
	    		String type = "request";
	    		
				 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					 
					 Element eElement = (Element) nNode;
					 
					 if(eElement.getAttribute("type") != "") {
						 type = eElement.getAttribute("type");
					 }
					 
					 if(eElement.getAttribute("invoke") != "") {
						 if(type.equals("request")) {
							 nextInvoke.add(eElement.getAttribute("invoke"));
						 }
					 }
					 
					 if(eElement.getAttribute("name") != "") {
						 
						 protocol = eElement.getAttribute("name");
						 
					 }
					 
					 if(protocol.equals("db")) {
						 
						 NodeList message =  nNode.getChildNodes();
						 
						 for (int temp2 = 0; temp2 < message.getLength(); temp2++) {
							 
							 Node eqxRawNode = message.item(temp2);
							 
							 if (eqxRawNode.getNodeType() == Node.ELEMENT_NODE) {
								 
								 Element eElement2 = (Element) eqxRawNode;
								 
								 if(eElement2.getAttribute("id") != "") {
									 
									 e01IDList.add(eElement2.getAttribute("id"));
									 
								 }
			
							 }
							 
						 }
					 }
					 
					 if(enableCDRLog_EQStat) {
					 
						 if(protocol.equals("EQLOG")) {
							 
							 NodeList message =  nNode.getChildNodes();
							 
							 for (int temp2 = 0; temp2 < message.getLength(); temp2++) {
								 
								 Node eqxRawNode = message.item(temp2);
								 
								 if (eqxRawNode.getNodeType() == Node.ELEMENT_NODE) {
									 
									 Element eElement2 = (Element) eqxRawNode;
									 
									 PrintWriter eqLogOut = new PrintWriter(new BufferedWriter(new FileWriter("log/"+eElement2.getAttribute("name")+".log", true)));
									 eqLogOut.println(eElement2.getAttribute("value"));
									 eqLogOut.close();
										
								 }
								 
							 }
							 
						 }
						 
						 if(protocol.equals("EQSTAT")) {
							 
							 NodeList message =  nNode.getChildNodes();
							 
							 for (int temp2 = 0; temp2 < message.getLength(); temp2++) {
								 
								 Node eqxRawNode = message.item(temp2);
								 
								 if (eqxRawNode.getNodeType() == Node.ELEMENT_NODE) {
									 
									 Element eElement2 = (Element) eqxRawNode;
									 
									 PrintWriter eqLogOut = new PrintWriter(new BufferedWriter(new FileWriter("log/AppStat.txt", true)));
									 eqLogOut.println(eElement2.getAttribute("name"));
									 eqLogOut.close();
										
								 }
								 
							 }
							 
						 }
					 
					 }
					 
				 }
				 
	        }
	    	
	    	NodeList mList = doc.getElementsByTagName("EquinoxMessage");
	    		
			Node nNode = mList.item(0);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				 
				 Element eElement = (Element) nNode;
	
				 nextIns =  eElement.getElementsByTagName("value").item(0).getAttributes().getNamedItem("val").getTextContent();
			
				 if(eElement.getAttribute("ret") != "") {
					 
//					 if(eElement.getAttribute("ret").equals("10")) {
//						 System.out.println("End of flow !!");
//						 System.exit(0);
//					 }
				 }
				 
				 if(eElement.getAttribute("timeout") != "") {
					 
					 lastTimeout = Integer.parseInt(eElement.getAttribute("timeout"));
				 }

			}
	   
	    } catch(Exception ex) {
	    	System.exit(0);
	    }
    }

}
