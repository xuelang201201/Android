package tarena.day0403;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("����������׳ˣ�");
		int n = new Scanner(System.in).nextInt();
		
		/*
		 * ��n��ֵ���ݵ�jieCheng()�������ٵõ������ķ���ֵ����r
		 */
		String r = jieCheng(n);
		System.out.println(n+"! = "+r);
	} //main��������
	
	//1.�������� 2.������ 3.�����б�
	static String jieCheng(int n) {
		/*
		 * ����n��4
		 * 
		 * r=1
		 * 
		 * i=2,r*=i
		 * i=3,r*=i
		 * i=4,r*=i
		 * 
		 * 1.�������r=1
		 * 2.ѭ�� i �� 2 ��<=n����
		 *     3.r*=i
		 * 4.���� r ��ֵ    
		 */
		
		/*long r = 1;
		
		for(int i=2; i<=n; i++) {
			r *= i;
		}*/
		
		BigInteger r = BigInteger.valueOf(1);
		for(int i=2; i<=n; i++) {
			BigInteger bi = BigInteger.valueOf(i);
			r = r.multiply(bi);
		}
		
		return r.toString();
		
	}
	
}
