package tarena.day0605;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * �Ƚ�һ�� Soldier ����
		 * �ٽ�������ڴ��ַ�������� s1 
		 */
		Soldier s1 = new Soldier();
		Soldier s2 = new Soldier();
		/*
		 * ��s1���ʵ�һ��ʿ�������ڴ�ռ�
		 * �е�id����,��id��ֵ
		 */
		s1.id = 9527;
		s2.id = 9528;
		/*
		 * ��s1���ʵ�һ��ʿ������
		 * �������ʿ��ִ�� go() �����еĴ���
		 */
		s1.go();
		s2.go();
		s2.standby();
		
		s1.attack();

	}
}
