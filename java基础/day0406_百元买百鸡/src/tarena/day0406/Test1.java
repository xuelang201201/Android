package tarena.day0406;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("����5Ԫ1ֻ");
		System.out.println("ĸ��3Ԫ1ֻ");
		System.out.println("С��1Ԫ3ֻ");
		System.out.println("��Ԫ��ټ�");
		System.out.println("---------------------");
		/*
		 * 1.ѭ��g �� 0 �� <=20 ����
		 *     2.100Ԫ��g ֻ����ʣ���Ǯ����money
		 *     3.money��ĸ���������������max
		 *     4.ѭ�� m �� 0 �� <=max ����
		 *         5.money�� m ֻĸ����ʣ���Ǯ
		 *           ȫ����С������������x
		 *         6.��� g+m+x==100
		 *         	   7.��ʾ g,m,x �������
		 */

		for (int g=0; g<=20; g++) {
			int money = 100 - g * 5;
			int max = money / 3;
			for (int m=0; m<=max; m++) {
				int x = (money - m * 3) * 3;
				if(g+m+x==100) {
					System.out.println(
							"����"+g+"ֻ��"+
							"ĸ��"+m+"ֻ��"+
							"С��"+x+"ֻ");
				}
			}
		}
	}
}
