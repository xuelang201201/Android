package cn.tedu.viewpager_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Page02Fragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 创建View对象，并返回，类似BaseAdapter中的getView()
		View view = inflater.inflate(R.layout.fragment_page_02, null);
		return view;
	}
}
