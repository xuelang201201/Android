package tarena.day1302;

import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		System.out.println("ÑÔÂÛ×ÔÓÉ£º");
		String s = new Scanner(System.in).nextLine();
		
		String regex = "²İÄàÂí|ÂíÀÕ¸ê±Ú|ÄáÂê|ÄãÃÃ|ºú½õÌÎ|ÎÂ¼Ò±¦";
		
		s = s.replaceAll(regex, "***");
		System.out.println(s);
	}
}
