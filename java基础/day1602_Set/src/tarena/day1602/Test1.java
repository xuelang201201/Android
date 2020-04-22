package tarena.day1602;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Test1 {
	public static void main(String[] args) {
		Set<String> set;
		
		//set = new HashSet<>();
		set = new TreeSet<>();
		
		set.add("fff");
		set.add("hhh");
		set.add("yyy");
		set.add("qqq");
		set.add("aaa");
		set.add("zzz");
		
		System.out.println(set.size());
		System.out.println(set);
		System.out.println(set.contains("qqq"));
		System.out.println(set.remove("fff"));
		System.out.println(set);
		
		//迭代器遍历
		
		for(Iterator<String> it = set.iterator(); it.hasNext(); ) {
			String s = it.next();  
			System.out.println(s);
		}
		
		Iterator<String> it = set.iterator(); //创建迭代器对象
		while(it.hasNext()) { //当还有
			String s = it.next();  //取下一个
			System.out.println(s);
		}
	}
}
