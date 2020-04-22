package tarena.day1606;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		while(true) {
			try {
				f();
				break;
			} catch (ArithmeticException e) {
				System.out.println("不能除0，请重新尝试");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("两个！不是一个！");
			} catch (Exception e) {
				System.out.println("出错，请重试");
			} 
		}
	}

	private static void f() {
		System.out.println("输入逗号隔开的两个整数：");
		String s = new Scanner(System.in).nextLine();
		
		//456,24
		String[] a = s.split(",");
		int n1 = Integer.parseInt(a[0]);
		int n2 = Integer.parseInt(a[1]);
		
		int r = n1 / n2;
		System.out.println(r);
		/* 23542,534
		 * 345
		 * abc,def
		 * 45345,0
		 */
	}
}
