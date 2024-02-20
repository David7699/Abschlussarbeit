package BSI.SIGN_ECDSA_SECP384R1_BC;

import static BSI.RSA_BC.RSA_Utils.generateECKeyBC;
import static BSI.RSA_BC.RSA_Utils.getTime;
import static BSI.RSA_BC.RSA_Utils.timeInSeconds;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECDSA_secp384r1_SHA256 {
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
		Security.addProvider(new BouncyCastleProvider());
		input = Files.readAllBytes(Paths.get(args[0]));
		ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
		keyPair = generateECKeyBC(ecSpec);
		prvKey = keyPair.getPrivate();
		pubKey = keyPair.getPublic();
		signature = Signature.getInstance("SHA256withECDSA", "BC");
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
