package tarena.day0206;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("����뾶��cm����");
		double r = new Scanner(System.in).nextDouble();
		double C = 2 * 3.14 * r;
		double S = 3.14 * r * r;
		
		System.out.println("�ܳ���"+C+"cm");
		System.out.println("�����"+S+"cm2");
	}
}
