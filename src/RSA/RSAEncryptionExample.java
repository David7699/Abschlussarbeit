package RSA;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import org.bouncycastle.util.encoders.Hex;

public class RSAEncryptionExample {
    public static void main(String[] args) throws Exception {
	byte[] input = new byte[] { 0x00, (byte) 0xaf, (byte) 0xfe, 0x00 };

	Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
	KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");

	// create the keys using KeyFactory, RSAPublicKeySpec, RSAPrivateKeySpec, RSAPublicKey, and RSAPrivateKey
	RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger("d46f473a2d746537de2056ae3092c451", 16),
		new BigInteger("11", 16)); // e
	RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(new BigInteger("d46f473a2d746537de2056ae3092c451", 16),
		new BigInteger("57791d5430d593164082036ad8b29fb1", 16)); // d

	RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
	RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec);

	System.out.println("Private Key Infos:");
	System.out.println("n = " + rsaPrivateKey.getModulus().toString(16));
	System.out.println("n has " + rsaPrivateKey.getModulus().bitLength() + " bits.");
	System.out.println("d = " + rsaPrivateKey.getPrivateExponent().toString(16));

	System.out.println("Public Key Infos:");
	System.out.println("n = " + rsaPublicKey.getModulus().toString(16));
	System.out.println("e = " + rsaPublicKey.getPublicExponent().toString(16));

	System.out.println("Algorithm = " + cipher.getAlgorithm());
	System.out.println("input : " + Hex.toHexString(input));

	// encryption step

	cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

	byte[] cipherText = cipher.doFinal(input);

	System.out.println("cipher: " + Hex.toHexString(cipherText));

	// decryption step

	cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

	byte[] plainText = cipher.doFinal(cipherText);

	System.out.println("plain : " + Hex.toHexString(plainText));
    }
}

