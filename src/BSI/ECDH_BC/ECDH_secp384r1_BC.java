package BSI.ECDH_BC;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

import static BSI.RSA_BC.RSA_Utils.getTime;
import static BSI.RSA_BC.RSA_Utils.timeInSeconds;

import javax.crypto.KeyAgreement;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECDH_secp384r1_BC {
	public static void main(String[] args) throws Exception{
		Security.addProvider(new BouncyCastleProvider());
		KeyPairGenerator g = KeyPairGenerator.getInstance("ECDH","BC");
		ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
		g.initialize(ecSpec, new SecureRandom());
		KeyAgreement aKeyAgree = KeyAgreement.getInstance("ECDH","BC");
		KeyAgreement bKeyAgree = KeyAgreement.getInstance("ECDH","BC");
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
