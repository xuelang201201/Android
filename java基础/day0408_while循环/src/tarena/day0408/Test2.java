package tarena.day0408;

public class Test2 {
	public static void main(String[] args) {
		/*
		 * >0.999
		 * 
		 * 1.����double����a
		 * 2.�Ȳ���һ�����ֵ����a
		 * 3.���ж�a������Ҫ��ֵ���ص�2
		 * 
		 * 4.��ʾa��ֵ
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
