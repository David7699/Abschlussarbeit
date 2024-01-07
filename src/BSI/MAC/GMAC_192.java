package BSI.MAC;

import static BSI.AES.AESUtils.aesSecretKeySpec;
import static BSI.AES.AESUtils.getKey192Bit;
import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class GMAC_192 {
	static byte[] input;
	static byte[] input_mac;
	static byte[] iv = new byte[12];
	static SecureRandom sr = new SecureRandom();
	
	static Mac mac;
	static SecretKey key = aesSecretKeySpec(getKey192Bit());
	
	public static void getMac() throws Exception{
		mac.init(key, new IvParameterSpec(iv));
		input_mac = mac.doFinal(input);
	}
	
	public static void main(String[] args) throws Exception{
		Security.addProvider(new BouncyCastleProvider());
		input = Files.readAllBytes(Paths.get(args[0]));
		mac = Mac.getInstance("AESGMAC", "BC");
		
		long start = getTime();
		for( int i = 0; i <= 1000; i++) {
			getMac();
			sr.nextBytes(iv);
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
		
	}
}
