package cn.tedu.music_player_2.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("mm:ss",
			Locale.CHINA);
	private static Date date = new Date();

	public static String getFormattedTime(long timeMillis) {
		date.setTime(timeMillis);
		return sdf.format(date);
	}
}
