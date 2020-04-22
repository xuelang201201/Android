package cn.tedu.android_day10_phone;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText etPhone;
	private MyPhoneReceiver receiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etPhone = (EditText) findViewById(R.id.et_phone_number);
		// 开启监听 监听电话状态的更改
		TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		// 调用listen方法 开启监听
		manager.listen(new MyPhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
		// 取消监听
		// manager.listen(null, 0);
		
		// 注册广播接收器
		receiver = new MyPhoneReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
		this.registerReceiver(receiver, filter);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unregisterReceiver(receiver);
	}
	
	/**
	 * 打电话
	 */
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btn_call_phone:
			Intent intent = new Intent(Intent.ACTION_CALL);
			Uri uri = Uri.parse("tel:" + etPhone.getText().toString());
			intent.setData(uri);
			startActivity(intent);
			break;
		}
	}
	
	/**
	 * 接收呼出电话广播的广播接收器
	 */
	class MyPhoneReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 获取电话号码
			String number = getResultData();
			Log.i("info", "接收到呼出电话广播：" + number);
			// 拦截呼出电话：判断如果电话是xxx的话，不让打
			if (number.equals("14444444444")) {
				setResultData(null);
			}
		}
		
	}
	
	/**
	 * 电话状态监听器
	 */
	class MyPhoneListener extends PhoneStateListener {
		// 当电话状态更改时执行该监听方法
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			Log.i("info", "number:" + incomingNumber);
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				Log.i("info", "CALL_STATE_IDLE");
				break;
				
			case TelephonyManager.CALL_STATE_OFFHOOK:
				Log.i("info", "CALL_STATE_OFFHOOK");
				break;
				
			case TelephonyManager.CALL_STATE_RINGING:
				Log.i("info", "CALL_STATE_RINGING");
				break;
			}
		}
	}
}
