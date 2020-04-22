package tarena.day09night;

import android.widget.TextView;

public class Transformer {
	/*����*/
	private Weapon weapon;
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	/*�����ķ���*/
	public void attack(TextView tv) {
		if(weapon==null) {
			tv.setText("����ҧ");
			return;
		}
		tv.append("���ν�տ�ʼ����");
		String type = "";
		switch (weapon.getType()) {
		case Weapon.TYPE_COLD:
			type = "������";
			break;
		case Weapon.TYPE_HEAT:
			type = "������";
			break;
		case Weapon.TYPE_NUCLEAR:
			type = "������";
			break;
		}
		tv.setText("\nʹ��"+type+":"+weapon.getName());
		weapon.kill(tv);
	}
}
