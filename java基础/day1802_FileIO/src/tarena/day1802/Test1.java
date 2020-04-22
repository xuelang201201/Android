package tarena.day1802;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test1 {
	public static void main(String[] args) throws IOException {
		FileOutputStream out = new FileOutputStream("d:/abc/f2");
		
		out.write(97);//00 00 00 61 --> 61
		out.write(98);
		out.write(99);
		out.write(356);//00 00 01 64 --> 64
		byte[] buf = {
				101,102,103,104,105,
				106,107,108,109,110
		};
		out.write(buf);
		out.write(buf, 5, 4);
		
		out.close();
		System.out.println("请打开 d:/abc/f2 查看文件中的字节值数据");
	}
}
