package tarena.day0402;

public class Test3 {
	public static void main(String[] args) {
		/*
		 *         *            
		 *        ***
		 *       *****
		 *      ******* 
		 *     *********
		 *     
		 *  1.ѭ�� i �� 1 �� <=5 ����
		 *      2.ѭ�� j �� 1 �� <=5-i ����
		 *      	3.��ӡ�ո�
		 *      4.ѭ�� j �� 1 �� <=i*2-1 ����
		 *      	5.��ӡ *
		 */
		for(int i=1; i<=5; i++) {
			for(int j=1; j<=5-i; j++) {
				System.out.print(" ");
			}
			for(int j=1; j<=i*2-1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
