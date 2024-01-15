package BSI.SIGN_DSA_2048_JCE;

import static BSI.RSA_BC.RSA_Utils.generateDSAKeyJCE;
import static BSI.RSA_BC.RSA_Utils.getTime;
import static BSI.RSA_BC.RSA_Utils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class DSA_2048_SHA384_SIGN_JCE {
	static byte[] input;
	static byte[] signed_input;
	static boolean signed_verification;
	static Signature sig;
	static PublicKey pubKey;
	static PrivateKey prvKey;
	
	public static void getSign() throws Exception{
		sig.initSign(prvKey);
		sig.update(input);
		signed_input = sig.sign();
	}
	
	public static void getVerification() throws Exception {
		sig.initVerify(pubKey);
		sig.update(input);
		signed_verification = sig.verify(signed_input);
	}
	
	public static void main(String[] args) throws Exception{
		input = Files.readAllBytes(Paths.get(args[0]));
		sig = Signature.getInstance("SHA384withDSA");
		KeyPair kp = generateDSAKeyJCE(2048);
		pubKey = kp.getPublic();
		prvKey = kp.getPrivate();
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
