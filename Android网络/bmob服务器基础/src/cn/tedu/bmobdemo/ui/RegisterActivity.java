package cn.tedu.bmobdemo.ui;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.listener.SaveListener;
import cn.tedu.bmobdemo.R;
import cn.tedu.bmobdemo.bean.UserBean;

public class RegisterActivity extends Activity {

	// properties --> Java Compiler --> Annotation Processing --> Enable.. --> Factory Path
	@Bind(R.id.iv_register_avatar)
	ImageView ivAvatar; // 头像
	
	@Bind(R.id.et_register_username)
	EditText etUsername; // 用户名
	
	@Bind(R.id.et_register_pwd)
	EditText etPassword; // 密码
	
	@Bind(R.id.rg_register_gender)
	RadioGroup rgGender; // 性别
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// 所有利用@bind注入的组件都是来自R.layout.activity_register布局文件
		ButterKnife.bind(this);
	}
	
	@OnClick(R.id.btn_register_submit)
	public void submit(View v) {
		// 收集用户输入的内容，创建一个对象保存到服务器
		UserBean userBean = new UserBean();
		
		userBean.setUrl("");
		
		userBean.setUsername(etUsername.getText().toString());
		String password = etPassword.getText().toString();
		// 对password做md5编码
		String md5 = new String(Hex.encodeHex(DigestUtils.sha(password))).toUpperCase();
		userBean.setPassword(md5);
		
		if (rgGender.getCheckedRadioButtonId() == R.id.radio_m) {
			userBean.setGender(true);
		} else {
			userBean.setGender(false);
		}
		
		// 把userBean保存到服务器
		userBean.save(this, new SaveListener() {
			
			@Override
			public void onSuccess() {
				Toast.makeText(RegisterActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(RegisterActivity.this, "保存失败，错误码：" + arg0 + ": " + arg1, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
