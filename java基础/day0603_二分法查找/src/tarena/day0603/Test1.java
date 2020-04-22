package tarena.day0603;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		int[] a = random();//随机乱序数组
		//对数组a排序
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		System.out.println("输入查找的值：");
		int t = new Scanner(System.in).nextInt();
		
		//二分法查找，
		//在a数组中查找t的位置
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
		 * 1.定义下标lo=0,hi=a.length-1
		 * 2.定义下标mid;
		 * 3.当lo<=hi
		 *     4.mid = (lo+hi)/2
		 *     5.如果 mid 位置的值比t大
		 *         6.hi=mid-1
		 *     7.否则如果 mid 位置的值比t小 		
		 *         8.lo=mid+1
		 *     9.否则
		 *         10.返回 mid 的值
		 * 11.返回无意义值-(lo+1)		
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
