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
		System.out.println("A静态初始化块");
		System.out.println(B.v4);//打印v4默认值0，还没有执行子类的赋值运算
	}
	public A() {
		System.out.println("A()");
	}
}

class B extends A {
	int v3 = 3;
	static int v4 = 4;
	static {
		System.out.println("B静态初始化块");
	}
	public B() {
		System.out.println("B()");
	}
}
