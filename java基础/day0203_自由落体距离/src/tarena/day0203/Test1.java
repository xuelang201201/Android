package tarena.day0203;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入降落时间（秒）： ");
		//先获得输入的值，再赋给变量t
		double t = new Scanner(System.in).nextDouble();
		
		//先套公式求出距离，再赋给变量d
		double d = 1.0/2 * 9.8 * t * t;
		
		System.out.println("你已下落了"+d+" 米");
	}
}
