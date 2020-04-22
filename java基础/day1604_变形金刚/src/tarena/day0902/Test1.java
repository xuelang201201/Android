package tarena.day0902;

public class Test1 {
	public static void main(String[] args) {
		Sword s = new Sword();
		AK47 a = new AK47();
		Lyb l = new Lyb();
		Transformer t = new Transformer();
		
		t.attack();
		t.setWeapon(s);
		t.attack();
		t.setWeapon(a);
		t.attack();
		t.setWeapon(l);
		t.attack();
	}
}
