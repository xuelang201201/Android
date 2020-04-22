package tarena.day0407;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("输入整数n求n内质数的数量");
		int n = new Scanner(System.in).nextInt();
		//n的值传递到zhiShu()方法进行运算，
		//并得到它的返回值赋给变量count
		int count = zhiShu(n);
		System.out.println(count);
	}//main结束

	//1.返回类型 2.方法名 3.参数列表
	static int zhiShu(int n) {
		/*
		 * 1.定义计算变量 count=1 (已知有一个质数是2)
		 * 2.循环i 从 3 到 <=n 递增
		 *     3.求 i 开方 +1 赋给变量 max
		 *     4.循环j 从 2 到   <= max 递增
		 *         5.如果i能被j整除
		 *             6.继续i循环下一轮，判断下一个i值
		 *     7.计数递增count++
		 *     
		 * 8.返回count的值
		 */

		int count = 1;
		outer://循环命名
			for(int i=3; i<=n; i++) {
				double max = Math.sqrt(i) + 1;
				for(int j=2; j<max; j++) {
					if(i%j == 0) {
						continue outer;
					}
				}
				count++; //i是质数
			}
		return count;
	}
}
