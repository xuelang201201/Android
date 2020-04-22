package tarena.day0501;

import java.util.Random;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * 产生[1,1001) 范围的随机值
		 * 
		 *  1+[0,1000)
		 *  
		 *  
		 *  new Random().nextInt(1000);
		 *  产生[0,1000) 范围内的int整数
		 *  
		 */
		
		int r = 1 + new Random().nextInt(1000);
		
		/*
		 * 1.显示提示
		 * 2.死循环
		 *     3.提示“猜：”
		 *     4.获得输入的值赋给 c
		 *     5.如果c>r，提示“大”
		 *     6.如果c<r，提示“小”
		 *     7.否则
		 *         8.提示“对”
		 *         9.break
		 */
		System.out.println("已经产生了一个[1,1001) 范围内的整数，");
		System.out.println("请猜这个数是几");
		
		while(true) {
			System.out.println("猜：");
			int c = new Scanner(System.in).nextInt();
			if(c>r) {
				System.out.println("大");
			} else if(c<r) {
				System.out.println("小");
			} else {
				System.out.println("对");
				break;
			}
		}
	}
}
