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
		
		// 上下文菜单
		View tvHelloWorld = findViewById(R.id.tv_helloworld);
		// 需要注册
		registerForContextMenu(tvHelloWorld);
		
		View ivImage = findViewById(R.id.iv_image);
		registerForContextMenu(ivImage);
		
		lvContacts = (ListView) findViewById(R.id.lv_contacts);
		contacts = new ArrayList<String>();
		contacts.add("梅西");
		contacts.add("内马尔");
		contacts.add("苏亚雷斯");
		contacts.add("伊涅斯塔");
		contacts.add("布斯克茨");
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
			menu.add(Menu.NONE, ACTION_COPY, Menu.NONE, "复制文本");
			break;

		case R.id.iv_image:
			menu.add(Menu.NONE, ACTION_SAVE_IMAGE, Menu.NONE, "保存图片");
			break;
			
		case R.id.lv_contacts:
			// 根据第三个参数，获取点击ListView列表项的position
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
			int position = info.position;
			// 根据position获取列表项中联系人的名字
			String name = contacts.get(position);
			
			menu.add(Menu.NONE, ACTION_EDIT_CONTACT, Menu.NONE, "编辑 " + name);
			menu.add(Menu.NONE, ACTION_DELETE_CONTACT, Menu.NONE, "删除 " + name);
			break;
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ACTION_COPY:
			Toast.makeText(this, "您选择了 复制文本 操作！", Toast.LENGTH_SHORT).show();
			break;

		case ACTION_SAVE_IMAGE:
			Toast.makeText(this, "您选择了 保存图片 操作！", Toast.LENGTH_SHORT).show();
			break;
			
		case ACTION_EDIT_CONTACT:
			// 获取点击ListView列表项的position
			ContextMenuInfo menuInfo = item.getMenuInfo();
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
			int position = info.position;
			// 根据position获取列表项中联系人的名字
			String name = contacts.get(position);
			Toast.makeText(this, "您选择了 编辑 (" + name + ") 操作！", Toast.LENGTH_SHORT).show();
			break;
			
		case ACTION_DELETE_CONTACT:
			Toast.makeText(this, "您选择了 删除联系人 操作！", Toast.LENGTH_SHORT).show();
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
			msg = "您选择了File菜单";
			break;

		case R.id.setting_edit:
			msg = "您选择了Edit菜单";
			break;
			
		case R.id.setting_refactor:
			msg = "您选择了Refactor菜单";
			break;
			
		case R.id.setting_source:
			msg = "您选择了Source菜单";
			break;
			
		case R.id.setting_nevigate:
			msg = "您选择了Nevigate菜单";
			break;
			
		}
		
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		return super.onOptionsItemSelected(item);
	}

}
