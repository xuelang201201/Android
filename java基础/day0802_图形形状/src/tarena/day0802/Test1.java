package tarena.day0802;

public class Test1 {
	public static void main(String[] args) {
	  //f(new Shape()); //抽象类不能创建对象
		f(new Line());
		f(new Square());
		f(new Circle());
	}
	
	static void f(Shape s) {
		s.draw();   //先找子类，再找父类
		
		if(s instanceof Line) {
			Line line = (Line) s;  //向下转型必须强制转型
			line.length();	
		}
		
		s.clean();
	}
}
