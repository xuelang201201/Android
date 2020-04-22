package cn.tedu.deadlock_thread;

public class DeadLockThread extends Thread {
	public static Object key_1 = new Object();
	public static Object key_2 = new Object();
	public int userId;
	
	public DeadLockThread(int userId) {
		this.userId = userId;
	}

	@Override
	public void run() {
		if (userId == 1) {
			synchronized (key_1) {
				System.out.println("�û�1������key_1");
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				synchronized (key_2) {
					System.out.println("�û�1������key_2");
				}
			}
		} else {
			synchronized (key_2) {
				System.out.println("�û�2������key_2");
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				synchronized (key_1) {
					System.out.println("�û�2������key_1");
				}
			}
		}
	}
}
