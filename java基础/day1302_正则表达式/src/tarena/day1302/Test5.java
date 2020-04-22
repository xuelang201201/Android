package tarena.day1302;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*������ʽ��Matcher��������*/
public class Test5 {
	public static void main(String[] args) {
		System.out.println("���룺");
		String s = new Scanner(System.in).nextLine();
		
		String regex = "\\d{3,}";
		
		Matcher m = Pattern.compile(regex).matcher(s);
		
		while(m.find()) {
			String s2 = m.group();
			int start = m.start();
			int end = m.end();
			System.out.println(start+","+end+": "+s2);
		}
	}
}
