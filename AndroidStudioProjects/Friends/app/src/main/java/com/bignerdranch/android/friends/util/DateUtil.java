package com.bignerdranch.android.friends.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtil {

    /**
     * 通过一个date时间戳 解析为时间字符串
     * @param date
     * @return
     * 当天：   HH:mm
     * 昨天：   昨天
     * 以前：   周几
     */
    public static String parse(long date) {
        // 当前时间
        Calendar now = Calendar.getInstance();
        // date时间
        Calendar other = Calendar.getInstance(Locale.CHINA);
        other.setTimeInMillis(date);

        int dayOfYearNow = now.get(Calendar.DAY_OF_YEAR);
        int dayOfYearOther = other.get(Calendar.DAY_OF_YEAR);
        int yearNow = now.get(Calendar.YEAR);
        int yearOther = other.get(Calendar.YEAR);

        // subYear == 0，说明是同一年
        int subYear = yearNow - yearOther;

        // 两个时间如果是同一天
        if (dayOfYearNow == dayOfYearOther && subYear == 0) {
            if (now.getTimeInMillis() - (60 * 1000) < date) {
                return "刚刚";
            }
            return new SimpleDateFormat("ah:mm", Locale.CHINA).format(other.getTime());
        }
        // 看看是不是昨天
        now.add(Calendar.DAY_OF_YEAR, -1);
        if (now.get(Calendar.DAY_OF_YEAR) == dayOfYearOther && subYear == 0) {
            return new SimpleDateFormat("昨天 ah:mm", Locale.CHINA).format(other.getTime());
        }

        // 输出周几
        String dayString = "";
        if (subYear == 0) {
            if (now.get(Calendar.WEEK_OF_YEAR) == other.get(Calendar.WEEK_OF_YEAR)) {
                int day = other.get(Calendar.DAY_OF_WEEK);
                switch (day) {
                    case Calendar.MONDAY:
                        dayString = "周一";
                        break;
                    case Calendar.TUESDAY:
                        dayString = "周二";
                        break;
                    case Calendar.WEDNESDAY:
                        dayString = "周三";
                        break;
                    case Calendar.THURSDAY:
                        dayString = "周四";
                        break;
                    case Calendar.FRIDAY:
                        dayString = "周五";
                        break;
                    case Calendar.SATURDAY:
                        dayString = "周六";
                        break;
                    case Calendar.SUNDAY:
                        dayString = "周日";
                        break;
                }
            } else {
                return new SimpleDateFormat("M月d日", Locale.CHINA).format(other.getTime());
            }
        } else {
            return new SimpleDateFormat("yyyy年M月d日", Locale.CHINA).format(other.getTime());
        }
        return dayString + new SimpleDateFormat(" ah:mm", Locale.CHINA).format(other.getTime());
    }

    /**
     * 短信界面时间
     * @param date
     * @return
     */
    public static String parse2(long date) {
        // 当前时间
        Calendar now = Calendar.getInstance();
        // date时间
        Calendar other = Calendar.getInstance(Locale.CHINA);
        other.setTimeInMillis(date);

        int dayOfYearNow = now.get(Calendar.DAY_OF_YEAR);
        int dayOfYearOther = other.get(Calendar.DAY_OF_YEAR);
        int yearNow = now.get(Calendar.YEAR);
        int yearOther = other.get(Calendar.YEAR);

        // subYear == 0，说明是同一年
        int subYear = yearNow - yearOther;

        // 两个时间如果是同一天
        if (dayOfYearNow == dayOfYearOther && subYear == 0) {
            if (now.getTimeInMillis() - (60 * 1000) < date) {
                return "刚刚";
            }

            return new SimpleDateFormat("ah:mm", Locale.CHINA).format(other.getTime());
        }

        // 看看是不是昨天
        now.add(Calendar.DAY_OF_YEAR, -1);
        if (now.get(Calendar.DAY_OF_YEAR) == dayOfYearOther && subYear == 0) {
            return new SimpleDateFormat("昨天 ah:mm", Locale.CHINA).format(other.getTime());
        }

        if (subYear == 0) {
                return new SimpleDateFormat("M-d HH:mm", Locale.CHINA).format(other.getTime());
        } else {
            return new SimpleDateFormat("yyyy-M-d HH:mm", Locale.CHINA).format(other.getTime());
        }
    }
}
