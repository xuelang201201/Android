package tarena.day0303;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("����������");
		int n = new Scanner(System.in).nextInt();
		
		/*
		 * ����f()��������n��ֵ���ݵ�f()�������м��㣬
		 * Ȼ���ٵõ������ļ�������������r
		 */
		int r = f(n);
		System.out.println(r);
		
	}//main����
	
	//1.�������� 2.������ 3.�����б�
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
