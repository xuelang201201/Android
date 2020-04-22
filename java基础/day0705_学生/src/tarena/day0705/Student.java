package tarena.day0705;

public class Student {
	int id;         //学号
	String name;    //姓名
	String gender;  //性别
	int age;        //年龄
	
	public Student() {
		
	}
	public Student(int id, String name) {
		this(id, name, null);
	}
	public Student(int id, String name, String gender) {
		//从一个构造方法调用
		//从另一个重载的构造方法
		this(id, name, gender, 0);
	}
	public Student(int id, String name, String gender, int age) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	
	public String toString() {
		return
				"\n学号："+id+
				"\n姓名："+name+
				"\n性别："+gender+
				"\n年龄："+age;
	}
}
