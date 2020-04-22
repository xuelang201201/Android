package tarena.day1202;

import java.util.Scanner;

public class Test4 {
	public static void main(String[] args) {
		Student s1 = new Student(9527,"张三","男",22);
		Student s2 = new Student(9528,"李四","女",21);
		Student s3 = new Student(9527,"张三","男",22);
		
		System.out.println(s1.toString());
		System.out.println(s2.toString());
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println(s1.equals(s2));
		System.out.println(s1.equals(s3));
	}
}
