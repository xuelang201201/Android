package tarena.day0202;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("��������a: ");
		//�Ȼ�������ֵ���ٰ����ֵ��������a
		int a = new Scanner(System.in).nextInt();
		System.out.println("��������b: ");
		int b = new Scanner(System.in).nextInt();
		
		int c = a;//a��ֵ����c
		a = b;//b��ֵ����a
		b = c;//c��ֵ����b
		System.out.println(a);
		System.out.println(b);
	}
}
