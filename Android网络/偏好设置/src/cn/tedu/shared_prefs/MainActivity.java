package cn.tedu.shared_prefs;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	private EditText etUsername;
	private EditText etEmail;
	private EditText etCity;
	private Button btnSave;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etUsername = (EditText) findViewById(R.id.et_username);
		etEmail = (EditText) findViewById(R.id.et_email);
		etCity = (EditText) findViewById(R.id.et_city);
		btnSave = (Button) findViewById(R.id.btn_save);
		
		btnSave.setOnClickListener(this);
		
		// --- 读取偏好设置 ---
		// 1. 读取偏好设置
		SharedPreferences sp = getSharedPreferences("userinfo", MODE_PRIVATE);
		String username = sp.getString("username", null);
		String email = sp.getString("email", null);
		String city = sp.getString("city", null);
		
		// 2. 将读取的数据设置到各EditText中
		etUsername.setText(username);
		etEmail.setText(email);
		etCity.setText(city);
	}

	@Override
	public void onClick(View v) {
		// 1. 获取各EditText中用户输入的数据
		String username = etUsername.getText().toString().trim();
		String email = etEmail.getText().toString().trim();
		String city = etCity.getText().toString().trim();
		
		// 2. 验证数据的有效性
		    // （跳过）
		
		// 3. 保存数据
		SharedPreferences sp = getSharedPreferences("userinfo", MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("username", username);
		editor.putString("email", email);
		editor.putString("city", city);
		// 提交保存
		editor.commit();
		
		// 4. 提示保存成功
		Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
	}
}
