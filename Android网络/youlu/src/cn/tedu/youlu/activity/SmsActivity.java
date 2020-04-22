package cn.tedu.youlu.activity;

import java.util.List;

import cn.tedu.youlu.adapter.SmsAdapter;
import cn.tedu.youlu.entity.Conversation;
import cn.tedu.youlu.entity.Sms;
import cn.tedu.youlu.presenter.ISmsPresenter;
import cn.tedu.youlu.presenter.impl.SmsPresenter;
import cn.tedu.youlu.view.ISmsView;

import com.tarena.youlu.R;
import com.tarena.youlu.R.layout;
import com.tarena.youlu.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class SmsActivity extends Activity implements ISmsView{
	private ISmsPresenter presenter;
	private List<Sms> smss;
	private ListView listView;
	
	public SmsActivity() {
		presenter = new SmsPresenter(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		setViews();
		//获取传递进来的conversation对象
		Conversation con=(Conversation)getIntent().getSerializableExtra("conversation");
		int threadId=con.getId();
		//加载所有短信息
		presenter.loadSmsByThreadId(threadId);
	}

	private void setViews() {
		listView = (ListView) findViewById(R.id.listView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sms, menu);
		return true;
	}

	@Override
	public void setList(List<Sms> smss) {
		this.smss = smss;
	}

	@Override
	public void showList() {
		Log.i("info", "短信数据："+smss.toString());
		//自定义Adapter  呈现界面
		SmsAdapter adapter = new SmsAdapter(this, smss);
		listView.setAdapter(adapter);
	}

}
