package cn.tedu.music_player_3.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.tedu.music_player_3.R;
import cn.tedu.music_player_3.entity.Music;

public class MusicAdapter extends BaseAdapter<Music> {

	public MusicAdapter(Context context, List<Music> data) {
		super(context, data);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 获取需要被显示的数据
		Music music = getData().get(position);
		
		// 加载模板，准备承载数据的控件
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = getLayoutInflater().inflate(R.layout.music_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.tv_music_item_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		// 向控件中填充数据
		holder.title.setText(music.getTitle());
		
		// 返回
		return convertView;
	}
	
	class ViewHolder {
		TextView title;
	}

}
