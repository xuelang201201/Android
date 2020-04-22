package tarena.day0602;

import java.util.Arrays;
import java.util.Random;

public class Test1 {
	public static void main(String[] args) {
		//1.随机产生乱序数组赋给a
		int[] a = random();
		System.out.println(Arrays.toString(a));

		System.out.println("-----------------------------");
		//2.调用sort() 方法对数组 a 进行排序
		sort(a);
		System.out.println("-----------------------------");
		
		System.out.println(Arrays.toString(a));
		
	}//main结束
	
	static int[] random() {
		/*
		 * 1.随机产生5+[0,6)范围的长度值len
		 *    5+new Random().nextInt(6);
		 * 2.新建len长度的int[] 数组赋给a
		 * 3.循环 i 遍历数组a
		 *     4.在i位置放入100内随机整数
		 * 5.返回数组a
		 */
		int len = 5 + new Random().nextInt(6);
		int[] a = new int[len];
		for(int i=0; i<len; i++) {
			a[i] = new Random().nextInt(100);
		}
		return a;
		
	}
	
	//void 空，无返回值
	static void sort(int[] a) {
		/*
		 * 1.循环i遍历数组a
		 *     2.循环j 从 a.length-1 到 >i 递减
		 *         3.如果a[j]<a[j-1]
		 *             4.交换两位置的值
		 *     5.显示数组 a 的值          
		 */
		
		for(int i=0; i<a.length; i++) {
			//将最小值向前推到i位置
			
			boolean flag = false;//还没有交换
			for(int j=a.length-1; j>i; j--) {
				if(a[j] < a[j-1]) {
					int c = a[j];
					a[j] = a[j-1];
					a[j-1] = c;
					flag = true;//有交换
				}
			}
			if(! flag) {//!flag 意思是： !flag == true 即 flag = false
				break;
			}
			System.out.println(Arrays.toString(a));
		}
	}
}
