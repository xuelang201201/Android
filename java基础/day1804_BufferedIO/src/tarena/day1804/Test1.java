package tarena.day1804;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test1 {
	public static void main(String[] args) throws IOException {
		/*    ��BufferedOutputStream
		 * ��FileOutputStream
		 * f3
		 */
		FileOutputStream fos = new FileOutputStream("d:/abc/f3");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		bos.write(97);
		bos.write(98);
		bos.write(99);
		
		bos.flush();
		
		/*
		 * �߼���close()
		 *   1.��flush()
		 *   2.��ӵ���close()
		 */
		bos.close();
	}
}
