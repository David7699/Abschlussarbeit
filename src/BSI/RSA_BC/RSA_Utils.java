package BSI.RSA_BC;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class RSA_Utils {
	
	
	public static KeyPair generateRSAKeyBC(int bits) throws Exception{
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA","BC");
		generator.initialize(bits);
		return generator.generateKeyPair();
	}
	
	public static KeyPair generateRSAKeyJCE(int bits) throws Exception{
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(bits);
		return generator.generateKeyPair();
	}
	
	public static KeyPair generateDSAKeyBC(int bits) throws Exception{
		KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA","BC");
		generator.initialize(bits);
		return generator.generateKeyPair();
	}
	
	public static KeyPair generateDSAKeyJCE(int bits) throws Exception{
		KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA");
		generator.initialize(bits);
		return generator.generateKeyPair();
	}
	
	public static KeyPair generateECKeyBC(ECGenParameterSpec ecSpec) throws Exception{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
		keyGen.initialize(ecSpec,new SecureRandom());
		return keyGen.generateKeyPair();
	}
	
	public static KeyPair generateECKeyJCE(ECGenParameterSpec ecSpec) throws Exception{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
		keyGen.initialize(ecSpec,new SecureRandom());
		return keyGen.generateKeyPair();
	}
	
	public static long getTime() {
		return System.currentTimeMillis();
	}
	
	public static float timeInSeconds(long start, long end) {
		return (end - start) / 1000F;
	}
}
