package cn.tedu.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {


	private CheckBox cbCheckAll;
	private CheckBox cbPhoto1;
	private CheckBox cbPhoto2;
	private CheckBox cbPhoto3;
	private CheckBox cbPhoto4;
	private CheckBox cbPhoto5;
	private CheckBox cbPhoto6;
	private CheckBox cbPhoto7;
	private Button btnSubmit;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cbCheckAll = (CheckBox)findViewById(R.id.cb_check_all);
		cbPhoto1 = (CheckBox)findViewById(R.id.cb_photo_1);
		cbPhoto2 = (CheckBox)findViewById(R.id.cb_photo_2);
		cbPhoto3 = (CheckBox)findViewById(R.id.cb_photo_3);
		cbPhoto4 = (CheckBox)findViewById(R.id.cb_photo_4);
		cbPhoto5 = (CheckBox)findViewById(R.id.cb_photo_5);
		cbPhoto6 = (CheckBox)findViewById(R.id.cb_photo_6);
		cbPhoto7 = (CheckBox)findViewById(R.id.cb_photo_7);
		btnSubmit = (Button)findViewById(R.id.btnSubmit);

		InnerOnClickListener listener = new InnerOnClickListener();
		btnSubmit.setOnClickListener(listener);

		InnerOnCheckedChangeListener l = new InnerOnCheckedChangeListener();
		cbCheckAll.setOnCheckedChangeListener(l);

	}

	private class InnerOnCheckedChangeListener implements OnCheckedChangeListener {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if(cbCheckAll.isChecked()) {
				cbPhoto1.setChecked(true);
				cbPhoto2.setChecked(true);
				cbPhoto3.setChecked(true);
				cbPhoto4.setChecked(true);
				cbPhoto5.setChecked(true);
				cbPhoto6.setChecked(true);
				cbPhoto7.setChecked(true);
			} else {
				cbPhoto1.setChecked(false);
				cbPhoto2.setChecked(false);
				cbPhoto3.setChecked(false);
				cbPhoto4.setChecked(false);
				cbPhoto5.setChecked(false);
				cbPhoto6.setChecked(false);
				cbPhoto7.setChecked(false);
			}
		}

	}

	private class InnerOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			//准备提示的文字
			String result = "";

			//根据CheckBox选中状态,决定是否提示
			if(cbPhoto1.isChecked()) {
				result += cbPhoto1.getText().toString() + " ";
			}
			if(cbPhoto2.isChecked()) {
				result += cbPhoto2.getText().toString() + " ";
			}
			if(cbPhoto3.isChecked()) {
				result += cbPhoto3.getText().toString() + " ";
			}
			if(cbPhoto4.isChecked()) {
				result += cbPhoto4.getText().toString() + " ";
			}
			if(cbPhoto5.isChecked()) {
				result += cbPhoto5.getText().toString() + " ";
			}
			if(cbPhoto6.isChecked()) {
				result += cbPhoto6.getText().toString() + " ";
			}
			if(cbPhoto7.isChecked()) {
				result += cbPhoto7.getText().toString() + " ";
			}

			//提示文字
			Toast.makeText(MainActivity.this, result.trim(), Toast.LENGTH_LONG).show();//不要忘记 .show()
		}
	}
}
