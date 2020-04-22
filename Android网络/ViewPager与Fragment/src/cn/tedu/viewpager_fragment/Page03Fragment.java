package cn.tedu.viewpager_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Page03Fragment extends Fragment implements View.OnClickListener {

	private Button btnStart;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 创建View对象，并返回，类似BaseAdapter中的getView()
		View view = inflater.inflate(R.layout.fragment_page_03, null);
		
		// 初始化控件
		btnStart = (Button) view.findViewById(R.id.btn_start);
		
		// 为控件配置监听器
		btnStart.setOnClickListener(this);
		
		// 返回
		return view;
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getActivity(), "这是由Page03Fragment控制显示的内容！", Toast.LENGTH_LONG).show();
	}
}
