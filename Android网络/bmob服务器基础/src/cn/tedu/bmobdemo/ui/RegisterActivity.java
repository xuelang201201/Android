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
	ImageView ivAvatar; // ͷ��
	
	@Bind(R.id.et_register_username)
	EditText etUsername; // �û���
	
	@Bind(R.id.et_register_pwd)
	EditText etPassword; // ����
	
	@Bind(R.id.rg_register_gender)
	RadioGroup rgGender; // �Ա�
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// ��������@bindע��������������R.layout.activity_register�����ļ�
		ButterKnife.bind(this);
	}
	
	@OnClick(R.id.btn_register_submit)
	public void submit(View v) {
		// �ռ��û���������ݣ�����һ�����󱣴浽������
		UserBean userBean = new UserBean();
		
		userBean.setUrl("");
		
		userBean.setUsername(etUsername.getText().toString());
		String password = etPassword.getText().toString();
		// ��password��md5����
		String md5 = new String(Hex.encodeHex(DigestUtils.sha(password))).toUpperCase();
		userBean.setPassword(md5);
		
		if (rgGender.getCheckedRadioButtonId() == R.id.radio_m) {
			userBean.setGender(true);
		} else {
			userBean.setGender(false);
		}
		
		// ��userBean���浽������
		userBean.save(this, new SaveListener() {
			
			@Override
			public void onSuccess() {
				Toast.makeText(RegisterActivity.this, "����ɹ�", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(RegisterActivity.this, "����ʧ�ܣ������룺" + arg0 + ": " + arg1, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
