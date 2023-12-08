package RSA;

import java.security.KeyFactory;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSATest {
	public static void main(String[] args) throws Exception{
		java.security.Security.addProvider(new BouncyCastleProvider());
		byte[] input = new byte[] { 0x00, (byte) 0xaf, (byte) 0xfe, 0x00 };
		Cipher cipher = Cipher.getInstance("RSA", "BC");
		KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");

	}
}
