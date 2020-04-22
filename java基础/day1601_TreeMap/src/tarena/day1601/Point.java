package tarena.day1601;

public class Point implements Comparable<Point> { //Compareable接口
	private int x;
	private int y;
	
	public Point() {
		super();
	}
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	
	/* 重写Comparable接口的比较方法
	 * 当前对象，与参与对象o比较大小
	 * 当前对象大，正数
	 * 当前对象小，负数
	 * 相同，0
	 */
	@Override
	public int compareTo(Point o) {
		return x - o.x;
	}
}
