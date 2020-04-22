package cn.tedu.key_event;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	private long lastClickBackTimeMillis;
	
	@Override
	public boolean onKeyDown(
			int keyCode, // ���ı���
			KeyEvent event // �������¼�
			) {
		// �жϰ��µ��ǲ��Ƿ��ؼ�
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �ж��ǲ�����������
			long currentClickBackTimeMillis = System.currentTimeMillis();
			if (currentClickBackTimeMillis - lastClickBackTimeMillis > 1000) { //��һ�ΰ�ʱ��϶�����1��
				Toast.makeText(MainActivity.this, "�ٰ�1���˳�Ӧ�ó���", Toast.LENGTH_SHORT).show();
				lastClickBackTimeMillis = currentClickBackTimeMillis;
				return true;
			}
		}
		
		return super.onKeyDown(keyCode, event);
	}
}
