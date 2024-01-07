package test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyTest {
	public static void main(String[] args) throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("ChaCha20");
		keyGen.init(256, SecureRandom.getInstanceStrong());
		SecretKey key = keyGen.generateKey();
		
		System.out.println(key.getAlgorithm());
	}
}
