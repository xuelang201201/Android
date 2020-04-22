package tarena.day1202;

import java.util.Arrays;
import java.util.Scanner;

/* 输入email地址，提取名字部分 */
public class Test5 {

	public static void main(String[] args) {
		System.out.println("输入Email地址：");
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