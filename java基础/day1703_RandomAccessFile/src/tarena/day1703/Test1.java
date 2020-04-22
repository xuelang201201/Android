package tarena.day1703;

import java.io.RandomAccessFile;
import java.util.Arrays;

public class Test1 {
	public static void main(String[] args) throws Exception {
		RandomAccessFile raf = new RandomAccessFile("d:/abc/f1", "rw");
		
		raf.write(97);//00 00 00 61 --> 61
		raf.write(98);
		raf.write(99);
		raf.write(356);//00 00 01 64 --> 64
		byte[] buf = {
			101,102,103,104,105,
			106,107,108,109,110
		};
		raf.write(buf);
		raf.write(buf, 6, 3);
		
		//定位下标到0位置
		raf.seek(0);
		
		//单字节读取标准格式
		int b;
		while((b = raf.read()) != -1) {
			System.out.println(b);
		}
		
		///
		raf.seek(0);
		
		//批量读取标准格式
		buf = new byte[5];
		int n;//每次读取的数量
		while((n = raf.read(buf)) != -1) {
			System.out.println(n+": "+Arrays.toString(buf));
		}
		
		raf.close();//释放系统资源
		System.out.println("打开d:/abc/f1 切换16进制查看");
	}
}
