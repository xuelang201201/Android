package tarena.day0305;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("����ٷ��Ʒ�����");
		int s = new Scanner(System.in).nextInt();
		
		/*
		 * 1.����f()��������s��ֵ���ݵ�f()�����������㣬
		 *   Ȼ���ٵõ����ķ���ֵ����r
		 * 
		 */
		String r = f(s);
		System.out.println(r);
	} //main����
	
	//1.�������� 2.������ 3.�����б�
	static String f(int s) {
		if(s<0 || s>100) {
			return "��������ȷ";
		}
		
		//׼���������ı���result=""
		String result = "";
		switch(s/10) {
		case 0:
		case 1:
			result = "E";
			break;
		case 2:
		case 3:
		case 4:
		case 5:
			result = "D";
			break;
		case 6:
			result = "C";
			break;
		case 7:
		case 8:
			result = "B";
			break;
		case 9:
		case 10:
			result = "A";
		}
		return result;
	}
}
