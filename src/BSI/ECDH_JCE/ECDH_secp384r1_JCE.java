package BSI.ECDH_JCE;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

import static BSI.RSA_BC.RSA_Utils.getTime;
import static BSI.RSA_BC.RSA_Utils.timeInSeconds;

import javax.crypto.KeyAgreement;

public class ECDH_secp384r1_JCE {
	public static void main(String[] args) throws Exception{
		KeyPairGenerator g = KeyPairGenerator.getInstance("EC","SunEC");
		ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
		g.initialize(ecSpec, new SecureRandom());
		KeyAgreement aKeyAgree = KeyAgreement.getInstance("ECDH","SunEC");
		KeyAgreement bKeyAgree = KeyAgreement.getInstance("ECDH","SunEC");
		long start = getTime();
		for(int i = 0; i <= 1000; i++){
			KeyPair aKeyPair = g.generateKeyPair();
			KeyPair bKeyPair = g.generateKeyPair();
			aKeyAgree.init(aKeyPair.getPrivate());
			bKeyAgree.init(bKeyPair.getPrivate());
			aKeyAgree.doPhase(bKeyPair.getPublic(), true);
			bKeyAgree.doPhase(aKeyPair.getPublic(), true);
			byte[] k1 = aKeyAgree.generateSecret();
			byte[] k2 = bKeyAgree.generateSecret();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}
