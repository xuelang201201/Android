package tarena.day0301;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入整数：");
		int a = new Scanner(System.in).nextInt();
		
		/*
		 * 1.a向右移动24位再强转成byte赋给b1
		 * 2.a向右移动16位再强转成byte赋给b2
		 * 3.a向右移动8位再强转成byte赋给b3
		 * 4.a向右移动0位再强转成byte赋给b4
		 */
		
		byte b1 = (byte) (a>>24);
		byte b2 = (byte) (a>>16);
		byte b3 = (byte) (a>>8);
		byte b4 = (byte) (a>>0);
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		
		/*
		 * 合并
		 * 0.定义变量r=0
		 * 1.b1左移24位，再无符号右移0位，再与r求或赋给r
		 * 2.b1左移24位，再无符号右移8位，再与r求或赋给r
		 * 3.b1左移24位，再无符号右移16位，再与r求或赋给r
		 * 4.b1左移24位，再无符号右移24位，再与r求或赋给r
		 */
		
		int r = 0;
		r = r | (b1<<24>>>0);
		r = r | (b2<<24>>>8);
		r = r | (b3<<24>>>16);
		r = r | (b4<<24>>>24);
		System.out.println(r);
	}
}
