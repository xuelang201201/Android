package tarena.day1202;

public class Test1 {
	public static void main(String[] args) {
		char[] a = {'h', 'e', 'l', 'l', 'o'};
		String s1 = new String(a);//新分配内存
		
		String s2 = "hello";//常量池新分配内存
		String s3 = "hello";//获取常量池存在的对象
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s1 == s2);
		System.out.println(s2 == s3);
		System.out.println(s1.equals(s2));
	}
}
