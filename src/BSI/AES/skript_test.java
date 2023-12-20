package BSI.AES;

import static BSI.AES.AESUtils.aesSecretKeySpec;
import static BSI.AES.AESUtils.getInputLaptop;
import static BSI.AES.AESUtils.getKey128Bit;
import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class skript_test{
	static byte[] input;
	static byte[] input_encrypted;
	static {
		try {
			input = getInputLaptop();
		} catch (Exception e){
			System.out.println("File cant be found");
		}
	}
	static byte[] keyBytes = getKey128Bit();
	static SecretKeySpec key = aesSecretKeySpec(keyBytes);
	static byte[] iv = Hex.decode("9f741fdb5d8845bdb48a94394e84f8a3");
	static Cipher cipher;
	static {
		try {
			cipher = Cipher.getInstance("AES/CBC/NoPadding", "BC");
		} catch (Exception e){
			System.out.println("Cipher not found");
		}
	}
	
	
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
		long start = getTime();
		for(int i = 0; i <= 100000; i++) {
			encrypt();
			decrypt();
		}
		long end = getTime();
		System.out.println((end - start) / 1000F);
	}
}