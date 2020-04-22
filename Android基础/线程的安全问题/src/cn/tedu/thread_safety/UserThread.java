package cn.tedu.thread_safety;

public class UserThread extends Thread {

	public UserThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		getMoney();
	}

	public void getMoney() {
		System.out.println(getName() + "当前余额："+Bank.money);

		synchronized (Bank.lock) {

			if (Bank.money >= 4000) {
				System.out.println(getName() + "余额超过4000块，执行取款... ...");
				Bank.money -= 4000;
				System.out.println(getName() + "取款成功！");
			} else {
				System.out.println(getName() + "余额不足，取款失败！");
			}
		}
	}
}
