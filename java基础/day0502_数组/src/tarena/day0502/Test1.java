package tarena.day0502;

import java.util.Arrays;
import java.util.Random;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * ���½�һ�����int ���������飬
		 * �ٽ�������ڴ��ַ��������a
		 */
		int[] a = new int[5];
		System.out.println(a[0]);
		System.out.println(a[4]);
		
		//�������飬
		//��ÿ��λ�÷�һ��100�ڵ��������
		for(int i=0; i<a.length; i++) {
			a[i] = new Random().nextInt(100);
		}
		
		//Arrays.toString(a)
		//�����������е�ֵ���ӳ��ַ���
		System.out.println(Arrays.toString(a));
	}
}
