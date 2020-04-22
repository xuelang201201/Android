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
		// ע��㲥������
		receiver = new SmsStateReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("ACTION_SEND_OK_OR_NOT");
		filter.addAction("ACTION_RECEIVED_OK_OR_NOT");
		this.registerReceiver(receiver, filter);
		// ע��
		r2 = new ReceiveSmsReceiver();
		IntentFilter f2 = new IntentFilter();
		f2.addAction("android.provider.Telephony.SMS_RECEIVED");
		this.registerReceiver(r2, f2);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ȡ��ע��㲥������
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

		// ��ʾ�����Ƿ��ͳɹ�
		Intent i1 = new Intent();
		i1.setAction("ACTION_SEND_OK_OR_NOT");
		PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
		// ��ʾ�Է��Ƿ�ɹ����ն���
		Intent i2 = new Intent();
		i2.setAction("ACTION_RECEIVED_OK_OR_NOT");
		PendingIntent deliveryIntent = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);

		// ����ı���������Ҫ���
		ArrayList<String> list = manager.divideMessage(message);
		for (String item : list) {
			manager.sendTextMessage(number, null, item, sentIntent, deliveryIntent);
		}

		// 3. Ȩ��
		// android.permission.SEND_SMS
	}

	/**
	 * ���ն��ŵ�����ϵͳ�㲥
	 */
	class ReceiveSmsReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// ��ȡintent�еĶ��ź��뼰����
			Object[] objs = (Object[]) intent.getExtras().get("pdus");
			for (int i = 0; i < objs.length; i++) {
				byte[] bytes = (byte[]) objs[i];
				// ϵͳ��װ��SmsMessage��������һ������
				SmsMessage msg = SmsMessage.createFromPdu(bytes);
				String text = msg.getDisplayMessageBody();
				String number = msg.getDisplayOriginatingAddress();
				// ��ӡ
				Log.i("info", "���룺" + number);
				Log.i("info", "���ݣ�" + text);
				if (number.equals("100861")) { // ������֤��
					// text: your code is: 573356
					String code = text.split(":")[1];
					etNumber.setText(code);
				}
				if (text.contains("banzheng")) {
					// ���ض��ţ�����ϵͳӦ�ó�����շ�����
					this.abortBroadcast();
				}
			}
		}

	}

	/**
	 * �������״̬�Ĺ㲥������
	 * �����ŷ��ͳɹ���ʧ��ʱ��ִ��onReceive
	 */
	class SmsStateReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int code = getResultCode(); // �����
			switch (code) {
			case Activity.RESULT_OK: // ���ͳɹ�
				Toast.makeText(context, "���ͳɹ�", Toast.LENGTH_SHORT).show();
				break;

			case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
			case SmsManager.RESULT_ERROR_NO_SERVICE:
			case SmsManager.RESULT_ERROR_NULL_PDU:
			case SmsManager.RESULT_ERROR_RADIO_OFF:
				Toast.makeText(context, "����ʧ��", Toast.LENGTH_SHORT).show();
				break;
			}
		}

	}
}
