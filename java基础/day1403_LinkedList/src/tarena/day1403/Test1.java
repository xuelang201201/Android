package tarena.day1403;

import java.util.Iterator;
import java.util.LinkedList;

public class Test1 {
	public static void main(String[] args) {
		//<> 泛型
		//不支持基本类型
		//限制集合中存放的数据类型
		LinkedList<String> list = new LinkedList<>();
		
		list.add("ggg");
		list.add("aaa");
		list.add("uuu");
		list.add("aaa");
		list.add(null);
		System.out.println(list.size());
		System.out.println(list);
		list.add(3,"333");
		list.addFirst(">>>");
		list.addLast("<<<");
		System.out.println(list);
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		System.out.println(list.removeFirst());
		System.out.println(list.removeLast());
		System.out.println(list);
		System.out.println(list.get(0));
		System.out.println(list.get(list.size()-1));
		System.out.println(list.contains("aaa"));
		System.out.println(list.set(5, "555"));
		System.out.println(list.remove(2));
		System.out.println(list.remove("aaa"));
		
		//下标遍历集合中的数据
		//双向链表，下标遍历效率低
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		//双向链表，迭代器遍历效率高
		Iterator<String> it = list.iterator(); //创建迭代器对象
		while(it.hasNext()) { //当还有
			String s = it.next(); //取下一条
			System.out.println(s);
		}
	}
}
