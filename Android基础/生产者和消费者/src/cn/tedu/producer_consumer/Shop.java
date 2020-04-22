package cn.tedu.producer_consumer;

public class Shop {
	private static final int MAX_SIZE = 10;
	
	/**
	 * ���
	 */
	private int currentSize = 0;
	
	public synchronized void produce() throws InterruptedException {
		while (currentSize >= MAX_SIZE) {
			System.out.println("���̼ҡ����ﵽ����" + currentSize + "�� ��ͣ������");
			wait();
		}
		
		currentSize++;
		System.out.println("���̼ҡ���ӿ�棬��ǰΪ" + currentSize);
		notifyAll();
	}
	
	public synchronized void consume() throws InterruptedException {
		while (currentSize <= 0) {
			System.out.println("���˿͡����Ϊ" + currentSize + "�� ��ͣ���ѣ�");
			wait();
		}
		
		currentSize--;
		System.out.println("���˿͡�������Ʒ�����Ѻ���Ϊ" + currentSize);
		notifyAll();
	}
}
