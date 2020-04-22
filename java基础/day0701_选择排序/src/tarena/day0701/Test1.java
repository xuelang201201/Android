package tarena.day0701;

import java.util.Arrays;
import java.util.Random;

public class Test1 {
	public static void main(String[] args) {
		//1.随机产生乱序数组赋给 a
		int[] a = random();
		System.out.println(Arrays.toString(a));
		
		System.out.println("---------------------------");
		//2.调用 sort() 方法对数组a进行排序
		sort(a);
		System.out.println("---------------------------");
		
		System.out.println(Arrays.toString(a));
		
		
	}//main结束
	
	
	static int[] random() {
		/*
		 * 1.随机产生5+[0, 6)范围的长度值len
		 *     5+new Random().nextInt(6);
		 * 2.新建len长度的 int[] 数组赋给a
		 * 3.循环 i 遍历数组a
		 *     4.在i位置放入100内随机整数
		 * 5.返回数组 a
		 */
		int len = 5+new Random().nextInt(6);
		int[] a = new int[len];
		for(int i=0;i<len;i++) {
			a[i] = new Random().nextInt(100);
		}
		return a;
	}
	
	// void 空,无返回值
	static void sort(int[] a) {
		for(int i=0;i<a.length-1;i++) {
			//找 [i, a.length)范围内的最小值,
			//假设i位置最小，下标i赋给j
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