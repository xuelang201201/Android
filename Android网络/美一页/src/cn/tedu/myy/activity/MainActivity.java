package cn.tedu.myy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.tedu.myy.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.iv_add_mould:
			Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
			intent.putExtra("isFirst", true);
			startActivity(intent);
			break;
		}
	}
}
