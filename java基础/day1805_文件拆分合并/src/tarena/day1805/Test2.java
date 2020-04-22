package tarena.day1805;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		System.out.println("�������ļ���ŵ�Ŀ¼��");
		String s1 = new Scanner(System.in).nextLine();
		File dir = new File(s1);
		if(! dir.isDirectory()) {
			System.out.println("��������ȷ�ġ�Ŀ¼��·��");
			return;
		}
		System.out.println("����ϲ���Ŀ¼�ļ���");
		String s2 = new Scanner(System.in).nextLine();
		try {
			heBing(dir, s2);
			System.out.println("���");
		} catch (Exception e) {
			System.out.println("ʧ��");
			e.printStackTrace();
		}
	}

	private static void heBing(File dir, String file) throws IOException {
		//�б�ֻ�г����ֺ�׺���ļ�
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()) {
					return false;
				}
				// wer.wer.21
				return 
						f.getName().matches(".+\\.\\d+");
			}
		});
		
		///���б������ֺ�׺����
		Arrays.sort(files, new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				//sld.as.23 --> 23
				String n1 = o1.getName();
				String n2 = o2.getName();
				
				n1 = n1.substring(n1.lastIndexOf(".") + 1);
				n2 = n2.substring(n2.lastIndexOf(".") + 1);
				return //�����������ֱ�����
					Integer.parseInt(n1) - Integer.parseInt(n2);
			}
		});
		
		///
		FileOutputStream out = new FileOutputStream(file);
		
		FileInputStream in;
		
		byte[] buf = new byte[8192];
		int n;//����
		
		for (File f : files) {
			in = new FileInputStream(f);
			while((n = in.read(buf)) != -1) {
				out.write(buf, 0, n);
			}
			
			in.close();
		}
		
		out.close();
	}
}
