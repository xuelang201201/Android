package tarena.day1504;
/*
 * �ַ����е��ַ�ͳ��
 */
import java.util.HashMap;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		System.out.println("�����ַ�����");
		String s = new Scanner(System.in).nextLine();
		
		/*
		 * 1.�½�HashMap<Character, Integer>
		 *   ���󣬸�������map
		 * 2.ѭ�� i �� 0 �� <s.length() ����
		 *     3.ȡ�� i λ���ַ�����c
		 *     4.��map ȡ���ַ�c��Ӧ�ļ���ֵ
		 *       ��������Integer count
		 *     5.���count == null
		 *         6.���ַ�c�ͼ���ֵ1����map
		 *     7.����
		 *         8.���ַ�c�ͼ���ֵcount+1����map
		 * 9.��ӡmap
		 */
		
		HashMap<Character, Integer> map = new HashMap<>();
		
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			Integer count = map.get(c);
			if(count == null) {
				map.put(c, 1);
			} else {
				map.put(c, count+1);
			}
		}
		System.out.println(map);
	}
}
