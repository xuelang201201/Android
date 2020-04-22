package tarena.day0605;

import java.util.Random;

/*
 * 封装：
 * 
 * 将士兵相关的属性数据、逻辑运算方法，
 * 封装成一个类组件
 */
public class Soldier {
	//属性变量、成员变量
	int id;//成员变量有默认值 0
	int blood = 100;
	
	//构造方法
	public Soldier() {
		System.out.println("Soldier() 构造方法执行");
	}
	
	//方法
	public void go() {
		System.out.println(id+"号士兵前进");
	}
	public void standby() {
		System.out.println(id+"号士兵待命");
	}
	public void attack() {
		if(blood <= 0) {
			System.out.println("这是"+id+"号士兵的尸体");
			return;
		}
		System.out.println(id+"号士兵进攻");
		
		int b = new Random().nextInt(10);
		if(blood < b) {//血量不够
			b = blood;//有多少减多少
		}
		blood -= b;
		System.out.println("减血：" + b);
		System.out.println("血量：" + blood);
		
		if(blood == 0) {
			System.out.println(id+"号士兵已阵亡");
		}
	}
}
