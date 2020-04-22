package cn.tedu.activity_life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends Activity implements View.OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Log.d("tedu", "MainActivity.onCreate()");
		View btnStartMain = findViewById(R.id.btn_start_main);
		btnStartMain.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	@Override
	protected void onStart() {
		Log.d("tedu", "SecondActivity@" + hashCode() + ".onStart()");
		super.onStart();
	}
	
	@Override
	protected void onRestart() {
		Log.d("tedu", "SecondActivity@" + hashCode() + ".onReStart()");
		super.onRestart();
	}
	
	@Override
	protected void onResume() {
		Log.d("tedu", "SecondActivity@" + hashCode() + ".onResume()");
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		Log.d("tedu", "SecondActivity@" + hashCode() + ".onPause()");
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		Log.d("tedu", "SecondActivity@" + hashCode() + ".onStop()");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		Log.d("tedu", "SecondActivity@" + hashCode() + ".onDestroy()");
		super.onDestroy();
	}
}
