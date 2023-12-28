package BSI.AES;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

public class AESUtils {
	
	public static byte[] getKey128Bit() {
		return Hex.decode("000102030405060708090a0b0c0d0e0f");
	}
	
	public static byte[] getKey192Bit() {
		return Hex.decode("000102030405060708090a0b0c0d0e0f0001020304050607");
	}
	
	public static byte[] getKey256Bit() {
		return Hex.decode("000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f");
	}
	
	public static SecretKeySpec aesSecretKeySpec(byte[] keyBytes) {
		return new SecretKeySpec(keyBytes, "AES");
	}
	
	public static byte[] getInputLaptop() throws Exception {
		return Hex.decode(Files.readAllBytes(Paths.get("C:\\Users\\Dell\\Downloads\\128Byte.txt")));
	}
	
	public static byte[] getInputPc() throws Exception {
		return Hex.decode(Files.readAllBytes(Paths.get("C:\\Users\\David\\Desktop\\Code\\Abschlussarbeit\\128Byte.txt")));
	}
	
	public static long getTime() {
		return System.currentTimeMillis();
	}
	
	public static float timeInSeconds(long start, long end) {
		return (end - start) / 1000F;
	}
	
}
