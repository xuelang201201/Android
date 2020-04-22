package tarena.day1801;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("�������/���ܵ��ļ�·����");
		String s = new Scanner(System.in).nextLine();
		File f = new File(s);
		if(! f.isFile()) {
			System.out.println("��������ȷ�ġ��ļ���·��");
			return;
		}
		System.out.println("KEY:");
		int key = new Scanner(System.in).nextInt();
		try {
			encript(f, key);
			System.out.println("����/�������");
		} catch (Exception e) {
			System.out.println("����/����ʧ��");
			//��ӡ�����쳣��Ϣ
			e.printStackTrace();
		}
	}

	private static void encript(File f, int key) throws Exception {
		
		/*
		 * 1.�½� RandomAccessFile ���󸳸�raf
		 *   �Ӳ����ļ� f ��ȡ����
		 * 2.���ֽڶ�ȡ���ݣ���ȡ���ֽ�ֵ�������� b
		 *     3.b �� key ����򣬽�����¸��� b
		 *     4.��λ�±굽��ǰλ�õ���һ��λ��
		 *         raf.getFilePointer()-1
		 *     5.���ֽ�ֵ b д���ļ�
		 * 6.�ر� raf
		 */
		
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		
		//���ֽڶ�ȡ
		/*int b;
		while((b = raf.read()) != -1) {
			b ^= key;
			raf.seek(raf.getFilePointer()-1);
			raf.write(b);
		}*/
		
		//������ȡ
		//1k 2k 4k 8k
		//1024 2048 4096 8192
		byte[] buf = new byte[8192];
		int n;//����
		while((n = raf.read(buf)) != -1) {
			for(int i=0; i<n; i++) {
				buf[i] ^= key;
			}
			raf.seek(raf.getFilePointer()-n);
			raf.write(buf, 0, n);
		}
		
		raf.close();
	}
}
