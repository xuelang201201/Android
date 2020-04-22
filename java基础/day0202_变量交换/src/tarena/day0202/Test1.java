package tarena.day0202;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入整数a: ");
		//先获得输入的值，再把这个值赋给变量a
		int a = new Scanner(System.in).nextInt();
		System.out.println("输入整数b: ");
		int b = new Scanner(System.in).nextInt();
		
		int c = a;//a的值赋给c
		a = b;//b的值赋给a
		b = c;//c的值赋给b
		System.out.println(a);
		System.out.println(b);
	}
}
