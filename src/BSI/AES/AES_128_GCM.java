package BSI.AES;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jcajce.spec.AEADParameterSpec;
import org.bouncycastle.util.encoders.Hex;

public class AES_128_GCM {
	public static void main(String[] args) throws Exception
	    {
			SecretKey key = new SecretKeySpec(Hex.decode("000102030405060708090a0b0c0d0e0f"), "AES");
			
			byte[] iv = Hex.decode("bbaa99887766554433221100");
			byte[] msg = Hex.decode("a0a1a2a3a4a5a6a7a0a1");
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
