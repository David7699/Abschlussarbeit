package BSI.SIGN_ECDSA_SECP521R1_JCE;

import static BSI.RSA_BC.RSA_Utils.generateECKeyJCE;
import static BSI.RSA_BC.RSA_Utils.getTime;
import static BSI.RSA_BC.RSA_Utils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class ECDSA_secp521r1_SHA3_256_JCE {
	static byte[] input;
	static byte[] signed_input;
	static boolean signed_verification;
	static Signature signature;
	static KeyPair keyPair;
	static PrivateKey prvKey;
	static PublicKey pubKey;
	
	public static void getSign() throws Exception {
		signature.initSign(prvKey);
		signature.update(input);
		signed_input = signature.sign();
	}
	
	public static void getVerification() throws Exception {
		signature.initVerify(pubKey);
		signature.update(input);
		signed_verification = signature.verify(signed_input);
	}

	public static void main(String[] args) throws Exception{
		input = Files.readAllBytes(Paths.get(args[0]));
		ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp521r1");
		keyPair = generateECKeyJCE(ecSpec);
		prvKey = keyPair.getPrivate();
		pubKey = keyPair.getPublic();
		signature = Signature.getInstance("SHA3-256withECDSA");
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			getSign();
			getVerification();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
		System.out.println(signed_verification);
		
	}
}
