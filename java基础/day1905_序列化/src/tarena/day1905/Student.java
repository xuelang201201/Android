package tarena.day1905;

import java.io.Serializable;

/*
 * Serializable 接口是空接口
 * 成为标识接口，
 * 
 * 标识 Student 类型可以序列化
 */
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String gender;
	private int age;
	private static int height;
	private transient int weight;
	
	public Student() {
		super();
	}

	public Student(int id, String name, String gender, int age, int height,
			int weight) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return
				"\n学号：" + id + "\n姓名：" + name + "\n性别：" + gender
				+ "\n年龄：" + age + "\n身高：" + height + "\n体重：" + weight;
	}
}
