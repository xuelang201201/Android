package cn.tedu.aidl_client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.tedu.aidl_server.IMusicPlayer;
import cn.tedu.aidl_server.Music;

public class MainActivity extends Activity implements OnClickListener {
	private IMusicPlayer player;
	private ServiceConnection conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btn_bind_service).setOnClickListener(this);
		findViewById(R.id.btn_call_play).setOnClickListener(this);
		findViewById(R.id.btn_call_pause).setOnClickListener(this);
		findViewById(R.id.btn_call_get_duration).setOnClickListener(this);
		findViewById(R.id.btn_call_get_music).setOnClickListener(this);
	}

	private class InnerServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("tedu", "MainActivity$InnerServiceConnection.onServiceConnected()");

			player = IMusicPlayer.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_bind_service:
			Intent service = new Intent();
			service.setAction("cn.tedu.intent.action.AIDL_SERVER");
			conn = new InnerServiceConnection();
			bindService(service, conn, BIND_AUTO_CREATE);
			break;

		case R.id.btn_call_play:
			try {
				player.play();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;

		case R.id.btn_call_pause:
			try {
				player.pause();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
			
		case R.id.btn_call_get_duration:
			try {
				long duration = player.getDuration();
				Toast.makeText(this, "duration -> " + duration, Toast.LENGTH_LONG).show();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
			
		case R.id.btn_call_get_music:
			try {
				Music music = player.getMusic();
				Toast.makeText(this, "music -> " + music, Toast.LENGTH_LONG).show();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	protected void onDestroy() {
		if(conn != null) {
			unbindService(conn);
		}
		super.onDestroy();
	}

}
