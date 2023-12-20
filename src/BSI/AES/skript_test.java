package BSI.AES;

import static BSI.AES.AESUtils.aesSecretKeySpec;
import static BSI.AES.AESUtils.getInputPc;
import static BSI.AES.AESUtils.getKey128Bit;
import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class skript_test{
	static byte[] input;
	static byte[] input_encrypted;
	static Cipher cipher;
	
	static byte[] keyBytes = getKey128Bit();
	static SecretKeySpec key = aesSecretKeySpec(keyBytes);
	static byte[] iv = Hex.decode("9f741fdb5d8845bdb48a94394e84f8a3");
	
	public static byte[] encrypt() throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
		input_encrypted = cipher.doFinal(input);
		return input_encrypted;
	}
	
	public static byte[] decrypt() throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
		return cipher.doFinal(input_encrypted);
	}
	
	
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		input = Files.readAllBytes(Paths.get("C:\\Users\\David\\Downloads\\bild.jpg"));
		cipher = Cipher.getInstance("AES/CBC/NoPadding", "BC");
		long start = getTime();
		encrypt();
		decrypt();
		long end = getTime();
		System.out.println((end - start) / 1000F);
	}
}
