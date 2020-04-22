package tarena.day1906;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Test3 {
	public static void main(String[] args) throws Exception {
		/*
		 *    -DataInputStream
		 *  -FileInputStream
		 * f8
		 */
		DataInputStream dis = new DataInputStream(new FileInputStream("d:/abc/f8"));
		
		int len = dis.readInt();
		byte[] buf = new byte[len];
		dis.read(buf);
		dis.close();
		
		///
		/*
		 *    -ObjectInputStream
		 *  -ByteArrayInputStream
		 * buf
		 */
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf));
		
		Student s = (Student) ois.readObject();
		ois.close();
		
		System.out.println(s);
	}
}
