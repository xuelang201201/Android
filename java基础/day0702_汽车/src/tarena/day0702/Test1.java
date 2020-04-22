package tarena.day0702;

public class Test1 {
	public static void main(String[] args) {
		//先新建Car 对象，再将对象的内存地址赋给变量a
		Car a = new Car("别摸我","红色",230);
		Car b = new Car("保十洁","绿色",30);
		
		//用引用a找到第一个汽车变量,访问它的属性变量
		/*a.brand = "别摸我";
		a.color = "红色";
		a.speed = 230;
		
		b.brand = "保十洁";
		b.color = "绿色";
		b.speed = 30;*/
		
		//用引用a找到第一个汽车对象,让它执行go() 方法的代码
		a.go();
		a.stop();
		b.go();
		b.stop();
	}
}
