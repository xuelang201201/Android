package tarena.day1803;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("����ԭ�ļ���");
		String s = new Scanner(System.in).nextLine();
		File src = new File(s);
		if(! src.isFile()) {
			System.out.println("��������ȷ�ġ��ļ���·��");
			return;
		}
		
		System.out.println("����Ŀ���ļ���");
		String s2 = new Scanner(System.in).nextLine();
		File target = new File(s2);
		if(target.isDirectory()) {
			System.out.println("��������ȷ�ġ��ļ���·��");
			return;
		}
		
		try {
			copy(src, target);
			System.out.println("�������");
		} catch (Exception e) {
			System.out.println("����ʧ��");
			//��ӡ�����쳣��Ϣ
			e.printStackTrace();
		}
	}

	private static void copy(File src, File target) throws Exception {
		/*
		 * 1.�½� FileInputStream ���� in 
		 *   ������ļ� src ���
		 * 2.�½�FileOutputStream ���� out
		 *   ������ļ� target ���
		 * 3.���ֽڶ�ȡ
		 *     4.��ȡ���ֽ�ֵ������������
		 *     
		 * 5.in.close();
		 *   out.close();
		 */
		
		BufferedInputStream in = 
				new BufferedInputStream(
						new FileInputStream(src));
		
		BufferedOutputStream out = 
				new BufferedOutputStream(
						new FileOutputStream(target));
		
		int b;
		while((b=in.read()) != -1) {
			out.write(b);
		}
		
		/*byte[] buf = new byte[8192];
		int n;//����
		while((n=in.read(buf)) != -1) {
			out.write(buf, 0, n);
		}*/
		
		in.close();
		out.close();
	}
}
