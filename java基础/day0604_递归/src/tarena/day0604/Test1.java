package tarena.day0604;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("����������׳�");
		int n = new Scanner(System.in).nextInt();
		
		long r = f(n);
		System.out.println(r);
	}

	private static long f(int n) {
		if(n == 0) { //������⣬���ټ����ݹ�
			return 1;
		}
		return n * f(n-1);
	}
}
