package tarena.day0405;

public class Test1 {
	public static void main(String[] args) {
		double sum = 0;
		for(int a=1,b=1,i=0;//����������
				i<100000000;
				a*=-1,b+=2,i++) {//�޸Ķ������
			sum += a/(double)b;
		}
		
		double pi = sum * 4;
		System.out.println(pi);
	}
}
