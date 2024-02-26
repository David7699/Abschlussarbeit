package BSI.KEYS_EC;

import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class EC_KEY_SECP256r1_BC {
	public static void main(String[] args) throws Exception{
		Security.addProvider(new BouncyCastleProvider());
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("EC","BC");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
			keyGenerator.initialize(ecSpec, new SecureRandom());
			KeyPair kp = keyGenerator.generateKeyPair();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}

