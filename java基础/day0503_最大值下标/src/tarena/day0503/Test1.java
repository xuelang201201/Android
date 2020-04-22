package tarena.day0503;

import java.util.Arrays;
import java.util.Random;

public class Test1 {
	public static void main(String[] args) {
		int[] a = new int[5];
		for(int i=0; i<a.length; i++) {
			a[i] = new Random().nextInt(100);
		}
		
		/* index = 0 假设0位置最大
		 * 
		 * index
		 *  |
		 * [2,6,1,8,3]
		 *    i
		 * 
		 * 1.定义下标变量index=0
		 * 2.循环 i 从 1 到 <a.length 递增
		 *     3.如果i位置的值 > index位置的值
		 *         4.index = i
		 *         
		 * 5.显示 index 下标
		 */
		System.out.println(Arrays.toString(a));
		
		int index = 0;
		for(int i=1; i<a.length; i++) { 
			//找到数组中最大值所在的位置
			if(a[i] > a[index]) {
				index = i;
			}
		}
		System.out.println(index);		
	}
}
