package tarena.day1702;

import java.io.File;

public class Test4 {
	public static void main(String[] args) {
		File f1 = new File("d:/abc/a.txt");
		File f2 = new File("d:/abc/b.txt");
		
		if(! (f1.exists() ^ f2.exists())) {
			System.out.println("�����ļ�����ֻ��һ������");
			return;
		}
		
		if(f1.exists()) {
			boolean b = f1.renameTo(f2);
			System.out.println("�����Ƿ�ɹ���"+b);
		} else {
			boolean b = f2.renameTo(f1);
			System.out.println("�����Ƿ�ɹ���"+b);
		}
	}
}
