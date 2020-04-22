package tarena.day0405;

public class Test1 {
	public static void main(String[] args) {
		double sum = 0;
		for(int a=1,b=1,i=0;//定义多个变量
				i<100000000;
				a*=-1,b+=2,i++) {//修改多个变量
			sum += a/(double)b;
		}
		
		double pi = sum * 4;
		System.out.println(pi);
	}
}
