package tarena.day0802;

public class Test1 {
	public static void main(String[] args) {
	  //f(new Shape()); //�����಻�ܴ�������
		f(new Line());
		f(new Square());
		f(new Circle());
	}
	
	static void f(Shape s) {
		s.draw();   //�������࣬���Ҹ���
		
		if(s instanceof Line) {
			Line line = (Line) s;  //����ת�ͱ���ǿ��ת��
			line.length();	
		}
		
		s.clean();
	}
}
