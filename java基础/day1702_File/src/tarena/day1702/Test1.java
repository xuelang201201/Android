package tarena.day1702;

import java.io.File;

public class Test1 {
	public static void main(String[] args) {
		String path;
		
		path = "D:/FavoriteVideo/readme.txt";//���ڵ��ļ�
		//path = "D:/FavoriteVideo/";//���ڵ�Ŀ¼
		//path = "W��/java";//��д�Ĳ����ڵ�·��
		
		File f = new File(path);
		System.out.println("�Ƿ�ɶ�: "+f.canRead());
		System.out.println("�Ƿ��д: "+f.canWrite());
		System.out.println("�Ƿ��ִ��: "+f.canExecute());
		System.out.println("�Ƿ�����: "+f.isHidden());
		System.out.println("�Ƿ����: "+f.exists());
		System.out.println("����·��: "+f.getAbsolutePath());
		System.out.println("�ļ���: "+f.getName());
		System.out.println("����Ŀ¼: "+f.getParent());
		System.out.println("����޸�ʱ��: "+f.lastModified());
		System.out.println("�ֽ�����Ŀ¼��Ч��: "+f.length());
		System.out.println("�Ƿ�Ŀ¼: "+f.isDirectory());
		System.out.println("�Ƿ��ļ�: "+f.isFile());
		System.out.println("�ռ��С: "+f.getTotalSpace());
		System.out.println("���ÿռ�: "+f.getFreeSpace());
	}
}
