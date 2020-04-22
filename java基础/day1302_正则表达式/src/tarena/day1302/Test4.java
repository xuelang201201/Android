package tarena.day1302;

import java.util.Scanner;

public class Test4 {
	public static void main(String[] args) {
		System.out.println("输入关键词列表，用逗号、分号或空格隔开：");
		String s = new Scanner(System.in).nextLine();
		
		/*
		 * sd,e;tr re,,,we,,; ;,,,;;;,  ;sdfex
		 */
		
		String regex = "[,; ]+";
		
		String[] a = s.split(regex);
		for(int i=0; i<a.length; i++) {
			System.out.println(a[i]);
		}
	}
}
