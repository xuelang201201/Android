package tarena.day1403;

import java.util.Iterator;
import java.util.LinkedList;

public class Test1 {
	public static void main(String[] args) {
		//<> ����
		//��֧�ֻ�������
		//���Ƽ����д�ŵ���������
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
		
		//�±���������е�����
		//˫�������±����Ч�ʵ�
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		//˫����������������Ч�ʸ�
		Iterator<String> it = list.iterator(); //��������������
		while(it.hasNext()) { //������
			String s = it.next(); //ȡ��һ��
			System.out.println(s);
		}
	}
}
