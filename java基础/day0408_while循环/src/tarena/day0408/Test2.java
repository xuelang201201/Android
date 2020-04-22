package tarena.day0408;

public class Test2 {
	public static void main(String[] args) {
		/*
		 * >0.999
		 * 
		 * 1.定义double变量a
		 * 2.先产生一个随机值赋给a
		 * 3.再判断a不是想要的值，回到2
		 * 
		 * 4.显示a的值
		 */
		int count = 1;
		double a;
		do {
			a = Math.random();
			count++;
		} while(a <= 0.999);
		
		System.out.println(count+": "+a);
	}
}
