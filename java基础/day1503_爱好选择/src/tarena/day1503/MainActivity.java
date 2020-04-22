package tarena.day1503;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;
	private ArrayList<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView)findViewById(R.id.tv1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void doClick(View view) {
		CheckBox cb = (CheckBox) view;
		String s = cb.getText().toString();
		
		//如果多选钮是勾选状态
		if(cb.isChecked()) {
			list.add(s);
		} else { //取消状态
			list.remove(s);
		}
		
		tv.setText(""); //清除显示内容
		for(int i=0; i<list.size(); i++) {
			if(i != 0) {
				tv.append("，");
			}
			tv.append(list.get(i));
		}
	}

}
