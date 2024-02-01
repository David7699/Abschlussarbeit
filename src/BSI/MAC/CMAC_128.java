package BSI.MAC;

import static BSI.AES.AESUtils.getKey128Bit;
import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;

import javax.crypto.Mac;
import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import static BSI.AES.AESUtils.aesSecretKeySpec;

public class CMAC_128 {
	static byte[] input;
	static SecretKey key = aesSecretKeySpec(getKey128Bit());
	static Mac mac;
	static byte[] mac_output;
	
	public static void getMac() throws Exception{
		mac.init(key);
		mac.update(input);
		mac_output = mac.doFinal();
	}
	
	public static void main(String[] args) throws Exception{
		Security.addProvider(new BouncyCastleProvider());
		input = Files.readAllBytes(Paths.get(args[0]));
		mac = Mac.getInstance("AESCMAC", "BC");
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			getMac();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}