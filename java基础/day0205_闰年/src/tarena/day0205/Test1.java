package tarena.day0205;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("������ţ�");
		//�Ȼ�������ֵ���ٸ�ֵ������year
		int year = new Scanner(System.in).nextInt();
		
		/* 
		 * 1.year�ܷ�4���������� a
		 * 2.year�ܷ�100���������� b
		 * 3.year�ܷ�400���������� c
		 * 3.5. �����ַ�������r = "ƽ��"
		 * 4.��� a ����
		 *     5.��� b �Ǽ�
		 *         6.r = "����"
		 * 
		 * 7.���c����
		 *     8.r = "����"
		 * 9.��ʾr��ֵ
		 */
		
		boolean a = year % 4 == 0;
		boolean b = year % 100 == 0;
		boolean c = year % 400 == 0;
		
		String r = "ƽ��";
		/*if(a) {
			if(b == false) {
				r = "����";
			}
		}
		if(c) {
			r = "����";
		}*/
		
		/*
		 * a���沢�ҡ���b�����棬����c����
		 */
		if((a && !b) || c) {
			r = "����";
		}
		System.out.println(r);
	}
}
