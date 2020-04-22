package tarena.day1805;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("�����ļ�·����");
		String s = new Scanner(System.in).nextLine();
		File file = new File(s);
		if(! file.isFile()) {
			System.out.println("��������ȷ�ġ��ļ���·��");
			return;
		}
		System.out.println("�������ļ���С(Kb)��");
		long size = new Scanner(System.in).nextLong();
		try {
			split(file, size);
			System.out.println("������");
		} catch (Exception e) {
			System.out.println("���ʧ��");
			e.printStackTrace();
		}
	}

	private static void split(File file, long size) throws Exception {
		//ԭ�ļ��ļ���
		String name = file.getName();
		//׼��Ŀ¼
		File dir = new File(file.getAbsolutePath()+"_split");
		if(dir.exists()) {//����ļ����ڣ����ɾ��
			File[] files = dir.listFiles();
			for (File f : files) {
				f.delete();
			}
		} else {
			dir.mkdir();
		}
		
		//////////////////
		/*
		 * 1.�½�FileInputStream����in
		 *   �Ӳ����ļ�file
		 * 2.����FileOutputStream out=null
		 * 3.�����ֽڼ�������long byteCount=0
		 * 4.�����ļ���������fileCount=0
		 * 5.���ֽڶ�ȡ����ȡ���ֽ�ֵ��������b
		 *     6.��� out==null ���� byteCount==size
		 *         7.��� out != null�� out.close()
		 *         8.�½� FileOutputStream ����out
		 *           ���ļ� dir Ŀ¼�� name+"."+(++fileCount)
		 *          new File(dir, name+"."+(++fileCount));
		 *         9.byteCount=0
		 * 
		 *     10.���ֽ�ֵ b ����������
		 *     11.byteCount++
		 * 12.in.close()
		 * 13.out.close()
		 */
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream out = null;
		long byteCount = 0;
		int fileCount = 0;
		
		//���ֽڶ�ȡ�ļ�
		int b;
		while((b=in.read()) != -1) {
			if(out == null || byteCount == size) {
				if(out != null) {
					out.close();
				}
				out = new BufferedOutputStream(
						new FileOutputStream(
								new File(dir, name+"."+(++fileCount))));
				byteCount = 0;
			}
			
			out.write(b);
			byteCount++;
		}
		
		in.close();
		out.close();
	}
}
