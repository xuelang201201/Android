package tarena.day1902;

import java.util.Arrays;

public class Test1 {
	public static void main(String[] args) throws Exception {
		/*
		 * Unicode -->
		 *     Ĭ��
		 *     GBK
		 *     GB2312
		 *     UTF-8
		 *     UTF-16Be
		 *     UTF-18Le  
		 */
		String s = "abc���Ć��F";
		System.out.println(s);
		
		byte[] a;
		String enc;
		
		enc = "Ĭ��";
		a = s.getBytes();
		System.out.println(enc+":\t"+Arrays.toString(a));
		
		enc = "GBK";
		a = s.getBytes(enc);
		System.out.println(enc+":\t"+Arrays.toString(a));
		
		enc = "GB2312";
		a = s.getBytes(enc);
		System.out.println(enc+":\t"+Arrays.toString(a));
		
		enc = "UTF-8";
		a = s.getBytes(enc);
		System.out.println(enc+":\t"+Arrays.toString(a));
		
		enc = "UTF-16Be";
		a = s.getBytes(enc);
		System.out.println(enc+":\t"+Arrays.toString(a));
		
		enc = "UTF-16Le";
		a = s.getBytes(enc);
		System.out.println(enc+":\t"+Arrays.toString(a));
		
	}
}
