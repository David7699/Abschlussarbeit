package BSI.KEYS_AES;

import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.security.SecureRandom;

import javax.crypto.spec.SecretKeySpec;

public class AES_192_KEY_RANDOM {
	public static void main(String[] args) {
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			byte[] keyArray = new byte[24];
			SecureRandom sr = new SecureRandom();
			sr.nextBytes(keyArray);
			SecretKeySpec key = new SecretKeySpec(keyArray, "AES");
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}
