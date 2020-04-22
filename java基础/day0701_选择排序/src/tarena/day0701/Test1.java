package tarena.day0701;

import java.util.Arrays;
import java.util.Random;

public class Test1 {
	public static void main(String[] args) {
		//1.��������������鸳�� a
		int[] a = random();
		System.out.println(Arrays.toString(a));
		
		System.out.println("---------------------------");
		//2.���� sort() ����������a��������
		sort(a);
		System.out.println("---------------------------");
		
		System.out.println(Arrays.toString(a));
		
		
	}//main����
	
	
	static int[] random() {
		/*
		 * 1.�������5+[0, 6)��Χ�ĳ���ֵlen
		 *     5+new Random().nextInt(6);
		 * 2.�½�len���ȵ� int[] ���鸳��a
		 * 3.ѭ�� i ��������a
		 *     4.��iλ�÷���100���������
		 * 5.�������� a
		 */
		int len = 5+new Random().nextInt(6);
		int[] a = new int[len];
		for(int i=0;i<len;i++) {
			a[i] = new Random().nextInt(100);
		}
		return a;
	}
	
	// void ��,�޷���ֵ
	static void sort(int[] a) {
		for(int i=0;i<a.length-1;i++) {
			//�� [i, a.length)��Χ�ڵ���Сֵ,
			//����iλ����С���±�i����j
			int j = i;
			for(int k=i+1;k<a.length;k++) {
				if(a[k] < a[j]) {
					j = k;
				}
			}
			
			int c = a[i];
			a[i] = a[j];
			a[j] = c;
			System.out.println(Arrays.toString(a));
		}
	}
}