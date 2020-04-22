package cn.tedu.asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	private ProgressBar pbProgress;
	private TextView tvProgress;
	private InnerTask task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
		tvProgress = (TextView) findViewById(R.id.tv_progress);
		
		findViewById(R.id.btn_start).setOnClickListener(this);
		findViewById(R.id.btn_stop).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
			task = new InnerTask();
			task.execute(); // 执行
			break;

		case R.id.btn_stop:
			task.cancel(true); // 取消
			onDestroy();
			break;
		}
	}
	
	private class InnerTask extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			for (int i = 1; i <= 100; i++) {
				Log.i("tedu", "InnerTask.doInBackground(), thread id -> " + Thread.currentThread().getId() + ", i -> " + i);
				// 发布进度值
				publishProgress(i);
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			pbProgress.setProgress(values[0]);
			tvProgress.setText( values[0] + " / 100");
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			Toast.makeText(MainActivity.this, "任务执行完成！", Toast.LENGTH_LONG).show();
		}
	}
}
