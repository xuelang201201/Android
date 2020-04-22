package tarena.day0409;

public class Test2 {
	public static void main(String[] args) {
		System.out.println("上三角99乘法表");

		for(int i=9; i>=1; i--) {
			for(int j=1; j<=i; j++) {
				System.out.print(j+"x"+i+"="+i*j+"\t");
			}
			System.out.println();
		}
	}
}
