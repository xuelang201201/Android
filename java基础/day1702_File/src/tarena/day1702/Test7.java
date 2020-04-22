package tarena.day1702;

import java.io.File;
import java.util.Scanner;

public class Test7 {
	public static void main(String[] args) {
		System.out.println("����Ŀ¼·������Ŀ¼��С��");
		String s = new Scanner(System.in).nextLine();
		
		File dir = new File(s);
		if(! dir.isDirectory()) {
			System.out.println("��������ȷ��Ŀ¼·��");
			return;
		}
		
		long size = dirLength(dir);
		System.out.println(size);
	}

	private static long dirLength(File dir) {
		/*
		 * 1.�����С�ۼӱ���long size=0
		 * 2.�Բ���Ŀ¼dir �б����� files
		 * 3.���files == null
		 *     4.����0
		 * 5.���� files ���飬ȡ�� File ���󸳸� f
		 *     6.��� f ���ļ�
		 *         7.�õ��ļ� f �Ĵ�С�ۼӵ�size
		 *     8.����
		 *         9.�ݹ���Ŀ¼ f �Ĵ�С�ۼӵ�size
		 * 10.���� size ��ֵ
		 */
		
		long size = 0;
		File[] files = dir.listFiles();
		if(files == null) {
			return 0;
		}
		
		for(File f : files) {
			if(f.isFile()) {
				size += f.length();
			} else {
				size += dirLength(f);
			}
		}
		
		return size;
	}
}
