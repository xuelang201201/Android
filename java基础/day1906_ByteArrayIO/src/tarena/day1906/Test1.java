package tarena.day1906;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Test1 {
	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(97);
		out.write(98);
		out.write(99);
		out.close();
		
		byte[] a = out.toByteArray();
		
		ByteArrayInputStream in = new ByteArrayInputStream(a);
		int b;
		while((b = in.read()) != -1){
			System.out.println(b);
		}
		in.close();
	}
}
