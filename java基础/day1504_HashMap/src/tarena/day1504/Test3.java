package tarena.day1504;

import java.util.HashMap;

public class Test3 {
	public static void main(String[] args) {
		Student s1 = new Student(6,"张三","男",22);
		Student s2 = new Student(6,"张三","男",22);
		
		HashMap<Student, Integer> map = new HashMap<>();
		
		map.put(s1, 96);
		//同一个学生，覆盖成绩
		map.put(s2, 91);
		System.out.println(map);
		
		/*
		 * 如果两个对象哈希值相同，才能计算出相同的下标值
		 */
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		
		/*
		 * 如果哈希值相同，equals()也相等，才能覆盖
		 */
		System.out.println(s1.equals(s2));
	}
}
