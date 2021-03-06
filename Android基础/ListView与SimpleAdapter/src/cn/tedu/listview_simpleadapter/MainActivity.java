package cn.tedu.listview_simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private ListView lvContacts;
	private SimpleAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lvContacts = (ListView) findViewById(R.id.lv_contacts);
		
		
		Context context = this;
		
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		Map<String, Object> item;
		
		item = new HashMap<String, Object>();
		item.put("name", "÷��");
		item.put("number", "01012345610");
		item.put("photo", R.drawable.headimage01);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "������");
		item.put("number", "01012345611");
		item.put("photo", R.drawable.headimage02);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "������˹");
		item.put("number", "01012345609");
		item.put("photo", R.drawable.headimage06);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "��ά");
		item.put("number", "01012345606");
		item.put("photo", R.drawable.headimage07);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "����˹��");
		item.put("number", "01012345608");
		item.put("photo", R.drawable.headimage11);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "���ɶ���");
		item.put("number", "01012345607");
		item.put("photo", R.drawable.headimage12);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "����");
		item.put("number", "01012345605");
		item.put("photo", R.drawable.headimage15);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "������");
		item.put("number", "01012345604");
		item.put("photo", R.drawable.headimage16);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "����");
		item.put("number", "01012345603");
		item.put("photo", R.drawable.headimage18);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "�����");
		item.put("number", "01012345602");
		item.put("photo", R.drawable.headimage19);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "������");
		item.put("number", "01012345601");
		item.put("photo", R.drawable.headimage11);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "��������");
		item.put("number", "01012345612");
		item.put("photo", R.drawable.headimage12);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "�ϰ���");
		item.put("number", "01012345613");
		item.put("photo", R.drawable.headimage13);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "�����");
		item.put("number", "01012345614");
		item.put("photo", R.drawable.headimage14);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "���");
		item.put("number", "01012345615");
		item.put("photo", R.drawable.headimage15);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "����");
		item.put("number", "01012345616");
		item.put("photo", R.drawable.headimage16);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "�°�÷��");
		item.put("number", "01012345617");
		item.put("photo", R.drawable.headimage17);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "����˹");
		item.put("number", "01012345618");
		item.put("photo", R.drawable.headimage18);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "ɣ��˹");
		item.put("number", "01012345619");
		item.put("photo", R.drawable.headimage19);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "����");
		item.put("number", "01012345620");
		item.put("photo", R.drawable.headimage20);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "�ض�˹�ظ�");
		item.put("number", "01012345621");
		item.put("photo", R.drawable.headimage01);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "�ǹ���");
		item.put("number", "01012345622");
		item.put("photo", R.drawable.headimage02);
		data.add(item);
		
		item = new HashMap<String, Object>();
		item.put("name", "�²�����");
		item.put("number", "01012345623");
		item.put("photo", R.drawable.headimage03);
		data.add(item);
		
		int resource = R.layout.contact_item;
		String[] from = {"name", "number", "photo"};
		int[] to =
			{
				R.id.tv_contact_item_name, 
				R.id.tv_contact_item_number,
				R.id.iv_contact_item_photo
			};
		
		adapter = new SimpleAdapter(context, data, resource, from, to);
		lvContacts.setAdapter(adapter);
	}
}
