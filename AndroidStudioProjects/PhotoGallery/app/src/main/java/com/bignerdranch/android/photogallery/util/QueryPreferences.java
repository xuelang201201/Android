package com.bignerdranch.android.photogallery.util;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * 管理保存的查询字符串
 */
public class QueryPreferences {
    /**
     * 用作查询字符串的存储key，读取和写入时都要用到它。
     */
    private static final String PREF_SEARCH_QUERY = "searchQuery";
    /**
     * 存储最近一次获取图片的ID
     */
    private static final String PREF_LAST_RESULT_ID = "lastResultId";
    /**
     * 存储定时器状态
     */
    private static final String PREF_IS_ALARM_ON = "isAlarmOn";

    /**
     * 返回shared preferences中保存的查询字符串值。
     * @param context
     * @return
     */
    public static String getStoredQuery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SEARCH_QUERY, null);
    }

    /**
     * 向指定context的默认shared preferences写入查询输入值。
     * @param context
     * @param query
     */
    public static void setStoredQuery(Context context, String query) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit() // SharedPreferences.edit()方法可获取一个SharedPreferences.Editor实例。
                        // 它就是在SharedPreferences中保存查询信息要用到的类。
                .putString(PREF_SEARCH_QUERY, query)
                .apply();
    }

    public static String getLastResultId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_LAST_RESULT_ID, null);
    }

    public static void setLastResultId(Context context, String lastResultId) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_LAST_RESULT_ID, lastResultId)
                .apply();
    }

    public static boolean isAlarmOn(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_IS_ALARM_ON, false);
    }

    public static void setAlarmOn(Context context, boolean isOn) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(PREF_IS_ALARM_ON, isOn)
                .apply();
    }
}
