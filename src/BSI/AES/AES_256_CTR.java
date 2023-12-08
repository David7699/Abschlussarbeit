package BSI.AES;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

public class AES_256_CTR {
	public static void main(String[] args) throws Exception {
		byte[] keyBytes = Hex.decode("000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f"); // Key of Size: 256Bit
		
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES"); // SecretKey for aes with keyBytes
		
		Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "BC"); // Initialiaze Cipher with AES/CTR from BC Provider
		
		byte[] input = Hex.decode("a0a1a2a3a4a5a6a7a0a1a2a3a4a5a6"); // File size doesnt matter
		
		byte[] iv = Hex.decode("9f741fdb5d8845bdb48a94394e84f8a3"); // CTR need IV multiple of Block size
		
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv)); // initialize encryption
		
		byte[] output_encrypted = cipher.doFinal(input); // encrypt
		
		System.out.println(Hex.toHexString(output_encrypted));
		
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv)); //decrypt
		
		byte[] output_decrypted = cipher.doFinal(output_encrypted);
		
		System.out.println(Hex.toHexString(output_decrypted));
 	}
}
