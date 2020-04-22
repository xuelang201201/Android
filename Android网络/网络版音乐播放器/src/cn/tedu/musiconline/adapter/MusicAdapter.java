package cn.tedu.musiconline.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.tedu.musiconline.R;
import cn.tedu.musiconline.entity.Music;

public class MusicAdapter extends BaseAdapter {
	
	private Context context;
	private List<Music> musics;
	private LayoutInflater inflater;
	
	public MusicAdapter(Context context, List<Music> musics) {
		this.context = context;
		this.musics = musics;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return musics.size();
	}

	@Override
	public Music getItem(int position) {
		return musics.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.music_item, null);
			holder = new ViewHolder();
			holder.ivAlbum = (ImageView) convertView.findViewById(R.id.iv_album);
			holder.tvAuthor = (TextView) convertView.findViewById(R.id.tv_author);
			holder.tvDuration = (TextView) convertView.findViewById(R.id.tv_duration);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvSinger = (TextView) convertView.findViewById(R.id.tv_singer);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		// 给holder中的控件设置参数
		Music music = getItem(position);
		holder.tvName.setText(music.getName());
		holder.tvAuthor.setText(music.getAuthor());
		holder.tvSinger.setText(music.getSinger());
		holder.tvDuration.setText(music.getDurationtime());
		return convertView;
	}
	
	class ViewHolder {
		ImageView ivAlbum;
		TextView tvName;
		TextView tvAuthor;
		TextView tvSinger;
		TextView tvDuration;
	}
}
