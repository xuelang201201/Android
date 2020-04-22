package cn.tedu.listview_section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	/**
	 * 数据源：联系人列表的数据源
	 */
	private List<Contact> contacts;
	/**
	 * ListView：联系人列表
	 */
	private ListView lvContacts;
	/**
	 * Adapter：联系人列表的Adapter
	 */
	private ContactAdapter contactAdapter;
	/**
	 * ListView：右侧字母导航
	 */
	private ListView lvSections;
	/**
	 * Adapter：右侧字母导航的Adapter
	 */
	private ArrayAdapter<String> sectionAdapter;
	/**
	 * 数据源：右侧字母导航的数据源
	 */
	private String[] sections = "#,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,#".split(",");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		contacts = new ArrayList<Contact>();
		Contact contact;

		contact = new Contact("Messi", "10000", R.drawable.headimage01);
		contacts.add(contact);

		contact = new Contact("Neymar", "10001", R.drawable.headimage02);
		contacts.add(contact);

		contact = new Contact("Suarez", "10002", R.drawable.headimage03);
		contacts.add(contact);

		contact = new Contact("C.Ronaldo", "10003", R.drawable.headimage04);
		contacts.add(contact);

		contact = new Contact("Bale", "10004", R.drawable.headimage05);
		contacts.add(contact);

		contact = new Contact("Benzema", "10005", R.drawable.headimage06);
		contacts.add(contact);

		contact = new Contact("Lewandowski", "10006", R.drawable.headimage07);
		contacts.add(contact);

		contact = new Contact("Higuain", "10007", R.drawable.headimage08);
		contacts.add(contact);

		contact = new Contact("Aguero", "10008", R.drawable.headimage09);
		contacts.add(contact);

		contact = new Contact("Dybala", "10009", R.drawable.headimage10);
		contacts.add(contact);

		contact = new Contact("D.Costa", "10010", R.drawable.headimage11);
		contacts.add(contact);

		contact = new Contact("Firmino", "10011", R.drawable.headimage12);
		contacts.add(contact);

		contact = new Contact("A.Robben", "10012", R.drawable.headimage13);
		contacts.add(contact);

		contact = new Contact("Xavi", "10013", R.drawable.headimage14);
		contacts.add(contact);

		contact = new Contact("Iniesta", "10014", R.drawable.headimage15);
		contacts.add(contact);

		contact = new Contact("Busquets", "10015", R.drawable.headimage16);
		contacts.add(contact);

		contact = new Contact("Rakitic", "10016", R.drawable.headimage17);
		contacts.add(contact);

		contact = new Contact("Pogba", "10017", R.drawable.headimage18);
		contacts.add(contact);

		contact = new Contact("Sterling", "10018", R.drawable.headimage19);
		contacts.add(contact);

		contact = new Contact("Hazard", "10019", R.drawable.headimage20);
		contacts.add(contact);

		contact = new Contact("Yaya Toure", "10020", R.drawable.headimage10);
		contacts.add(contact);

		contact = new Contact("Maradona", "10021", R.drawable.headimage15);
		contacts.add(contact);

		contact = new Contact("Pele", "10022", R.drawable.headimage13);
		contacts.add(contact);

		contact = new Contact("Batistuta", "10023", R.drawable.headimage04);
		contacts.add(contact);

		contact = new Contact("Puyol", "10024", R.drawable.headimage16);
		contacts.add(contact);

		contact = new Contact("Shevchenko", "10025", R.drawable.headimage09);
		contacts.add(contact);

		contact = new Contact("Gerrard", "10026", R.drawable.headimage08);
		contacts.add(contact);

		contact = new Contact("Giroud", "10027", R.drawable.headimage18);
		contacts.add(contact);

		contact = new Contact("Henry", "10028", R.drawable.headimage09);
		contacts.add(contact);

		contact = new Contact("Zidane", "10029", R.drawable.headimage19);
		contacts.add(contact);

		contact = new Contact("Ozil", "10030", R.drawable.headimage12);
		contacts.add(contact);
		
		// 对数据源进行排序
		Collections.sort(contacts, new Comparator<Contact>() {
			@Override
			public int compare(Contact lhs, Contact rhs) {
				return lhs.getName().compareTo(rhs.getName());
			}
		});

		// ListView控件
		lvContacts = (ListView) findViewById(R.id.lv_contacts);

		// Adapter
		contactAdapter = new ContactAdapter(this, contacts);

		// 为ListView配置Adapter
		lvContacts.setAdapter(contactAdapter);

		// --- 以下是右侧字母导航相关 ---
		lvSections = (ListView) findViewById(R.id.lv_sections);
		sectionAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.section_item, sections);
		lvSections.setAdapter(sectionAdapter);
		InnerOnItemClickListener listener = new InnerOnItemClickListener();
		lvSections.setOnItemClickListener(listener);
	}

	private class InnerOnItemClickListener implements OnItemClickListener {
		
		@Override
		public void onItemClick(
				AdapterView<?> parent, 
				View view, 
				int position,
				long id) {
			// 第3个参数表示点击的是第几个列表项
			// 根据第3个参数，获取点击的字母
			String str = sections[position];
			char ch = str.charAt(0);
			// 获取该字母应该出现在联系人列表中的位置
			int pos = contactAdapter.getPositionForSection(ch);
			// 滑动联系人列表到指定的位置
			lvContacts.setSelection(pos);
		}
	}
}

