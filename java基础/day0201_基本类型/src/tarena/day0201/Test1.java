package tarena.day0201;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * ����8���������ֱ𱣴�������������Сֵ�����ֵ
		 */
		 
		byte a = -128;
		byte b = 127;
		
		/* Short.MIN_VALUE
		 * ����jdk���һ��Short�࣬
		 * ����ȡshort������Сֵ
		 */
		short c = Short.MAX_VALUE;
		short d = Short.MIN_VALUE;
		int e = Integer.MAX_VALUE;
		int f = Integer.MIN_VALUE;
		long g = Long.MAX_VALUE;
		long h = Long.MIN_VALUE;
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
		System.out.println(g);
		System.out.println(h);
	}
}
