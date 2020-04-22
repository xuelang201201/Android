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
		//ȥ��������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_flight_search);
		
		setData(); //׼��Ҫ��ʾ�ĳ����б�����
		setViews();//��ÿؼ�����ʾ�����б�����
		setListeners();
	}

	private void setData() {
		cities = new String[] {
				"����","�Ϻ�","����"
		};
	}

	private void setViews() {
		sp = (Spinner)findViewById(R.id.sp1);
		sp2 = (Spinner)findViewById(R.id.sp2);
		bt = (Button)findViewById(R.id.bt1);
		lv = (ListView)findViewById(R.id.lv1);
		
		//�����˵�sp��ʾ��������cities
		//һ�����صĴ���
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
				Inquiry(); //��ѯ
			}
		});
	}
	
	protected void Inquiry() {
		/*
		 * 1.���ѡ��ĳ������������
		 *   ����from, to
		 * 2.�½�FlightService���󸳸�����serv
		 * 3.����serv.findByCity(from, to)
		 *   ��������ص�FlightPlan[]���鸳��flights
		 * 4.�½�String[]���鸳��a
		 *   ���鳤����flights.length
		 * 5.��������
		 *     6.ȡ��iλ�õ�FlightPlan���󸳸�f
		 *     7.������f�з�װ������ֵ��
		 *       ���ӳ��ַ�����ֵ��a[i]
		 * 8.��lv��ʾa���������
		 */
		
		String from = (String)sp.getSelectedItem();
		String to = (String)sp2.getSelectedItem();
		
		FlightService serv = new FlightService();
		FlightPlan[] flights = serv.findByCity(from, to);
		String[] a = new String[flights.length];
		for(int i=0; i<a.length; i++) {
			FlightPlan f = flights[i];
			a[i] = 
					"\n��ţ�" +f.getId()+
					"\n����ţ�" +f.getNumb()+
					"\n����������" +f.getFromAirport()+
					"\n���������" +f.getToAirport()+
					"\n����ʱ�䣺" +f.getDepartureTime()+
					"\n�ﵽʱ�䣺" +f.getArrivalTime()+
					"\n���ͣ�" +f.getPlane()+
					"\nƱ�ۣ�" +f.getPrice();
		}
		
		//��lv����ʾa���������
		//һ�����صĴ���
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
