package tarena.day1902;


public class Test2 {
	public static void main(String[] args) throws Exception {
		byte[] a;
		String s;
		String enc;
		
		a = new byte[]{97, 98, 99, -42, -48, -50, -60, -122, -76, -23, 70};
		enc = "Ĭ��";
		s = new String(a);
		System.out.println(s);
		
		a = new byte[]{97, 98, 99, -42, -48, -50, -60, -122, -76, -23, 70};
		enc = "GBK";
		s = new String(a, enc);
		System.out.println(s);
		
		a = new byte[]{97, 98, 99, -42, -48, -50, -60, 63, 63};
		enc = "GB2312";
		s = new String(a, enc);
		System.out.println(s);
		
		a = new byte[]{97, 98, 99, -28, -72, -83, -26, -106, -121, -27, -106, -122, -23, -107, -107};
		enc = "UTF-8";
		s = new String(a, enc);
		System.out.println(s);
		
		a = new byte[]{0, 97, 0, 98, 0, 99, 78, 45, 101, -121, 85, -122, -107, 85};
		enc = "UTF-16Be";
		s = new String(a, enc);
		System.out.println(s);
		
		a = new byte[]{97, 0, 98, 0, 99, 0, 45, 78, -121, 101, -122, 85, 85, -107};
		enc = "UTF-16Le";
		s = new String(a, enc);
		System.out.println(s);
		
	}
}
