package tarena.day1401;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) throws ParseException {
		System.out.println("�������ڣ� �������һ�������(yyyy-MM-dd)��");
		
		String s = new Scanner(System.in).nextLine();
		
		// ��2016-2-5�� --> Date���� --> ȡ������ֵ
		// ��ϵͳ��ǰʱ����ʱ���
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		// ctrl+1, add throws
		Date d = f.parse(s);
		long t = d.getTime() - System.currentTimeMillis();
		
		t = t/1000/60/60/24;
		
		System.out.println("����������"+t);
	}
}
