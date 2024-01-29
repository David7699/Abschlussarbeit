package BSI.KEYS_AES;

import javax.crypto.spec.SecretKeySpec;
import static BSI.AES.AESUtils.getTime;
import static BSI.AES.AESUtils.timeInSeconds;

import org.bouncycastle.util.encoders.Hex;

public class AES_128_KEY {
	public static void main(String[] args) {
		long start = getTime();
		for(int i = 0; i <= 1000; i++) {
			SecretKeySpec key = new SecretKeySpec(Hex.decode("000102030405060708090a0b0c0d0e0f"), "AES");
		}
		long end = getTime();
		System.out.println(timeInSeconds(start, end));
	}
}
