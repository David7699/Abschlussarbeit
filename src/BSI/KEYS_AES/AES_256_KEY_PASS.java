package BSI.KEYS_AES;

import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_256_KEY_PASS {
	public static void main(String[] args) throws Exception{
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			byte[] salt = new byte[100];
			SecureRandom sr = new SecureRandom();
			sr.nextBytes(salt);
			PBEKeySpec pbeKeySpec = new PBEKeySpec("Test1234".toCharArray(),salt,1000, 256);
			SecretKey pbeKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(pbeKeySpec);
			SecretKeySpec key = new SecretKeySpec(pbeKey.getEncoded(), "AES");
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}
