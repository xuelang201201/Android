package tarena.day0705;

public class Student {
	int id;         //ѧ��
	String name;    //����
	String gender;  //�Ա�
	int age;        //����
	
	public Student() {
		
	}
	public Student(int id, String name) {
		this(id, name, null);
	}
	public Student(int id, String name, String gender) {
		//��һ�����췽������
		//����һ�����صĹ��췽��
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
				"\nѧ�ţ�"+id+
				"\n������"+name+
				"\n�Ա�"+gender+
				"\n���䣺"+age;
	}
}
