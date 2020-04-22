package cn.tedu.music_player_3.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 通用工具类
 */
public class CommonUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("mm:ss", Locale.CHINA);
	private static Date date = new Date();
	
	/**
	 * 把时间格式化为mm:ss的格式
	 * 
	 * @param timeMillis 被格式化的时间的毫秒数
	 * @return 格式化为mm:ss的时间
	 */
	public static String getFormattedTime(long timeMillis) {
		return getFormattedTime("mm:ss", timeMillis);
	}

	/**
	 * 把时间格式化为指定的字符串
	 * 
	 * @param pattern 模式字符串，例如：yyyy-MM-dd HH:mm:ss或者mm:ss
	 * @param timeMillis 被格式化的时间的毫秒数
	 * @return 根据模式字符串格式化后的时间
	 */
	private static String getFormattedTime(String pattern, long timeMillis) {
		sdf.applyPattern(pattern);
		date.setTime(timeMillis);
		return sdf.format(date);
	}
}
