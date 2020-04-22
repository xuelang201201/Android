package tarena.day1801;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入加密/解密的文件路径：");
		String s = new Scanner(System.in).nextLine();
		File f = new File(s);
		if(! f.isFile()) {
			System.out.println("请输入正确的“文件”路径");
			return;
		}
		System.out.println("KEY:");
		int key = new Scanner(System.in).nextInt();
		try {
			encript(f, key);
			System.out.println("加密/解密完成");
		} catch (Exception e) {
			System.out.println("加密/解密失败");
			//打印完整异常信息
			e.printStackTrace();
		}
	}

	private static void encript(File f, int key) throws Exception {
		
		/*
		 * 1.新建 RandomAccessFile 对象赋给raf
		 *   从参数文件 f 读取数据
		 * 2.单字节读取数据，读取的字节值赋给变量 b
		 *     3.b 与 key 求异或，结果重新赋给 b
		 *     4.定位下标到当前位置的上一个位置
		 *         raf.getFilePointer()-1
		 *     5.将字节值 b 写到文件
		 * 6.关闭 raf
		 */
		
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		
		//单字节读取
		/*int b;
		while((b = raf.read()) != -1) {
			b ^= key;
			raf.seek(raf.getFilePointer()-1);
			raf.write(b);
		}*/
		
		//批量读取
		//1k 2k 4k 8k
		//1024 2048 4096 8192
		byte[] buf = new byte[8192];
		int n;//数量
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
