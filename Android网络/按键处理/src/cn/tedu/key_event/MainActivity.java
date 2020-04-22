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
			int keyCode, // 键的编码
			KeyEvent event // 按键的事件
			) {
		// 判断按下的是不是返回键
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 判断是不是连续按下
			long currentClickBackTimeMillis = System.currentTimeMillis();
			if (currentClickBackTimeMillis - lastClickBackTimeMillis > 1000) { //第一次按时间肯定大于1秒
				Toast.makeText(MainActivity.this, "再按1次退出应用程序", Toast.LENGTH_SHORT).show();
				lastClickBackTimeMillis = currentClickBackTimeMillis;
				return true;
			}
		}
		
		return super.onKeyDown(keyCode, event);
	}
}
