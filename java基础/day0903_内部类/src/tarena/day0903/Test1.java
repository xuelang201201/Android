package tarena.day0903;

import tarena.day0903.A.Inner1;
import tarena.day0903.A.Inner2;

public class Test1 {
	public static void main(String[] args) {
		Inner1 inner1 = new Inner1();
		System.out.println("创建了静态内部类的实例");

		////
		A a = new A();
		Inner2 inner2 = a.new Inner2();
		System.out.println("创建了非静态内部类的实例");
	}
}

class A {
	static class Inner1 {
	}	
	class Inner2 {
	}	
}