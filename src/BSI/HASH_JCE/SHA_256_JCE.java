package BSI.HASH_JCE;

import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class SHA_256_JCE {
	static byte[] input;
	static byte[] input_hashed;
	static MessageDigest md;
	
	static void hash() {
		md.update(input);
		input_hashed = md.digest();
	}
	
	public static void main(String[] args) throws Exception{
		input = Files.readAllBytes(Paths.get(args[0]));
		md = MessageDigest.getInstance("SHA256");
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			hash();
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}
