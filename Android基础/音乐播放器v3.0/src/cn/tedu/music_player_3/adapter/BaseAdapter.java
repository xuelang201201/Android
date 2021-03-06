package cn.tedu.music_player_3.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;

/**
 * 自定义Adapter基类，本例中所有Adapter都应该继承自本类
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
	private Context context;
	private LayoutInflater layoutInflater;
	private List<T> data;
	
	public BaseAdapter(Context context, List<T> data) {
		super();
		setContext(context);
		setData(data);
		setLayoutInflater();
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("参数Context不允许为null！！！");
		}
		this.context = context;
	}

	public LayoutInflater getLayoutInflater() {
		return layoutInflater;
	}

	public void setLayoutInflater() {
		if (context == null) {
			throw new RuntimeException("没有获取到有效的Context！！！");
		}
		layoutInflater = LayoutInflater.from(this.context);
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		if (data == null) {
			data = new ArrayList<T>();
		}
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}
	
	@Override
	public Object getItem(int position) {
		return null;
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}
}
