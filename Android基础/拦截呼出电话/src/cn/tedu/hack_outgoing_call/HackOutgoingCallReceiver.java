package cn.tedu.hack_outgoing_call;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class HackOutgoingCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 1.��ȡ�绰����
		String number = getResultData();
		Log.d("tedu", "ԭʼ�绰�����ǣ�" + number);
		
		// 2.�жϺ����Ƿ���Ҫ����
		if ("10000".equals(number)) {
			// 2.1 �۸ĵ绰����
			number = null;
			// 2.2 ���۸ĺ�ĵ绰������д���
			setResultData(number);
		}
	}
	
}
