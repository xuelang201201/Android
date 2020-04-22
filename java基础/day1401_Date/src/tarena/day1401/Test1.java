package tarena.day1401;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {
	public static void main(String[] args) {
		Date a = new Date();
		Date b = new Date(6000000000000L);
		//对象传递到println()方法内，
		//在方法内部调用了 a.toString()
		//获取字符串，再打印
		System.out.println(a);
		System.out.println(b);
		
		System.out.println(a.getTime());
		System.out.println(b.getTime());
		
		a.setTime(0);
		System.out.println(a);
		
		////
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String s;
		
		s = f.format(a);
		System.out.println(s);
		s = f.format(b);
		System.out.println(s);
	}
}
