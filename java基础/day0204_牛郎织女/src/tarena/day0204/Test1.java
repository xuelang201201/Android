package tarena.day0204;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("两星相聚 16.4 光年");
		System.out.println("光速 299792458 米/秒");
		System.out.println("喜鹊身长 0.46 米");
		System.out.println("牛郎织女相会，需要多少只喜鹊");

		/* 1.求一光年多少米，赋给 ly
		 * 2.求16.4光年多少米，赋给d
		 * 3.求喜鹊数量赋给r
		 * 4.对喜鹊数量向上取整，赋给n
		 * 5.显示n的值
		 */
		long ly = 299792458L*60*60*24*365;
		double d = ly * 16.4;
		double r = d / 0.46;
		/* Math.ceil(r) 天花板，向上取整
		 * 取整的结果是double类型，
		 * 强制转型成long整数类型
		 */
		long n = (long) Math.ceil(r);
		System.out.println(n);
	}
}
