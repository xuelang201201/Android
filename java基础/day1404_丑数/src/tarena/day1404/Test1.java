package tarena.day1404;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("求第几个丑数：");
		int n = new Scanner(System.in).nextInt();
		
		String ugly = chouShu(n);
		System.out.println(ugly);
	}

	private static String chouShu(int n) {
		LinkedList<BigInteger> list3 = new LinkedList<>();
		LinkedList<BigInteger> list5 = new LinkedList<>();
		LinkedList<BigInteger> list7 = new LinkedList<>();
		
		list3.add(BigInteger.valueOf(3));//list3.add(Long.valueOf(3L));
		list5.add(BigInteger.valueOf(5));
		list7.add(BigInteger.valueOf(7));
		
		BigInteger r = BigInteger.valueOf(0);
		for(int i=1; i<=n; i++) {
			BigInteger a = list3.getFirst();
			BigInteger b = list5.getFirst();
			BigInteger c = list7.getFirst();
			r = a.compareTo(b) < 0 ? a : b;
			r = r.compareTo(c) < 0 ? r : c;
			if(r.equals(a)) {
				list3.removeFirst();
			}
				
			if(r.equals(b)) {
				list5.removeFirst();
			}
				
			if(r.equals(c)) {
				list7.removeFirst();
			}
			
			list3.add(r.multiply(BigInteger.valueOf(3)));
			list5.add(r.multiply(BigInteger.valueOf(5)));
			list7.add(r.multiply(BigInteger.valueOf(7)));
		}
		return r.toString();
	}

	
	
	/*private static long chouShu(int n) {
		 0.定义保存结果的变量long r=0L
		 * 1.定义计数变量c=0
		 * 2.循环 long i 从3开始无条件递增
		 *     3.定义变量 long j = i
		 *     4.当j能被3整除，j/=3
		 *     5.当j能被5整除，j/=5
		 *     6.当j能被7整除，j/=7
		 *     
		 *     7.如果j==1
		 *         8.c++
		 *         9.如果 c==n
		 *             10.r=i
		 *             11.break
		 * 12.返回r的值
		 
		
		long r = 0L;
		int c = 0;
		for(long i=3; ; i++) {
			
			long j = i;
			
			while(j%3 == 0) {
				j /= 3;
			}
			
			while(j%5 == 0) {
				j /= 5;
			}
			
			while(j%7 == 0) {
				j /= 7;
			}
			
			if(j==1) {
				c++;
				if(c==n) {
					r = i;
					break;
				}
			}
		}
		return r;
	}*/
}
