package tarena.day1805;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入文件路径：");
		String s = new Scanner(System.in).nextLine();
		File file = new File(s);
		if(! file.isFile()) {
			System.out.println("请输入正确的“文件”路径");
			return;
		}
		System.out.println("输入拆分文件大小(Kb)：");
		long size = new Scanner(System.in).nextLong();
		try {
			split(file, size);
			System.out.println("拆分完成");
		} catch (Exception e) {
			System.out.println("拆分失败");
			e.printStackTrace();
		}
	}

	private static void split(File file, long size) throws Exception {
		//原文件文件名
		String name = file.getName();
		//准备目录
		File dir = new File(file.getAbsolutePath()+"_split");
		if(dir.exists()) {//如果文件存在，逐个删除
			File[] files = dir.listFiles();
			for (File f : files) {
				f.delete();
			}
		} else {
			dir.mkdir();
		}
		
		//////////////////
		/*
		 * 1.新建FileInputStream赋给in
		 *   接参数文件file
		 * 2.定义FileOutputStream out=null
		 * 3.定义字节计数变量long byteCount=0
		 * 4.定义文件计数变量fileCount=0
		 * 5.单字节读取，读取的字节值赋给变量b
		 *     6.如果 out==null 或者 byteCount==size
		 *         7.如果 out != null， out.close()
		 *         8.新建 FileOutputStream 赋给out
		 *           接文件 dir 目录中 name+"."+(++fileCount)
		 *          new File(dir, name+"."+(++fileCount));
		 *         9.byteCount=0
		 * 
		 *     10.将字节值 b 向输出流输出
		 *     11.byteCount++
		 * 12.in.close()
		 * 13.out.close()
		 */
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream out = null;
		long byteCount = 0;
		int fileCount = 0;
		
		//单字节读取文件
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
