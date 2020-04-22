package cn.tedu.listview_baseadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	/**
	 * 控件：显示联系人列表的ListView
	 */
	private ListView lvContacts;
	
	/**
	 * 联系人列表的Adapter
	 */
	private BaseAdapter adapter;
	
	/**
	 * 数据源：联系人的数据的List集合
	 */
	private List<Contact> contacts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化ListView
		lvContacts = (ListView) findViewById(R.id.lv_contacts);
		
		//初始化联系人的数据源
		contacts = new ArrayList<Contact>();
		Contact contact;
		
		contact = new Contact("梅西", "01012345610", R.drawable.headimage01);
		contacts.add(contact);
		
		contact = new Contact("内马尔", "01012345611", R.drawable.headimage02);
		contacts.add(contact);
		
		contact = new Contact("苏亚雷斯", "01012345609", R.drawable.headimage06);
		contacts.add(contact);
		
		contact = new Contact("哈维", "01012345606", R.drawable.headimage07);
		contacts.add(contact);
		
		contact = new Contact("伊涅斯塔", "01012345608", R.drawable.headimage11);
		contacts.add(contact);
		
		contact = new Contact("罗纳尔多", "01012345607", R.drawable.headimage12);
		contacts.add(contact);
		
		contact = new Contact("贝尔", "01012345605", R.drawable.headimage15);
		contacts.add(contact);
		
		contact = new Contact("本泽马", "01012345604", R.drawable.headimage16);
		contacts.add(contact);
		
		contact = new Contact("伊布", "01012345603", R.drawable.headimage18);
		contacts.add(contact);
		
		contact = new Contact("厄齐尔", "01012345602", R.drawable.headimage19);
		contacts.add(contact);
		
		contact = new Contact("阿奎罗", "01012345601", R.drawable.headimage11);
		contacts.add(contact);
		
		contact = new Contact("迪玛利亚", "01012345612", R.drawable.headimage12);
		contacts.add(contact);
		
		contact = new Contact("迪巴拉", "01012345613", R.drawable.headimage13);
		contacts.add(contact);
		
		contact = new Contact("博格巴", "01012345614", R.drawable.headimage14);
		contacts.add(contact);
		
		contact = new Contact("格策", "01012345615", R.drawable.headimage15);
		contacts.add(contact);
		
		contact = new Contact("穆勒", "01012345616", R.drawable.headimage16);
		contacts.add(contact);
		
		contact = new Contact("奥巴梅杨", "01012345617", R.drawable.headimage17);
		contacts.add(contact);
		
		contact = new Contact("罗伊斯", "01012345618", R.drawable.headimage18);
		contacts.add(contact);
		
		contact = new Contact("桑切斯", "01012345619", R.drawable.headimage19);
		contacts.add(contact);
		
		contact = new Contact("布冯", "01012345620", R.drawable.headimage20);
		contacts.add(contact);
		
		contact = new Contact("特尔斯特根", "01012345621", R.drawable.headimage01);
		contacts.add(contact);
		
		contact = new Contact("乔哈特", "01012345622", R.drawable.headimage02);
		contacts.add(contact);
		
		contact = new Contact("德布劳内", "01012345623", R.drawable.headimage03);
		contacts.add(contact);
		
		//不能直接新建BaseAdapter的对象，因为BaseAdapter是abstract的
		adapter = new ContactAdapter(this, contacts);
		
		//为ListView配置Adapter
		lvContacts.setAdapter(adapter);
	}
}
