package tarena.day1702;

import java.io.File;
import java.util.Scanner;

public class Test7 {
	public static void main(String[] args) {
		System.out.println("输入目录路径，求目录大小：");
		String s = new Scanner(System.in).nextLine();
		
		File dir = new File(s);
		if(! dir.isDirectory()) {
			System.out.println("请输入正确的目录路径");
			return;
		}
		
		long size = dirLength(dir);
		System.out.println(size);
	}

	private static long dirLength(File dir) {
		/*
		 * 1.定义大小累加变量long size=0
		 * 2.对参数目录dir 列表，赋给 files
		 * 3.如果files == null
		 *     4.返回0
		 * 5.遍历 files 数组，取出 File 对象赋给 f
		 *     6.如果 f 是文件
		 *         7.得到文件 f 的大小累加到size
		 *     8.否则
		 *         9.递归求目录 f 的大小累加到size
		 * 10.返回 size 的值
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
