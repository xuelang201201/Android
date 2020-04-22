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
 * AppWidget的控制器类
 */
public class MyAppWidget extends AppWidgetProvider {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		// 接收自定义广播
		String action = intent.getAction();
		if (action.equals("ACTION_UPDATE_TEXTVIEW")) {
			// 更新AppWidget的textview 随机颜色
			// 1. AppWidgetManager
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			// 2. RemoteView
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
			// 随机颜色
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
	 * 每当创建一个AppWidget实例时执行
	 * 当自动更新时间到时，也会执行该方法
	 * @param manager:AppWidget管理器
	 * @param ids: 需要更新的AppWidget的id
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager manager,
			int[] ids) {
		Log.i("info", "onUpdate.."+Arrays.toString(ids));
		// 更新AppWidget的UI界面
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
		views.setTextViewText(R.id.textView1, "你好， 世界！");
		views.setTextColor(R.id.textView1, Color.CYAN);
		// 给AppWidget的button1添加点击意图
		Intent intent1 = new Intent(context, MainActivity.class);
		PendingIntent pi1 = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.button1, pi1);
		
		// 给AppWidget的button2添加点击意图
		Intent intent2 = new Intent("ACTION_UPDATE_TEXTVIEW");
		PendingIntent pi2 = PendingIntent.getBroadcast(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.button2, pi2);
		
		// 调用manager.updateAppWidget()方法更新
		manager.updateAppWidget(ids, views);
	}

	/**
	 * 每当移除AppWidget实例时执行
	 * @param ids 移除的实例的id
	 */
	@Override
	public void onDeleted(Context context, int[] ids) {
		Log.i("info", "onDeleted.."+Arrays.toString(ids));
	}

	/**
	 * 当第一次创建AppWidget实例是执行
	 */
	@Override
	public void onEnabled(Context context) {
		Log.i("info", "onEnabled..");
	}

	/**
	 * 当最后一个AppWidget实例移除后执行
	 */
	@Override
	public void onDisabled(Context context) {
		Log.i("info", "onDisabled..");
	}
}
