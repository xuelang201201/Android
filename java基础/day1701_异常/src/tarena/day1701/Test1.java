package tarena.day1701;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		try {
			f();
		} catch (ParseException e) {
			System.out.println("格式错误");
		} catch (IOException e) {
			System.out.println("无法创建文件");
		}
	}

	private static void f() throws ParseException, IOException {
		System.out.println("输入生日(yyyy-MM-dd)：");
		String s = new Scanner(System.in).nextLine();
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date d = fmt.parse(s);
		long t = d.getTime();
		//"d:/21342532342423.txt"
		File f = new File("d:/"+t+".txt");
		f.createNewFile();
	}
}
