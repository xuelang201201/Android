package cn.tedu.youlu.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cn.tedu.youlu.activity.SmsActivity;
import cn.tedu.youlu.entity.Conversation;
import cn.tedu.youlu.presenter.IConversationPresenter;
import cn.tedu.youlu.presenter.impl.ConversationPresenter;
import cn.tedu.youlu.view.IConversationView;

import com.tarena.youlu.R;

public class ConversationFragment extends Fragment implements IConversationView {
	private List<Conversation> cs;
	private ListView listView;
	private IConversationPresenter presenter;
	private ArrayAdapter<Conversation> adapter;

	public ConversationFragment() {
		this.presenter = new ConversationPresenter(this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_conversation, null);
		//初始化
		listView = (ListView) view.findViewById(R.id.listView);
		//添加监听
		setListener();
		//调用presenter的方法执行流程
		presenter.loadConversation();
		return view;
	}
	
	private void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Conversation con=cs.get(position);
				//启动Activity  并且传递con对象
				Intent intent = new Intent(getActivity(), SmsActivity.class);
				intent.putExtra("conversation", con);
				startActivity(intent);
			}
		});
	}

	@Override
	public void setList(List<Conversation> cs) {
		this.cs = cs;
	}

	@Override
	public void showList() {
		//自定义Adapter
		//Log.i("info", "会话数据："+cs.toString());
		adapter = new ArrayAdapter<Conversation>(getActivity(), android.R.layout.simple_list_item_1, cs);
		listView.setAdapter(adapter);
	}

}
