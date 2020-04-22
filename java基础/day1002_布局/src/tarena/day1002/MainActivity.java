package tarena.day1002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button bt1;
	private Button bt2;
	private Button bt3;
	private Button bt4;
	private Button bt5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setViews();
		setListeners();
	}

	private void setViews() {
		bt1 = (Button)findViewById(R.id.button1);
		bt2 = (Button)findViewById(R.id.button2);
		bt3 = (Button)findViewById(R.id.button3);
		bt4 = (Button)findViewById(R.id.button4);
		bt5 = (Button)findViewById(R.id.button5);	
	}

	private void setListeners() {
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						MainActivity.this,
						RelativeLayoutActivity.class);

				startActivity(intent);
			}
		});

		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						MainActivity.this,
						LinearLayoutActivity.class);
				
				startActivity(intent);
			}
		});

		bt3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						MainActivity.this,
						TableLayoutActivity.class);
				
				startActivity(intent);
			}
		});

		bt4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						MainActivity.this,
						FrameLayoutActivity.class);
				
				startActivity(intent);
			}
		});

		bt5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						MainActivity.this,
						GridLayoutActivity.class);
				
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
