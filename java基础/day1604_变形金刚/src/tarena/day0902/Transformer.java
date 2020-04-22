package tarena.day0902;

public class Transformer {
	/*
	 * 变形金刚使用一个武器接口，
	 * 武器接口作为成员，封装在变形金刚中
	 */
	private Weapon w;//默认值是null
	
	public void setWeapon(Weapon w) {
		this.w = w;
	}
	
	public void attack() {
		if(w == null) {
			System.out.println("用牙咬");
			return;
		} 
		
		String type = "";
		switch(w.getType()) {
		case COLD:
			type = "冷兵器";
			break;
		case HEAT:
			type = "热兵器";
			break;
		case NUCLEAR:
			type = "核武器";
			break;
		}
		
		System.out.println("使用" + type + w.getName() + "进攻");
		w.kill();
	}
}
