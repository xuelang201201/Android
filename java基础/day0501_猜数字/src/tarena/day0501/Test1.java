package tarena.day0501;

import java.util.Random;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * ����[1,1001) ��Χ�����ֵ
		 * 
		 *  1+[0,1000)
		 *  
		 *  
		 *  new Random().nextInt(1000);
		 *  ����[0,1000) ��Χ�ڵ�int����
		 *  
		 */
		
		int r = 1 + new Random().nextInt(1000);
		
		/*
		 * 1.��ʾ��ʾ
		 * 2.��ѭ��
		 *     3.��ʾ���£���
		 *     4.��������ֵ���� c
		 *     5.���c>r����ʾ����
		 *     6.���c<r����ʾ��С��
		 *     7.����
		 *         8.��ʾ���ԡ�
		 *         9.break
		 */
		System.out.println("�Ѿ�������һ��[1,1001) ��Χ�ڵ�������");
		System.out.println("���������Ǽ�");
		
		while(true) {
			System.out.println("�£�");
			int c = new Scanner(System.in).nextInt();
			if(c>r) {
				System.out.println("��");
			} else if(c<r) {
				System.out.println("С");
			} else {
				System.out.println("��");
				break;
			}
		}
	}
}
