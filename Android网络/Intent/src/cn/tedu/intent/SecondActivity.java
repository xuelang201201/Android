package cn.tedu.intent;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		String email = intent.getStringExtra("email");
		// Music music = intent.getParcelableExtra("music");
		ArrayList<Music> musics = intent.getParcelableArrayListExtra("musics");
		Toast.makeText(this, "name=" + name + ", email=" + email + ", musics=" + musics, Toast.LENGTH_LONG).show();
	}
}
