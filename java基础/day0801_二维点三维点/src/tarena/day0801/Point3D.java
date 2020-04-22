package tarena.day0801;

public class Point3D extends Point {
	int z;
	
/*	public Point3D() {
		super();
	}*///默认调用无参，Point中没有无参方法，所以会报错
	
	public Point3D(int x, int y, int z) {
		//父类不存在无参构造方法，必须手动调用有参构造方法
		super(x, y);
		this.z = z;
	}
	
	//重写toString();

	public String toString() {
		return "("+x+", "+y+", "+z+")";
	}

	public double distance() {
		return Math.sqrt(x*x + y*y + z*z);
	}
}
