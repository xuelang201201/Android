package tarena.day0902;

public class Lyb implements Weapon {
	public void kill() {
		System.out.println("��ը��");
	}

	public String getName() {
		return "������";
	}

	public WeaponType getType() {
		return WeaponType.NUCLEAR;
	}
}
