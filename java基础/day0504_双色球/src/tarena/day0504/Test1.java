package tarena.day0504;

import java.util.Arrays;
import java.util.Random;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * 准备红球数组和篮球数组
		 * 1.新建33个长度的红球数组赋给r
		 * 2.新建16个长度的篮球数组赋给b
		 * 3.遍历红球数组放入红球号码1到33
		 * 3.遍历蓝球数组放入蓝球号码1到16
		 */
		
		int[] r = new int[33];
		int[] b = new int[16];
		for(int i=0; i<33; i++) {
			r[i] = i + 1;
		}
		for(int i=0; i<16; i++) {
			b[i] = i + 1;
		}
		System.out.println(Arrays.toString(r));
		System.out.println(Arrays.toString(b));
		
		/*
		 * 选择6个不重复的红球
		 * 1.新建6个长度的数组赋给red
		 * 2.新建33个长度的标记数组flag
		 * 3.循环i 从 0 到 <6 递增
		 *     4.在[0,33)范围内随机挑选一个下标值j
		 *     5.当 flag[j] 是true，回到4
		 *     
		 *     6.将r数组j位置的值赋给red数组i位置
		 *     7.flag[j]置为true
		 */
		
		//红球
		int[] red = new int[6];
		boolean[] flag = new boolean[33];
		for(int i=0; i<6; i++) {
			int j;
			do {
				j = new Random().nextInt(33);
			} while(flag[j]);
			
			red[i] = r[j];
			flag[j] = true;
		}
		
		//蓝球
		int blue = b[new Random().nextInt(16)];
		
		//输出结果
		System.out.println("红球：");
		System.out.println(Arrays.toString(red));
		System.out.println("蓝球：");
		System.out.println(blue);
	}
}
