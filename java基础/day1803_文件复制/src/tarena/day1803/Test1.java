package tarena.day1803;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入原文件：");
		String s = new Scanner(System.in).nextLine();
		File src = new File(s);
		if(! src.isFile()) {
			System.out.println("请输入正确的“文件”路径");
			return;
		}
		
		System.out.println("输入目标文件：");
		String s2 = new Scanner(System.in).nextLine();
		File target = new File(s2);
		if(target.isDirectory()) {
			System.out.println("请输入正确的“文件”路径");
			return;
		}
		
		try {
			copy(src, target);
			System.out.println("复制完成");
		} catch (Exception e) {
			System.out.println("复制失败");
			//打印完整异常信息
			e.printStackTrace();
		}
	}

	private static void copy(File src, File target) throws Exception {
		/*
		 * 1.新建 FileInputStream 赋给 in 
		 *   与参数文件 src 相接
		 * 2.新建FileOutputStream 赋给 out
		 *   与参数文件 target 相接
		 * 3.单字节读取
		 *     4.读取的字节值，向输出流输出
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
		int n;//数量
		while((n=in.read(buf)) != -1) {
			out.write(buf, 0, n);
		}*/
		
		in.close();
		out.close();
	}
}
