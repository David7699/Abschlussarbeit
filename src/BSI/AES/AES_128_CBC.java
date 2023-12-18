package BSI.AES;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class AES_128_CBC {
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
	
		for(int i = 0; i <= 1000; i++) {
			
		byte[] keyBytes = Hex.decode("000102030405060708090a0b0c0d0e0f"); // Key of Size: 128Bit = 16 Byte
		
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES"); // SecretKey for aes with keyBytes
		
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "BC"); // Initialiaze Cipher with AES/CBC from BC Provider
		
		byte[] input =  Hex.decode(Files.readAllBytes(Paths.get("C:\\Users\\Dell\\Downloads\\128Byte.txt"))); // To be Replaced with File multiple of 128 Bit
		
		byte[] iv = Hex.decode("9f741fdb5d8845bdb48a94394e84f8a3"); // CBC need IV multiple of Block size
		
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv)); // initialize encryption
		
		byte[] output_encrypted = cipher.doFinal(input); // encrypt
		
		System.out.println(Hex.toHexString(output_encrypted));
		
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv)); //decrypt
		
		byte[] output_decrypted = cipher.doFinal(output_encrypted);
		
		System.out.println(Hex.toHexString(output_decrypted));
		}
	}
}
