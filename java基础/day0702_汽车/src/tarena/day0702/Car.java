package tarena.day0702;
/*
 * ��װ
 * 
 * ��������ص����ݡ��߼�������룬
 * ��װ��һ�������
 */
public class Car {
	String brand; //Ʒ��
	String color; //��ɫ
	int speed;    //ʱ��
	
	//���췽��
	public Car(
			String brand, //�ֲ������������Ա����ͬ��
			String color, 
			int speed) {
		//�����ͬ���ֲ�������������this.xxx �����ó�Ա����
		//this ��ʾ��ǰ���ڴ����Ķ���
		this.brand = brand;
		this.color = color;
		this.speed = speed;
	}
	
	public void go() {
		System.out.println(color+"��"+brand+"��������ʱ��"+speed+"ǰ��");
	}
	public void stop() {
		System.out.println(color+"��"+brand+"����ֹͣ");
	}
}
