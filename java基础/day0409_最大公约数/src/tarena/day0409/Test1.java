package tarena.day0409;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("�����������������Լ��");
		
		System.out.print("������һ��������");
		int m = new Scanner(System.in).nextInt();
		
		System.out.print("��������һ��������");
		int n = new Scanner(System.in).nextInt();
		
		//�ӽ�Сֵ��ǰ�ݼ�������ͬʱ������������ֵ
		int t = m < n ? m : n;
		for(;;t--) {
			if(m%t == 0 && n%t == 0) {
				break;
			}
		}
		System.out.println(m+","+n+"�����Լ���ǣ�"+t);
	}
}
