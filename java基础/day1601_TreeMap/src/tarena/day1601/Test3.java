package tarena.day1601;

import java.util.Comparator;
import java.util.TreeMap;

/*
 * TreeMap���Ƚϴ�С������֮����
 * TreeMap��ӱȽ���Comparator 
 */
public class Test3 {
	public static void main(String[] args) {
		Point a = new Point(1, 17);
		Point b = new Point(2, 23);
		
		
		Comparator<Point> cmp = new Comparator<Point>() {//TreeMap��ӱȽ���
			/*
			 * o1��o2�ȴ�С��
			 * o1��������
			 * o1С��������
			 * ��ͬ��0
			 */
			@Override
			public int compare(Point o1, Point o2) {
				return o2.getY() - o1.getY();
			}
		};
		
		
		TreeMap<Point, String> map = new TreeMap<>(cmp);//����Ƚ������ñȽ����Ƚϴ�С
		
		map.put(a, "1.7��");
		map.put(b, "2.3��");
		
		System.out.println(map);
	}
}
