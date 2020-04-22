package tarena.day0902;

public class AK47 implements Weapon {
	public void kill() {
		System.out.println("¥Ú«π");
	}

	public String getName() {
		return "AK47";
	}

	public int getType() {
		return Weapon.TYPE_HEAT;
	}
}
