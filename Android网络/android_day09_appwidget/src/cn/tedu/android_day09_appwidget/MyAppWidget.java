package cn.tedu.android_day09_appwidget;

import java.util.Arrays;
import java.util.Random;

import android.R.color;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * AppWidget�Ŀ�������
 */
public class MyAppWidget extends AppWidgetProvider {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		// �����Զ���㲥
		String action = intent.getAction();
		if (action.equals("ACTION_UPDATE_TEXTVIEW")) {
			// ����AppWidget��textview �����ɫ
			// 1. AppWidgetManager
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			// 2. RemoteView
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
			// �����ɫ
			int[] colors = 
				{
					Color.BLACK, Color.BLUE, Color.CYAN, 
					Color.DKGRAY, Color.GRAY, Color.GREEN, 
					Color.LTGRAY, Color.MAGENTA, Color.RED,
					Color.TRANSPARENT, Color.WHITE, Color.YELLOW
				};
			views.setTextColor(R.id.textView1, colors[new Random().nextInt(colors.length)]);
			// 3. manager.updateAppWidget()
			ComponentName name = new ComponentName(context, MyAppWidget.class);
			manager.updateAppWidget(name, views);
		}
	}

	/**
	 * ÿ������һ��AppWidgetʵ��ʱִ��
	 * ���Զ�����ʱ�䵽ʱ��Ҳ��ִ�и÷���
	 * @param manager:AppWidget������
	 * @param ids: ��Ҫ���µ�AppWidget��id
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager manager,
			int[] ids) {
		Log.i("info", "onUpdate.."+Arrays.toString(ids));
		// ����AppWidget��UI����
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
		views.setTextViewText(R.id.textView1, "��ã� ���磡");
		views.setTextColor(R.id.textView1, Color.CYAN);
		// ��AppWidget��button1��ӵ����ͼ
		Intent intent1 = new Intent(context, MainActivity.class);
		PendingIntent pi1 = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.button1, pi1);
		
		// ��AppWidget��button2��ӵ����ͼ
		Intent intent2 = new Intent("ACTION_UPDATE_TEXTVIEW");
		PendingIntent pi2 = PendingIntent.getBroadcast(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.button2, pi2);
		
		// ����manager.updateAppWidget()��������
		manager.updateAppWidget(ids, views);
	}

	/**
	 * ÿ���Ƴ�AppWidgetʵ��ʱִ��
	 * @param ids �Ƴ���ʵ����id
	 */
	@Override
	public void onDeleted(Context context, int[] ids) {
		Log.i("info", "onDeleted.."+Arrays.toString(ids));
	}

	/**
	 * ����һ�δ���AppWidgetʵ����ִ��
	 */
	@Override
	public void onEnabled(Context context) {
		Log.i("info", "onEnabled..");
	}

	/**
	 * �����һ��AppWidgetʵ���Ƴ���ִ��
	 */
	@Override
	public void onDisabled(Context context) {
		Log.i("info", "onDisabled..");
	}
}
