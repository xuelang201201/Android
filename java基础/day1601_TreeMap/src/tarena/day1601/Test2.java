package tarena.day1601;

import java.util.TreeMap;

/* TreeMap���Ƚϴ�С������֮һ��
 * ʵ��Comparable�ӿ�
 */
public class Test2 {
	public static void main(String[] args) {
		Point a = new Point(1, 17);
		Point b = new Point(2, 23);
		
		TreeMap<Point, String> map = new TreeMap<>();//����TreeMap����
		map.put(a, "1.7��");//�����ֵ������
		map.put(b, "2.3��");
		
		System.out.println(map);
	}
}
