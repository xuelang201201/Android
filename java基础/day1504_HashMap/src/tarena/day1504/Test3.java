package tarena.day1504;

import java.util.HashMap;

public class Test3 {
	public static void main(String[] args) {
		Student s1 = new Student(6,"����","��",22);
		Student s2 = new Student(6,"����","��",22);
		
		HashMap<Student, Integer> map = new HashMap<>();
		
		map.put(s1, 96);
		//ͬһ��ѧ�������ǳɼ�
		map.put(s2, 91);
		System.out.println(map);
		
		/*
		 * ������������ϣֵ��ͬ�����ܼ������ͬ���±�ֵ
		 */
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		
		/*
		 * �����ϣֵ��ͬ��equals()Ҳ��ȣ����ܸ���
		 */
		System.out.println(s1.equals(s2));
	}
}
