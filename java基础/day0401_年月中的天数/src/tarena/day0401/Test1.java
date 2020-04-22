package tarena.day0401;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("年：");
		int year = new Scanner(System.in).nextInt();
		System.out.println("月：");
		int month = new Scanner(System.in).nextInt();
		/*
		 * 将year，month的值，传递到f()方法进行运算，
		 * 并得到它的返回值赋给变量days
		 */
		int days = f(year, month);
		System.out.println(days);
	}//main结束
	
	//1.返回类型 2.方法名 3.参数列表
	static int f(int year, int month) {
		//定义保存最终结果的变量r=0
		int r = 0;
		switch(month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			r = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			r = 30;
			break;
		case 2:
			if((year%4==0)&&(year%100!=0) || year%400==0) {
				r = 29;
			}else {
				r = 28;
			}
			break;
		default:
			r = -1;//无意义值
		}//switch结束 
		return r;
	}
	
}
