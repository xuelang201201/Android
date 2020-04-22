package tarena.day0206;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入半径（cm）：");
		double r = new Scanner(System.in).nextDouble();
		double C = 2 * 3.14 * r;
		double S = 3.14 * r * r;
		
		System.out.println("周长："+C+"cm");
		System.out.println("面积："+S+"cm2");
	}
}
