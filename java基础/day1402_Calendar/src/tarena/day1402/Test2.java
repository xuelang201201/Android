package tarena.day1402;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) throws ParseException {
		System.out.println("������-��(yyyy-MM)��");
		String s = new Scanner(System.in).nextLine();
		/*
		 * "2016-2"
		 *   -->Date����(2016-2-1 0:0:0)
		 *   -->�½�Calendar�����ó�Date�����ʱ���
		 *   
		 * 1.�½�SimpleDateFormat���󸳸�fmt
		 *   �ƶ���ʽ��"yyyy-MM"
		 * 2.���ø�ʽ���ߵ�parse() ����������
		 *   ���ַ���s������Date���󸳸�d
		 * 3.�½�Calendar���󸳸�c
		 * 4.��c���ó���Date����d��ͬ��ʱ���
		 * 5.���һ�����ܼ�����day
		 * 6.��õ��������������max
		 * 7.�����������count=0
		 * 8.��ӡ�ַ���"��\tһ\t��\t��\t��\t��\t��"
		 * 9.ѭ��i��0��<day-1����
		 *     10.��ӡ" \t"
		 *     11.count++
		 *     
		 * 12.ѭ��i��1��<=max����
		 *     13.��ӡi+"\t"
		 *     14.count++
		 *     15.���count==7
		 *         16.��ӡ����
		 *         17.count=0
		 */
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
		Date d = fmt.parse(s);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int day = c.get(DAY_OF_WEEK);//���һ�����ܼ�����day
		int max = c.getActualMaximum(DAY_OF_MONTH);//��õ��������������max
		int count = 0;
		System.out.println("��\tһ\t��\t��\t��\t��\t��");
		for(int i=0; i<day-1; i++) {
			System.out.print(" \t");
			count++;
		}
		
		for(int i=1; i<=max; i++) {
			System.out.print(i+"\t");
			count++;
			if(count == 7) {
				System.out.println();
				count = 0;
			}
		}
	
	}
}
