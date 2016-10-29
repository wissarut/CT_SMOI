package ct.af.utils;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import ec02.utils.AppLog;

public class HexDecode {

	public String decodeHex(String str) {
		
		byte[] bytes;
		String result = "";
		
		try {
			
			bytes = Hex.decodeHex(str.toCharArray());
			result = new String(bytes, "UTF-16");
			
		} catch (DecoderException e) {
			AppLog.e("[Exception] DecoderException");
		} catch (UnsupportedEncodingException e) {
			AppLog.e("[Exception] UnsupportedEncodingException");
		}

		return result;
	}
}
