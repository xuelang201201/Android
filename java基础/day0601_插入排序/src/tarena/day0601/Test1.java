package tarena.day0601;

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
		 * 1.循环i从1到<a.length递增
		 *     2.循环j从i-1到>=0 递减
		 *         3.如果a[j+1] < a[j]
		 *             4.交换两个位置的值
		 *         5.否则
		 *             6.break
		 *     7.显示a数组的值
		 */
		for(int i=1; i<a.length; i++) {
			//将值插入该在的位置
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
