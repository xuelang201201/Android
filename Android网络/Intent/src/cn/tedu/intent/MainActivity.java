package cn.tedu.intent;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button startSecond;
	private Button startDial;
	private Button startCall;
	private Button startSecond2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startSecond = (Button) findViewById(R.id.btn_start_second);
		startSecond.setOnClickListener(this);
		
		startDial = (Button) findViewById(R.id.btn_dial);
		startDial.setOnClickListener(this);

		startCall = (Button) findViewById(R.id.btn_call);
		startCall.setOnClickListener(this);
		
		startSecond2 = (Button) findViewById(R.id.btn_start_second_2);
		startSecond2.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.btn_start_second:
			Intent intent = new Intent(this, SecondActivity.class);
			intent.putExtra("email", "xuelang201201@gmail.com");
			intent.putExtra("name", "κ��");
			
			Music music = new Music();
			music.path = "c:/music/Ұ��.mp3";
			music.name = "Ұ��";
			music.duration = 999666;
			
			Music music2 = new Music();
			music2.path = "c:/music/����.mp3";
			music2.name = "����";
			music.duration = 888777;
			
			ArrayList<Music> musics = new ArrayList<Music>();
			musics.add(music);
			musics.add(music2);
			
			intent.putParcelableArrayListExtra("musics", musics);
			
			startActivity(intent);
			break;

		case R.id.btn_dial:
			Intent intent2 = new Intent();
			intent2.setAction("android.intent.action.DIAL");
			Uri data = Uri.parse("tel://10086");
			intent2.setData(data);
			startActivity(intent2);
			break;
			
		case R.id.btn_call:
			Intent intent3 = new Intent(Intent.ACTION_CALL);
			intent3.setData(Uri.parse("tel://1008611"));
			startActivity(intent3);
			break;
			
		case R.id.btn_start_second_2:
			Intent intent4 = new Intent();
			intent4.setAction("cn.tedu.intent.action.SECOND");
			intent4.addCategory("cn.tedu.intent.category.HELLO");
			intent4.addCategory("cn.tedu.intent.category.HELLO_WORLD");
			startActivity(intent4);
			break;
		}
	}
}
