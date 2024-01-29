package BSI.KEYS_AES;

import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.security.Security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AES_192_KEY_GEN_BC {
	public static void main(String[] args) throws Exception{
		Security.addProvider(new BouncyCastleProvider());
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES","BC");
			keyGenerator.init(192);
			SecretKey key = keyGenerator.generateKey();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}
