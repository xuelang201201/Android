package tarena.day1302;

import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		System.out.println("�������ɣ�");
		String s = new Scanner(System.in).nextLine();
		
		String regex = "������|���ո��|����|����|������|�¼ұ�";
		
		s = s.replaceAll(regex, "***");
		System.out.println(s);
	}
}
