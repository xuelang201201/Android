package tarena.day1702;

import java.io.File;
import java.io.IOException;

public class Test5 {
	public static void main(String[] args) throws IOException {
		File f = File.createTempFile("abc", ".txt");
		
		System.out.println(f.getAbsolutePath());
	}
}
