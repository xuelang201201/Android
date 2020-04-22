package tarena.day1702;

import java.io.File;

public class Test3 {
	public static void main(String[] args) {
		File f = new File("d:/abc/a/b/c");
		
		if(f.exists()) {
			boolean b = f.delete();
			System.out.println("删除是否成功："+b);
		} else {
			boolean b = f.mkdirs();
			System.out.println("创建是否成功："+b);
		}
	}
}
