package cn.tedu.test;

public class WorkThread extends Thread {

	@Override
	public void run() {
		for(int i = 0; i < 99; i++) {
			System.out.println("WorkThread i = " + i);
		}
	}
}
