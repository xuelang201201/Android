package tarena.day0401;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("�꣺");
		int year = new Scanner(System.in).nextInt();
		System.out.println("�£�");
		int month = new Scanner(System.in).nextInt();
		/*
		 * ��year��month��ֵ�����ݵ�f()�����������㣬
		 * ���õ����ķ���ֵ��������days
		 */
		int days = f(year, month);
		System.out.println(days);
	}//main����
	
	//1.�������� 2.������ 3.�����б�
	static int f(int year, int month) {
		//���屣�����ս���ı���r=0
		int r = 0;
		switch(month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			r = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			r = 30;
			break;
		case 2:
			if((year%4==0)&&(year%100!=0) || year%400==0) {
				r = 29;
			}else {
				r = 28;
			}
			break;
		default:
			r = -1;//������ֵ
		}//switch���� 
		return r;
	}
	
}
