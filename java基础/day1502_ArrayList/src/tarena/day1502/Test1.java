package tarena.day1502;

import java.util.ArrayList;
import java.util.Iterator;

public class Test1 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(888);
		list.add(222);
		list.add(555);
		list.add(333);
		list.add(444);
		list.add(444);
		list.add(null);
		
		System.out.println(list.size()); //长度是10，元素个数是7
		System.out.println(list);
		list.add(6, 666);
		System.out.println(list);
		System.out.println(list.get(0));
		System.out.println(list.get(list.size()-1));
		System.out.println(list.contains(333));
		System.out.println(list.set(1, 111));
		System.out.println(list);
		System.out.println(list.remove(2));
		System.out.println(list);
		System.out.println(list.remove(Integer.valueOf(444)));
		System.out.println(list);
		//下标遍历
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}

		//迭代器遍历
		/*for循环写迭代器*/
		for(Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
			Integer i = it.next();
			System.out.println(i);
		}
		
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			Integer i = it.next();
			System.out.println(i);
		}
	}
}
