package BSI.AES;

import static BSI.AES.AESUtils.getKey256Bit;
import static BSI.AES.AESUtils.getTime;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class Salsa20 {
	static byte[] input;
	static byte[] input_encrypted;
	static byte[] input_decrypted;
	static byte[] key_bytes = getKey256Bit();
	
	
	static Cipher cipher;		
	static SecretKeySpec key = new SecretKeySpec(key_bytes, "ChaCha7539");;
	static byte[] iv = Hex.decode("0102030405060708");
	static int counter = 237;

	static SecretKey getKey() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("ChaCha20");
		keyGen.init(256, SecureRandom.getInstanceStrong());
		return keyGen.generateKey();
	}
	
	static byte[] getNonce() {
		byte[] newNonce = new byte[12];
		new SecureRandom().nextBytes(newNonce);
		return newNonce;
 	}
	
	
	static void encrypt() throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
		input_encrypted = cipher.doFinal(input);
	}
	
	static void decrypt() throws Exception {
		
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
		input_decrypted = cipher.doFinal(input_encrypted);
	}
	
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		input = Files.readAllBytes(Paths.get(args[0]));
		cipher = Cipher.getInstance("Salsa20", "BC");
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			encrypt();
			decrypt();
		}
		long end = getTime();
		System.out.println((end - start) / 1000);
		System.out.println(Arrays.equals(input_decrypted, input));
	}
}
