package tarena.day0402;

public class Test3 {
	public static void main(String[] args) {
		/*
		 *         *            
		 *        ***
		 *       *****
		 *      ******* 
		 *     *********
		 *     
		 *  1.循环 i 从 1 到 <=5 递增
		 *      2.循环 j 从 1 到 <=5-i 递增
		 *      	3.打印空格
		 *      4.循环 j 从 1 到 <=i*2-1 递增
		 *      	5.打印 *
		 */
		for(int i=1; i<=5; i++) {
			for(int j=1; j<=5-i; j++) {
				System.out.print(" ");
			}
			for(int j=1; j<=i*2-1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
