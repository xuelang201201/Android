package cn.tedu.bmobdemo.ui;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import cn.tedu.bmobdemo.R;
import cn.tedu.bmobdemo.bean.UserBean;

public class MainActivity extends Activity {

	Intent intent;
	
	@Bind(R.id.et_username)
	EditText etName;
	
	@Bind(R.id.et_password)
	EditText etPwd;
	
	@Bind(R.id.btn_login)
	Button btnLogin;
	
	@Bind(R.id.btn_register)
	Button btnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// b7a8d4a7b30d8caa3a2680bdb49ea5ce
		// 初始化 Bmob SDK
		// 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
		Bmob.initialize(this, "b7a8d4a7b30d8caa3a2680bdb49ea5ce");
		setContentView(R.layout.activity_main);

	}
	
	@OnClick(R.id.btn_login)
	public void login() {
		BmobQuery<UserBean> bmobQuery = new BmobQuery<UserBean>();
		
		bmobQuery.getObject(this, objectId, new GetListener<UserBean>() {
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onSuccess(UserBean arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@OnClick(R.id.btn_register)
	public void register() {
		intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}
}
