package tarena.day0603;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		int[] a = random();//�����������
		//������a����
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		System.out.println("������ҵ�ֵ��");
		int t = new Scanner(System.in).nextInt();
		
		//���ַ����ң�
		//��a�����в���t��λ��
		int index = binarySearch(a, t);
		System.out.println(index);
	}

	private static int[] random() {
		int len = 5 + new Random().nextInt(6);
		int[] a = new int[len];
		for(int i=0; i<len; i++) {
			a[i] = new Random().nextInt(100);
		}
		return a;
	}

	private static int binarySearch(int[] a, int t) {
		/*
		 * 1.�����±�lo=0,hi=a.length-1
		 * 2.�����±�mid;
		 * 3.��lo<=hi
		 *     4.mid = (lo+hi)/2
		 *     5.��� mid λ�õ�ֵ��t��
		 *         6.hi=mid-1
		 *     7.������� mid λ�õ�ֵ��tС 		
		 *         8.lo=mid+1
		 *     9.����
		 *         10.���� mid ��ֵ
		 * 11.����������ֵ-(lo+1)		
		 */
		int lo = 0; 
		int hi = a.length - 1;
		int mid;
		while(lo <= hi) {
			mid = (lo + hi) / 2;
		    if(a[mid] > t) {
		    	hi = mid - 1;
		    } else if(a[mid] < t) {
		    	lo = mid + 1;
		    } else {
		    	return mid;
		    }     	
		}
		return -(lo+1);	
	}
}
