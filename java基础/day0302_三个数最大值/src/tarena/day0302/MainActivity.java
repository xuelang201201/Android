package tarena.day0302;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void doClick(View view) {
		/*
		 * 1.获得输入框和文本控件
		 * 	 赋给et1，et2，et3，tv变量
		 * 2.从三个输入框获得输入的值，
		 * 	 再转成int，赋给a，b，c
		 * 3.求a，b最大值赋给max
		 * 4.求max，c最大值赋给max
		 * 5.在文本控件tv中显示max的值
		 */
		EditText et1 = (EditText) findViewById(R.id.editText1);
		EditText et2 = (EditText) findViewById(R.id.editText2);
		EditText et3 = (EditText) findViewById(R.id.editText3);
		TextView tv = (TextView) findViewById(R.id.textView1);
		
		int a = Integer.parseInt(et1.getText().toString());
		int b = Integer.parseInt(et2.getText().toString());
		int c = Integer.parseInt(et3.getText().toString());
		
		/*int max = a > b ? a : b;
		max = max > c ? max : c;*/
		
		int max = a > b ? (a>c?a:c) : (b>c?b:c);
	
		tv.setText("最大值："+max);
	}

}
