package tarena.day0502;

import java.util.Arrays;
import java.util.Random;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * 先新建一个存放int 整数的数组，
		 * 再将数组的内存地址赋给变量a
		 */
		int[] a = new int[5];
		System.out.println(a[0]);
		System.out.println(a[4]);
		
		//遍历数组，
		//在每个位置放一个100内的随机整数
		for(int i=0; i<a.length; i++) {
			a[i] = new Random().nextInt(100);
		}
		
		//Arrays.toString(a)
		//将数组中所有的值连接成字符串
		System.out.println(Arrays.toString(a));
	}
}
