package tarena.day0403;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入整数求阶乘：");
		int n = new Scanner(System.in).nextInt();
		
		/*
		 * 将n的值传递到jieCheng()方法，再得到方法的返回值赋给r
		 */
		String r = jieCheng(n);
		System.out.println(n+"! = "+r);
	} //main方法结束
	
	//1.返回类型 2.方法名 3.参数列表
	static String jieCheng(int n) {
		/*
		 * 假设n是4
		 * 
		 * r=1
		 * 
		 * i=2,r*=i
		 * i=3,r*=i
		 * i=4,r*=i
		 * 
		 * 1.定义变量r=1
		 * 2.循环 i 从 2 到<=n递增
		 *     3.r*=i
		 * 4.返回 r 的值    
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
