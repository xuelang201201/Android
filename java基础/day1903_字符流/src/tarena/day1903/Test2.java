package tarena.day1903;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Test2 {
	public static void main(String[] args) throws IOException {
		InputStreamReader in = 
				new InputStreamReader(
						new FileInputStream(
								"d:/abc/f6"));
								//"d:/abc/f6"), "UTF-8");
		
		int c;
		while((c = in.read()) != -1) {
			System.out.print((char)c);
		}
		in.close();
	}
}
