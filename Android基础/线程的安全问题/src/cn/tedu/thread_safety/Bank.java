package cn.tedu.thread_safety;

public class Bank {
	
	public static Object lock = new Object();
	
	public static int money = 5000;
}
