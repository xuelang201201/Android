package cn.tedu.music_player_2.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.tedu.music_player_2.R;
import cn.tedu.music_player_2.entity.Music;

public class MusicAdapter extends MyBaseAdapter {
	private Context context;
	private List<Music> data;
	private LayoutInflater inflater ;
	
	public MusicAdapter(Context context, List<Music> data) {
		setContext(context);
		setData(data);
		inflater = LayoutInflater.from(this.context);
	}
	
	public void setContext(Context context) {
		if(context == null) {
			throw new IllegalArgumentException("参数Context不允许为null！！！");
		}
		this.context = context;
	}
	
	public void setData(List<Music> data) {
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
		ViewHolder holder;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.music_item, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.tv_music_item_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Music music = data.get(position);
		
		holder.title.setText(music.getTitle());
		
		// 判断TextView的显示状态
		if(music.isPlaying()) {
			holder.title.setBackgroundColor(Color.parseColor("#ff6625"));
			holder.title.setTextColor(Color.WHITE);
		} else {
			holder.title.setBackground(null);
			holder.title.setTextColor(Color.BLACK);
		}
		
		return convertView;
	}
	
	private class ViewHolder {
		TextView title;
	}
	
}