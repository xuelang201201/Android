package cn.tedu.message_handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

	private static ProgressBar pbProgress;
	private static TextView tvProgress;
	private Button btnStart;
	private Handler handler;
	
	private static final int MESSAGE_UPDATE_PROGRESS = 998;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
		tvProgress = (TextView) findViewById(R.id.tv_progress);
		btnStart = (Button) findViewById(R.id.btn_start);
		
		btnStart.setOnClickListener(this);
		handler = new InnerHandler(new InnerHandlerCallback());
	}

	private class InnerHandlerCallback implements Handler.Callback {

		@Override
		public boolean handleMessage(Message msg) {
			if (msg.what == MESSAGE_UPDATE_PROGRESS) {
				// 根据参数Message中的arg1属性设置进度
				pbProgress.setProgress(msg.arg1);
				tvProgress.setText(msg.arg1 + " / 100");
				Log.i("tedu", "InnerHandlerCallback.handleMessage()");
			}
			return true; // 已经消费
		}
		
	}
	
	private static class InnerHandler extends Handler {
		
		public InnerHandler(Callback callback) {
			super(callback);
		}

		@Override
		public void handleMessage(Message msg) {
			// 根据参数Message中的arg1属性设置进度
			if (msg.what == MESSAGE_UPDATE_PROGRESS) {
				// 根据参数Message中的arg1属性设置进度
				pbProgress.setProgress(msg.arg1);
				tvProgress.setText(msg.arg1 + " / 100");
				Log.d("tedu", "InnerHandler.handleMessage()");
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		new UpdateThread().start();
	}
	
	private class UpdateThread extends Thread {
		
		int i;
		
		@Override
		public void run() {
			for (i = 1; i <= 100; i++) {
				// 创建消息
				Message msg = Message.obtain(handler, new Runnable() {
					
					@Override
					public void run() {
						pbProgress.setProgress(i);
					}
				});
				// 标识消息的类型
				msg.what = MESSAGE_UPDATE_PROGRESS;
				// 在消息中封装数据
				msg.arg1 = i;
				// 发出消息
				// handler.sendMessage(msg);
				msg.sendToTarget();
				
				// 精简版
				// Message.obtain(handler, MESSAGE_UPDATE_PROGRESS, i, 0).sendToTarget();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
