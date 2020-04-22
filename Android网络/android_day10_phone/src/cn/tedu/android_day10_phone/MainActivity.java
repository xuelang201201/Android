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
		// �������� �����绰״̬�ĸ���
		TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		// ����listen���� ��������
		manager.listen(new MyPhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
		// ȡ������
		// manager.listen(null, 0);
		
		// ע��㲥������
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
	 * ��绰
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
	 * ���պ����绰�㲥�Ĺ㲥������
	 */
	class MyPhoneReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// ��ȡ�绰����
			String number = getResultData();
			Log.i("info", "���յ������绰�㲥��" + number);
			// ���غ����绰���ж�����绰��xxx�Ļ������ô�
			if (number.equals("14444444444")) {
				setResultData(null);
			}
		}
		
	}
	
	/**
	 * �绰״̬������
	 */
	class MyPhoneListener extends PhoneStateListener {
		// ���绰״̬����ʱִ�иü�������
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
