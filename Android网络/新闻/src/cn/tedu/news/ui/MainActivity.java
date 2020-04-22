package cn.tedu.news.ui;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cn.tedu.news.R;
import cn.tedu.news.entity.MyNews;
import cn.tedu.news.util.HttpUtil;
import cn.tedu.news.util.HttpUtil.OnGetNewsListener;

public class MainActivity extends Activity {

	private List<String> news;
	private ListView lvNews;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		HttpUtil.getNews(this, new OnGetNewsListener() {
			
			@Override
			public void OnSuccess(MyNews myNews) {
				news = myNews.getResult();
				lvNews = (ListView) findViewById(R.id.lv_news);
				adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, news);
				lvNews.setAdapter(adapter);
			}
		});
	}
}
