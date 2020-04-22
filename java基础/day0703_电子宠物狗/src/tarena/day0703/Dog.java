package tarena.day0703;

public class Dog {
	String name;
	int hungry = 50;
	int happy = 50;
	
	public void feed() {
		if(hungry == 0) {
			System.out.println(name+"吃饱了，不需要喂食了");
			return;
		}
		System.out.println("给狗狗"+name+"喂食");
		hungry -= 10;
		System.out.println("饥饿度："+hungry);
	}
	public void play() {
		if(hungry == 100) {
			System.out.println(name+"该喂食了");
			return;
		}
		
		System.out.println("陪狗狗"+name+"玩儿");
		happy += 10;
		hungry += 10;
		System.out.println("快乐度："+happy);
		System.out.println("饥饿度："+hungry);
	}
	public void punish() {
		System.out.println("打狗狗"+name+"的pp");
		happy -= 10;
		System.out.println("快乐度："+happy);
	}
}
