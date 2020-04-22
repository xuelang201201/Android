package tarena.day1101;

import tarena.day1101.service.FlightService;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.tarena.abs.datasource.entities.FlightPlan;

public class FlightSearchActivity extends Activity {
	
	private String[] cities;
	private Spinner sp;
	private Spinner sp2;
	private Button bt;
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_flight_search);
		
		setData(); //准备要显示的城市列表数据
		setViews();//获得控件并显示城市列表数据
		setListeners();
	}

	private void setData() {
		cities = new String[] {
				"北京","上海","重庆"
		};
	}

	private void setViews() {
		sp = (Spinner)findViewById(R.id.sp1);
		sp2 = (Spinner)findViewById(R.id.sp2);
		bt = (Button)findViewById(R.id.bt1);
		lv = (ListView)findViewById(R.id.lv1);
		
		//下拉菜单sp显示城市数组cities
		//一段神秘的代码
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_spinner_item, 
				cities);
		
		adapter.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		
		sp.setAdapter(adapter);
		sp2.setAdapter(adapter);
	}

	private void setListeners() {
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Inquiry(); //查询
			}
		});
	}
	
	protected void Inquiry() {
		/*
		 * 1.获得选择的出发、到达城市
		 *   赋给from, to
		 * 2.新建FlightService对象赋给变量serv
		 * 3.调用serv.findByCity(from, to)
		 *   获得它返回的FlightPlan[]数组赋给flights
		 * 4.新建String[]数组赋给a
		 *   数组长度是flights.length
		 * 5.遍历数组
		 *     6.取出i位置的FlightPlan对象赋给f
		 *     7.将对象f中封装的属性值，
		 *       连接成字符串赋值到a[i]
		 * 8.在lv显示a数组的数据
		 */
		
		String from = (String)sp.getSelectedItem();
		String to = (String)sp2.getSelectedItem();
		
		FlightService serv = new FlightService();
		FlightPlan[] flights = serv.findByCity(from, to);
		String[] a = new String[flights.length];
		for(int i=0; i<a.length; i++) {
			FlightPlan f = flights[i];
			a[i] = 
					"\n编号：" +f.getId()+
					"\n航班号：" +f.getNumb()+
					"\n出发机场：" +f.getFromAirport()+
					"\n到达机场：" +f.getToAirport()+
					"\n出发时间：" +f.getDepartureTime()+
					"\n达到时间：" +f.getArrivalTime()+
					"\n机型：" +f.getPlane()+
					"\n票价：" +f.getPrice();
		}
		
		//在lv中显示a数组的数据
		//一段神秘的代码
		ArrayAdapter<String> adapter = 
				new ArrayAdapter<String>(
						this,
						android.R.layout.simple_list_item_1,
						a);
		
		lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.flight_search, menu);
		return true;
	}

}
