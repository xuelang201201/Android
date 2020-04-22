package tarena.day0904;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * 窗口启动时，
 * 系统会自动创建 MainActivity 实例，
 * 然后会立即调用它的 onCreate() 方法
 */
public class MainActivity extends Activity {
	/*
	 * 窗口父类 Activity 中定义的方法，
	 * 窗口子类中，必须重写这个方法
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/* 必须先调用父类的 onCreate() 方法
		 * 父类中有代码，检查子类是否调用过父类方法
		 */
		super.onCreate(savedInstanceState);
		/* 在当前窗口中，显示制定的界面
		*/
		setContentView(R.layout.activity_main);
		
		//获得界面上的按钮空间对象
		Button bt = (Button) findViewById(R.id.button1);
		
		//为按钮设置监听器
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//1.写纸条
				Intent intent = new Intent(
						MainActivity.this,      //当前窗口对象
						SecondActivity.class);  //目标对象类
				//2.传纸条
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
