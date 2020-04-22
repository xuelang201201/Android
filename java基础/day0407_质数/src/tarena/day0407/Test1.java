package tarena.day0407;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("��������n��n������������");
		int n = new Scanner(System.in).nextInt();
		//n��ֵ���ݵ�zhiShu()�����������㣬
		//���õ����ķ���ֵ��������count
		int count = zhiShu(n);
		System.out.println(count);
	}//main����

	//1.�������� 2.������ 3.�����б�
	static int zhiShu(int n) {
		/*
		 * 1.���������� count=1 (��֪��һ��������2)
		 * 2.ѭ��i �� 3 �� <=n ����
		 *     3.�� i ���� +1 �������� max
		 *     4.ѭ��j �� 2 ��   <= max ����
		 *         5.���i�ܱ�j����
		 *             6.����iѭ����һ�֣��ж���һ��iֵ
		 *     7.��������count++
		 *     
		 * 8.����count��ֵ
		 */

		int count = 1;
		outer://ѭ������
			for(int i=3; i<=n; i++) {
				double max = Math.sqrt(i) + 1;
				for(int j=2; j<max; j++) {
					if(i%j == 0) {
						continue outer;
					}
				}
				count++; //i������
			}
		return count;
	}
}
