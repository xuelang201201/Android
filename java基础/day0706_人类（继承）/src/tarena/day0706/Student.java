package tarena.day0706;

public class Student extends Person {
	//构造方法不继承
	String school;
	public Student() {
		//super();//默认的，不写也执行
		System.out.println("Student()");
	}
	public Student(String name, String gender, int age, String school) {
		//super();
		super(name, gender, age);  //调用父类构造函数
		this.school = school;
		System.out.println("Student(1,2,3)");
	}
	/*
	 * 重写
	 * 从父类继承的方法，不满足子类的需要，
	 * 可以在子类中，重新编写这个方法
	 */
	public String toString() {
		return
				super.toString() +
				"\n学校："+school;
	}
	
}
