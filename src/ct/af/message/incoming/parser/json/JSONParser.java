package ct.af.message.incoming.parser.json;

public class JSONParser extends org.json.simple.parser.JSONParser {
	
	private static JSONParser jsonParser = null;
	
	protected  JSONParser() {
		  
	}
	public static JSONParser getInstance() {
		if(jsonParser == null) {
			jsonParser = new JSONParser();
		}
		return jsonParser;
	}

}
