package BSI.AES;

import static BSI.AES.AESUtils.aesSecretKeySpec;
import static BSI.AES.AESUtils.getInputPc;
import static BSI.AES.AESUtils.getKey128Bit;
import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class template {
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		byte[] input = new byte[0];
		byte[] output_encrypted = new byte[0];
		byte[] output_decrypted = new byte[0];
		
		long start = getTime();
		
			
		byte[] keyBytes = getKey128Bit(); // Key of Size: 128Bit = 16 Byte
		
		SecretKeySpec key = aesSecretKeySpec(keyBytes); // SecretKey for aes with keyBytes
		
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "BC"); // Initialiaze Cipher with AES/CBC from BC Provider
		
		input =  Files.readAllBytes(Paths.get("C:\\Users\\David\\Downloads\\bild.jpg"));
		
		byte[] iv = Hex.decode("9f741fdb5d8845bdb48a94394e84f8a3"); // CBC need IV multiple of Block size
		
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv)); // initialize encryption
		
		output_encrypted = cipher.doFinal(input); // encrypt
		
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv)); //decrypt
		
		output_decrypted = cipher.doFinal(output_encrypted);
		
		long end = getTime();
		
		System.out.println("Time: " + timeInSeconds(start, end));
		
		if(Arrays.equals(input, output_decrypted)) {
			System.out.println("Input = decrypt(encrypt(input)");
		} else {
			System.out.println("input != decrypt(encrypt(input)");
		}
	}
}
