package tarena.day0803;

public class Test1 {
	public static void main(String[] args) {
		f(new Programmer());
		f(new Manager());
	}
	
	static void f(Employee e) {
		System.out.println(e.gongZi());
		System.out.println(e.jiangJin());
		System.out.println(e.zongHe());
	}
}
