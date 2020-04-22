package tarena.day0902;

public class Sword implements Weapon {

	public void kill() {
		System.out.println("Ë£½£");
	}

	public String getName() {
		return "ÒÐÌì½£";
	}

	public int getType() {
		return Weapon.TYPE_COLD;
	}
	
}
