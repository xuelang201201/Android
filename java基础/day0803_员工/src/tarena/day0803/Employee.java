package tarena.day0803;

public abstract class Employee {
	public abstract double gongZi();
	public abstract double jiangJin();
	
	public double zongHe() {
		return gongZi() + jiangJin();
	}
}
