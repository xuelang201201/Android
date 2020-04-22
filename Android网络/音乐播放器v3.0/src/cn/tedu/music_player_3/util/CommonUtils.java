package cn.tedu.music_player_3.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * ͨ�ù�����
 */
public class CommonUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("mm:ss", Locale.CHINA);
	private static Date date = new Date();
	
	/**
	 * ��ʱ���ʽ��Ϊmm:ss�ĸ�ʽ
	 * 
	 * @param timeMillis ����ʽ����ʱ��ĺ�����
	 * @return ��ʽ��Ϊmm:ss��ʱ��
	 */
	public static String getFormattedTime(long timeMillis) {
		return getFormattedTime("mm:ss", timeMillis);
	}

	/**
	 * ��ʱ���ʽ��Ϊָ�����ַ���
	 * 
	 * @param pattern ģʽ�ַ��������磺yyyy-MM-dd HH:mm:ss����mm:ss
	 * @param timeMillis ����ʽ����ʱ��ĺ�����
	 * @return ����ģʽ�ַ�����ʽ�����ʱ��
	 */
	private static String getFormattedTime(String pattern, long timeMillis) {
		sdf.applyPattern(pattern);
		date.setTime(timeMillis);
		return sdf.format(date);
	}
}
