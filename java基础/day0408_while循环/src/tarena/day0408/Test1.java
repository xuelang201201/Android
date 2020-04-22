package tarena.day0408;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * >0.999
		 */
		int count = 1;
		
		while(true) {
			double a = Math.random();
			if (a > 0.999) {
				System.out.println(count+": "+a);
				break;
			}
			count++;
		}
	}
}
