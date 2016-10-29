package ct.af.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 25/8/2555
 * To change this template use File | Settings | File Templates.
 */
public class Zip {
    public static byte[] compressBytes(byte[] input) throws UnsupportedEncodingException, IOException {
        Deflater df = new Deflater();     
  
        df.setInput(input);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(input.length);
        df.finish();
        byte[] buff = new byte[1024];  
        while (!df.finished()) {
            int count = df.deflate(buff);      
            baos.write(buff, 0, count);     
        }
        baos.close();
        byte[] output = baos.toByteArray();

        return output;
    }

    public static byte[] extractBytes(byte[] input) throws UnsupportedEncodingException, IOException, DataFormatException {
        Inflater ifl = new Inflater(); 
        ifl.setInput(input);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(input.length);
        byte[] buff = new byte[1024];
        while (!ifl.finished()) {
            int count = ifl.inflate(buff);
            baos.write(buff, 0, count);
        }
        baos.close();
        byte[] output = baos.toByteArray();

        return output;
    }

    public static void compressFile(String src, String dest) throws IOException {
        java.util.zip.GZIPOutputStream out = new java.util.zip.GZIPOutputStream(new java.io.FileOutputStream(dest));
        java.io.FileInputStream in = new java.io.FileInputStream(src);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.finish();
        out.close();
    }
}
