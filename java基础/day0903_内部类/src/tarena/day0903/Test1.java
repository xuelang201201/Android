package tarena.day0903;

import tarena.day0903.A.Inner1;
import tarena.day0903.A.Inner2;

public class Test1 {
	public static void main(String[] args) {
		Inner1 inner1 = new Inner1();
		System.out.println("�����˾�̬�ڲ����ʵ��");

		////
		A a = new A();
		Inner2 inner2 = a.new Inner2();
		System.out.println("�����˷Ǿ�̬�ڲ����ʵ��");
	}
}

class A {
	static class Inner1 {
	}	
	class Inner2 {
	}	
}