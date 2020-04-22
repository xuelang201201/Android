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
		// ��ȡResultReceiver
		ResultReceiver resultReceiver = intent.getParcelableExtra("result_receiver");
		
		// ׼�����������
		Bundle resultData = new Bundle();
		
		// ѭ��
		for (int i = 0; i <= 100; i++) {
			// ׼������ı�ʶ
			int resultCode = UPDATE_PROGRESS;
			// ��װ����
			resultData.putInt("progress", i);
			// ִ�з���
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
