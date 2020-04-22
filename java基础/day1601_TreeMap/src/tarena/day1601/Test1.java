package tarena.day1601;

import java.util.TreeMap;

public class Test1 {
	public static void main(String[] args) {
		TreeMap<Integer, String> map = new TreeMap<>();
		
		map.put(88888, "8");
		map.put(22222, "2");
		map.put(55555, "5");
		map.put(11111, "1");
		map.put(99999, "9");
		map.put(66666, "6");
		map.put(33333, "3");

		System.out.println(map.size());
		System.out.println(map);
		System.out.println(map.get(55555));
		System.out.println(map.containsKey(99999));
		System.out.println(map.containsValue("2"));
		System.out.println(map.remove(33333));
		System.out.println(map);
	}
}
