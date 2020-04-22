package tarena.day1601;

import java.util.TreeMap;

/* TreeMap键比较大小，方法之一：
 * 实现Comparable接口
 */
public class Test2 {
	public static void main(String[] args) {
		Point a = new Point(1, 17);
		Point b = new Point(2, 23);
		
		TreeMap<Point, String> map = new TreeMap<>();//创建TreeMap对象
		map.put(a, "1.7亿");//放入键值对数据
		map.put(b, "2.3亿");
		
		System.out.println(map);
	}
}
