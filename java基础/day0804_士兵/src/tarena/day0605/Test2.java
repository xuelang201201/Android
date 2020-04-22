package tarena.day0605;

public class Test2 {
	public static void main(String[] args) {
		Soldier[] a = new Soldier[5];
		for(int i=0; i<a.length; i++) {
			a[i] = new Soldier();
			a[i].id = i + 1;
		}
		
		while(Soldier.count != 0) {
			for(int i=0; i<a.length; i++) {
				a[i].attack();
			}
			System.out.println("Ê¿±øÊýÁ¿£º"+Soldier.count);
			System.out.println("----------------");
		}
	}
}
