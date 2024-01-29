package BSI.KEYS_RSA;

import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSA_2048_KEY_GEN {
	public static void main(String[] args) throws Exception{
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
			keyGenerator.initialize(2048);
			KeyPair key = keyGenerator.generateKeyPair();
			PublicKey pubKey = key.getPublic();
			PrivateKey prvKey = key.getPrivate();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}
