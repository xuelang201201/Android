package tarena.day0305;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入百分制分数：");
		int s = new Scanner(System.in).nextInt();
		
		/*
		 * 1.调用f()方法，将s的值传递到f()方法进行运算，
		 *   然后再得到它的返回值赋给r
		 * 
		 */
		String r = f(s);
		System.out.println(r);
	} //main结束
	
	//1.返回类型 2.方法名 3.参数列表
	static String f(int s) {
		if(s<0 || s>100) {
			return "分数不正确";
		}
		
		//准备保存结果的变量result=""
		String result = "";
		switch(s/10) {
		case 0:
		case 1:
			result = "E";
			break;
		case 2:
		case 3:
		case 4:
		case 5:
			result = "D";
			break;
		case 6:
			result = "C";
			break;
		case 7:
		case 8:
			result = "B";
			break;
		case 9:
		case 10:
			result = "A";
		}
		return result;
	}
}
