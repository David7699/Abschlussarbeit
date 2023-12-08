package BSI.AES;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

public class AES_192_CCM {
public static void main(String[] args) throws Exception { // Maybe with AAD
		
		byte[] keyBytes = Hex.decode("000102030405060708090a0b0c0d0e0f0001020304050607"); // Key of Size: 192Bit = 16 Byte
		
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES"); // SecretKey for aes with keyBytes
		
		Cipher cipher = Cipher.getInstance("AES/CCM/NoPadding", "BC"); // Initialiaze Cipher with AES/CCM from BC Provider
		
		byte[] input = Hex.decode("a0a1a2a3a4a5a6a7"); // File size doesnt matter
		
		byte[] iv = Hex.decode("9f741fdb5d8845bdb48a94394e"); 
		
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv)); // initialize encryption
		
		
		byte[] output_encrypted = cipher.doFinal(input); // encrypt
		
		System.out.println(Hex.toHexString(output_encrypted));
		
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv)); //decrypt
		
		byte[] output_decrypted = cipher.doFinal(output_encrypted);
		
		System.out.println(Hex.toHexString(output_decrypted));
	}
}
