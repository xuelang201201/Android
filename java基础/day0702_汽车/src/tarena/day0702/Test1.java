package tarena.day0702;

public class Test1 {
	public static void main(String[] args) {
		//���½�Car �����ٽ�������ڴ��ַ��������a
		Car a = new Car("������","��ɫ",230);
		Car b = new Car("��ʮ��","��ɫ",30);
		
		//������a�ҵ���һ����������,�����������Ա���
		/*a.brand = "������";
		a.color = "��ɫ";
		a.speed = 230;
		
		b.brand = "��ʮ��";
		b.color = "��ɫ";
		b.speed = 30;*/
		
		//������a�ҵ���һ����������,����ִ��go() �����Ĵ���
		a.go();
		a.stop();
		b.go();
		b.stop();
	}
}
