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

//ϵͳ�Զ�����ʵ�������� onCreate() ����
public class MainActivity extends Activity {

	private String[] cities;
	private ListView lv;
	private Spinner sp;
	private Button bt;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//�ڵ�ǰ������ʾ����
		setContentView(R.layout.activity_main);
		
		//��ʾ����֮��
		//׼�����б�����ʾ������
		setData();
		//�ӵ�ǰ��ʾ�Ľ����ÿؼ����ڿؼ�����ʾ����
		setViews();
		//Ϊ�ؼ����ü�����
		setListeners();
	}

	private void setData() {
		cities = new String[] {
				"����","�Ϻ�","����",
				"����","�人","����",
				"����","����","����",
				"�׶�","ŦԼ","�׶�",
				"����","����","Ϥ��"};
	}

	private void setViews() {
		lv = (ListView)findViewById(R.id.listView1);
		sp = (Spinner)findViewById(R.id.spinner1);
		bt = (Button)findViewById(R.id.button1);
		tv = (TextView)findViewById(R.id.textView1);
		//��lv��sp����ʾcities�����е�����
		//һ�����صĴ��룬��׿�����ٽ���ʲô��˼
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
					int position,//�������Ŀ�±�
					long arg3) {
				
				Toast.makeText(
						MainActivity.this,
						"����±꣺"+position+"�����У�"+cities[position],
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
						"ѡ���±꣺"+position+"�����У�"+cities[position],
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
						"�����˰�ť",
						0).show();
				
				return true;
			
			}
		});
		
		tv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					tv.setText("����ѽ~~");
					break;
				case MotionEvent.ACTION_UP:
					tv.setText("����å��");
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
