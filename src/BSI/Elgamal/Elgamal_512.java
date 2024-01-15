package BSI.Elgamal;

import static BSI.RSA_BC.RSA_Utils.getTime;
import static BSI.RSA_BC.RSA_Utils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Elgamal_512 {
	static byte[] input;
	static byte[] input_encrypted;
	static byte[] input_decrypted;
	
	static SecureRandom sr;
	static PublicKey pubKey;
	static PrivateKey prvKey; 
	
	static Cipher cipher;
	
	public static void encrypt() throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		input_encrypted = cipher.doFinal(input);
	}
	
	public static void decrypt() throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, prvKey);
		input_decrypted = cipher.doFinal(input_encrypted);
	}
	
	
	public static void main(String[] args) throws Exception{
		Security.addProvider(new BouncyCastleProvider());
		cipher = Cipher.getInstance("ElGamal/None/NoPadding", "BC");
		input = Files.readAllBytes(Paths.get(args[0]));
		KeyPairGenerator generator = KeyPairGenerator.getInstance("ElGamal", "BC");
		generator.initialize(512);
		KeyPair pair = generator.generateKeyPair();
		pubKey = pair.getPublic();
		prvKey = pair.getPrivate();
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			encrypt();
			decrypt();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
		System.out.println(Arrays.equals(input_decrypted, input_decrypted));
	}
}
