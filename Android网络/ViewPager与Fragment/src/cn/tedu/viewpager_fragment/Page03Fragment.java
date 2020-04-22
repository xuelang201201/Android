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
		// ����View���󣬲����أ�����BaseAdapter�е�getView()
		View view = inflater.inflate(R.layout.fragment_page_03, null);
		
		// ��ʼ���ؼ�
		btnStart = (Button) view.findViewById(R.id.btn_start);
		
		// Ϊ�ؼ����ü�����
		btnStart.setOnClickListener(this);
		
		// ����
		return view;
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getActivity(), "������Page03Fragment������ʾ�����ݣ�", Toast.LENGTH_LONG).show();
	}
}
