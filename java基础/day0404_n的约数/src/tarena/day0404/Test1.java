package tarena.day0404;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入整数显示所有的约数");
		int n = new Scanner(System.in).nextInt();
		
		/*
		 * 1.循环变量i从1到<=n 递增
		 *     2.如果n 能被i 整除
		 *         3.显示i 的值
		 */
		for(int i=1; i<=n; i++) {
			if(n%i == 0) {
				System.out.println(i);
			}
		}
		
	}
}
