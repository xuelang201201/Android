package tarena.day1101;

import tarena.day1101.service.UserService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et1;
	private EditText et2;
	private ImageButton ib1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		setViews();
		setListeners();	
	}

	private void setViews() {
		et1 = (EditText)findViewById(R.id.et1);
		et2 = (EditText)findViewById(R.id.et2);
		ib1 = (ImageButton)findViewById(R.id.ib1);
	}

	private void setListeners() {
		ib1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Login(); //登录
			}
		});
	}

	protected void Login() {
		/* //面向过程的代码
		 * 1.从输入框获取用户名密码
		 * 2.从服务器端获得系统所有的用户信息
		 * 3.遍历比对
		 *     4.找到相同的用户名密码
		 *         5.登录成功，跳到下一个窗口
		 *         6.方法结束
		 * 7.登录失败，显示失败提示
		 * 
		 * -------------------------
		 * //面向对象的代码
		 * 1.从输入框获取用户名密码
		 * 2.新建UserService 实例赋给serv
		 * 3.调用serv.login(用户名，密码)
		 *   并得到它的返回值赋给b
		 * 4.如果 b 是true
		 *     5.跳转窗口
		 * 6.否则
		 *     7.显示失败提示
		 */
		
		String n = et1.getText().toString();
		String p = et2.getText().toString();
		UserService serv = new UserService();
		boolean b = serv.login(n, p);
		if(b) {
			startActivity(new Intent(this, MenuActivity.class));
			//销毁登录窗口
			finish(); //从Activity父类继承的方法
		} else {
			Toast.makeText(this, "登录失败，请检查用户名密码", 0).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
