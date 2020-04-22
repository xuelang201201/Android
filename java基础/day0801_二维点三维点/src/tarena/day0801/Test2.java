package tarena.day0801;

public class Test2 {
	public static void main(String[] args) {
		Point a;
		
		a = new Point(2,3);
		f(a);
		
		a = new Point3D(2,3,4);
		f(a);
	}

	private static void f(Point a) {
		System.out.println(a.toString());
		System.out.println(a.distance());		
	}
}
