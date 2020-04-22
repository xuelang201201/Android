package tarena.day09night;

import android.widget.TextView;

public class Transformer {
	/*武器*/
	private Weapon weapon;
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	/*进攻的方法*/
	public void attack(TextView tv) {
		if(weapon==null) {
			tv.setText("用牙咬");
			return;
		}
		tv.append("变形金刚开始进攻");
		String type = "";
		switch (weapon.getType()) {
		case Weapon.TYPE_COLD:
			type = "冷武器";
			break;
		case Weapon.TYPE_HEAT:
			type = "热武器";
			break;
		case Weapon.TYPE_NUCLEAR:
			type = "核武器";
			break;
		}
		tv.setText("\n使用"+type+":"+weapon.getName());
		weapon.kill(tv);
	}
}
