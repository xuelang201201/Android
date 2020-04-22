package tarena.day0402;

public class Test1 {
	public static void main(String[] args) {
		//1到10打印
		System.out.println("打印1到10：");
		for(int i=1; i<=10;	i++) {
			System.out.println(i);
		}
		
		//10到1打印
		System.out.println("打印10到1：");
		for(int j=10; j>=1; j--) {
			System.out.println(j);
		}
		
		//2,4,6,8,10
		System.out.println("打印2到10：");
		for(int k=2; k<=10; k+=2) {
			System.out.println(k);
		}
		
		//9,99,999,9999,...,999999999
		System.out.println("打印9到999999999：");
		for(int n=9; n<=999999999; n=n*10+9) {
			System.out.println(n);
		}
	}
}
