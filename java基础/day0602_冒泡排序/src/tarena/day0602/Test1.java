package tarena.day0602;

import java.util.Arrays;
import java.util.Random;

public class Test1 {
	public static void main(String[] args) {
		//1.��������������鸳��a
		int[] a = random();
		System.out.println(Arrays.toString(a));

		System.out.println("-----------------------------");
		//2.����sort() ���������� a ��������
		sort(a);
		System.out.println("-----------------------------");
		
		System.out.println(Arrays.toString(a));
		
	}//main����
	
	static int[] random() {
		/*
		 * 1.�������5+[0,6)��Χ�ĳ���ֵlen
		 *    5+new Random().nextInt(6);
		 * 2.�½�len���ȵ�int[] ���鸳��a
		 * 3.ѭ�� i ��������a
		 *     4.��iλ�÷���100���������
		 * 5.��������a
		 */
		int len = 5 + new Random().nextInt(6);
		int[] a = new int[len];
		for(int i=0; i<len; i++) {
			a[i] = new Random().nextInt(100);
		}
		return a;
		
	}
	
	//void �գ��޷���ֵ
	static void sort(int[] a) {
		/*
		 * 1.ѭ��i��������a
		 *     2.ѭ��j �� a.length-1 �� >i �ݼ�
		 *         3.���a[j]<a[j-1]
		 *             4.������λ�õ�ֵ
		 *     5.��ʾ���� a ��ֵ          
		 */
		
		for(int i=0; i<a.length; i++) {
			//����Сֵ��ǰ�Ƶ�iλ��
			
			boolean flag = false;//��û�н���
			for(int j=a.length-1; j>i; j--) {
				if(a[j] < a[j-1]) {
					int c = a[j];
					a[j] = a[j-1];
					a[j-1] = c;
					flag = true;//�н���
				}
			}
			if(! flag) {//!flag ��˼�ǣ� !flag == true �� flag = false
				break;
			}
			System.out.println(Arrays.toString(a));
		}
	}
}
