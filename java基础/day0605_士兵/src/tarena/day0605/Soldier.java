package tarena.day0605;

import java.util.Random;

/*
 * ��װ��
 * 
 * ��ʿ����ص��������ݡ��߼����㷽����
 * ��װ��һ�������
 */
public class Soldier {
	//���Ա�������Ա����
	int id;//��Ա������Ĭ��ֵ 0
	int blood = 100;
	
	//���췽��
	public Soldier() {
		System.out.println("Soldier() ���췽��ִ��");
	}
	
	//����
	public void go() {
		System.out.println(id+"��ʿ��ǰ��");
	}
	public void standby() {
		System.out.println(id+"��ʿ������");
	}
	public void attack() {
		if(blood <= 0) {
			System.out.println("����"+id+"��ʿ����ʬ��");
			return;
		}
		System.out.println(id+"��ʿ������");
		
		int b = new Random().nextInt(10);
		if(blood < b) {//Ѫ������
			b = blood;//�ж��ټ�����
		}
		blood -= b;
		System.out.println("��Ѫ��" + b);
		System.out.println("Ѫ����" + blood);
		
		if(blood == 0) {
			System.out.println(id+"��ʿ��������");
		}
	}
}
