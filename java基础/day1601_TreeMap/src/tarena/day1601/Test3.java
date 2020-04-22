package tarena.day1601;

import java.util.Comparator;
import java.util.TreeMap;

/*
 * TreeMap键比较大小，方法之二：
 * TreeMap外接比较器Comparator 
 */
public class Test3 {
	public static void main(String[] args) {
		Point a = new Point(1, 17);
		Point b = new Point(2, 23);
		
		
		Comparator<Point> cmp = new Comparator<Point>() {//TreeMap外接比较器
			/*
			 * o1和o2比大小，
			 * o1大，正数，
			 * o1小，负数，
			 * 相同，0
			 */
			@Override
			public int compare(Point o1, Point o2) {
				return o2.getY() - o1.getY();
			}
		};
		
		
		TreeMap<Point, String> map = new TreeMap<>(cmp);//插入比较器，用比较器比较大小
		
		map.put(a, "1.7亿");
		map.put(b, "2.3亿");
		
		System.out.println(map);
	}
}
