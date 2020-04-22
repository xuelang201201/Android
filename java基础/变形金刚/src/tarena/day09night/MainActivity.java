package tarena.day09night;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView tv;
	private Transformer transformer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView)findViewById(R.id.textView1);
	}
	
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			bxjg();
			break;
		case R.id.button2:
			ytj();
			break;
		case R.id.button3:
			ak47();
			break;
		case R.id.button4:
			lyb();
			break;
		case R.id.button5:
			jingong();
			break;
		}
	}

	private void jingong() {
		transformer.attack(tv);
	}

	private void lyb() {
		transformer.setWeapon(new Lyb());
		tv.setText("接入了狼牙棒");
	}

	private void ak47() {
		transformer.setWeapon(new AK47());
		tv.setText("接入了AK47");
	}

	private void ytj() {
		transformer.setWeapon(new Sword());
		tv.setText("接入了倚天剑");
	}

	private void bxjg() {
		transformer = new Transformer();
		tv.setText("创建了变形金刚");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
