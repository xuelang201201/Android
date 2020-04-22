package tarena.day1806;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Test2 {
	public static void main(String[] args) throws IOException {
		DataInputStream in = 
				new DataInputStream(
						new FileInputStream("d:/abc/f4"));
		
		try {
			while(true) {
				System.out.println(in.readUTF());
				System.out.println(in.readUTF());
				System.out.println(in.readInt());
			}
		} catch (EOFException e) {
			//System.out.println("∂¡»°ÕÍ≥…");
		}
		
		in.close();
	}
}
