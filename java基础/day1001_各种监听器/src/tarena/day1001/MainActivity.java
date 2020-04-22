package tarena.day1001;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//系统自动创建实例并调用 onCreate() 方法
public class MainActivity extends Activity {

	private String[] cities;
	private ListView lv;
	private Spinner sp;
	private Button bt;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//在当前窗口显示界面
		setContentView(R.layout.activity_main);
		
		//显示界面之后
		//准备在列表中显示的数据
		setData();
		//从当前显示的界面获得控件，在控件中显示数据
		setViews();
		//为控件设置监听器
		setListeners();
	}

	private void setData() {
		cities = new String[] {
				"北京","上海","广州",
				"深圳","武汉","重庆",
				"大连","杭州","东京",
				"首尔","纽约","伦敦",
				"巴黎","柏林","悉尼"};
	}

	private void setViews() {
		lv = (ListView)findViewById(R.id.listView1);
		sp = (Spinner)findViewById(R.id.spinner1);
		bt = (Button)findViewById(R.id.button1);
		tv = (TextView)findViewById(R.id.textView1);
		//在lv和sp中显示cities数组中的数据
		//一段神秘的代码，安卓部分再解释什么意思
		ArrayAdapter<String> adapter;
		
		adapter = new ArrayAdapter<String>(
				this, 
				android.R.layout.simple_list_item_1,
				cities);
		lv.setAdapter(adapter);
		
		adapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_spinner_item,
				cities);
		adapter.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
	}

	private void setListeners() {
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(
					AdapterView<?> arg0, 
					View arg1, 
					int position,//点击的条目下标
					long arg3) {
				
				Toast.makeText(
						MainActivity.this,
						"点击下标："+position+"，城市："+cities[position],
						Toast.LENGTH_SHORT).show();//Toast.LENGTH_SHORT=0,Toast.LENGTH_LONG=1
			}
		});
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(
					AdapterView<?> arg0,
					View arg1,
					int position,
					long arg3) {
				
				Toast.makeText(
						MainActivity.this,
						"选中下标："+position+"，城市："+cities[position],
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		bt.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				Toast.makeText(
						MainActivity.this,
						"长按了按钮",
						0).show();
				
				return true;
			
			}
		});
		
		tv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					tv.setText("好痒呀~~");
					break;
				case MotionEvent.ACTION_UP:
					tv.setText("臭流氓！");
					break;
				}
				
				return true;
				
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
