package tarena.day0902;

public class AK47 implements Weapon {
	public void kill() {
		System.out.println("��ǹ");
	}

	public String getName() {
		return "AK47";
	}

	public WeaponType getType() {
		return WeaponType.HEAT;
	}
}
