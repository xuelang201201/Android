package cn.tedu.simple_android_thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvTimer;
	private boolean isRunning;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvTimer = (TextView) findViewById(R.id.tv_timer);
		
		isRunning = true;
		InnerThread t = new InnerThread();
		t.start();
	}
	
	@Override
	protected void onDestroy() {
		isRunning = false;
		super.onDestroy();
	}
	
	private class InnerThread extends Thread {
		
		private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		private Date date = new Date();
		private Runner runner = new Runner();
		
		private class Runner implements Runnable {
			@Override
			public void run() {
				date.setTime(System.currentTimeMillis());
				tvTimer.setText(sdf.format(date));
			}
		}
		@Override
		public void run() {
			while(isRunning) {
				runOnUiThread(runner);
				
				// œﬂ≥Ã–›√ﬂ1√Î
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
