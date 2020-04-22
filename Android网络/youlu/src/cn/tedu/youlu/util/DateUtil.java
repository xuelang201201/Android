package cn.tedu.youlu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

	/**
	 * ͨ��һ��dateʱ��� ����Ϊʱ���ַ���
	 * @param date
	 * @return
	 * ���죺   HH:mm
	 * ���죺   ����
	 * ��ǰ��   ���ڼ�
	 */
	public static String parse(long date) {
		//��ǰʱ��
		Calendar now = Calendar.getInstance();
		//dateʱ��
		Calendar other = Calendar.getInstance();
		other.setTimeInMillis(date);
		//����ʱ�������ͬһ��
		if(now.get(Calendar.DAY_OF_YEAR) ==
				other.get(Calendar.DAY_OF_YEAR) &&
				now.get(Calendar.YEAR) == other.get(Calendar.YEAR)){
			return new SimpleDateFormat("HH:mm").format(other.getTime());
		}
		//�����ǲ�������
		now.add(Calendar.DAY_OF_YEAR, -1);
		if(now.get(Calendar.DAY_OF_YEAR) ==
				other.get(Calendar.DAY_OF_YEAR) &&
				now.get(Calendar.YEAR) == other.get(Calendar.YEAR)){
			return "����";
		}
		//������ڼ�
		int day = other.get(Calendar.DAY_OF_WEEK);
		String dayString = "";
		switch (day) {
		case Calendar.MONDAY:
			dayString = "����һ";
			break;
		case Calendar.TUESDAY:
			dayString = "���ڶ�";
			break;
		case Calendar.WEDNESDAY:
			dayString = "������";
			break;
		case Calendar.THURSDAY:
			dayString = "������";
			break;
		case Calendar.FRIDAY:
			dayString = "������";
			break;
		case Calendar.SATURDAY:
			dayString = "������";
			break;
		case Calendar.SUNDAY:
			dayString = "������";
			break;
		}
		return dayString;
	}

}
