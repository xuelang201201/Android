package cn.tedu.producer_consumer;

public class Test {
	public static void main(String[] args) {
		Shop shop = new Shop();
		Producer producer = new Producer(shop);
		Consumer consumer = new Consumer(shop);
		
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		
		t1.start();
		t2.start();
	}
}
