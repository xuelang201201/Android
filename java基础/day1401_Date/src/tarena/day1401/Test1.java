package tarena.day1401;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {
	public static void main(String[] args) {
		Date a = new Date();
		Date b = new Date(6000000000000L);
		//���󴫵ݵ�println()�����ڣ�
		//�ڷ����ڲ������� a.toString()
		//��ȡ�ַ������ٴ�ӡ
		System.out.println(a);
		System.out.println(b);
		
		System.out.println(a.getTime());
		System.out.println(b.getTime());
		
		a.setTime(0);
		System.out.println(a);
		
		////
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String s;
		
		s = f.format(a);
		System.out.println(s);
		s = f.format(b);
		System.out.println(s);
	}
}
