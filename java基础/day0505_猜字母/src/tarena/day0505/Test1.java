package tarena.day0505;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * ׼��26����д��ĸ����
		 * 1.�½�char[] ���鸳��chars
		 * 2.�������飬�����д��ĸA-Z
		 *     0       1      2
		 *    'A'+0   'A'+1  'A'+2
		 */

		char[] chars = new char[26];
		for(int i=0; i<26; i++) {
			chars[i] = (char) ('A'+i);
		}
		//System.out.println(Arrays.toString(chars));

		/*
		 * 1.ѭ��i��0��<5����
		 *     2.��[i,26)��Χ�����ѡ�±�j
		 *           i+[0,26-i)
		 *     3.����chars����i��jλ�õ�ֵ
		 *     
		 * 4.��chars�����ȡǰ5��ֵ���һ�������鸳������r
		 */

		for(int i=0; i<5; i++) {
			int j = i + new Random().nextInt(26-i);

			char c = chars[i];
			chars[i] = chars[j];
			chars[j] = c;
		}

		//����ǰ5��λ�ó�������
		char[] r = Arrays.copyOf(chars, 5);
		System.out.println(Arrays.toString(r));

		System.out.println("�Ѿ�����5�����ظ��Ĵ�д��ĸ");
		System.out.println("�����5����ĸ");
		/*
		 * 1.��ѭ��
		 *     2.��ʾ���£���
		 *     3.���������ַ�������s
		 *     4.�ַ���sת��char[]���鸳��c
		 *     5.�Ƚ� c �� r ����õ��ȽϽ������result
		 *     6.��ʾresult
		 *     7.���result ��"5A0B"
		 *         8.break
		 */
		while(true) {
			System.out.print("�£�");
			String s = new Scanner(System.in).nextLine();
			//s.toCharArray();
			//���ַ���sת��char[] ����
			char[] c = s.toCharArray();
			String result = compare(c, r);
			System.out.println(result);
			if(result.equals("5A0B")) {
				break;
			}
		}
	}//main����

	static String compare(char[] c, char[] r) {

		/* a=0, b=0
		 * 
		 * r TKBNW
		 *    j
		 * 
		 * c KTALW
		 *   i
		 *   
		 * 1.�����������a=0,b=0
		 * 2.ѭ��i ����c����
		 *     3.ѭ�� j ����r����
		 *         4.���r[j]==c[i]
		 *             5.���i==j, a++
		 *             6.����, b++
		 * 7.����a+"A"+b+"B"
		 */

		int a = 0;
		int b = 0;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(c[i] == r[j]) {
					if(i == j) {
						a++;
					} else {
						b++;
					}
				}
			}
		}
		return a+"A"+b+"B";
	}
}
