package cn.tedu.thread_safety;

public class Test {
	public static void main(String[] args) {
		
		UserThread t1 = new UserThread("���ҡ�");
		UserThread t2 = new UserThread("�����衿");
		
		t1.start();
		t2.start();
	}
}
