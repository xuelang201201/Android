package tarena.day0203;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("���뽵��ʱ�䣨�룩�� ");
		//�Ȼ�������ֵ���ٸ�������t
		double t = new Scanner(System.in).nextDouble();
		
		//���׹�ʽ������룬�ٸ�������d
		double d = 1.0/2 * 9.8 * t * t;
		
		System.out.println("����������"+d+" ��");
	}
}
