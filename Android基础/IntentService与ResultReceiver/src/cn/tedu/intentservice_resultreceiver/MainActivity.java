package cn.tedu.intentservice_resultreceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
	
	private ProgressBar pbProgress;
	private TextView tvProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
		tvProgress = (TextView) findViewById(R.id.tv_progress);
		
		findViewById(R.id.btn_start).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, WorkService.class);
		InnerReceiver resuReceiver = new InnerReceiver();
		intent.putExtra("result_receiver", resuReceiver);
		startService(intent);
	}
	
	private class InnerReceiver extends ResultReceiver {

		public InnerReceiver() {
			super(new Handler());
		}
		
		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {
			// 参数1：结果的标识
			// 参数2：结果的数据
			if (resultCode == WorkService.UPDATE_PROGRESS) {
				int progress = resultData.getInt("progress");
				pbProgress.setProgress(progress);
				tvProgress.setText(progress + " / 100");
			}
		}
	}
}
