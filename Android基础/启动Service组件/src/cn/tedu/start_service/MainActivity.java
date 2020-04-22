package cn.tedu.start_service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	private View btnStartService;
	private View btnStopService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnStartService = findViewById(R.id.btn_start_service);
		btnStopService = findViewById(R.id.btn_stop_service);

		btnStartService.setOnClickListener(this);
		btnStopService.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start_service:
			Intent intent1 = new Intent(this, WorkService.class);
			startService(intent1);
			break;

		case R.id.btn_stop_service:
			Intent intent2 = new Intent(this, WorkService.class);
			stopService(intent2);
			break;
		}
	}
}
