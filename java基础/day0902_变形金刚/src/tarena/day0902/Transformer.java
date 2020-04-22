package tarena.day0902;

public class Transformer {
	/* 在一个类中使用接口，只需要定义一个接口类型的成员变量即可。
	 * 
	 * Weapon接口已经被Sword、AK47、Lyb类使用，
	 * 实现了接口中定义的抽象方法，
	 * 那么在Transformer类中使用Weapon接口，
	 * 只需要定义一个Weapon类型的成员变量即可。
	 */
	
	private Weapon w;/*默认值是null，
	                                          实际上就相当于定义了一个Weapon类型的空引用，
	                                          表示Weapon接口上还没有接上武器。*/
	
	/* 接收参数的变量 w 定义成Weapon父类型，
	 * 是为了接收传过来的任意的子类型
	 * 子类型：Sword,AK47,Lyb
	 */
	public void setWeapon(Weapon w) { 
		this.w = w;
	}
	
	public void attack() {//让变形金刚进攻
		if(w == null) {//相当于没有接入任何的武器
			System.out.println("用牙咬");
			return;
		} 
		
		//用武器进攻
		String type = "";
		switch(w.getType()) {//向w接口发送getType()调用指令
		case Weapon.TYPE_COLD://可以写1。这样写便于阅读理解
			type = "冷兵器";
			break;
		case Weapon.TYPE_HEAT://可以写2
			type = "热兵器";
			break;
		case Weapon.TYPE_NUCLEAR://可以写3
			type = "核武器";
			break;
		}
		
		//向w接口发送getName()调用指令
		System.out.println("使用" + type + w.getName() + "进攻");
		
		//向w接口发送kill()调用指令
		//由具体接入的武器来执行这个方法
		w.kill();
	}
}
