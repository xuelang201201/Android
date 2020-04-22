package tarena.day1504;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Test1 {
	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		
		map.put(9527, "唐伯虎");
		map.put(9528, "华夫人");
		map.put(9529, "祝枝山");
		map.put(9530, "石榴");
		map.put(9531, "秋香");
		map.put(9532, "小强");
		map.put(9533, "旺财");
		map.put(9530, "如花");
		map.put(null, "---");
		System.out.println(map.size());
		System.out.println(map);
		System.out.println(map.get(9527));
		System.out.println(map.remove(9531));
		System.out.println(map.containsKey(9533));
		System.out.println(map.containsValue("如花"));
		
		
		Set<Integer> keys = map.keySet();
		Set<Entry<Integer, String>> entries = map.entrySet();
		Collection<String> values = map.values();
		
		System.out.println("==================================");
		
		for(Iterator<Integer> it = keys.iterator(); it.hasNext(); ) {
			Integer k = it.next();
			System.out.println(k);
		}
		
		System.out.println("==================================");
		
		for(Iterator<Entry<Integer, String>> it = entries.iterator(); it.hasNext(); ) {
			Entry<Integer, String> e = it.next();
			System.out.println(e);
		}
		
		System.out.println("==================================");

		for(Iterator<String> it = values.iterator(); it.hasNext(); ) {
			String v = it.next();
			System.out.println(v);
		}
		
		//for-each 简化
		System.out.println("==================================");
		for(Entry<Integer, String> e : entries) {
			Integer k = e.getKey();
			String v = e.getValue();
			System.out.println(k+"="+v);
		}
		
	}
}
