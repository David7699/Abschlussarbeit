package RSA;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class RSAv1 {
	Key key;
	KeyGenerator keyGen;
	Cipher encrypt;
	
	//Security.addProvider(new BouncyCastleProvider());
	
	//try {
		//keyGen = KeyGenerator.getInstance("RSA", "BC");
		//keyGen.init(null);
	//}
}
