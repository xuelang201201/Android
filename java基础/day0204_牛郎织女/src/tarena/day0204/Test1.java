package tarena.day0204;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("������� 16.4 ����");
		System.out.println("���� 299792458 ��/��");
		System.out.println("ϲȵ�� 0.46 ��");
		System.out.println("ţ��֯Ů��ᣬ��Ҫ����ֻϲȵ");

		/* 1.��һ��������ף����� ly
		 * 2.��16.4��������ף�����d
		 * 3.��ϲȵ��������r
		 * 4.��ϲȵ��������ȡ��������n
		 * 5.��ʾn��ֵ
		 */
		long ly = 299792458L*60*60*24*365;
		double d = ly * 16.4;
		double r = d / 0.46;
		/* Math.ceil(r) �컨�壬����ȡ��
		 * ȡ���Ľ����double���ͣ�
		 * ǿ��ת�ͳ�long��������
		 */
		long n = (long) Math.ceil(r);
		System.out.println(n);
	}
}
