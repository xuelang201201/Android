package tarena.day0902;

public class Test1 {
	public static void main(String[] args) {
		//�����ĸ�����
		Sword s = new Sword();//��
		AK47 a = new AK47();//ǹ
		Lyb l = new Lyb();//������
		
		Transformer t = new Transformer();//���ν��
		
		t.attack();//δ��������������ҧ
		
		/*
		 * ��������s��
		 * ����setWeapon()������
		 * �ڷ����У�Ϊ��Ա���� w ��ֵ
		 * 
		 * t.w = s;
		 */
		t.setWeapon(s);//����һ�ѽ�
		t.attack();//�ٽ���
		t.setWeapon(a);
		t.attack();
		t.setWeapon(l);
		t.attack();
	}
}
