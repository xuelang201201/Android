package tarena.day0706;

public class Test1 {
	public static void main(String[] args) {
		Person p = new Person("����", "��", 20);
		Student s = new Student();
		Employee e = new Employee();
		s.name = "����";
		s.gender = "Ů";
		s.age = 22;
		s.school = "Tarena";
		
		e.name = "����";
		e.gender = "��";
		e.age = 24;
		e.company = "Tarena";
		
		System.out.println(p.toString());
		System.out.println(s.toString());//�����������Ҹ��࣬�����о�ִ�������toString();
		System.out.println(e.toString());//�������û��toSring(),��ִ�и����toString();
		
	}
}
