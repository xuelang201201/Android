package tarena.day1401;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) throws ParseException {
		System.out.println("输入日期， 求距离这一天的天数(yyyy-MM-dd)：");
		
		String s = new Scanner(System.in).nextLine();
		
		// “2016-2-5” --> Date对象 --> 取出毫秒值
		// 与系统当前时间求时间差
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		// ctrl+1, add throws
		Date d = f.parse(s);
		long t = d.getTime() - System.currentTimeMillis();
		
		t = t/1000/60/60/24;
		
		System.out.println("距离天数："+t);
	}
}
