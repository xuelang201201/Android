package cn.tedu.youlu.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cn.tedu.youlu.adapter.CalllogAdapter;
import cn.tedu.youlu.entity.Calllog;
import cn.tedu.youlu.presenter.ICalllogPresenter;
import cn.tedu.youlu.presenter.impl.CalllogPresenter;
import cn.tedu.youlu.view.ICalllogView;

import com.tarena.youlu.R;

public class CalllogFragment extends Fragment implements ICalllogView {
	private ListView listView;
	private List<Calllog> logs;
	private ICalllogPresenter presenter;
	private CalllogAdapter adapter;
	
	public CalllogFragment() {
		this.presenter = new CalllogPresenter(this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//加载布局
		View view = inflater.inflate(R.layout.fragment_calllog, null);
		//控件初始化
		listView=(ListView) view.findViewById(R.id.lvCalllog);
		//调用presenter的方法 执行流程
		presenter.loadAllCalllogs();
		return view;
	}
	
	@Override
	public void setCalllogList(List<Calllog> logs) {
		this.logs = logs;
	}
	
	@Override
	public void showList() {
		//自定义Adapter  给listView设置Adapter
		adapter = new CalllogAdapter(getActivity(), listView, logs);
		listView.setAdapter(adapter);
	}

}
