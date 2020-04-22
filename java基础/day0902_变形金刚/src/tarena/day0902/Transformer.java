package tarena.day0902;

public class Transformer {
	/* ��һ������ʹ�ýӿڣ�ֻ��Ҫ����һ���ӿ����͵ĳ�Ա�������ɡ�
	 * 
	 * Weapon�ӿ��Ѿ���Sword��AK47��Lyb��ʹ�ã�
	 * ʵ���˽ӿ��ж���ĳ��󷽷���
	 * ��ô��Transformer����ʹ��Weapon�ӿڣ�
	 * ֻ��Ҫ����һ��Weapon���͵ĳ�Ա�������ɡ�
	 */
	
	private Weapon w;/*Ĭ��ֵ��null��
	                                          ʵ���Ͼ��൱�ڶ�����һ��Weapon���͵Ŀ����ã�
	                                          ��ʾWeapon�ӿ��ϻ�û�н���������*/
	
	/* ���ղ����ı��� w �����Weapon�����ͣ�
	 * ��Ϊ�˽��մ������������������
	 * �����ͣ�Sword,AK47,Lyb
	 */
	public void setWeapon(Weapon w) { 
		this.w = w;
	}
	
	public void attack() {//�ñ��ν�ս���
		if(w == null) {//�൱��û�н����κε�����
			System.out.println("����ҧ");
			return;
		} 
		
		//����������
		String type = "";
		switch(w.getType()) {//��w�ӿڷ���getType()����ָ��
		case Weapon.TYPE_COLD://����д1������д�����Ķ����
			type = "�����";
			break;
		case Weapon.TYPE_HEAT://����д2
			type = "�ȱ���";
			break;
		case Weapon.TYPE_NUCLEAR://����д3
			type = "������";
			break;
		}
		
		//��w�ӿڷ���getName()����ָ��
		System.out.println("ʹ��" + type + w.getName() + "����");
		
		//��w�ӿڷ���kill()����ָ��
		//�ɾ�������������ִ���������
		w.kill();
	}
}
