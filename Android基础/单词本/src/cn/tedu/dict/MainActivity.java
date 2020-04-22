package cn.tedu.dict;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener {

	private TextView tvTitle;
	private EditText etEn;
	private EditText etZh;
	private Button btnSubmit;
	private Button btnBack;
	private ListView lvWords;
	private WordAdapter adapter;
	private List<Word> words;
	private boolean isEditMode;
	private long editId;
	private int deletePosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ʼ���ؼ�
		tvTitle = (TextView) findViewById(R.id.tv_title);
		etEn = (EditText) findViewById(R.id.et_en);
		etZh = (EditText) findViewById(R.id.et_zh);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		btnBack = (Button) findViewById(R.id.btn_back);
		lvWords = (ListView) findViewById(R.id.lv_words);

		// ��ʾListView
		WordDao dao = new WordDao(this);
		words = dao.query(null, null, "_id desc");
		adapter = new WordAdapter(this, words);
		lvWords.setAdapter(adapter);

		// Ϊ�ؼ����ü�����
		btnSubmit.setOnClickListener(this);
		btnBack.setOnClickListener(this);

		// ע�������Ĳ˵�
		registerForContextMenu(lvWords);
	}

	private static final int ACTION_EDIT = 100;
	private static final int ACTION_DELETE = 200;

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// 1.��ȡposition
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		int position = info.position;
		// 2.�����˵���
		menu.add(Menu.NONE, ACTION_EDIT, Menu.NONE, "�༭ " + words.get(position).getEn());
		menu.add(Menu.NONE, ACTION_DELETE, Menu.NONE, "ɾ�� " + words.get(position).getEn());
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// 1.��ȡposition
		ContextMenuInfo menuInfo = item.getMenuInfo();
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		int position = info.position;
		// 2.׼��WordDao����
		// WordDao dao = new WordDao(this);
		// 3.����ѡ��Ĳ˵�����в���
		switch (item.getItemId()) {
		case ACTION_EDIT:
			isEditMode = true;
			Word word = words.get(position);
			editId = word.getId();
			etEn.setText(word.getEn());
			etZh.setText(word.getZh());
			btnSubmit.setText("�޸�");
			btnBack.setVisibility(View.VISIBLE);
			tvTitle.setText("�༭ " + word.getEn() + " ����Ϣ");
			break;

		case ACTION_DELETE:
			deletePosition = position;
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder
				.setTitle("����")
				.setMessage("ɾ�����������ɻָ���ȷ��Ҫɾ����")
				.setPositiveButton("ȷ��", this)
				.setNegativeButton("ȡ��", null)
				.create()
				.show();
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		WordDao dao = new WordDao(this);
		int affectedRows = dao.delete(words.get(deletePosition).getId());
		if (affectedRows > 0) {
			words.clear();
			words.addAll(dao.query(null, null, "_id desc"));
			adapter.notifyDataSetChanged();
		} else {
			Toast.makeText(this, "ɾ��ʧ�ܣ�����ϵ����Ա��", Toast.LENGTH_SHORT).show();
		}
	} 
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			// ��ԭΪ����ӡ�ģʽ
			isEditMode = false;
			tvTitle.setText("�����뵥����Ϣ��");
			etEn.setText("");
			etZh.setText("");
			btnSubmit.setText("���");
			btnBack.setVisibility(View.GONE);
			break;

		case R.id.btn_submit:
			// 1.��ȡEditText�е�����
			String en = etEn.getText().toString().trim();
			String zh = etZh.getText().toString().trim();

			// 2.��֤���ݵ���Ч��
			// (����)

			// 3.�����ݷ�װΪWord����
			Word word = new Word();
			word.setEn(en);
			word.setZh(zh);

			// 4.��ȡWordDao����
			WordDao dao = new WordDao(this);

			// 5.�жϲ���ģʽ
			if (isEditMode) {
				// ��Word�����з�װ���ݵ�ID
				word.setId(editId);
				// �޸����ݣ�����ȡ����
				int affectedRows = dao.update(word);
				// ȡ���༭ģʽ
				isEditMode = false;
				tvTitle.setText("�����뵥����Ϣ��");
				etEn.setText("");
				etZh.setText("");
				btnSubmit.setText("���");
				btnBack.setVisibility(View.GONE);
				// ��ʾ�������
				if(affectedRows > 0) {
					words.clear();
					words.addAll(dao.query(null, null, "_id desc"));
					adapter.notifyDataSetChanged();
				} else {
					Toast.makeText(this, "ɾ��ʧ�ܣ�����ϵ����Ա��", Toast.LENGTH_SHORT).show();
				}
			} else {
				// ������ݣ�����ȡ���
				long id = dao.insert(word);
				// ��ʾ�������
				if (id == -1) {
					Toast.makeText(this, "�����Ѵ��ڣ������ظ���ӣ�", Toast.LENGTH_SHORT).show();
					
				} else {
					// Toast.makeText(this, "������ݳɹ���", Toast.LENGTH_SHORT).show();
					etEn.setText("");
					etZh.setText("");

					// ����ListView
					words.clear();
					words.addAll(dao.query(null, null, "_id desc"));
					adapter.notifyDataSetChanged();
				}
			} 
			break;
		}
	}
}
