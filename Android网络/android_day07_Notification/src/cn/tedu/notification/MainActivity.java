package cn.tedu.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends Activity {

	private static final int NOTIFICATION_ID = 100;
	private static final int NOTIFICATION_ID2 = 101;
	private static final int NOTIFICATION_ID3 = 102;
	private static final int NOTIFICATION_ID4 = 103;
	private MusicReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 注册广播接收者
		receiver = new MusicReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("ACTION_PRESS_BUTTON_PREVIOUS");
		filter.addAction("ACTION_PRESS_BUTTON_PLAY");
		this.registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unregisterReceiver(receiver);
	}
	
	/**
	 * 监听方法
	 * @param v
	 */
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send_notification:
			sendNotification();
			break;

		case R.id.btn_clear_notification:
			clearNotification();
			break;

		case R.id.btn_download_notification:
			downloadImage();
			break;

		case R.id.btn_progress_notification:
			sendProgressNotification();
			break;

		case R.id.btn_custom_notification:
			sendCustomUINotification();
			break;
		}
	}

	/**
	 * 发送自定义UI的通知
	 */
	private void sendCustomUINotification() {
		// 发通知
		NotificationManager manager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification.Builder builder = new Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		// 设置自定义UI
		RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.notification_item);
		// views中的方法
		Intent i1 = new Intent("ACTION_PRESS_BUTTON_PREVIOUS");
		PendingIntent pi1 = PendingIntent.getBroadcast(this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.btn_previous, pi1);
		// 给btn_play也添加点击意图
		Intent i2 = new Intent("ACTION_PRESS_BUTTON_PLAY");
		PendingIntent pi2 = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.btn_play, pi2);
		
		builder.setContent(views);
		Notification n = builder.build();
		manager.notify(NOTIFICATION_ID4, n);
	}

	/**
	 * 发送带进度条的通知
	 */
	private void sendProgressNotification() {
		// 发通知
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification.Builder builder = new Notification.Builder(this);
		builder.setContentTitle("文件下载")
			.setSmallIcon(R.drawable.ic_launcher)
			.setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.stat_sys_download))
			.setTicker("图片正在下载....")
			.setProgress(0, 0, true);
		Notification n = builder.build();
		manager.notify(NOTIFICATION_ID3, n);

		// 启动工作线程 模拟下载进度
		new WorkThread().start();
	}

	class WorkThread extends Thread {

		public void run() {
			for (int i=1; i<=10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int progress = i*10;
				// 更新通知的UI，再次发送通知
				NotificationManager manager = (NotificationManager)	getSystemService(NOTIFICATION_SERVICE);
				Notification.Builder builder = new Notification.Builder(MainActivity.this);
				builder.setContentTitle("文件下载")
					.setSmallIcon(R.drawable.ic_launcher)
					.setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.stat_sys_download))
					.setTicker("图片正在下载....")
					.setProgress(100, progress, false);
				// 如果已经下载完成
				if (progress==100) {
					// 把进度条去掉   显示文本
					builder.setProgress(0, 0, false);
					builder.setContentText("文件下载完成");
					// 设置通知的点击意图
					Intent intent = new Intent(MainActivity.this, MainActivity.class);
					PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
					builder.setContentIntent(pi);
					// 点击后如果有响应意图 则通知消失
					builder.setAutoCancel(true);
				}
				Notification n = builder.build();
				manager.notify(NOTIFICATION_ID3, n);
			}
		}
	}

	/**
	 * 模拟下载，发送自动更新进度的通知
	 */
	int number = 0;
	private void downloadImage() {
		// 发通知
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 若希望重新显示滚动消息，则先删除
		manager.cancel(NOTIFICATION_ID2);
		// 重新发
		Notification.Builder builder = new Notification.Builder(this);
		builder.setContentTitle("文件下载")
		.setContentText("下载进度" + number + "%")
		.setSmallIcon(R.drawable.ic_launcher)
		.setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.stat_sys_download))
		.setTicker("图片正在下载...");
		Notification n = builder.build();
		manager.notify(NOTIFICATION_ID2, n);
		number += 10;
	}

	/**
	 * 清除通知
	 */
	private void clearNotification() {
		// 1. 获取NotificationManager
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 2. 清除通知
		manager.cancel(NOTIFICATION_ID);
		manager.cancel(NOTIFICATION_ID2);
		manager.cancel(NOTIFICATION_ID3);
	}

	/**
	 * 发送通知
	 */
	private void sendNotification() {
		// 1. 获取NotificationManager
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 2. 获取Notification对象
		Notification.Builder builder = new Notification.Builder(this);
		builder.setContentInfo("ContentInfo")
		.setContentText("ContentText")
		.setContentTitle("ContentTitle")
		.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
		.setSmallIcon(R.drawable.ic_launcher)
		.setSubText("subText")
		.setTicker("滚动消息");
		Notification n = builder.build();
		// 通知常驻通知栏的做法
		n.flags = Notification.FLAG_NO_CLEAR;
		// 3. 发送通知
		manager.notify(NOTIFICATION_ID, n);
	}
	
	/**
	 * 广播接收器
	 */
	class MusicReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Log.i("info", "action:"+action);
		}
		
	}
	
}
