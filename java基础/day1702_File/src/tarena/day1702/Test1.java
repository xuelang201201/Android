package tarena.day1702;

import java.io.File;

public class Test1 {
	public static void main(String[] args) {
		String path;
		
		path = "D:/FavoriteVideo/readme.txt";//存在的文件
		//path = "D:/FavoriteVideo/";//存在的目录
		//path = "W：/java";//乱写的不存在的路径
		
		File f = new File(path);
		System.out.println("是否可读: "+f.canRead());
		System.out.println("是否可写: "+f.canWrite());
		System.out.println("是否可执行: "+f.canExecute());
		System.out.println("是否隐藏: "+f.isHidden());
		System.out.println("是否存在: "+f.exists());
		System.out.println("完整路径: "+f.getAbsolutePath());
		System.out.println("文件名: "+f.getName());
		System.out.println("所在目录: "+f.getParent());
		System.out.println("最后修改时间: "+f.lastModified());
		System.out.println("字节量（目录无效）: "+f.length());
		System.out.println("是否目录: "+f.isDirectory());
		System.out.println("是否文件: "+f.isFile());
		System.out.println("空间大小: "+f.getTotalSpace());
		System.out.println("可用空间: "+f.getFreeSpace());
	}
}
