package tarena.day0203;

import java.math.BigDecimal;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("���뽵��ʱ�䣨�룩�� ");
		//�Ȼ�������ֵ���ٸ�������t
		double t = new Scanner(System.in).nextDouble();
		
		//���׹�ʽ������룬�ٸ�������d
		//double d = 1.0/2 * 9.8 * t * t;

		BigDecimal bd1 = BigDecimal.valueOf(0.5);
		BigDecimal bd2 = BigDecimal.valueOf(9.8);
		BigDecimal bd3 = BigDecimal.valueOf(t);
		BigDecimal bd4 = bd1.multiply(bd2).multiply(bd3).multiply(bd3);
 		
		double d = bd4.doubleValue();
		
		System.out.println("����������"+bd4+" ��");
	}
}
