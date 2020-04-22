package cn.tedu.youlu.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.tedu.youlu.adapter.CalllogAdapter;
import cn.tedu.youlu.entity.Calllog;
import cn.tedu.youlu.presenter.IDialPresenter;
import cn.tedu.youlu.presenter.impl.DialPresenter;
import cn.tedu.youlu.util.StringUtil;
import cn.tedu.youlu.view.IDialView;

import com.tarena.youlu.R;

public class DialFragment extends Fragment implements IDialView {
	private ListView listView;
	private List<Calllog> logs;
	private IDialPresenter presenter;
	private CalllogAdapter adapter;
	
	private RelativeLayout rlKeyboard;
	private GridView gvKeyboard;
	private String[] keys={"1","2","3","4","5","6","7","8","9","*","0","#"};
	private KeyBoardAdapter keyBoardAdapter;
	private TextView tvTitle;
	
	public DialFragment() {
		this.presenter = new DialPresenter(this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//���ز���
		View view = inflater.inflate(R.layout.fragment_dial, null);
		//�ؼ���ʼ��
		setViews(view);
		//��gridView����������
		setAdapter();
		//��Ӽ���
		setListener();
		//����presenter�ķ��� ִ������
		presenter.loadAllCalllogs();
		return view;
	}
	
	/**
	 * ��Ӽ���
	 */
	private void setListener() {
		//���GridView�е�item
		gvKeyboard.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String key = keys[position];
				//׷�ӵ�tvTitle��
				String text=tvTitle.getText().toString();
				text=StringUtil.atob(text)+key;
				String val=StringUtil.btoa(text);
				tvTitle.setText(val);
			}
		});
		
		
		//����ListViewʱ�ļ���
		listView.setOnScrollListener(new OnScrollListener() {
			//��������״̬�ĸı�
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Log.i("info", "scrollState:"+scrollState);
				if(scrollState==SCROLL_STATE_TOUCH_SCROLL){
					//�������̿ɼ�
					if(rlKeyboard.getVisibility() == View.VISIBLE){
						TranslateAnimation anim = new TranslateAnimation(0, 0, 0, rlKeyboard.getHeight());
						anim.setDuration(300);
						rlKeyboard.startAnimation(anim);
						rlKeyboard.setVisibility(View.INVISIBLE);
					}
				}
			}
			//һ��������ִ��
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				//Log.i("info", "scrollState:onScroll....");
				
			}
		});
		
		
		//����tvTitle ����绰����ʱ
		tvTitle.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(rlKeyboard.getVisibility() != View.VISIBLE){
					//���������ʾ   �����ķ�ʽ��ʾ
					rlKeyboard.setVisibility(View.VISIBLE);
					//����ƽ�ƶ���
					TranslateAnimation anim = new TranslateAnimation(0, 0, rlKeyboard.getHeight(), 0);
					anim.setDuration(500);
					rlKeyboard.startAnimation(anim);
				}
				return false;
			}
		});
	}

	//��gridView����������
	private void setAdapter() {
		keyBoardAdapter = new KeyBoardAdapter();
		gvKeyboard.setAdapter(keyBoardAdapter);
	}

	private void setViews(View view) {
		listView=(ListView) view.findViewById(R.id.lvCalllog);
		rlKeyboard = (RelativeLayout) view.findViewById(R.id.rlKeyboard);
		gvKeyboard = (GridView) view.findViewById(R.id.gvKeyboard);
		tvTitle = (TextView) view.findViewById(R.id.tvTitle);
	}

	@Override
	public void setCalllogList(List<Calllog> logs) {
		this.logs = logs;
	}
	
	@Override
	public void showList() {
		//�Զ���Adapter  ��listView����Adapter
		adapter = new CalllogAdapter(getActivity(), listView, logs);
		listView.setAdapter(adapter);
	}

	class KeyBoardAdapter extends BaseAdapter{
		public int getCount() {
			return keys.length;
		}
		public Object getItem(int position) {
			return keys[position];
		}
		public long getItemId(int position) {
			return position;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(getActivity(), R.layout.item_gv_keyboard, null);
			TextView tvKey=(TextView) convertView.findViewById(R.id.tvKey);
			tvKey.setText(keys[position]);
			return convertView;
		}
	}
	
}
