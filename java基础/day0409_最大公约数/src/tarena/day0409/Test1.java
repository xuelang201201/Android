package tarena.day0409;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入两个整数求最大公约数");
		
		System.out.print("请输入一个整数：");
		int m = new Scanner(System.in).nextInt();
		
		System.out.print("请再输入一个整数：");
		int n = new Scanner(System.in).nextInt();
		
		//从较小值向前递减，找能同时整除两个数的值
		int t = m < n ? m : n;
		for(;;t--) {
			if(m%t == 0 && n%t == 0) {
				break;
			}
		}
		System.out.println(m+","+n+"的最大公约数是："+t);
	}
}
