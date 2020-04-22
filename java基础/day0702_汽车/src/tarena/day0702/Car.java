package tarena.day0702;
/*
 * 封装
 * 
 * 将汽车相关的数据、逻辑运算代码，
 * 封装成一个类组件
 */
public class Car {
	String brand; //品牌
	String color; //颜色
	int speed;    //时速
	
	//构造方法
	public Car(
			String brand, //局部变量允许与成员变量同名
			String color, 
			int speed) {
		//如果有同名局部变量，必须用this.xxx 来引用成员变量
		//this 表示当前正在创建的对象
		this.brand = brand;
		this.color = color;
		this.speed = speed;
	}
	
	public void go() {
		System.out.println(color+"的"+brand+"汽车，以时速"+speed+"前进");
	}
	public void stop() {
		System.out.println(color+"的"+brand+"汽车停止");
	}
}
