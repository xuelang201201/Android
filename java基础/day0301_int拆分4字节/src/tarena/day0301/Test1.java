package tarena.day0301;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("����������");
		int a = new Scanner(System.in).nextInt();
		
		/*
		 * 1.a�����ƶ�24λ��ǿת��byte����b1
		 * 2.a�����ƶ�16λ��ǿת��byte����b2
		 * 3.a�����ƶ�8λ��ǿת��byte����b3
		 * 4.a�����ƶ�0λ��ǿת��byte����b4
		 */
		
		byte b1 = (byte) (a>>24);
		byte b2 = (byte) (a>>16);
		byte b3 = (byte) (a>>8);
		byte b4 = (byte) (a>>0);
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		
		/*
		 * �ϲ�
		 * 0.�������r=0
		 * 1.b1����24λ�����޷�������0λ������r��򸳸�r
		 * 2.b1����24λ�����޷�������8λ������r��򸳸�r
		 * 3.b1����24λ�����޷�������16λ������r��򸳸�r
		 * 4.b1����24λ�����޷�������24λ������r��򸳸�r
		 */
		
		int r = 0;
		r = r | (b1<<24>>>0);
		r = r | (b2<<24>>>8);
		r = r | (b3<<24>>>16);
		r = r | (b4<<24>>>24);
		System.out.println(r);
	}
}
