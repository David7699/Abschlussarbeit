package BSI.AES_JCE;

import static BSI.AES.AESUtils.getTime;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;

public class CHACHA20_256_JCE {
	static byte[] input;
	static byte[] input_encrypted;
	static byte[] input_decrypted;
	
	static SecretKey key;
	static byte[] nonce;
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
		Cipher cipher = Cipher.getInstance("ChaCha20");
		ChaCha20ParameterSpec spec = new ChaCha20ParameterSpec(nonce, counter);
		cipher.init(Cipher.ENCRYPT_MODE, key, spec);
		input_encrypted = cipher.doFinal(input);
	}
	
	static void decrypt() throws Exception {
		Cipher cipher = Cipher.getInstance("ChaCha20");
		ChaCha20ParameterSpec spec = new ChaCha20ParameterSpec(nonce, counter);
		cipher.init(Cipher.DECRYPT_MODE, key, spec);
		input_decrypted = cipher.doFinal(input_encrypted);
	}
	
	public static void main(String[] args) throws Exception {
		input = Files.readAllBytes(Paths.get(args[0]));
		key = getKey();
		nonce = getNonce();
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

