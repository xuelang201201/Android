package tarena.day0402;

public class Test5 {
	public static void main(String[] args) {
		for(int i=1; i<=100; i++) {
			
			if(i%10==3 || i%10==5 || i%10==7) {
				continue;
			}
			
			System.out.println(i);
		}
	}
}
