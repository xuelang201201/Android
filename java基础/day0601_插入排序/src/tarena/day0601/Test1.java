package tarena.day0601;

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
		 * 1.ѭ��i��1��<a.length����
		 *     2.ѭ��j��i-1��>=0 �ݼ�
		 *         3.���a[j+1] < a[j]
		 *             4.��������λ�õ�ֵ
		 *         5.����
		 *             6.break
		 *     7.��ʾa�����ֵ
		 */
		for(int i=1; i<a.length; i++) {
			//��ֵ������ڵ�λ��
			for(int j=i-1; j>=0; j--) {
				if(a[j+1] < a[j]) {
					int c = a[j+1];
					a[j+1] = a[j];
					a[j] = c;
				} else {
					break;
				}
			}
			System.out.println(Arrays.toString(a));
		}
	}
}
