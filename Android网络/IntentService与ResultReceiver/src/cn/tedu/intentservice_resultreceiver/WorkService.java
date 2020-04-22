package cn.tedu.intentservice_resultreceiver;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

public class WorkService extends IntentService {

	public WorkService() {
		super("");
	}
	
	public static final int UPDATE_PROGRESS = 1;

	@Override
	protected void onHandleIntent(Intent intent) {
		// 获取ResultReceiver
		ResultReceiver resultReceiver = intent.getParcelableExtra("result_receiver");
		
		// 准备结果的数据
		Bundle resultData = new Bundle();
		
		// 循环
		for (int i = 0; i <= 100; i++) {
			// 准备结果的标识
			int resultCode = UPDATE_PROGRESS;
			// 封装数据
			resultData.putInt("progress", i);
			// 执行发送
			resultReceiver.send(resultCode, resultData);
			Log.d("tedu", "i=" + i);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
