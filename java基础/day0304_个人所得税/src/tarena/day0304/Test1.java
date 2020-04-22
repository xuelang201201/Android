package tarena.day0304;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("工资：");
		int sal = new Scanner(System.in).nextInt();

		/*
		 * 调用f()方法将sal的值，传递到f()方法进行运算，
		 * 并得到它的返回值赋给tax
		 */

		double tax = f(sal);
		System.out.println("个人所得税"+tax);
	}//main结束


	//1.返回类型 2.方法名 3.参数列表
	static double f(int s) {
		/*
		 * 1.如果s<=3500,返回0
		 * 2.s减掉3500
		 * 3.定义税率变量r=0
		 * 4.定义速算扣除数变量k=0
		 * 5.根据s的范围为r和k赋值
		 * 6.套公式求出个人所得税赋给result
		 * 7.返回result变量的值
		 */
		if (s <= 3500) return 0;

		s -= 3500; //s = s - 3500;
		double r = 0;
		int k = 0;
		if(s <= 1500) {
			r = 0.03;
			k = 0;
		}else if(s <= 4500) {
			r = 0.1;
			k = 105;	
		}else if(s <= 9000) {
			r = 0.2;
			k = 555;
		}else if(s <= 35000) {
			r = 0.25;
			k = 1005;
		}else if(s <= 55000){
			r = 0.3;
			k = 2755;
		}else if(s <= 80000){
			r = 0.35;
			k = 5505;
		}else {
			r = 0.45; 
			k = 13505;
		}	

		double result = s * r - k;
		return result;
	}
}
