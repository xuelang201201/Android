package tarena.day0801;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * �½�Point��Point3D���󣬲�����
		 * ���ǵ�toString()��distance()����
		 */
		Point p1 = new Point(2, 3);
		Point p2 = new Point3D(2, 3, 4);
		
		System.out.println(p1.toString());
		System.out.println(p1.distance());
		System.out.println(p2.toString());
		System.out.println(p2.distance());
	}
}
