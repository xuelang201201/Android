package tarena.day0604;

import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		//斐波那契数
		System.out.println("求第几个斐波那契数：");
		int n = new Scanner(System.in).nextInt();
		
		long r = g(n);
		//long r = f(n);
		System.out.println(r);
	}

	private static long g(int n) {
		long a = 1;
		long b = 1;
		for(int i=3; i<=n; i++) {
			b = a + b;
			a = b - a;
		}
		return b;
	}

	/*private static long f(int n) {
		if(n == 1 || n == 2) {
			return 1;
		}
		return f(n-2) + f(n-1);
	}*/
}
