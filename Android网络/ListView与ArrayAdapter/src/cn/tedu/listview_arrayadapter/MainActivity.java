package cn.tedu.listview_arrayadapter;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView lvContacts;//能private就private，除非有充足的理由
	private ArrayAdapter<String> adapter; //默认值null

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lvContacts = (ListView) findViewById(R.id.lv_contacts);
		Context context = this;
		int textViewResourceId = R.layout.contact_item; //列表模板
		
		List<String> contacts = new ArrayList<String>(); //数据源
		contacts.add("梅西");
		contacts.add("内马尔");
		contacts.add("苏亚雷斯");
		contacts.add("哈维");
		contacts.add("伊涅斯塔");
		contacts.add("罗纳尔多");
		contacts.add("贝尔");
		contacts.add("本泽马");
		contacts.add("伊布");
		contacts.add("厄齐尔");
		contacts.add("阿奎罗");
		contacts.add("迪玛利亚");
		contacts.add("迪巴拉");
		contacts.add("博格巴");
		contacts.add("格策");
		contacts.add("穆勒");
		contacts.add("奥巴梅杨");
		contacts.add("罗伊斯");
		contacts.add("桑切斯");
		contacts.add("布冯");
		contacts.add("特尔斯特根");
		contacts.add("乔哈特");
		contacts.add("德布劳内");
		
		adapter = new ArrayAdapter<String>(context, textViewResourceId, contacts);
		lvContacts.setAdapter(adapter); //显示列表
	}
}
