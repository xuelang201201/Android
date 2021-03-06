package cn.tedu.music_player_1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonUtils {
	/**
	 * 格式化日期/时间的工具
	 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("mm:ss", Locale.CHINA);
	/**
	 * 被格式化的时间对象，在被格式化之前，应该通过setTime()重新调整时间
	 */
	private static Date date = new Date();
	/**
	 * 将毫秒数转换为mm:ss的字符串格式
	 * @param timeMillis 被格式化的时间的毫秒数
	 * @return 转换为mm:ss的字符串格式
	 */
	public static String getFormattedDate(long timeMillis) {
		date.setTime(timeMillis);
		return sdf.format(date);
	}
}
