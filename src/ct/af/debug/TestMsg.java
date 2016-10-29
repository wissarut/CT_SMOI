package ct.af.debug;

import ec02.exception.MessageParserException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TestMsg extends Testamentor  {
	
	public static void main(String[] param) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException, MessageParserException, ExecutionException, InterruptedException {

		testCaseList.add("msgFree.xml");

		runScn();
		
	}
}
