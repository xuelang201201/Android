package tarena.day1702;

import java.io.File;
import java.io.IOException;

public class Test2 {
	public static void main(String[] args) throws IOException {
		File f = new File("d:/abc/a.txt");
		if(f.exists()) {
			boolean b = f.delete();
			System.out.println("删除是否成功："+b);
		} else {
			/*
			 * 如果所在的目录不存在，会出现异常
			 * 如果文件已经存在，会返回false
			 */
			boolean b = f.createNewFile();
			System.out.println("创建是否成功："+b);
		}
	}
}
