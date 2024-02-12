package BSI.AES_PAD_PKCS5Padding;

import static BSI.AES.AESUtils.aesSecretKeySpec;
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

public class AES_128_CTR_PKCS5 {
	static byte[] input;
	static byte[] input_encrypted;
	static byte[] input_decrypted;
	static Cipher cipher;
	
	static byte[] keyBytes = getKey128Bit();
	static SecretKeySpec key = aesSecretKeySpec(keyBytes);
	static byte[] iv = Hex.decode("9f741fdb5d8845bdb48a94394e84f8a3");
	
	public static void encrypt() throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
		input_encrypted = cipher.doFinal(input);
	}
	
	public static void decrypt() throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
		input_decrypted = cipher.doFinal(input_encrypted);
	}
	
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		input = Files.readAllBytes(Paths.get(args[0]));
		cipher = Cipher.getInstance("AES/CTR/PKCS5Padding", "BC");
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			encrypt();
			decrypt();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
		System.out.println(Arrays.equals(input, input_decrypted));
 	}
}
