package com.bignerdranch.android.photogallery.service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.bignerdranch.android.photogallery.R;
import com.bignerdranch.android.photogallery.bean.GalleryItem;
import com.bignerdranch.android.photogallery.ui.PhotoGalleryActivity;
import com.bignerdranch.android.photogallery.util.FlickrFetchr;
import com.bignerdranch.android.photogallery.util.QueryPreferences;

import java.util.List;

public class PollService extends IntentService {
    private static final String TAG = "PollService";

    // private static final int POLL_INTERVAL = 1000 * 60; // 60秒
    private static final long POLL_INTERVAL = AlarmManager.INTERVAL_FIFTEEN_MINUTES;

    public static final String ACTION_SHOW_NOTIFICATION =
            "com.bignerdranch.android.photogallery.SHOW_NOTIFICATION";
    // 私有权限
    public static final String PREM_PRIVATE =
            "com.bignerdranch.android.photogallery.PRIVATE";

    public static final String REQUEST_CODE = "REQUEST_CODE";
    public static final String NOTIFICATION = "NOTIFICATION";

    public static Intent newIntent(Context context) {
        return new Intent(context, PollService.class);
    }

    public static void setServiceAlarm(Context context, boolean isOn) {
        Intent i = PollService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(
                context, // 用来发送intent的Context
                0, // 区分PendingIntent来源的请求代码
                i, // 待发送的Intent对象
                0 // 一组用来决定如何创建PendingIntent的标识符
        );

        AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);

        if (isOn) { // 设置定时器
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(), POLL_INTERVAL, pi);
        } else { // 取消定时器
            alarmManager.cancel(pi);
            pi.cancel();
        }

        // 存储定时器状态
        QueryPreferences.setAlarmOn(context, isOn);
    }

    /**
     * 判断定时器的启停状态
     * @param context
     * @return
     */
    public static boolean isServiceAlarmOn(Context context) {
        Intent i = PollService.newIntent(context);
        PendingIntent pi = PendingIntent
                .getService(context, 0, i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }

    public PollService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (!isNetworkAvailableAndConnected()) { // 检查后台网络的可用性
            return;
        }

        // 从默认SharedPreferences中获取当前查询结果以及上一次结果ID;
        String query = QueryPreferences.getStoredQuery(this);
        String lastResultId = QueryPreferences.getLastResultId(this);
        List<GalleryItem> items;

        // 使用FlickrFetchr获取最新结果集
        if (query == null) {
            items = new FlickrFetchr().fetchRecentPhotos();
        } else {
            items = new FlickrFetchr().searchPhotos(query);
        }

        if (items.size() == 0) {
            return;
        }

        String resulId = items.get(0).getId(); // 如果有结果返回，抓取第一条结果
        if (resulId.equals(lastResultId)) { // 确认是否不同于上一条结果ID
            Log.i(TAG, "Got an old result: " + resulId);
        } else {
            Log.i(TAG, "Got a new result: " + resulId);

            Resources resources = getResources();
            Intent i = PhotoGalleryActivity.newIntent(this);
            PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);

            Notification notification = new NotificationCompat.Builder(this)
                    .setTicker(resources.getString(R.string.new_pictures_title))
                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                    .setContentTitle(resources.getString(R.string.new_pictures_title))
                    .setContentText(resources.getString(R.string.new_pictures_text))
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();

            // 发送有序的broadcast
            showBackgroundNotification(0, notification);
        }

        // 将第一条结果存入SharedPreferences
        QueryPreferences.setLastResultId(this, resulId);
    }

    private void showBackgroundNotification(int requestCode, Notification notification) {
        Intent i = new Intent(ACTION_SHOW_NOTIFICATION);
        i.putExtra(REQUEST_CODE, requestCode);
        i.putExtra(NOTIFICATION, notification);
        sendOrderedBroadcast(
                i, // Intent
                PREM_PRIVATE, // 私有权限
                null, // result receiver
                null, // 支持result receiver运行的Handler
                Activity.RESULT_OK, // 结果代码初始值
                null, // 结果数据
                null // 有序broadcast的结果附加内容
        );
    }

    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }
}
