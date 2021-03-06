package tarena.day1906;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Test2 {
	public static void main(String[] args) throws IOException {
		Student s = new Student(534634, "����", "��", 23, 173, 71);
		
		/*
		 *   -ObjectOutputStream
		 * -ByteArrayOutputStream
		 */
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		
		oos.writeObject(s);
		
		oos.close();
		byte[] a = baos.toByteArray();
		
		/*
		 *    -DataOutputStream
		 *  -FileOutputStream
		 * f8
		 */
		DataOutputStream out =
				new DataOutputStream(
						new FileOutputStream("d:/abc/f8"));
		
		out.writeInt(a.length);
		out.write(a);
		out.close();
	}
}
