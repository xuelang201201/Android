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
		
		// ע��㲥������
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
	 * ��������
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
	 * �����Զ���UI��֪ͨ
	 */
	private void sendCustomUINotification() {
		// ��֪ͨ
		NotificationManager manager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification.Builder builder = new Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		// �����Զ���UI
		RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.notification_item);
		// views�еķ���
		Intent i1 = new Intent("ACTION_PRESS_BUTTON_PREVIOUS");
		PendingIntent pi1 = PendingIntent.getBroadcast(this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.btn_previous, pi1);
		// ��btn_playҲ��ӵ����ͼ
		Intent i2 = new Intent("ACTION_PRESS_BUTTON_PLAY");
		PendingIntent pi2 = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.btn_play, pi2);
		
		builder.setContent(views);
		Notification n = builder.build();
		manager.notify(NOTIFICATION_ID4, n);
	}

	/**
	 * ���ʹ���������֪ͨ
	 */
	private void sendProgressNotification() {
		// ��֪ͨ
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification.Builder builder = new Notification.Builder(this);
		builder.setContentTitle("�ļ�����")
			.setSmallIcon(R.drawable.ic_launcher)
			.setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.stat_sys_download))
			.setTicker("ͼƬ��������....")
			.setProgress(0, 0, true);
		Notification n = builder.build();
		manager.notify(NOTIFICATION_ID3, n);

		// ���������߳� ģ�����ؽ���
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
				// ����֪ͨ��UI���ٴη���֪ͨ
				NotificationManager manager = (NotificationManager)	getSystemService(NOTIFICATION_SERVICE);
				Notification.Builder builder = new Notification.Builder(MainActivity.this);
				builder.setContentTitle("�ļ�����")
					.setSmallIcon(R.drawable.ic_launcher)
					.setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.stat_sys_download))
					.setTicker("ͼƬ��������....")
					.setProgress(100, progress, false);
				// ����Ѿ��������
				if (progress==100) {
					// �ѽ�����ȥ��   ��ʾ�ı�
					builder.setProgress(0, 0, false);
					builder.setContentText("�ļ��������");
					// ����֪ͨ�ĵ����ͼ
					Intent intent = new Intent(MainActivity.this, MainActivity.class);
					PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
					builder.setContentIntent(pi);
					// ������������Ӧ��ͼ ��֪ͨ��ʧ
					builder.setAutoCancel(true);
				}
				Notification n = builder.build();
				manager.notify(NOTIFICATION_ID3, n);
			}
		}
	}

	/**
	 * ģ�����أ������Զ����½��ȵ�֪ͨ
	 */
	int number = 0;
	private void downloadImage() {
		// ��֪ͨ
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// ��ϣ��������ʾ������Ϣ������ɾ��
		manager.cancel(NOTIFICATION_ID2);
		// ���·�
		Notification.Builder builder = new Notification.Builder(this);
		builder.setContentTitle("�ļ�����")
		.setContentText("���ؽ���" + number + "%")
		.setSmallIcon(R.drawable.ic_launcher)
		.setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.stat_sys_download))
		.setTicker("ͼƬ��������...");
		Notification n = builder.build();
		manager.notify(NOTIFICATION_ID2, n);
		number += 10;
	}

	/**
	 * ���֪ͨ
	 */
	private void clearNotification() {
		// 1. ��ȡNotificationManager
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 2. ���֪ͨ
		manager.cancel(NOTIFICATION_ID);
		manager.cancel(NOTIFICATION_ID2);
		manager.cancel(NOTIFICATION_ID3);
	}

	/**
	 * ����֪ͨ
	 */
	private void sendNotification() {
		// 1. ��ȡNotificationManager
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 2. ��ȡNotification����
		Notification.Builder builder = new Notification.Builder(this);
		builder.setContentInfo("ContentInfo")
		.setContentText("ContentText")
		.setContentTitle("ContentTitle")
		.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
		.setSmallIcon(R.drawable.ic_launcher)
		.setSubText("subText")
		.setTicker("������Ϣ");
		Notification n = builder.build();
		// ֪ͨ��פ֪ͨ��������
		n.flags = Notification.FLAG_NO_CLEAR;
		// 3. ����֪ͨ
		manager.notify(NOTIFICATION_ID, n);
	}
	
	/**
	 * �㲥������
	 */
	class MusicReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Log.i("info", "action:"+action);
		}
		
	}
	
}
