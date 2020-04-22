package tarena.day0903;

public class Test2 {
	public static void main(String[] args) {
		Weapon w = f();
		w.kill();
		
		System.out.println("----------");
		
		Weapon w2 = f("屠龙刀");
		w2.kill();
		
	}

	private static Weapon f(final String name) {
		Weapon w = new Weapon() {
			public void kill() {
				//局部内部类中，使用外面的局部变量必须加final
				System.out.println("使用" + name + "进攻");
			}
		};
		return w;
		
	}
	
		

	private static Weapon f() {
		class AK47 implements Weapon {
			public void kill() {
				System.out.println("使用AK47进攻");
			}
		}
		
		AK47 a = new AK47();
		return a;
	}
}
