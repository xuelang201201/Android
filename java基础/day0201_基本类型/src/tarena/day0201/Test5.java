package tarena.day0201;

public class Test5 {
	public static void main(String[] args) {
		System.out.println(3/2);
		System.out.println(3d/2);
		
		//溢出测试
		int a = Integer.MAX_VALUE;
		a = a + 1;
		System.out.println(a);
		a = 300000000 * 60 * 60 * 24 * 365;
		System.out.println(a);
		
		//不精确测试
		System.out.println(2-1.9);
		System.out.println(2-1.8);
		System.out.println(2-1.7);
		System.out.println(2-1.6);
		System.out.println(2-1.5);
		System.out.println(2-1.4);
		System.out.println(4.35*100);
		System.out.println(4.36*100);
		System.out.println(4.37*100);
		System.out.println(4.38*100);
		
		//特殊值测试
		System.out.println(3.14/0);
		System.out.println(Double.MAX_VALUE*2);
		System.out.println(Math.sqrt(-2));
	}
}