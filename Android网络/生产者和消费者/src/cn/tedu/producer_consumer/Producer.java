package cn.tedu.producer_consumer;

import java.util.Random;

public class Producer implements Runnable {
	private Shop shop;
	
	public Producer(Shop shop) {
		super();
		this.shop = shop;
	}



	@Override
	public void run() {
		Random random = new Random();
		while (true) {
			try {
				shop.produce();
				
				Thread.sleep(100 + random.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
