package cn.tedu.broadcast_receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_send_broadcast).setOnClickListener(this);
		findViewById(R.id.btn_register_receiver).setOnClickListener(this);
		findViewById(R.id.btn_unregister_receiver).setOnClickListener(this);
		findViewById(R.id.btn_send_ordered_broadcast).setOnClickListener(this);
	}

	private InnerHighReceiver highReceiver = new InnerHighReceiver();
	private InnerMidReceiver midReceiver = new InnerMidReceiver();
	private InnerLowReceiver lowReceiver = new InnerLowReceiver();
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register_receiver:
			// 动态注册广播接收者
			IntentFilter highFilter = new IntentFilter();
			highFilter.addAction("CCTV_1");
			highFilter.addAction("CCTV_5");
			highFilter.addCategory("android.intent.category.DEFAULT");
			highFilter.setPriority(99999);
			
			IntentFilter midFilter = new IntentFilter();
			highFilter.addAction("CCTV_1");
			highFilter.addAction("CCTV_5");
			highFilter.addCategory("android.intent.category.DEFAULT");
			highFilter.setPriority(99999);
			
			IntentFilter lowFilter = new IntentFilter();
			highFilter.addAction("CCTV_1");
			highFilter.addAction("CCTV_5");
			highFilter.addCategory("android.intent.category.DEFAULT");
			highFilter.setPriority(99999);
			
			registerReceiver(lowReceiver, lowFilter);
			registerReceiver(midReceiver, midFilter);
			registerReceiver(highReceiver, highFilter);
			
			break;
			
		case R.id.btn_unregister_receiver:
			// 取消注册广播接收者
			unregisterReceiver(highReceiver);
			unregisterReceiver(midReceiver);
			unregisterReceiver(lowReceiver);
			break;

		case R.id.btn_send_broadcast:
			// 发送广播
			Intent intent = new Intent();
			intent.setAction("CCTV_1");
			intent.putExtra("name", "Mike");
			intent.putExtra("age", 19);
			sendBroadcast(intent);
			break;
			
		case R.id.btn_send_ordered_broadcast:
			Intent intent2 = new Intent();
			intent2.setAction("CCTV_5");
			sendOrderedBroadcast(intent2, null);
			break;
		}
	}
	
	private class InnerHighReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.e("tedu", "InnerHighReceiver.onReceive()");
			// Log.i("tedu", "name = " + intent.getStringExtra("name"));
			// Log.i("tedu", "age = " + intent.getIntExtra("age", -1));
			// 终止广播的传递
			// abortBroadcast();
			// setResultData("同学甲说女生乙好漂亮");
		}
		
	}

	private class InnerMidReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.w("tedu", "InnerMidReceiver.onReceive()");
			// String str = getResultData();
			// Log.w("tedu", str);
			// setResultData("同学甲说喜欢上女生乙了");
			// Log.i("tedu", "name = " + intent.getStringExtra("name"));
			// Log.i("tedu", "age = " + intent.getIntExtra("age", -1));
			// 终止广播的传递
			// abortBroadcast();
		}
		
	}
	
	private class InnerLowReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d("tedu", "InnerLowReceiver.onReceive()");
			// String str = getResultData();
			// Log.d("tedu", str);
			// setResultData("同学甲说跟女生乙分手了");
			// Log.i("tedu", "name = " + intent.getStringExtra("name"));
			// Log.i("tedu", "age = " + intent.getIntExtra("age", -1));
			// 终止广播的传递
			// abortBroadcast();
		}
		
	}
}
