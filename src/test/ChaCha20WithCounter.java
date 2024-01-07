package test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;


public class ChaCha20WithCounter {

		private static final String ENCRYPT_ALGO = "ChaCha20";

		public static void main(String[] args) throws Exception {

			String input = "Java & ChaCha20 with counter example.";

			SecretKey key = getKey(); // 256-bit secret key (32 bytes)
			byte[] nonce = getNonce(); // 96-bit nonce (12 bytes)
			int counter = 237; // 32-bit initial count (8 bytes)

			System.out.println("Input          : " + input);
			System.out.println("Input     (hex): " + convertBytesToHex(input.getBytes()));

			System.out.println("\n---Encryption---");
			byte[] cText = encrypt(input.getBytes(), key, nonce, counter); // encrypt

			System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
			System.out.println("Nonce     (hex): " + convertBytesToHex(nonce));
			System.out.println("Counter        : " + counter);
			System.out.println("Encrypted (hex): " + convertBytesToHex(cText));

			System.out.println("\n---Decryption---");

			byte[] pText = decrypt(cText, key, nonce, counter); // decrypt

			System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
			System.out.println("Nonce     (hex): " + convertBytesToHex(nonce));
			System.out.println("Counter        : " + counter);
			System.out.println("Decrypted (hex): " + convertBytesToHex(pText));
			System.out.println("Decrypted      : " + new String(pText));

		}

	// A 256-bit secret key (32 bytes)
		private static SecretKey getKey() throws NoSuchAlgorithmException {
			KeyGenerator keyGen = KeyGenerator.getInstance(ENCRYPT_ALGO);
			keyGen.init(256, SecureRandom.getInstanceStrong());
			return keyGen.generateKey();
		}

	// 96-bit nonce (12 bytes)
		private static byte[] getNonce() {
			byte[] newNonce = new byte[12];
			new SecureRandom().nextBytes(newNonce);
			return newNonce;
		}

		private static String convertBytesToHex(byte[] bytes) {
			StringBuilder result = new StringBuilder();
			for (byte temp : bytes) {
				result.append(String.format("%02x", temp));
			}
			return result.toString();
		}

		public static byte[] encrypt(byte[] pText, SecretKey key, byte[] nonce, int counter) throws Exception {

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

			ChaCha20ParameterSpec param = new ChaCha20ParameterSpec(nonce, counter);

			cipher.init(Cipher.ENCRYPT_MODE, key, param);

			byte[] encryptedText = cipher.doFinal(pText);

			return encryptedText;
		}

		public static byte[] decrypt(byte[] cText, SecretKey key, byte[] nonce, int counter) throws Exception {

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

			ChaCha20ParameterSpec param = new ChaCha20ParameterSpec(nonce, counter);

			cipher.init(Cipher.DECRYPT_MODE, key, param);

			byte[] decryptedText = cipher.doFinal(cText);

			return decryptedText;

		}

	}


