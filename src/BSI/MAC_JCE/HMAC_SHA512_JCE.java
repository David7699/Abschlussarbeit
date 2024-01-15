package BSI.MAC_JCE;

import static BSI.AES.AESUtils.getKey256Bit;
import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HMAC_SHA512_JCE {
	static byte[] input;
	static byte[] input_mac;
	
	static Mac mac;
	static SecretKey key = new SecretKeySpec(getKey256Bit(), "HmacSHA512");
	
	public static void getMac() throws Exception{
		mac.init(key);
		input_mac = mac.doFinal(input);
	}
	
	public static void main(String[] args) throws Exception{
		input = Files.readAllBytes(Paths.get("C:\\Users\\David\\Downloads\\137.bin"));
		mac = Mac.getInstance("HmacSHA512");
		
		long start = getTime();
		for( int i = 0; i <= 1000; i++) {
			getMac();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
		
	}
}
