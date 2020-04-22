package tarena.day0406;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("公鸡5元1只");
		System.out.println("母鸡3元1只");
		System.out.println("小鸡1元3只");
		System.out.println("百元买百鸡");
		System.out.println("---------------------");
		/*
		 * 1.循环g 从 0 到 <=20 递增
		 *     2.100元买g 只公鸡剩余的钱赋给money
		 *     3.money买母鸡的最大数量赋给max
		 *     4.循环 m 从 0 到 <=max 递增
		 *         5.money买 m 只母鸡后剩余的钱
		 *           全部买小鸡的数量赋给x
		 *         6.如果 g+m+x==100
		 *         	   7.显示 g,m,x 数量组合
		 */

		for (int g=0; g<=20; g++) {
			int money = 100 - g * 5;
			int max = money / 3;
			for (int m=0; m<=max; m++) {
				int x = (money - m * 3) * 3;
				if(g+m+x==100) {
					System.out.println(
							"公鸡"+g+"只，"+
							"母鸡"+m+"只，"+
							"小鸡"+x+"只");
				}
			}
		}
	}
}
