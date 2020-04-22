package tarena.day0902;

public class Lyb implements Weapon {
	public void kill() {
		System.out.println("¶ªÕ¨µ¯");
	}

	public String getName() {
		return "ÀÇÑÀ°ô";
	}

	public WeaponType getType() {
		return WeaponType.NUCLEAR;
	}
}
