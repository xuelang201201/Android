package tarena.day0205;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入年号：");
		//先获得输入的值，再赋值给变量year
		int year = new Scanner(System.in).nextInt();
		
		/* 
		 * 1.year能否被4整除，赋给 a
		 * 2.year能否被100整除，赋给 b
		 * 3.year能否被400整除，赋给 c
		 * 3.5. 定义字符串变量r = "平年"
		 * 4.如果 a 是真
		 *     5.如果 b 是假
		 *         6.r = "闰年"
		 * 
		 * 7.如果c是真
		 *     8.r = "闰年"
		 * 9.显示r的值
		 */
		
		boolean a = year % 4 == 0;
		boolean b = year % 100 == 0;
		boolean c = year % 400 == 0;
		
		String r = "平年";
		/*if(a) {
			if(b == false) {
				r = "闰年";
			}
		}
		if(c) {
			r = "闰年";
		}*/
		
		/*
		 * a是真并且“非b”是真，或者c是真
		 */
		if((a && !b) || c) {
			r = "闰年";
		}
		System.out.println(r);
	}
}
