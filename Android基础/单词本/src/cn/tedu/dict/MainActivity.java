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

		// 初始化控件
		tvTitle = (TextView) findViewById(R.id.tv_title);
		etEn = (EditText) findViewById(R.id.et_en);
		etZh = (EditText) findViewById(R.id.et_zh);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		btnBack = (Button) findViewById(R.id.btn_back);
		lvWords = (ListView) findViewById(R.id.lv_words);

		// 显示ListView
		WordDao dao = new WordDao(this);
		words = dao.query(null, null, "_id desc");
		adapter = new WordAdapter(this, words);
		lvWords.setAdapter(adapter);

		// 为控件配置监听器
		btnSubmit.setOnClickListener(this);
		btnBack.setOnClickListener(this);

		// 注册上下文菜单
		registerForContextMenu(lvWords);
	}

	private static final int ACTION_EDIT = 100;
	private static final int ACTION_DELETE = 200;

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// 1.获取position
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		int position = info.position;
		// 2.创建菜单项
		menu.add(Menu.NONE, ACTION_EDIT, Menu.NONE, "编辑 " + words.get(position).getEn());
		menu.add(Menu.NONE, ACTION_DELETE, Menu.NONE, "删除 " + words.get(position).getEn());
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// 1.获取position
		ContextMenuInfo menuInfo = item.getMenuInfo();
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		int position = info.position;
		// 2.准备WordDao对象
		// WordDao dao = new WordDao(this);
		// 3.根据选择的菜单项进行操作
		switch (item.getItemId()) {
		case ACTION_EDIT:
			isEditMode = true;
			Word word = words.get(position);
			editId = word.getId();
			etEn.setText(word.getEn());
			etZh.setText(word.getZh());
			btnSubmit.setText("修改");
			btnBack.setVisibility(View.VISIBLE);
			tvTitle.setText("编辑 " + word.getEn() + " 的信息");
			break;

		case ACTION_DELETE:
			deletePosition = position;
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder
				.setTitle("警告")
				.setMessage("删除操作将不可恢复！确认要删除吗？")
				.setPositiveButton("确认", this)
				.setNegativeButton("取消", null)
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
			Toast.makeText(this, "删除失败，请联系管理员！", Toast.LENGTH_SHORT).show();
		}
	} 
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			// 还原为“添加”模式
			isEditMode = false;
			tvTitle.setText("请输入单词信息：");
			etEn.setText("");
			etZh.setText("");
			btnSubmit.setText("添加");
			btnBack.setVisibility(View.GONE);
			break;

		case R.id.btn_submit:
			// 1.获取EditText中的数据
			String en = etEn.getText().toString().trim();
			String zh = etZh.getText().toString().trim();

			// 2.验证数据的有效性
			// (忽略)

			// 3.将数据封装为Word对象
			Word word = new Word();
			word.setEn(en);
			word.setZh(zh);

			// 4.获取WordDao对象
			WordDao dao = new WordDao(this);

			// 5.判断操作模式
			if (isEditMode) {
				// 向Word对象中封装数据的ID
				word.setId(editId);
				// 修改数据，并获取接收
				int affectedRows = dao.update(word);
				// 取消编辑模式
				isEditMode = false;
				tvTitle.setText("请输入单词信息：");
				etEn.setText("");
				etZh.setText("");
				btnSubmit.setText("添加");
				btnBack.setVisibility(View.GONE);
				// 提示操作结果
				if(affectedRows > 0) {
					words.clear();
					words.addAll(dao.query(null, null, "_id desc"));
					adapter.notifyDataSetChanged();
				} else {
					Toast.makeText(this, "删除失败，请联系管理员！", Toast.LENGTH_SHORT).show();
				}
			} else {
				// 添加数据，并获取结果
				long id = dao.insert(word);
				// 提示操作结果
				if (id == -1) {
					Toast.makeText(this, "单词已存在，无需重复添加！", Toast.LENGTH_SHORT).show();
					
				} else {
					// Toast.makeText(this, "添加数据成功！", Toast.LENGTH_SHORT).show();
					etEn.setText("");
					etZh.setText("");

					// 更新ListView
					words.clear();
					words.addAll(dao.query(null, null, "_id desc"));
					adapter.notifyDataSetChanged();
				}
			} 
			break;
		}
	}
}
