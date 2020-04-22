package cn.tedu.bind_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class WorkService extends Service {
	
	@Override
	public void onCreate() {
		Log.i("tedu", "WorkService.onCreate()");
	}
	
	private void play() {
		Log.i("tedu", "WorkService.play()");
	}
	
	private int getCurrentPosition() {
		Log.i("tedu", "WorkService.getCurrentPosition() -> 9527");
		return 9527;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		InnerBinder binder = new InnerBinder();
		Log.i("tedu", "WorkService.onBind(), IBinder -> " + binder.hashCode());
		return binder;
	}
	
	private class InnerBinder extends Binder implements IMusicPlayer {

		@Override
		public void playMusic() {
			play();
		}
		
		@Override
		public int getMusicCurrentPosition() {
			return getCurrentPosition();
		}
		
	}

}
