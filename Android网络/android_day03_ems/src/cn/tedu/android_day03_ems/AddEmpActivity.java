package cn.tedu.android_day03_ems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddEmpActivity extends Activity {
	
	private EditText etAddEmpName;
	private EditText etAddEmpAge;
	private EditText etAddEmpSalary;
	private RadioGroup rgAddEmpGender;
	
	// 声明Handler
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_ADD_EMP_SUCCESS:
				Toast.makeText(AddEmpActivity.this, "恭喜，添加数据成功！", Toast.LENGTH_SHORT).show();
				finish();
				break;

			case HANDLER_ADD_EMP_FAIL:
				Toast.makeText(AddEmpActivity.this, "抱歉，添加数据失败：" + msg.obj, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	public static final int HANDLER_ADD_EMP_SUCCESS = 1;
	public static final int HANDLER_ADD_EMP_FAIL = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_emp);
		// 初始化控件
		setViews();
	}

	// 初始化控件
	private void setViews() {
		etAddEmpName = (EditText) findViewById(R.id.et_add_emp_name);
		etAddEmpAge = (EditText) findViewById(R.id.et_add_emp_age);
		etAddEmpSalary = (EditText) findViewById(R.id.et_add_emp_salary);
		rgAddEmpGender = (RadioGroup) findViewById(R.id.rg_add_emp_gender);
	}

	/*
	 * 监听
	 */
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add_emp: // 添加员工
			new Thread() {
				public void run() {
					try {
						addEmp();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}.start();
			break;
		}
	}

	/**
	 * 发送http请求
	 * @throws IOException 
	 * @throws JSONException 
	 */
	private void addEmp() throws IOException, JSONException {
		// 1. HttpClient
		HttpClient client = new DefaultHttpClient();
		// 2. HttpPost
		String url = "http://172.16.44.131:8080/ems/addEmp";
		HttpPost post = new HttpPost(url);
		// 3. 设置post的消息头、请求参数
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("name", etAddEmpName.getText().toString()));
		list.add(new BasicNameValuePair("age", etAddEmpAge.getText().toString()));
		list.add(new BasicNameValuePair("salary", etAddEmpSalary.getText().toString()));
		list.add(new BasicNameValuePair("gender", rgAddEmpGender.getCheckedRadioButtonId() == R.id.radio_add_emp_M ? "M" : "F"));
		HttpEntity reqEntity = new UrlEncodedFormEntity(list, "utf-8");
		post.setEntity(reqEntity);
		// 4. 发送请求
		HttpResponse resp = client.execute(post);
		// 5. 接收响应，并且解析json
		// 获取状态码
		if(resp.getStatusLine().getStatusCode() == 200) {
			// 6. 提示用户结果
			String json = EntityUtils.toString(resp.getEntity());
			JSONObject jsonObj = new JSONObject(json);
			String res = jsonObj.getString("result");
			if (res.equals("ok")) {// 添加成功
				handler.sendEmptyMessage(HANDLER_ADD_EMP_SUCCESS);
			} else { // 添加失败
				Message msg = new Message();
				msg.what = HANDLER_ADD_EMP_FAIL;
				msg.obj = jsonObj.getString("msg");
				handler.sendMessage(msg);
			}
		} else {
			Log.e("info", "状态码不是200，赶紧检查一下URL地址");
		}
	}
}
