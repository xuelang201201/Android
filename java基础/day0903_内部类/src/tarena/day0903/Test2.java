package tarena.day0903;

public class Test2 {
	public static void main(String[] args) {
		Weapon w = f();
		w.kill();
		
		System.out.println("----------");
		
		Weapon w2 = f("������");
		w2.kill();
		
	}

	private static Weapon f(final String name) {
		Weapon w = new Weapon() {
			public void kill() {
				//�ֲ��ڲ����У�ʹ������ľֲ����������final
				System.out.println("ʹ��" + name + "����");
			}
		};
		return w;
		
	}
	
		

	private static Weapon f() {
		class AK47 implements Weapon {
			public void kill() {
				System.out.println("ʹ��AK47����");
			}
		}
		
		AK47 a = new AK47();
		return a;
	}
}
