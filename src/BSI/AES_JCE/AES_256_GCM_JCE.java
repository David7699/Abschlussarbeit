package BSI.AES_JCE;

import static BSI.AES.AESUtils.getKey256Bit;
import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

public class AES_256_GCM_JCE {
	static byte[] input;
	static byte[] input_encrypted;
	static byte[] input_decrypted;
	static Cipher cipher;
	static byte[] aad = Hex.decode("9f741fdb5d8845bdb48a94394e");
	
	static byte[] keyBytes = getKey256Bit();
	static SecretKey key = new SecretKeySpec(keyBytes, "AES");
	static byte[] iv = new byte[12];
	static SecureRandom sr = new SecureRandom();
	
	
	public static void encrypt() throws Exception{
		GCMParameterSpec spec = new GCMParameterSpec(96, iv);
		cipher.init(Cipher.ENCRYPT_MODE, key, spec);
		cipher.updateAAD(aad);
		input_encrypted = cipher.doFinal(input);
	}
	
	public static void decrypt() throws Exception {
		GCMParameterSpec specAAD = new GCMParameterSpec(96, iv);
		cipher.init(Cipher.DECRYPT_MODE, key, specAAD);
		cipher.updateAAD(aad);
		input_decrypted = cipher.doFinal(input_encrypted);
	}
	
	public static void main(String[] args) throws Exception
	    {
			input = Files.readAllBytes(Paths.get(args[0]));
			cipher = Cipher.getInstance("AES/GCM/NoPadding");
			long start = getTime();
			for (int i = 0; i <= 1000; i++) {
				encrypt();
				decrypt();
				sr.nextBytes(iv);
			}	
			long end = getTime();
			System.out.println(timeInSeconds(start, end));
			System.out.println(Arrays.equals(input, input_decrypted));
	    }
}
