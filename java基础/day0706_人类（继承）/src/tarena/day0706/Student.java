package tarena.day0706;

public class Student extends Person {
	//���췽�����̳�
	String school;
	public Student() {
		//super();//Ĭ�ϵģ���дҲִ��
		System.out.println("Student()");
	}
	public Student(String name, String gender, int age, String school) {
		//super();
		super(name, gender, age);  //���ø��๹�캯��
		this.school = school;
		System.out.println("Student(1,2,3)");
	}
	/*
	 * ��д
	 * �Ӹ���̳еķ������������������Ҫ��
	 * �����������У����±�д�������
	 */
	public String toString() {
		return
				super.toString() +
				"\nѧУ��"+school;
	}
	
}
