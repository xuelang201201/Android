package tarena.day0805;

public class Test1 {
	public static void main(String[] args) {
		B b1 = new B();
	}
}

class A {
	int v1 = 1;
	static int v2 = 2;
	static {
		System.out.println("A��̬��ʼ����");
		System.out.println(B.v4);//��ӡv4Ĭ��ֵ0����û��ִ������ĸ�ֵ����
	}
	public A() {
		System.out.println("A()");
	}
}

class B extends A {
	int v3 = 3;
	static int v4 = 4;
	static {
		System.out.println("B��̬��ʼ����");
	}
	public B() {
		System.out.println("B()");
	}
}
