package tarena.day0502;

import java.util.Arrays;
import java.util.Random;

public class Test2 {
	public static void main(String[] args) {
		int[] a = new int[5];
		for(int i=0; i<a.length; i++) {
			a[i] = new Random().nextInt(100);
		}
		
		/*
		 * ƽ��ֵ
		 * 
		 * sum=0
		 * 
		 * [23, 76, 45, 97, 23]
		 *                      i
		 *                      
		 * 1.�����ۼӱ���sum=0
		 * 2.����a����
		 *     3.��iλ�õ�ֵ�ۼӵ�sum
		 * 4.���ƽ��ֵ����avg
		 * 5.��ʾavg��ֵ
		 */
		System.out.println(Arrays.toString(a));
		
		int sum = 0;
		for(int i=0; i<a.length; i++) {
			sum += a[i];
		}
		int avg = sum / a.length; 
		System.out.println(avg);
	}
}
