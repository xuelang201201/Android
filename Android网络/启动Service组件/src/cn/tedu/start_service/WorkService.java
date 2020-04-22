package cn.tedu.start_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WorkService extends Service {

	@Override
	public void onCreate() {
		Log.d("tedu", "WorkService@" + hashCode() + ".onCreate()");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("tedu", "WorkService@" + hashCode() + ".onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.d("tedu", "WorkService@" + hashCode() + ".onDestroy()");
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// (Œﬁ ”)
		return null;
	}
}
