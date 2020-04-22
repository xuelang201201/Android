package tarena.day0409;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("99³Ë·¨±í");
		
		for(int i=1; i<=9; i++) {
			for(int j=1; j<=i; j++) {
				System.out.print(j+"x"+i+"="+i*j+"\t");
			}
			System.out.println();
		}
	}
}
