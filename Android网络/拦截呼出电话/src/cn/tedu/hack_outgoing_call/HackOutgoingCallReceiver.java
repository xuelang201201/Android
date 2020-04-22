package cn.tedu.hack_outgoing_call;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class HackOutgoingCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 1.获取电话号码
		String number = getResultData();
		Log.d("tedu", "原始电话号码是：" + number);
		
		// 2.判断号码是否需要处理
		if ("10000".equals(number)) {
			// 2.1 篡改电话号码
			number = null;
			// 2.2 将篡改后的电话号码进行传递
			setResultData(number);
		}
	}
	
}
