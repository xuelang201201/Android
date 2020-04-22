package cn.tedu.activity_life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d("tedu", "MainActivity.onCreate()");
		View btnStartSecond = findViewById(R.id.btn_start_second);
		btnStartSecond.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
	}
	
	@Override
	protected void onStart() {
		Log.d("tedu", "MainActivity.onStart()");
		super.onStart();
	}
	
	@Override
	protected void onRestart() {
		Log.d("tedu", "MainActivity.onReStart()");
		super.onRestart();
	}
	
	@Override
	protected void onResume() {
		Log.d("tedu", "MainActivity.onResume()");
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		Log.d("tedu", "MainActivity.onPause()");
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		Log.d("tedu", "MainActivity.onStop()");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		Log.d("tedu", "MainActivity.onDestroy()");
		super.onDestroy();
	}
}
