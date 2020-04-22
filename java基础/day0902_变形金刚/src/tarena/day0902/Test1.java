package tarena.day0902;

public class Test1 {
	public static void main(String[] args) {
		//创建四个对象
		Sword s = new Sword();//剑
		AK47 a = new AK47();//枪
		Lyb l = new Lyb();//狼牙棒
		
		Transformer t = new Transformer();//变形金刚
		
		t.attack();//未接入武器，用牙咬
		
		/*
		 * 武器对象s，
		 * 传入setWeapon()方法，
		 * 在方法中，为成员变量 w 赋值
		 * 
		 * t.w = s;
		 */
		t.setWeapon(s);//接入一把剑
		t.attack();//再进攻
		t.setWeapon(a);
		t.attack();
		t.setWeapon(l);
		t.attack();
	}
}
