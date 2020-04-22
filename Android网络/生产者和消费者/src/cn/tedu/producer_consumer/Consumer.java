package cn.tedu.producer_consumer;

import java.util.Random;

public class Consumer implements Runnable {
	private Shop shop;
	
	public Consumer(Shop shop) {
		super();
		this.shop = shop;
	}



	@Override
	public void run() {
		Random random = new Random();
		while (true) {
			try {
				shop.consume();
				
				Thread.sleep(100 + random.nextInt(3500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
