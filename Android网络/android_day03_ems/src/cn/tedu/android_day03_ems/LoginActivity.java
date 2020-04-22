package cn.tedu.android_day03_ems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText etName;
	private EditText etPwd;
	private EditText etCode;
	private ImageView ivCode;
	private String JSESSIONID;
	private Bitmap bitmap;

	// 声明handler
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_IMAGECODE_LOAD_SUCCESS:
				// 给ImageView更新图片
				if (bitmap != null) {
					ivCode.setImageBitmap(bitmap);
				}
				break;

			case HANDLER_LOGIN_SUCCESS:
				Toast.makeText(LoginActivity.this, "恭喜，登录成功！", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(LoginActivity.this, ListEmpActivity.class);
				startActivity(intent);
				finish();
				break;

			case HANDLER_LOGIN_FAIL:
				Toast.makeText(LoginActivity.this, "抱歉，登录失败：" + msg.obj, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	public static final int HANDLER_IMAGECODE_LOAD_SUCCESS = 1;
	public static final int HANDLER_LOGIN_SUCCESS = 2;
	public static final int HANDLER_LOGIN_FAIL = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setViews();
		// 去服务端下载验证码图片
		new Thread() {
			public void run() {
				try {
					loadCodeImage();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * 在工作线程中执行下载验证码图片
	 * @throws IOException
	 */
	protected void loadCodeImage() throws IOException {
		// 1. HttpClient
		HttpClient client = new DefaultHttpClient();
		// 2. HttpGet
		String url = "http://172.16.44.131:8080/ems/getCode.do";
		HttpGet get = new HttpGet(url);
		// 3. execute()
		HttpResponse resp = client.execute(get);
		// 4. 解析响应，获取图片
		byte[] bytes = EntityUtils.toByteArray(resp.getEntity());
		// 获取响应数据包中的Set-Cookie消息头
		Header header = resp.getFirstHeader("Set-Cookie");
		Log.i("info", "header:[" + header.getName() + ", " + header.getValue() + "]");
		// 获取JSESSIONID后，保存到客户端
		String val = header.getValue();
		//val:   JSESSIONID=abcd1234; Path=/ems
		JSESSIONID = val.split(";")[0];
		// 把bytes转成Bitmap
		bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		// 发消息给handler更新ImageView
		handler.sendEmptyMessage(HANDLER_IMAGECODE_LOAD_SUCCESS);
	}

	/**
	 * 初始化控件
	 */
	private void setViews() {
		etName = (EditText) findViewById(R.id.et_login_emp_username);
		etPwd = (EditText) findViewById(R.id.et_login_emp_age);
		etCode = (EditText) findViewById(R.id.et_login_emp_code);
		ivCode = (ImageView) findViewById(R.id.iv_login_emp_code);
	}

	/**
	 * 处理点击事件
	 */
	public void doClick (View v) {
		switch (v.getId()) {
		case R.id.btn_to_register: // 跳转到注册界面
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			break;
		
		case R.id.btn_login_emp: // 登录业务
			new Thread() {
				public void run() {
					try {
						login();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}.start();
			break;

		case R.id.iv_login_emp_code: // 换一张验证码
			new Thread() {
				public void run() {
					try {
						loadCodeImage();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			break;
		}
	}

	/**
	 * 工作线程中执行，用户登录
	 * @throws IOException
	 * @throws JSONException
	 */
	private void login() throws IOException, JSONException {
		// 1. HttpClient
		HttpClient client = new DefaultHttpClient();
		// 2. HttpPost
		String url = "http://172.16.44.131:8080/ems/login.do";
		HttpPost post = new HttpPost(url);
		// 3. 设置header HttpEntity
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setHeader("Cookie", JSESSIONID);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("loginname", etName.getText().toString()));
		list.add(new BasicNameValuePair("password", etPwd.getText().toString()));
		list.add(new BasicNameValuePair("code", etCode.getText().toString()));
		HttpEntity reqEntity = new UrlEncodedFormEntity(list, "utf-8");
		post.setEntity(reqEntity);
		// 4. 发送请求
		HttpResponse resp = client.execute(post);
		// 5. 解析响应 json
		String json = EntityUtils.toString(resp.getEntity());
		// json:{result:ok} {result:error, msg:xxx} 
		JSONObject jsonObj = new JSONObject(json);
		// 发消息给handler，更新UI
		if (jsonObj.getString("result").equals("ok")) {
			// 登录成功
			handler.sendEmptyMessage(HANDLER_LOGIN_SUCCESS);
		} else {
			// 登录失败
			Message msg = new Message();
			msg.what = HANDLER_LOGIN_FAIL;
			msg.obj = jsonObj.getString("msg");
			handler.sendMessage(msg);
		}
	}

}
