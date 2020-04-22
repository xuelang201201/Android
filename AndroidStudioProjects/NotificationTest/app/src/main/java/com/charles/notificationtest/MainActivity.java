package com.charles.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mSendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mSendNotice = (Button) findViewById(R.id.send_notice);
        mSendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

                Intent intent = new Intent(this, NotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                builder.setContentIntent(pendingIntent); // 设置通知栏点击意图
                builder.setContentTitle("标题"); // 设置通知栏标题

                String string = getString(R.string.app_name);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 悬挂式Notification，5.0后显示
                    builder.setContentText(string + "点击查看")
                            .setFullScreenIntent(pendingIntent, true);
                    builder.setCategory(NotificationCompat.CATEGORY_MESSAGE);
                    builder.setSmallIcon(R.mipmap.ic_launcher); // 设置通知小ICON（5.0必须采用白色透明图片）
                } else {
                    builder.setSmallIcon(R.mipmap.ic_launcher); // 设置通知小ICON
                    builder.setContentText(string);
                }

                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setTicker(string + "有警报！"); // 通知首次出现在通知栏，带上升动画效果的
                builder.setWhen(System.currentTimeMillis()); // 通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                builder.setPriority(NotificationCompat.PRIORITY_MAX); // 设置该通知优先级
                builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC); // 在任何情况下都显示
                builder.setAutoCancel(true); // 设置这个标志当用户单机面板就可以让通知自动取消
                builder.setOngoing(false); // ture，设置它为一个正在进行的通知。它们通常是用来表示一个后台任务，用户积极参与（如播放音乐）或以某种方式正在等待，因此占用设备（如一个文件下载，同步操作，主动网络连接）

                // 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用NotificationCompat.DEFAULT_ALL属性，可以组合
                builder.setVibrate(new long[] {0, 100, 500, 100}); // 震动效果需要震动权限
                builder.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notify)); // 声音
                builder.setDefaults(NotificationCompat.DEFAULT_LIGHTS); // 闪灯

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = builder.build();
                manager.notify(1, notification);

                break;
            default:
                break;
        }
    }
}
