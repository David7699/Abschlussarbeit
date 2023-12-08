package Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileTest {
	public static void main(String[] args) throws IOException{
		File fileIn = new File("src/Files/100B.txt");
		FileInputStream fl = new FileInputStream(fileIn);
		byte[] input = new byte[(int) fileIn.length()];
		fl.read(input);
		fl.close();
		
		System.out.println(Arrays.toString(input) + " " + input.length);
	}
}
