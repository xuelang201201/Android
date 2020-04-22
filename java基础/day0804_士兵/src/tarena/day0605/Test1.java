package tarena.day0605;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * 先建一个 Soldier 对象，
		 * 再将对象的内存地址赋给变量 s1 
		 */
		Soldier s1 = new Soldier();
		Soldier s2 = new Soldier();
		/*
		 * 用s1访问第一个士兵对象内存空间
		 * 中的id变量,给id赋值
		 */
		s1.id = 9527;
		s2.id = 9528;
		/*
		 * 用s1访问第一个士兵对象，
		 * 控制这个士兵执行 go() 方法中的代码
		 */
		s1.go();
		s2.go();
		s2.standby();
		
		s1.attack();

	}
}
