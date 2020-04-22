package cn.tedu.deadlock_thread;

public class Test {
	public static void main(String[] args) {
		DeadLockThread t1 = new DeadLockThread(1);
		DeadLockThread t2 = new DeadLockThread(2);
		
		t1.start();
		t2.start();
	}
}
