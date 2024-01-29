package BSI.KEYS_AES;

import java.security.SecureRandom;

import javax.crypto.spec.SecretKeySpec;

import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

public class AES_128_KEY_RANDOM {
	public static void main(String[] args) {
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			byte[] keyArray = new byte[16];
			SecureRandom sr = new SecureRandom();
			sr.nextBytes(keyArray);
			SecretKeySpec key = new SecretKeySpec(keyArray, "AES");
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}
