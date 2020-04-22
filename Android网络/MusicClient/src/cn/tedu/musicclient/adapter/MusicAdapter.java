package cn.tedu.musicclient.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.tedu.musicclient.R;
import cn.tedu.musicclient.entity.Music;
import cn.tedu.musicclient.util.ImageLoader;

public class MusicAdapter extends BaseAdapter{
	private Context context;
	private List<Music> musics;
	private LayoutInflater inflater;
	//声明ImageLoader  用于异步批量加载图片
	private ImageLoader imageLoader;
	
	public MusicAdapter(Context context, List<Music> musics, ListView listView) {
		this.context = context;
		this.musics = musics;
		this.imageLoader = new ImageLoader(context, listView);
		this.inflater = LayoutInflater.from(context);
	}

	
	public int getCount() {
		return musics.size();
	}

	public Music getItem(int position) {
		return musics.get(position);
	}
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_lv_music, null);
			holder = new ViewHolder();
			holder.ivAlbum = (ImageView) convertView.findViewById(R.id.ivAlbum);
			holder.tvName = (TextView)convertView.findViewById(R.id.tvName);
			holder.tvAuthor = (TextView)convertView.findViewById(R.id.tvAuthor);
			holder.tvSinger = (TextView)convertView.findViewById(R.id.tvSinger);
			holder.tvDuration = (TextView)convertView.findViewById(R.id.tvDuration);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		//给holder中的控件  设置参数
		Music music=getItem(position);
		holder.tvName.setText(music.getName());
		holder.tvAuthor.setText(music.getAuthor());
		holder.tvSinger.setText(music.getSinger());
		holder.tvDuration.setText(music.getDurationtime());
		//在imageView中显示图片
		imageLoader.displayImage(holder.ivAlbum, music.getAlbumpic(), position);
		return convertView;
	}
	
	class ViewHolder{
		ImageView ivAlbum;
		TextView tvName;
		TextView tvAuthor;
		TextView tvSinger;
		TextView tvDuration;
	}
	

	/**
	 * 停止工作线程
	 */
	public void stopThread() {
		imageLoader.stopThread();
	}
	
}
