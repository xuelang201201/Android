package tarena.day1202;

import java.util.Arrays;
import java.util.Scanner;

/* ����email��ַ����ȡ���ֲ��� */
public class Test5 {

	public static void main(String[] args) {
		System.out.println("����Email��ַ��");
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String e = s.trim();
		String at = "@";

		int index = e.lastIndexOf(at);
		String result = e.substring(0, index);

		//char chars[] = result.toCharArray();
		char[] chars = new char[result.length()];
		for(int i=0; i<result.length(); i++) {
			chars[i] = result.charAt(i);
		}
		System.out.println(chars);
	}
}