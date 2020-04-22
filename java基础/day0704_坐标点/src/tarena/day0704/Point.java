package tarena.day0704;

public class Point {
	int x;
	int y;
	
	//���췽��
	public Point() {
		
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "("+x+", "+y+")";
	}
	
	//��ԭ�����
	public double distance() {
		return Math.sqrt(x*x + y*y);
	}
	
	//����һ�����
	public double distance(Point p) {
		//��ǰ��� x,y ������� p �� x,y ��ֵ
		int dx = x - p.x;  //this.x, this.����ʡ��
		int dy = y - p.y;
		
		return Math.sqrt(dx*dx + dy*dy);
	}
}
