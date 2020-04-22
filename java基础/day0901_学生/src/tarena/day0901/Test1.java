package tarena.day0901;

public class Test1 {
	public static void main(String[] args) {
		Student s = new Student();
		
		//给学生s的四个属性设置值
		s.setId(9527);
		s.setName("张三");
		s.setAge(22);
		s.setGender("男");
			
		//获取学生s的四个属性打印
		System.out.println("学号：" + s.getId());
		System.out.println("姓名：" + s.getName());
		System.out.println("年龄：" + s.getAge());
		System.out.println("性别：" + s.getGender());
	}
}
