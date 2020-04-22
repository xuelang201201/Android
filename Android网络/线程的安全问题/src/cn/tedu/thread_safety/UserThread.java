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
		System.out.println(getName() + "��ǰ��"+Bank.money);

		synchronized (Bank.lock) {

			if (Bank.money >= 4000) {
				System.out.println(getName() + "����4000�飬ִ��ȡ��... ...");
				Bank.money -= 4000;
				System.out.println(getName() + "ȡ��ɹ���");
			} else {
				System.out.println(getName() + "���㣬ȡ��ʧ�ܣ�");
			}
		}
	}
}
