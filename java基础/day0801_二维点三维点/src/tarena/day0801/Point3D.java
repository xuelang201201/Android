package tarena.day0801;

public class Point3D extends Point {
	int z;
	
/*	public Point3D() {
		super();
	}*///Ĭ�ϵ����޲Σ�Point��û���޲η��������Իᱨ��
	
	public Point3D(int x, int y, int z) {
		//���಻�����޲ι��췽���������ֶ������вι��췽��
		super(x, y);
		this.z = z;
	}
	
	//��дtoString();

	public String toString() {
		return "("+x+", "+y+", "+z+")";
	}

	public double distance() {
		return Math.sqrt(x*x + y*y + z*z);
	}
}
