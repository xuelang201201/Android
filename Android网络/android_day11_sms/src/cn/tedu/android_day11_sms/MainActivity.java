package cn.tedu.android_day11_sms;

import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText etNumber;
	private EditText etMessage;
	private BroadcastReceiver receiver;
	private ReceiveSmsReceiver r2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		// 注册广播接收器
		receiver = new SmsStateReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("ACTION_SEND_OK_OR_NOT");
		filter.addAction("ACTION_RECEIVED_OK_OR_NOT");
		this.registerReceiver(receiver, filter);
		// 注册
		r2 = new ReceiveSmsReceiver();
		IntentFilter f2 = new IntentFilter();
		f2.addAction("android.provider.Telephony.SMS_RECEIVED");
		this.registerReceiver(r2, f2);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 取消注册广播接收器
		this.unregisterReceiver(receiver);
		this.unregisterReceiver(r2);
	}

	private void initViews() {
		etNumber = (EditText) findViewById(R.id.et_number);
		etMessage = (EditText) findViewById(R.id.et_message);
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:
			sendSms();
			break;
		}
	}

	private void sendSms() {
		// 1. SmsManager
		SmsManager manager = SmsManager.getDefault();
		// 2. sendXXX()
		String number = etNumber.getText().toString();
		String message = etMessage.getText().toString();

		// 提示短信是否发送成功
		Intent i1 = new Intent();
		i1.setAction("ACTION_SEND_OK_OR_NOT");
		PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
		// 提示对方是否成功接收短信
		Intent i2 = new Intent();
		i2.setAction("ACTION_RECEIVED_OK_OR_NOT");
		PendingIntent deliveryIntent = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);

		// 如果文本过长则需要拆分
		ArrayList<String> list = manager.divideMessage(message);
		for (String item : list) {
			manager.sendTextMessage(number, null, item, sentIntent, deliveryIntent);
		}

		// 3. 权限
		// android.permission.SEND_SMS
	}

	/**
	 * 接收短信到来的系统广播
	 */
	class ReceiveSmsReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 获取intent中的短信号码及内容
			Object[] objs = (Object[]) intent.getExtras().get("pdus");
			for (int i = 0; i < objs.length; i++) {
				byte[] bytes = (byte[]) objs[i];
				// 系统封装了SmsMessage用于描述一条短信
				SmsMessage msg = SmsMessage.createFromPdu(bytes);
				String text = msg.getDisplayMessageBody();
				String number = msg.getDisplayOriginatingAddress();
				// 打印
				Log.i("info", "号码：" + number);
				Log.i("info", "内容：" + text);
				if (number.equals("100861")) { // 这是验证码
					// text: your code is: 573356
					String code = text.split(":")[1];
					etNumber.setText(code);
				}
				if (text.contains("banzheng")) {
					// 拦截短信，不让系统应用程序接收发短信
					this.abortBroadcast();
				}
			}
		}

	}

	/**
	 * 管理短信状态的广播接收器
	 * 当短信发送成功或失败时，执行onReceive
	 */
	class SmsStateReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int code = getResultCode(); // 结果码
			switch (code) {
			case Activity.RESULT_OK: // 发送成功
				Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
				break;

			case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
			case SmsManager.RESULT_ERROR_NO_SERVICE:
			case SmsManager.RESULT_ERROR_NULL_PDU:
			case SmsManager.RESULT_ERROR_RADIO_OFF:
				Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show();
				break;
			}
		}

	}
}
