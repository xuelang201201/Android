package cn.tedu.test;

public class Worker implements Runnable {

	@Override
	public void run() {
		for(int i = 0; i < 99; i++) {
			System.out.println("Worker i = " + i);
		}
	}
}
