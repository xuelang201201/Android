package tarena.day0504;

import java.util.Arrays;
import java.util.Random;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * ׼�������������������
		 * 1.�½�33�����ȵĺ������鸳��r
		 * 2.�½�16�����ȵ��������鸳��b
		 * 3.���������������������1��33
		 * 3.����������������������1��16
		 */
		
		int[] r = new int[33];
		int[] b = new int[16];
		for(int i=0; i<33; i++) {
			r[i] = i + 1;
		}
		for(int i=0; i<16; i++) {
			b[i] = i + 1;
		}
		System.out.println(Arrays.toString(r));
		System.out.println(Arrays.toString(b));
		
		/*
		 * ѡ��6�����ظ��ĺ���
		 * 1.�½�6�����ȵ����鸳��red
		 * 2.�½�33�����ȵı������flag
		 * 3.ѭ��i �� 0 �� <6 ����
		 *     4.��[0,33)��Χ�������ѡһ���±�ֵj
		 *     5.�� flag[j] ��true���ص�4
		 *     
		 *     6.��r����jλ�õ�ֵ����red����iλ��
		 *     7.flag[j]��Ϊtrue
		 */
		
		//����
		int[] red = new int[6];
		boolean[] flag = new boolean[33];
		for(int i=0; i<6; i++) {
			int j;
			do {
				j = new Random().nextInt(33);
			} while(flag[j]);
			
			red[i] = r[j];
			flag[j] = true;
		}
		
		//����
		int blue = b[new Random().nextInt(16)];
		
		//������
		System.out.println("����");
		System.out.println(Arrays.toString(red));
		System.out.println("����");
		System.out.println(blue);
	}
}
