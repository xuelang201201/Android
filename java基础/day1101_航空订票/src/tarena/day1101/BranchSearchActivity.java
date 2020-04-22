package tarena.day1101;

import tarena.day1101.service.BranchService;

import com.tarena.abs.datasource.entities.Branch;

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

public class BranchSearchActivity extends Activity {

	private String[] cities;
	private Spinner sp;
	private Button bt;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_branch_search);
		
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
		 * 1.���ѡ��ĳ��и���city
		 * 2.�½�BranchService���󸳸�serv
		 * 3.����serv.findByCity(city)
		 *   ��������ص�Branch[]����branches
		 * 4.�½�String[]���鸳��a
		 *   ���鳤����branches.length
		 * 5.����branches����
		 *     6.ȡ��iλ�õ�Branch���󸳸�b
		 *     7.������b�е��������ӳ��ַ�����
		 *       ��ֵ��a[i]
		 * 8.���б�ؼ�lv����ʾa���������	
		 */
		String city = (String) sp.getSelectedItem();
		
		BranchService serv = new BranchService();
		Branch[] branches = serv.findByCity(city);
		String[] a = new String[branches.length];
		
		for(int i=0; i<a.length; i++) {
			Branch b = branches[i];
			a[i] = 
					"\n��ţ�"+b.getId()+
					"\n���ƣ�"+b.getName()+
					"\n��ַ��"+b.getAddress()+
					"\n�绰��"+b.getTelephone()+
					"\n���棺"+b.getFax();
		}
		
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
		getMenuInflater().inflate(R.menu.branch_search, menu);
		return true;
	}

}
