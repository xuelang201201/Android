package tarena.day0503;

import java.util.Arrays;
import java.util.Random;

public class Test1 {
	public static void main(String[] args) {
		int[] a = new int[5];
		for(int i=0; i<a.length; i++) {
			a[i] = new Random().nextInt(100);
		}
		
		/* index = 0 ����0λ�����
		 * 
		 * index
		 *  |
		 * [2,6,1,8,3]
		 *    i
		 * 
		 * 1.�����±����index=0
		 * 2.ѭ�� i �� 1 �� <a.length ����
		 *     3.���iλ�õ�ֵ > indexλ�õ�ֵ
		 *         4.index = i
		 *         
		 * 5.��ʾ index �±�
		 */
		System.out.println(Arrays.toString(a));
		
		int index = 0;
		for(int i=1; i<a.length; i++) { 
			//�ҵ����������ֵ���ڵ�λ��
			if(a[i] > a[index]) {
				index = i;
			}
		}
		System.out.println(index);		
	}
}
