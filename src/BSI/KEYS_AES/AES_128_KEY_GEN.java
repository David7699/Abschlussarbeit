package BSI.KEYS_AES;

import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AES_128_KEY_GEN {
	public static void main(String[] args) throws Exception{
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey key = keyGenerator.generateKey();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}
