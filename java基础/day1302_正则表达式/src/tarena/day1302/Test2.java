package tarena.day1302;

import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		System.out.println("输入固定电话：");
		String s = new Scanner(System.in).nextLine();
		/*
		 * 1234567
		 * 12345678
		 * 010-1234567
		 * 0101-1234567
		 * (010)12345678
		 * (0101)12345678
		 * 
		 * \d{7,8}
		 * ()?\d{7,8}
		 * (|)?\d{7,8}
		 * (\d{3,4}-|)?\d{7,8}
		 * (\d{3,4}-|\(\))?\d{7,8}
		 * (\d{3,4}-|\(\d{3,4}\))?\d{7,8}
		 * 
		 * (\\d{3,4}-|\\(\\d{3,4}\\))?\\d{7,8}
		 */
		String regex = "(\\d{3,4}-|\\(\\d{3,4}\\))?\\d{7,8}";
		if(s.matches(regex)) {
			System.out.println("格式正确");
		} else {
			System.out.println("格式错误");
		}
	}
}
