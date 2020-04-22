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
		 * 平均值
		 * 
		 * sum=0
		 * 
		 * [23, 76, 45, 97, 23]
		 *                      i
		 *                      
		 * 1.定义累加变量sum=0
		 * 2.遍历a数组
		 *     3.将i位置的值累加到sum
		 * 4.求出平均值赋给avg
		 * 5.显示avg的值
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
