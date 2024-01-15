package BSI.RSA_BC;

import static BSI.RSA_BC.RSA_Utils.generateRSAKeyBC;
import static BSI.RSA_BC.RSA_Utils.getTime;
import static BSI.RSA_BC.RSA_Utils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSA_1024 {
	static byte[] input;
	static byte[] input_encrypted;
	static byte[] input_decrypted;
	
	static Cipher cipher;
	static PublicKey pubKey;
	static PrivateKey prvKey;
	
	public static void encrypt() throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		input_encrypted = cipher.doFinal(input);
	}
	
	public static void decrypt() throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, prvKey);
		input_decrypted = cipher.doFinal(input_encrypted);
	}

	
	public static void main(String[] args) throws Exception{
		Security.addProvider(new BouncyCastleProvider());
		cipher = Cipher.getInstance("RSA/None/PKCS1Padding","BC");
		input = Files.readAllBytes(Paths.get("C:\\Users\\David\\Downloads\\137.bin"));
		KeyPair pair = generateRSAKeyBC(1024);
		pubKey = pair.getPublic();
		prvKey = pair.getPrivate();
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
