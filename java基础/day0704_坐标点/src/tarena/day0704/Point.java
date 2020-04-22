package tarena.day0704;

public class Point {
	int x;
	int y;
	
	//构造方法
	public Point() {
		
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "("+x+", "+y+")";
	}
	
	//距原点距离
	public double distance() {
		return Math.sqrt(x*x + y*y);
	}
	
	//距另一点距离
	public double distance(Point p) {
		//当前点的 x,y 与参数点 p 的 x,y 差值
		int dx = x - p.x;  //this.x, this.可以省略
		int dy = y - p.y;
		
		return Math.sqrt(dx*dx + dy*dy);
	}
}
