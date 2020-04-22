package tarena.arrayadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	/**
	 * ListView控件
	 */
	private ListView lvContracts;
	
	/**
	 * ListView的Adapter
	 */
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化ListView控件
		lvContracts = (ListView)findViewById(R.id.lv_contacts);
		
		//创建ArrayAdapter
		adapter = new ArrayAdapter<String>(this, R.layout.contact_item, getContacts());
		
		//为ListView控件设置Adapter
		lvContracts.setAdapter(adapter);
	}
	
	/**
	 * 获取联系人的数据源
	 * @return 联系人的数据源
	 */
	private List<String> getContacts() {
		
		List<String> contacts = new ArrayList<String>();
		
		contacts.add("Messi");
		contacts.add("Neymar");
		contacts.add("Suarez");
		contacts.add("Xavi");
		contacts.add("Iniesta");
		contacts.add("Ronaldo");
		contacts.add("Bale");
		contacts.add("Benzema");
		contacts.add("Bravo");
		contacts.add("Alex");
		contacts.add("Ozil");
		contacts.add("Heart");
		contacts.add("Hazard");
		contacts.add("Pogba");
		contacts.add("Dybala");
		contacts.add("Ibrahimovic");
		
		return contacts;
	}
}
