package BSI.AES;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

public class AESUtils {
	
	static byte[] getKey128Bit() {
		return Hex.decode("000102030405060708090a0b0c0d0e0f");
	}
	
	static byte[] getKey192Bit() {
		return Hex.decode("000102030405060708090a0b0c0d0e0f0001020304050607");
	}
	
	static byte[] getKey256Bit() {
		return Hex.decode("000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f");
	}
	
	static SecretKeySpec aesSecretKeySpec(byte[] keyBytes) {
		return new SecretKeySpec(keyBytes, "AES");
	}
	
	static byte[] getInputLaptop() throws Exception {
		return Hex.decode(Files.readAllBytes(Paths.get("C:\\Users\\Dell\\Downloads\\128Byte.txt")));
	}
	
	static byte[] getInputPc() throws Exception {
		return Hex.decode(Files.readAllBytes(Paths.get("C:\\Users\\David\\Desktop\\Code\\Abschlussarbeit\\128Byte.txt")));
	}
	
	static long getTime() {
		return System.currentTimeMillis();
	}
	
	static float timeInSeconds(long start, long end) {
		return (end - start) / 1000F;
	}
	
}
