package tarena.day0902;

public class Sword implements Weapon {

	public void kill() {
		System.out.println("ˣ��");
	}

	public String getName() {
		return "���콣";
	}

	public WeaponType getType() {
		return WeaponType.COLD;
	}
	
}
