package BSI.MAC;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import static BSI.AES.AESUtils.getKey256Bit;
import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;

public class HMAC_SHA256 {
	static byte[] input;
	static byte[] input_mac;
	
	static Mac mac;
	static SecretKey key = new SecretKeySpec(getKey256Bit(), "HmacSHA256");
	
	public static void getMac() throws Exception{
		mac.init(key);
		input_mac = mac.doFinal(input);
	}
	
	public static void main(String[] args) throws Exception{
		Security.addProvider(new BouncyCastleProvider());
		input = Files.readAllBytes(Paths.get(args[0]));
		mac = Mac.getInstance("HmacSHA256", "BC");
		
		long start = getTime();
		for( int i = 0; i <= 1000; i++) {
			getMac();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
		
	}	
}
