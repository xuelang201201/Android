package tarena.day0901;

public class Test1 {
	public static void main(String[] args) {
		Student s = new Student();
		
		//��ѧ��s���ĸ���������ֵ
		s.setId(9527);
		s.setName("����");
		s.setAge(22);
		s.setGender("��");
			
		//��ȡѧ��s���ĸ����Դ�ӡ
		System.out.println("ѧ�ţ�" + s.getId());
		System.out.println("������" + s.getName());
		System.out.println("���䣺" + s.getAge());
		System.out.println("�Ա�" + s.getGender());
	}
}
