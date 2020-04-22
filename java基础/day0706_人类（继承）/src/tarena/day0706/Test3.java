package tarena.day0706;

public class Test3 {
	public static void main(String[] args) {
		Person p = new Person("a","b",12);
		Student s = new Student("c","d",13,"e");
		Employee e = new Employee();
		
		f(p);
		f(s);
		f(e);
	}
	private static void f(Person p) {
		System.out.println(p.toString());
	}
}


