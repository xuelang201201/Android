package tarena.day0303;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入整数：");
		int n = new Scanner(System.in).nextInt();
		
		/*
		 * 调用f()方法，将n的值传递到f()方法进行计算，
		 * 然后再得到方法的计算结果赋给变量r
		 */
		int r = f(n);
		System.out.println(r);
		
	}//main结束
	
	//1.返回类型 2.方法名 3.参数列表
	static int f(int x) {
		if(x > 0) {
			return 1;
		} else if(x < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
