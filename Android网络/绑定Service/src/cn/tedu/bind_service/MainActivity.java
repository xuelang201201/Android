package cn.tedu.bind_service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {
	private View btnBindService;
	private IMusicPlayer player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnBindService = findViewById(R.id.btn_bind_service);
		btnBindService.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Log.d("tedu", "MainActivity.onClick() -> btnBindService");
		Intent service = new Intent(this, WorkService.class);
		ServiceConnection conn = new InnerServiceConnection();
		int flags = BIND_AUTO_CREATE;
		bindService(service, conn, flags);
	}

	private class InnerServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d("tedu", "MainActivity$InnerServiceConnection.onServiceConnected(), IBinder -> " + service.hashCode());
			player = (IMusicPlayer) service;
			player.playMusic();
			int currentPosition = player.getMusicCurrentPosition();
			Log.d("tedu", "MainActivity$InnerServiceConnection.onServiceConnected(), current position -> " + currentPosition);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// （无视：仅当意外断开时调用）
		}
		
	}

}
