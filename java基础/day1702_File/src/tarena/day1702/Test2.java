package tarena.day1702;

import java.io.File;
import java.io.IOException;

public class Test2 {
	public static void main(String[] args) throws IOException {
		File f = new File("d:/abc/a.txt");
		if(f.exists()) {
			boolean b = f.delete();
			System.out.println("ɾ���Ƿ�ɹ���"+b);
		} else {
			/*
			 * ������ڵ�Ŀ¼�����ڣ�������쳣
			 * ����ļ��Ѿ����ڣ��᷵��false
			 */
			boolean b = f.createNewFile();
			System.out.println("�����Ƿ�ɹ���"+b);
		}
	}
}
