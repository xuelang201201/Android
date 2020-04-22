package cn.tedu.producer_consumer;

public class Shop {
	private static final int MAX_SIZE = 10;
	
	/**
	 * 库存
	 */
	private int currentSize = 0;
	
	public synchronized void produce() throws InterruptedException {
		while (currentSize >= MAX_SIZE) {
			System.out.println("【商家】库存达到上限" + currentSize + "， 暂停生产！");
			wait();
		}
		
		currentSize++;
		System.out.println("【商家】添加库存，当前为" + currentSize);
		notifyAll();
	}
	
	public synchronized void consume() throws InterruptedException {
		while (currentSize <= 0) {
			System.out.println("【顾客】库存为" + currentSize + "， 暂停消费！");
			wait();
		}
		
		currentSize--;
		System.out.println("【顾客】消费商品，消费后库存为" + currentSize);
		notifyAll();
	}
}
