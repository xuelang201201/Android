package tarena.day1402;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) throws ParseException {
		System.out.println("输入年-月(yyyy-MM)：");
		String s = new Scanner(System.in).nextLine();
		/*
		 * "2016-2"
		 *   -->Date对象(2016-2-1 0:0:0)
		 *   -->新建Calendar并设置成Date对象的时间点
		 *   
		 * 1.新建SimpleDateFormat对象赋给fmt
		 *   制定格式："yyyy-MM"
		 * 2.调用格式工具的parse() 解析方法，
		 *   将字符串s解析成Date对象赋给d
		 * 3.新建Calendar对象赋给c
		 * 4.将c设置成与Date对象d相同的时间点
		 * 5.获得一号是周几赋给day
		 * 6.获得当月最大天数赋给max
		 * 7.定义计数变量count=0
		 * 8.打印字符串"日\t一\t二\t三\t四\t五\t六"
		 * 9.循环i从0到<day-1递增
		 *     10.打印" \t"
		 *     11.count++
		 *     
		 * 12.循环i从1到<=max递增
		 *     13.打印i+"\t"
		 *     14.count++
		 *     15.如果count==7
		 *         16.打印换行
		 *         17.count=0
		 */
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
		Date d = fmt.parse(s);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int day = c.get(DAY_OF_WEEK);//获得一号是周几赋给day
		int max = c.getActualMaximum(DAY_OF_MONTH);//获得当月最大天数赋给max
		int count = 0;
		System.out.println("日\t一\t二\t三\t四\t五\t六");
		for(int i=0; i<day-1; i++) {
			System.out.print(" \t");
			count++;
		}
		
		for(int i=1; i<=max; i++) {
			System.out.print(i+"\t");
			count++;
			if(count == 7) {
				System.out.println();
				count = 0;
			}
		}
	
	}
}
