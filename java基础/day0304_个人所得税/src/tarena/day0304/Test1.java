package tarena.day0304;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("���ʣ�");
		int sal = new Scanner(System.in).nextInt();

		/*
		 * ����f()������sal��ֵ�����ݵ�f()�����������㣬
		 * ���õ����ķ���ֵ����tax
		 */

		double tax = f(sal);
		System.out.println("��������˰"+tax);
	}//main����


	//1.�������� 2.������ 3.�����б�
	static double f(int s) {
		/*
		 * 1.���s<=3500,����0
		 * 2.s����3500
		 * 3.����˰�ʱ���r=0
		 * 4.��������۳�������k=0
		 * 5.����s�ķ�ΧΪr��k��ֵ
		 * 6.�׹�ʽ�����������˰����result
		 * 7.����result������ֵ
		 */
		if (s <= 3500) return 0;

		s -= 3500; //s = s - 3500;
		double r = 0;
		int k = 0;
		if(s <= 1500) {
			r = 0.03;
			k = 0;
		}else if(s <= 4500) {
			r = 0.1;
			k = 105;	
		}else if(s <= 9000) {
			r = 0.2;
			k = 555;
		}else if(s <= 35000) {
			r = 0.25;
			k = 1005;
		}else if(s <= 55000){
			r = 0.3;
			k = 2755;
		}else if(s <= 80000){
			r = 0.35;
			k = 5505;
		}else {
			r = 0.45; 
			k = 13505;
		}	

		double result = s * r - k;
		return result;
	}
}
