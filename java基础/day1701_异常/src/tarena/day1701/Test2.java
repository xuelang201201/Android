package tarena.day1701;

import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		System.out.println("输入两个浮点数：");
		
		double a = new Scanner(System.in).nextDouble();
		double b = new Scanner(System.in).nextDouble();
		
		try {
			double c = divide(a, b);
			System.out.println(c);
		} catch (ArithmeticException e) {
			System.out.println("不能除零是我们的错，请鞭笞我们吧！");
			
			//获得提示消息
			System.out.println(e.getMessage());
			//打印完整的异常信息
			e.printStackTrace();
		}
	}

	private static double divide(double a, double b) {
		if(b == 0) {
			ArithmeticException e = 
					new ArithmeticException("/ by zero");
			
			throw e;
		}
		
		return a / b;
	}
}
