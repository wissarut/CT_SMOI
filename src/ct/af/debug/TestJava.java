package ct.af.debug;

import java.io.UnsupportedEncodingException;
import java.util.EnumSet;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import ct.af.enums.ECommand;

public class TestJava {


	public static void main(String[] args) {
	
		int a = Integer.parseInt("2");
		int v = a * 24;
		
		System.out.println(Integer.toString(v));
		
	}
}