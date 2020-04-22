package cn.tedu.listview_baseadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	/**
	 * �ؼ�����ʾ��ϵ���б��ListView
	 */
	private ListView lvContacts;
	
	/**
	 * ��ϵ���б��Adapter
	 */
	private BaseAdapter adapter;
	
	/**
	 * ����Դ����ϵ�˵����ݵ�List����
	 */
	private List<Contact> contacts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ��ListView
		lvContacts = (ListView) findViewById(R.id.lv_contacts);
		
		//��ʼ����ϵ�˵�����Դ
		contacts = new ArrayList<Contact>();
		Contact contact;
		
		contact = new Contact("÷��", "01012345610", R.drawable.headimage01);
		contacts.add(contact);
		
		contact = new Contact("�����", "01012345611", R.drawable.headimage02);
		contacts.add(contact);
		
		contact = new Contact("������˹", "01012345609", R.drawable.headimage06);
		contacts.add(contact);
		
		contact = new Contact("��ά", "01012345606", R.drawable.headimage07);
		contacts.add(contact);
		
		contact = new Contact("����˹��", "01012345608", R.drawable.headimage11);
		contacts.add(contact);
		
		contact = new Contact("���ɶ���", "01012345607", R.drawable.headimage12);
		contacts.add(contact);
		
		contact = new Contact("����", "01012345605", R.drawable.headimage15);
		contacts.add(contact);
		
		contact = new Contact("������", "01012345604", R.drawable.headimage16);
		contacts.add(contact);
		
		contact = new Contact("����", "01012345603", R.drawable.headimage18);
		contacts.add(contact);
		
		contact = new Contact("�����", "01012345602", R.drawable.headimage19);
		contacts.add(contact);
		
		contact = new Contact("������", "01012345601", R.drawable.headimage11);
		contacts.add(contact);
		
		contact = new Contact("��������", "01012345612", R.drawable.headimage12);
		contacts.add(contact);
		
		contact = new Contact("�ϰ���", "01012345613", R.drawable.headimage13);
		contacts.add(contact);
		
		contact = new Contact("�����", "01012345614", R.drawable.headimage14);
		contacts.add(contact);
		
		contact = new Contact("���", "01012345615", R.drawable.headimage15);
		contacts.add(contact);
		
		contact = new Contact("����", "01012345616", R.drawable.headimage16);
		contacts.add(contact);
		
		contact = new Contact("�°�÷��", "01012345617", R.drawable.headimage17);
		contacts.add(contact);
		
		contact = new Contact("����˹", "01012345618", R.drawable.headimage18);
		contacts.add(contact);
		
		contact = new Contact("ɣ��˹", "01012345619", R.drawable.headimage19);
		contacts.add(contact);
		
		contact = new Contact("����", "01012345620", R.drawable.headimage20);
		contacts.add(contact);
		
		contact = new Contact("�ض�˹�ظ�", "01012345621", R.drawable.headimage01);
		contacts.add(contact);
		
		contact = new Contact("�ǹ���", "01012345622", R.drawable.headimage02);
		contacts.add(contact);
		
		contact = new Contact("�²�����", "01012345623", R.drawable.headimage03);
		contacts.add(contact);
		
		//����ֱ���½�BaseAdapter�Ķ�����ΪBaseAdapter��abstract��
		adapter = new ContactAdapter(this, contacts);
		
		//ΪListView����Adapter
		lvContacts.setAdapter(adapter);
	}
}
