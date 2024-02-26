package BSI.KEYS_EC;

import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class EC_KEY_SECP384r1_JCE {
	public static void main(String[] args) throws Exception{
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("EC","SunEC");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
			keyGenerator.initialize(ecSpec, new SecureRandom());
			KeyPair kp = keyGenerator.generateKeyPair();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}

