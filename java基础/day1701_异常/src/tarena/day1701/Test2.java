package tarena.day1701;

import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		System.out.println("����������������");
		
		double a = new Scanner(System.in).nextDouble();
		double b = new Scanner(System.in).nextDouble();
		
		try {
			double c = divide(a, b);
			System.out.println(c);
		} catch (ArithmeticException e) {
			System.out.println("���ܳ��������ǵĴ���������ǰɣ�");
			
			//�����ʾ��Ϣ
			System.out.println(e.getMessage());
			//��ӡ�������쳣��Ϣ
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
