package cn.tedu.menu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ListView lvContacts;
	private ArrayAdapter<String> adapter;
	private List<String> contacts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// �����Ĳ˵�
		View tvHelloWorld = findViewById(R.id.tv_helloworld);
		// ��Ҫע��
		registerForContextMenu(tvHelloWorld);
		
		View ivImage = findViewById(R.id.iv_image);
		registerForContextMenu(ivImage);
		
		lvContacts = (ListView) findViewById(R.id.lv_contacts);
		contacts = new ArrayList<String>();
		contacts.add("÷��");
		contacts.add("�����");
		contacts.add("������˹");
		contacts.add("����˹��");
		contacts.add("��˹�˴�");
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
		lvContacts.setAdapter(adapter);
		registerForContextMenu(lvContacts);
	}
	
	private static final int ACTION_COPY = 201;
	private static final int ACTION_SAVE_IMAGE = 202;
	private static final int ACTION_EDIT_CONTACT = 2031;
	private static final int ACTION_DELETE_CONTACT = 2032;

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		switch (v.getId()) {
		case R.id.tv_helloworld:
			menu.add(Menu.NONE, ACTION_COPY, Menu.NONE, "�����ı�");
			break;

		case R.id.iv_image:
			menu.add(Menu.NONE, ACTION_SAVE_IMAGE, Menu.NONE, "����ͼƬ");
			break;
			
		case R.id.lv_contacts:
			// ���ݵ�������������ȡ���ListView�б����position
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
			int position = info.position;
			// ����position��ȡ�б�������ϵ�˵�����
			String name = contacts.get(position);
			
			menu.add(Menu.NONE, ACTION_EDIT_CONTACT, Menu.NONE, "�༭ " + name);
			menu.add(Menu.NONE, ACTION_DELETE_CONTACT, Menu.NONE, "ɾ�� " + name);
			break;
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ACTION_COPY:
			Toast.makeText(this, "��ѡ���� �����ı� ������", Toast.LENGTH_SHORT).show();
			break;

		case ACTION_SAVE_IMAGE:
			Toast.makeText(this, "��ѡ���� ����ͼƬ ������", Toast.LENGTH_SHORT).show();
			break;
			
		case ACTION_EDIT_CONTACT:
			// ��ȡ���ListView�б����position
			ContextMenuInfo menuInfo = item.getMenuInfo();
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
			int position = info.position;
			// ����position��ȡ�б�������ϵ�˵�����
			String name = contacts.get(position);
			Toast.makeText(this, "��ѡ���� �༭ (" + name + ") ������", Toast.LENGTH_SHORT).show();
			break;
			
		case ACTION_DELETE_CONTACT:
			Toast.makeText(this, "��ѡ���� ɾ����ϵ�� ������", Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	private static final int ACTION_PROJECT = 105;
	private static final int ACTION_RUN = 106;
	private static final int ACTION_RUN_RUN = 1061;
	private static final int ACTION_RUN_DEBUG = 1062;
	
	private static final int ACTION_WINDOW = 107;
	private static final int ACTION_HELP = 108;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		menu.add(Menu.NONE, ACTION_PROJECT, Menu.NONE, "Project");
		
		SubMenu subMenu = menu.addSubMenu(Menu.NONE, ACTION_RUN, Menu.NONE, "Run");
		subMenu.add(Menu.NONE, ACTION_RUN_RUN, Menu.NONE, "Run");
		subMenu.add(Menu.NONE, ACTION_RUN_DEBUG, Menu.NONE, "Debug");
		
		menu.add(Menu.NONE, ACTION_WINDOW, Menu.NONE, "Window");
		menu.add(Menu.NONE, ACTION_HELP, Menu.NONE, "Help");
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String msg = null;
		switch (item.getItemId()) {
		case ACTION_PROJECT:
			break;
			
		case ACTION_RUN:
			break;
			
		case ACTION_WINDOW:
			break;
			
		case ACTION_HELP:
			break;
			
		case R.id.setting_file:
			msg = "��ѡ����File�˵�";
			break;

		case R.id.setting_edit:
			msg = "��ѡ����Edit�˵�";
			break;
			
		case R.id.setting_refactor:
			msg = "��ѡ����Refactor�˵�";
			break;
			
		case R.id.setting_source:
			msg = "��ѡ����Source�˵�";
			break;
			
		case R.id.setting_nevigate:
			msg = "��ѡ����Nevigate�˵�";
			break;
			
		}
		
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		return super.onOptionsItemSelected(item);
	}

}
