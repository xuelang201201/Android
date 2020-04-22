package cn.tedu.thread_safety;

public class Test {
	public static void main(String[] args) {
		
		UserThread t1 = new UserThread("¡¾ÎÒ¡¿");
		UserThread t2 = new UserThread("¡¾ÂèÂè¡¿");
		
		t1.start();
		t2.start();
	}
}
