package cn.tedu.aidl_server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class WorkService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		InnerBinder binder = new InnerBinder();
		return binder;
	}
	
	private class InnerBinder extends IMusicPlayer.Stub {

		@Override
		public void play() {
			Log.d("tedu", "WorkService$InnerBinder.play()");
		}

		@Override
		public void pause() {
			Log.d("tedu", "WorkService$InnerBinder.pause()");
		}

		@Override
		public long getDuraton() throws RemoteException {
			return 456789;
		}

		@Override
		public Music getMusic() throws RemoteException {
			Music music = new Music();
			music.name = "р╟вс";
			music.path = "c:/music/р╟вс.mp3";
			music.duration = 1234567;
			return music;
		}
		
	}

}
