package test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public class GCMWithAADExample {
	public static void main(String[] args) throws Exception {
		SecretKey aesKey = GCMExample.createConstantKey();

		byte[] iv = Hex.decode("bbaa99887766554433221100");
		byte[] msg = Strings.toByteArray("hello, world!");
		byte[] aad = Strings.toByteArray("now is the time!");

		System.out.println("msg:   " + Hex.toHexString(msg));

		byte[] cText = gcmEncryptWithAAD(aesKey, iv, msg, aad);

		System.out.println("cText: " + Hex.toHexString(cText));

		// Manipulation der AAD-Bytes erzeugt Exception:
		// aad[0] = (byte)~aad[0];
		
		byte[] pText = gcmDecryptWithAAD(aesKey, iv, cText, aad);

		System.out.println("pText: " + Hex.toHexString(pText));
	}

	/**
	 * Encrypt the passed in data pText using GCM with the passed in parameters and
	 * incorporating aData into the GCM MAC calculation.
	 *
	 * @param key   secret key to use.
	 * @param iv    the IV to use with GCM.
	 * @param pText the plain text input to the cipher.
	 * @param aData the associated data to be included in the GCM MAC.
	 * @return the cipher text.
	 */
	static byte[] gcmEncryptWithAAD(SecretKey key, byte[] iv, byte[] pText, byte[] aData) throws Exception {
	
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");

		GCMParameterSpec spec = new GCMParameterSpec(128, iv);

		cipher.init(Cipher.ENCRYPT_MODE, key, spec);
		
		cipher.updateAAD(aData);
		
		byte[] out = cipher.doFinal(pText);
		
		// The folgende Zeile wrde eine llegalStateException erzeugen
		// cipher.update("huhu".getBytes());
		
		return out;		
	}	

	/**
	 * Decrypt the passed in cipher text cText using GCM with the passed in
	 * parameters and incorporating aData into the GCM MAC calculation.
	 *
	 * @param key   secret key to use.
	 * @param iv    the IV originally used with GCM.
	 * @param cText the encrypted cipher text.
	 * @param aData the associated data to be included in the GCM MAC.
	 * @return the plain text.
	 */
	static byte[] gcmDecryptWithAAD(SecretKey key, byte[] iv, byte[] cText, byte[] aData) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");

		AEADParameterSpec spec = new AEADParameterSpec(iv, 128, aData);

		cipher.init(Cipher.DECRYPT_MODE, key, spec);

		return cipher.doFinal(cText);
	}
}

