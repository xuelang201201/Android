package cn.tedu.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LowReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("tedu", "LowReceiver.onReceive()");
		// String str = getResultData();
		// Log.d("tedu", str);
		// Log.i("tedu", "name = " + intent.getStringExtra("name"));
		// Log.i("tedu", "age = " + intent.getIntExtra("age", -1));
	}

}
