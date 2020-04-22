package cn.tedu.youlu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

	/**
	 * 通过一个date时间戳 解析为时间字符串
	 * @param date
	 * @return
	 * 当天：   HH:mm
	 * 昨天：   昨天
	 * 以前：   星期几
	 */
	public static String parse(long date) {
		//当前时间
		Calendar now = Calendar.getInstance();
		//date时间
		Calendar other = Calendar.getInstance();
		other.setTimeInMillis(date);
		//两个时间如果是同一天
		if(now.get(Calendar.DAY_OF_YEAR) ==
				other.get(Calendar.DAY_OF_YEAR) &&
				now.get(Calendar.YEAR) == other.get(Calendar.YEAR)){
			return new SimpleDateFormat("HH:mm").format(other.getTime());
		}
		//看看是不是昨天
		now.add(Calendar.DAY_OF_YEAR, -1);
		if(now.get(Calendar.DAY_OF_YEAR) ==
				other.get(Calendar.DAY_OF_YEAR) &&
				now.get(Calendar.YEAR) == other.get(Calendar.YEAR)){
			return "昨天";
		}
		//输出星期几
		int day = other.get(Calendar.DAY_OF_WEEK);
		String dayString = "";
		switch (day) {
		case Calendar.MONDAY:
			dayString = "星期一";
			break;
		case Calendar.TUESDAY:
			dayString = "星期二";
			break;
		case Calendar.WEDNESDAY:
			dayString = "星期三";
			break;
		case Calendar.THURSDAY:
			dayString = "星期四";
			break;
		case Calendar.FRIDAY:
			dayString = "星期五";
			break;
		case Calendar.SATURDAY:
			dayString = "星期六";
			break;
		case Calendar.SUNDAY:
			dayString = "星期日";
			break;
		}
		return dayString;
	}

}
