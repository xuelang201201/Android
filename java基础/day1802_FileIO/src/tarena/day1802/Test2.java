package tarena.day1802;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Test2 {
	public static void main(String[] args) throws IOException {
		FileInputStream in;
		
		in = new FileInputStream("d:/abc/f2");
		//单字节读取
		int b;
		while((b=in.read()) != -1) {
			System.out.println(b);
		}
		
		in.close();
		
		System.out.println("-----------------------------");
		
		in = new FileInputStream("d:/abc/f2");
		//批量字节读取
		int n;
		byte[] buf = new byte[5];
		while((n = in.read(buf)) != -1) {
			System.out.println(n+" : "+Arrays.toString(buf));
		}
		in.close();
	}
}
