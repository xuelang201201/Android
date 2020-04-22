package tarena.day0706;

public class Test1 {
	public static void main(String[] args) {
		Person p = new Person("张三", "男", 20);
		Student s = new Student();
		Employee e = new Employee();
		s.name = "李四";
		s.gender = "女";
		s.age = 22;
		s.school = "Tarena";
		
		e.name = "赵六";
		e.gender = "男";
		e.age = 24;
		e.company = "Tarena";
		
		System.out.println(p.toString());
		System.out.println(s.toString());//先找子类再找父类，子类有就执行子类的toString();
		System.out.println(e.toString());//如果子类没有toSring(),就执行父类的toString();
		
	}
}
