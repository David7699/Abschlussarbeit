package BSI.AES;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jcajce.spec.AEADParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class AES_192_GCM {
	public static void main(String[] args) throws Exception
    {
		Security.addProvider(new BouncyCastleProvider());
		
		for(int i = 0; i <= 1000; i++) {
		SecretKey key = new SecretKeySpec(Hex.decode("000102030405060708090a0b0c0d0e0f0001020304050607"), "AES");
		
		byte[] iv = Hex.decode("bbaa99887766554433221100");
		byte[] msg = Hex.decode(Files.readAllBytes(Paths.get("C:\\Users\\Dell\\Downloads\\128Byte.txt")));
		byte[] aad = Hex.decode("9f741fdb5d8845bdb48a94394e");
		
		System.out.println("Message: " +  Hex.toHexString(msg));
	
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
	
		GCMParameterSpec spec = new GCMParameterSpec(128, iv);
	
		cipher.init(Cipher.ENCRYPT_MODE, key, spec);
	
		cipher.updateAAD(aad);
   
		byte[] out = cipher.doFinal(msg);
		
		System.out.println("Ciphertext: " +  Hex.toHexString(out));
		
		AEADParameterSpec specAAD = new AEADParameterSpec(iv, 128, aad);
		
		cipher.init(Cipher.DECRYPT_MODE, key, specAAD);
		
		byte[] pText = cipher.doFinal(out);
		
		System.out.println("Plain: " + Hex.toHexString(pText));
		
		}
    
    }
}
