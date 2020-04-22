package tarena.day1701;

import java.util.Scanner;

public class Test4 {
	public static void main(String[] args) {
		System.out.println("用户名：");
		String n = new Scanner(System.in).nextLine();
		System.out.println("密码：");
		String p = new Scanner(System.in).nextLine();
		
		try{
			login(n, p);
			System.out.println("欢迎登陆");
		} catch (UsernameNotFoundException e) {
			System.out.println("用户名错误");
		} catch (WrongPasswordException e) {
			System.out.println("密码错误");
		}
	}

	private static void login(String n, String p) throws UsernameNotFoundException, WrongPasswordException {
		if(! n.equals("abc")) {
			throw new UsernameNotFoundException();
		}
		
		if(! p.equals("123")) {
			throw new WrongPasswordException();
		}
	}
}
