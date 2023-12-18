package BSI.ASYM;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class RSA {
	public static void main(String[] args) throws Exception {
		//Security.addProvider(new BouncyCastleProvider());
		byte[] msg = Hex.decode("a0a1a2a3a4a5a6a7a0a1");
		
		Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
		
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA","BC");
		
		keyPairGen.initialize(new RSAKeyGenParameterSpec(3072, RSAKeyGenParameterSpec.F4)); //Public Exponent 65537
		
		KeyPair keyPair = keyPairGen.generateKeyPair();
		
		RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
		
		RSAPrivateKey prvKey = (RSAPrivateKey) keyPair.getPrivate();
		
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		
		byte[] encryptedMessage = cipher.doFinal(msg);
		
		System.out.println("Encrypted Message= " + Hex.toHexString(encryptedMessage));
		
		cipher.init(Cipher.DECRYPT_MODE, prvKey);
		
		byte[] decryptedMessage = cipher.doFinal(encryptedMessage);
		
		System.out.println("Decrypted Message= " + Hex.toHexString(decryptedMessage)); 
		
	}
}
