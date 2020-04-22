package cn.tedu.music_player_1;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter {
	private Context context;
	private List<Music> data;
	
	public MusicAdapter(Context context, List<Music> data) {
		setContext(context);
		setData(data);
	}

	private void setContext(Context context) {
		if(context == null) {
			throw new IllegalArgumentException("参数Context不能为null！！！");
		}
		this.context = context;
	}

	private void setData(List<Music> data) {
		if(data == null) {
			data = new ArrayList<Music>();
		}
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//1.获取数据
		Music music = data.get(position);
		//2.获取模板对象
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.music_item, null);
		//3.向模板对象中填充数据
		TextView title = (TextView) view.findViewById(R.id.tv_music_item_title);
		title.setText(music.getTitle());
		//4.返回
		return view;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}
