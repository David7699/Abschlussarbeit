package test;

import java.security.SecureRandom;

import org.bouncycastle.util.encoders.Hex;

public class NonceTest {
	static byte[] nonce = new byte[12];
	static SecureRandom sr = new SecureRandom();
	public static void main(String[] args) {
		for(int i = 0; i <= 10; i++) {
			sr.nextBytes(nonce);
			System.out.println(Hex.toHexString(nonce));
		}
		
	}
}
